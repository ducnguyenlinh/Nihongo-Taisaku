package com.example.admin.nihongotaisaku.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.FeedbackAdapter;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.fragments.AlphabetLearnFragment;
import com.example.admin.nihongotaisaku.fragments.AlphabetWriteFragment;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.FeedbackModel;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import java.io.IOException;
import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlphabetLearn_WriteActivity extends AppCompatActivity {
    AlphabetLearnFragment alphabetLearnFragment;
    AlphabetWriteFragment alphabetWriteFragment;
    Bundle bundle;
    MediaPlayer mediaPlayer;

    FabSpeedDial fabSpeedDial;

    String soundAlphabet, image_writingAlphabet, image_compareAlphabet;
    int alphabetID = 0, classifyAlphabet = 0;

    private static final String TAG = "OCVSample::Activity";

    public AlphabetLearn_WriteActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    static {
        OpenCVLoader.initDebug();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_learn_write);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_control);

        bundle = new Bundle();
        bundle = getIntent().getBundleExtra("alphabet_data");
        image_writingAlphabet = bundle.getString("image_writingAlphabet");
        image_compareAlphabet = bundle.getString("image_compareAlphabet");
        soundAlphabet = bundle.getString("soundAlphabet");
        classifyAlphabet = bundle.getInt("classifyAlphabet", 0);
        alphabetID = bundle.getInt("alphabet_id_data", 0);

        getSupportActionBar().setTitle(bundle.getString("alphabet_japanese_data"));

        if (savedInstanceState == null){
            View v = LayoutInflater.from(AlphabetLearn_WriteActivity.this).inflate(R.layout.dialog_learn_and_write, null);
            Button btnLearn = (Button) v.findViewById(R.id.btnLearn);
            Button btnWrite = (Button) v.findViewById(R.id.btnWrite);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlphabetLearn_WriteActivity.this);
            AlertDialog OptionDialog = alertDialog.create();
            OptionDialog.setMessage("Hãy chọn chức năng bạn muốn học");
            OptionDialog.setView(v);
            OptionDialog.setTitle("Message");
            OptionDialog.show();

            if (classifyAlphabet == 2 || classifyAlphabet == 3){
                btnLearn.setEnabled(false);
            }
            btnLearn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alphabetLearnFragment = new AlphabetLearnFragment();
                    alphabetLearnFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.alphabet_image_container, alphabetLearnFragment)
                            .commit();
                    OptionDialog.dismiss();
                }
            });
            btnWrite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alphabetWriteFragment = new AlphabetWriteFragment();
                    alphabetWriteFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.alphabet_image_container, alphabetWriteFragment)
                            .commit();
                    OptionDialog.dismiss();
                }
            });
        }

        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_look_feedbacks:
                        getFeedbackAlphabetLocal(alphabetID);
                        break;
                    case R.id.action_feedback:
                        dialogFeedbackAlphabet();
                        break;
                    case R.id.action_learn_fab:
                        if (classifyAlphabet == 2 || classifyAlphabet == 3){
                            Toast.makeText(AlphabetLearn_WriteActivity.this,
                                    "Chữ cái này không có phần học", Toast.LENGTH_LONG).show();
                            break;
                        }
                        alphabetLearnFragment = new AlphabetLearnFragment();
                        alphabetLearnFragment.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.alphabet_image_container, alphabetLearnFragment)
                                .commit();
                        break;
                    case R.id.action_write_fab:
                        alphabetWriteFragment = new AlphabetWriteFragment();
                        alphabetWriteFragment.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.alphabet_image_container, alphabetWriteFragment)
                                .commit();
                        break;
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alphabet_image_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                return true;
            case R.id.action_speaker:
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(soundAlphabet);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
        }
        return false;
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(AlphabetLearn_WriteActivity.this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    private void dialogFeedbackAlphabet(){
        View v = LayoutInflater.from(AlphabetLearn_WriteActivity.this).inflate(R.layout.dialog_create_feedback, null);
        EditText edtFeedback = (EditText) v.findViewById(R.id.edtFeedback);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlphabetLearn_WriteActivity.this);
        alertDialog.setView(v);
        alertDialog.setTitle("Phản hồi");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createFeedbackAlphabetLocal(alphabetID, edtFeedback.getText().toString());
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
    //Create Feedback
    private void createFeedbackAlphabetLocal(int mAlphabetID, String content){
        final String object_class = "alphabet";
        Call<FeedbackModel> call_feedback_vocabulary = (new APIRetrofit()).getAPIService().createFeedbackService(
                SharedPrefManager.getInstance(AlphabetLearn_WriteActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(AlphabetLearn_WriteActivity.this).getUser().getAuthentication_token(),
                object_class, mAlphabetID, content
        );
        call_feedback_vocabulary.enqueue(new Callback<FeedbackModel>() {
            @Override
            public void onResponse(Call<FeedbackModel> call, Response<FeedbackModel> response) {

            }

            @Override
            public void onFailure(Call<FeedbackModel> call, Throwable t) {

            }
        });
    }

    //Get Feedback
    private void getFeedbackAlphabetLocal(int mAlphabetID){
        final String object_class = "alphabet";
        Call<ArrayList<FeedbackModel>> call_feedbacks = (new APIRetrofit()).getAPIService().getFeedbacksService(
                SharedPrefManager.getInstance(AlphabetLearn_WriteActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(AlphabetLearn_WriteActivity.this).getUser().getAuthentication_token(),
                object_class, mAlphabetID
        );
        call_feedbacks.enqueue(new Callback<ArrayList<FeedbackModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FeedbackModel>> call, Response<ArrayList<FeedbackModel>> response) {
                View v = LayoutInflater.from(AlphabetLearn_WriteActivity.this).inflate(R.layout.dialog_feedbacks, null);
                RecyclerView rclFeedbacks = (RecyclerView) v.findViewById(R.id.rclFeedbacks);
                rclFeedbacks.setHasFixedSize(true);
                rclFeedbacks.setLayoutManager(new LinearLayoutManager(AlphabetLearn_WriteActivity.this));

                FeedbackAdapter feedbackAdapter = new FeedbackAdapter(AlphabetLearn_WriteActivity.this, response.body());
                rclFeedbacks.setAdapter(feedbackAdapter);
                feedbackAdapter.notifyDataSetChanged();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlphabetLearn_WriteActivity.this);
                alertDialog.setView(v);
                alertDialog.setTitle("Xem phản hồi");
                alertDialog.setPositiveButton("OK", null);
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<ArrayList<FeedbackModel>> call, Throwable t) {

            }
        });
    }
}
