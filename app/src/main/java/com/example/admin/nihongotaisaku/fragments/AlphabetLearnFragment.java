package com.example.admin.nihongotaisaku.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.AlphabetImageModel;
import com.markjmind.propose.Propose;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlphabetLearnFragment extends Fragment {
    ImageView imgAssociation, img_picture_1, img_picture_2, img_picture_3;
    TextView tvDescription;
    ViewGroup move_lyt, book_lyt;

    int alphabetID = 0;
    String str_association = "", str_description = "", str_picture_1 = "",
            str_picture_2 = "", str_picture_3 = "";

    public AlphabetLearnFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alphabet_image, container, false);
        imgAssociation = (ImageView) v.findViewById(R.id.imgAssociation);
        tvDescription = (TextView) v.findViewById(R.id.tvDescription);
        img_picture_1 = (ImageView) v.findViewById(R.id.img_picture_1);
        img_picture_2 = (ImageView) v.findViewById(R.id.img_picture_2);
        img_picture_3 = (ImageView) v.findViewById(R.id.img_picture_3);

        move_lyt = (ViewGroup) v.findViewById(R.id.move_lyt);
        book_lyt = (ViewGroup) v.findViewById(R.id.book_lyt);
        move_lyt.setCameraDistance(Propose.getCameraDistanceY(getContext())*5);
        book_lyt.setCameraDistance(Propose.getCameraDistanceY(getContext())*5);


        alphabetID = getArguments().getInt("alphabet_id_data");
        getAlphabetImage(alphabetID);

        /** create animator **/
        ObjectAnimator paperAnim = ObjectAnimator.ofFloat(move_lyt, View.ROTATION_Y, 0,180);
        ObjectAnimator paperUpDown = ObjectAnimator.ofFloat(move_lyt, View.ROTATION_X, -50,50);
        ObjectAnimator bookUpDown = ObjectAnimator.ofFloat(book_lyt, View.ROTATION_X, -50,50);

        /** create propose **/
        Propose propose = new Propose(getContext());
        propose.motionRight.play(paperAnim); 					 /** set right move animator **/
        propose.motionUp.play(paperUpDown,500).with(bookUpDown); /** set UpDown move animator **/
        propose.motionUp.enableFling(false).enableTabUp(false).enableSingleTabUp(false).move(250); // UpDown option
        move_lyt.setOnTouchListener(propose);  					 /** set touch listener **/
        return v;
    }

    private void getAlphabetImage(int alphabetID){
        Call<AlphabetImageModel> call_alphabet_images = (new APIRetrofit()).getAPIService().
                getAlphabetImagesService(
                        alphabetID,
                        SharedPrefManager.getInstance(getContext()).getUser().getEmail(),
                        SharedPrefManager.getInstance(getContext()).getUser().getAuthentication_token()

                );
        call_alphabet_images.enqueue(new Callback<AlphabetImageModel>() {
            @Override
            public void onResponse(Call<AlphabetImageModel> call, Response<AlphabetImageModel> response) {
                str_association = response.body().getImage_association();
                str_description = response.body().getDescription();
                str_picture_1 = response.body().getPicture_1();
                str_picture_2 = response.body().getPicture_2();
                str_picture_3 = response.body().getPicture_3();

                tvDescription.setText(str_description);

                Glide.with(getContext())
                        .load(str_association)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgAssociation);

                Glide.with(getContext())
                        .load(str_picture_1)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(img_picture_1);

                Glide.with(getContext())
                        .load(str_picture_2)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(img_picture_2);

                Glide.with(getContext())
                        .load(str_picture_3)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(img_picture_3);
            }

            @Override
            public void onFailure(Call<AlphabetImageModel> call, Throwable t) {

            }
        });
    }

}
