package com.example.admin.nihongotaisaku.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.helper.CanvasView;

import org.opencv.android.Utils;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgproc.Imgproc;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class AlphabetWriteFragment extends Fragment{
    ImageView img_writing;
    private CanvasView signature_canvas;
    BottomNavigationView bottomBar;
    Mat mat1, mat2;

    int alphabetID = 0;
    String result_compare = "";

    ProgressDialog progress;

    public AlphabetWriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_alphabet_write, container, false);
        img_writing = (ImageView) v.findViewById(R.id.img_writting);
        signature_canvas = (CanvasView) v.findViewById(R.id.canvas_view);
        bottomBar = (BottomNavigationView) v.findViewById(R.id.bottomBar);

        alphabetID = getArguments().getInt("alphabet_id_data");

        Glide.with(getContext())
                .load(getArguments().getString("image_writingAlphabet"))
                .asGif()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_writing);

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_clear:
                        signature_canvas.clearCanvas();
                        return true;
                    case R.id.action_compare:
                        if (getArguments().getInt("classifyAlphabet") == 2 ||
                                getArguments().getInt("classifyAlphabet") == 3){
                            if (getArguments().getInt("positonAlphabet") > 24) {
                                Toast.makeText(getContext(),
                                        "Chữ cái này không có phần so sánh", Toast.LENGTH_LONG).show();
                                return true;
                            }
                        }
                        new LoadImage().execute(getArguments().getString("image_compareAlphabet"));
                        return true;
                    case R.id.action_undo:
                        signature_canvas.onClickUndo();
                        signature_canvas.invalidate();
                        return true;
                    case R.id.action_redo:
                        signature_canvas.onClickRedo();
                        signature_canvas.invalidate();
                        return true;
                }
                return false;
            }
        });
        return v;
    }

    @SuppressLint("ResourceAsColor")
    public void compareMats(Bitmap bmMyView,Bitmap bitmap){
        Bitmap bitmap1 = bmMyView;
        bitmap1 = bitmap1.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap bitmap2 = bitmap;
        bitmap2 = bitmap2.copy(Bitmap.Config.ARGB_8888, true);

        bitmap1 = Bitmap.createScaledBitmap(bitmap1, 300, 300, true);
        bitmap2 = Bitmap.createScaledBitmap(bitmap2, 300, 300, true);

        mat1 = new Mat();
        mat2 = new Mat();

        Utils.bitmapToMat(bitmap1, mat1, false);
        Utils.bitmapToMat(bitmap2, mat2, false);


        Imgproc.cvtColor(mat1, mat1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(mat2, mat2, Imgproc.COLOR_BGR2GRAY);

        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();

        //Definition of ORB keypoint detector and descriptor extractors
        FeatureDetector detector = FeatureDetector.create(FeatureDetector.PYRAMID_FAST);
        DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.ORB);

        //Detect keypoints
        detector.detect(mat1, keypoints1);
        detector.detect(mat2, keypoints2);

        Log.d("LOG!", "number of query Keypoints1 = " + keypoints1.size());
        Log.d("LOG!", "number of query Keypoints2 = " + keypoints2.size());

        if ( ((int)keypoints1.size().height)*100/((int)keypoints2.size().height) < 10){
            result_compare = ((float)keypoints1.size().height)*100/((float)keypoints2.size().height) + "%";
            openCaptureDialog(bitmap2, bitmap1, result_compare);
        }
        else {
            //Extract descriptors
            extractor.compute(mat1, keypoints1, descriptors1);
            extractor.compute(mat2, keypoints2, descriptors2);

            //Definition of descriptor matcher
            DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING);

            //Match points of two images
            MatOfDMatch matches = new MatOfDMatch();
            matcher.match(descriptors1, descriptors2, matches);

            Log.d("LOG!", "number of query Keypoints1 = " + keypoints1.size());
            Log.d("LOG!", "number of query Keypoints2 = " + keypoints2.size());

            Log.d("LOG!", "number of descriptors= " + descriptors1.size());
            Log.d("LOG!",
                    "number of dupDescriptors= " + descriptors2.size());

            Log.d("LOG!", "Matches Size " + matches.size());

            // Filter matches by distance
            MatOfDMatch filtered = filterMatchesByDistance(matches);

            int total = (int) matches.size().height;
            int Match = (int) filtered.size().height;
            Log.d("LOG", "total:" + total + " Match:" + Match);
/*
            float key1 = (float) keypoints1.size().height;
            float key2 = (float) keypoints2.size().height;

            float result = (key2-key1)*Match*100/(key2*total);*/

            result_compare = String.valueOf((float) (Match * 100) / total + "%");
            openCaptureDialog(bitmap2, bitmap1, result_compare);
        }
    }

    static MatOfDMatch filterMatchesByDistance(MatOfDMatch matches){
        List<DMatch> matches_original = matches.toList();
        List<DMatch> matches_filtered = new ArrayList<DMatch>();

        int DIST_LIMIT = 60;
        // Check all the matches distance and if it passes add to list of filtered matches
        Log.d("DISTFILTER", "ORG SIZE:" + matches_original.size() + "");
        for (int i = 0; i < matches_original.size(); i++) {
            DMatch d = matches_original.get(i);
            if (Math.abs(d.distance) <= DIST_LIMIT) {
                matches_filtered.add(d);
            }
        }
        Log.d("DISTFILTER", "FIL SIZE:" + matches_filtered.size() + "");

        MatOfDMatch mat = new MatOfDMatch();
        mat.fromList(matches_filtered);
        return mat;
    }

    private void openCaptureDialog(Bitmap mBitmapCompare, Bitmap mBitmapCanvas, String mCompare){
        View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_compare, null);
        ImageView img_compare = (ImageView) v.findViewById(R.id.img_compare);
        ImageView img_canvas = (ImageView) v.findViewById(R.id.img_canvas);
        TextView tv_compare = (TextView) v.findViewById(R.id.tv_compare);

        img_compare.setImageBitmap(mBitmapCompare);
        img_canvas.setImageBitmap(mBitmapCanvas);
        tv_compare.setText(mCompare);

        AlertDialog.Builder captureDialog = new AlertDialog.Builder(getContext());
        captureDialog.setTitle("COMPARE");
        captureDialog.setView(v);
        captureDialog.setPositiveButton("OK", null);
        captureDialog.show();

    }

    public class LoadImage extends AsyncTask<String,Void,Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap=null;
            try {
                bitmap= BitmapFactory.decodeStream((InputStream)new URL(params[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPreExecute() {
            //show progress dialog while image is loading
            progress=new ProgressDialog(getContext());
            progress.setMessage("Loading Image....");
            progress.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap!=null) {
                Bitmap bmMyView = signature_canvas.getBitMaps();
                if (bmMyView == null){
                    Toast.makeText(getContext(),"You must paint before compare!",Toast.LENGTH_LONG).show();
                    progress.dismiss();
                    return;
                }
                compareMats(bmMyView, bitmap);
                progress.dismiss();
            } else {
                progress.dismiss();
                Toast.makeText(getContext(),"Some error occurred!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
