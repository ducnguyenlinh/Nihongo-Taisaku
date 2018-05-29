package com.example.admin.nihongotaisaku.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.FeedbackAdapter;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.FeedbackModel;
import com.example.admin.nihongotaisaku.models.SentenceModel;
import com.example.admin.nihongotaisaku.models.VocabularyModel;
import com.markjmind.propose.Propose;
import com.markjmind.propose.listener.JwAnimatorListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VocabularyActivity extends AppCompatActivity {

    float width;
    View touch_lyt, lyt1,lyt2,lyt3,lyt4;
    TextView tv1_1, tv1_2, tv2_1, tv2_2, tv2_3, tv2_4, tv4_1;
    ImageView img3_1;
    Propose propose;

    private String str_japanese = "", str_spell = "", str_mean = "", str_picture = "";
    private int vocabularyID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

        tv1_1 = (TextView) findViewById(R.id.tv1_1);
        tv1_2 = (TextView) findViewById(R.id.tv1_2);
        tv2_1 = (TextView) findViewById(R.id.tv2_1);
        tv2_2 = (TextView) findViewById(R.id.tv2_2);
        tv2_3 = (TextView) findViewById(R.id.tv2_3);
        tv2_4 = (TextView) findViewById(R.id.tv2_4);
        tv4_1 = (TextView) findViewById(R.id.tv4_1);
        img3_1 = (ImageView) findViewById(R.id.img3_1);

        vocabularyID =  getIntent().getIntExtra("vocabulary_id", 0);
        getVocabularyLocal(vocabularyID);

        width = 300*Propose.getDensity(this);
        float ds = (width*150);
        touch_lyt = findViewById(R.id.touch_lyt);
        lyt1 = findViewById(R.id.lyt1);
        lyt1.setCameraDistance(ds);
        lyt2 = findViewById(R.id.lyt2);
        lyt2.setCameraDistance(ds);
        lyt3 = findViewById(R.id.lyt3);
        lyt3.setCameraDistance(ds);
        lyt4 = findViewById(R.id.lyt4);
        lyt4.setCameraDistance(ds);

        ObjectAnimator rot1 = ObjectAnimator.ofFloat(lyt1, View.ROTATION_Y, 0,90);
        rot1.setDuration(1000);
        rot1.setInterpolator(null);

        rot1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = width*((float)animation.getCurrentPlayTime())/1000f;
				lyt1.setPivotX(width/2-x/2);
                //lyt1.setPivotX(0);

            }
        });
        ObjectAnimator tran1 = ObjectAnimator.ofFloat(lyt1, View.TRANSLATION_X, 0,width);
        tran1.setDuration(1000);
        tran1.setInterpolator(null);

        ObjectAnimator rot2 = ObjectAnimator.ofFloat(lyt2, View.ROTATION_Y, 270, 360);
        rot2.setDuration(1000);
        rot2.setInterpolator(null);
        rot2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = width*((float)animation.getCurrentPlayTime())/1000f;
				lyt2.setPivotX(width-x/2);
                //lyt2.setPivotX(width);
            }
        });
        ObjectAnimator tran2 = ObjectAnimator.ofFloat(lyt2, View.TRANSLATION_X, -width,0);
        tran2.setDuration(1000);
        tran2.setInterpolator(null);


        ObjectAnimator rot3 = ObjectAnimator.ofFloat(lyt3, View.ROTATION_X, 270, 360);
        rot3.setDuration(1000);
        rot3.setInterpolator(null);
        lyt3.setPivotX(width/2);
        ObjectAnimator tran3 = ObjectAnimator.ofFloat(lyt3, View.TRANSLATION_Y, width,0);
        tran3.setDuration(1000);
        tran3.setInterpolator(null);

        ObjectAnimator rot4 = ObjectAnimator.ofFloat(lyt4, View.ROTATION_X, 90, 0);
        rot4.setDuration(1000);
        rot4.setInterpolator(null);
        lyt4.setPivotX(width/2);
        ObjectAnimator tran4 = ObjectAnimator.ofFloat(lyt4, View.TRANSLATION_Y, -width,0);
        tran4.setDuration(1000);
        tran4.setInterpolator(null);



        ObjectAnimator up1 = ObjectAnimator.ofFloat(lyt1, View.ROTATION_X, 0, 90);
        up1.setDuration(1000);
        up1.setInterpolator(null);
        lyt1.setPivotX(width/2);
        ObjectAnimator upTran1 = ObjectAnimator.ofFloat(lyt1, View.TRANSLATION_Y, 0,-width);
        upTran1.setDuration(1000);
        upTran1.setInterpolator(null);

        ObjectAnimator up2 = ObjectAnimator.ofFloat(lyt2, View.ROTATION_X, 0, 90);
        up2.setDuration(1000);
        up2.setInterpolator(null);
        lyt2.setPivotX(width/2);
        ObjectAnimator upTran2 = ObjectAnimator.ofFloat(lyt2, View.TRANSLATION_Y, 0,-width);
        upTran2.setDuration(1000);
        upTran2.setInterpolator(null);

        ObjectAnimator down1 = ObjectAnimator.ofFloat(lyt1, View.ROTATION_X, 0, -90);
        down1.setDuration(1000);
        down1.setInterpolator(null);
        lyt1.setPivotX(width/2);
        ObjectAnimator downTran1 = ObjectAnimator.ofFloat(lyt1, View.TRANSLATION_Y, 0,width);
        downTran1.setDuration(1000);
        downTran1.setInterpolator(null);

        ObjectAnimator down2 = ObjectAnimator.ofFloat(lyt2, View.ROTATION_X, 0, -90);
        down2.setDuration(1000);
        down2.setInterpolator(null);
        lyt2.setPivotX(width/2);
        ObjectAnimator downTran2 = ObjectAnimator.ofFloat(lyt2, View.TRANSLATION_Y, 0,width);
        downTran2.setDuration(1000);
        downTran2.setInterpolator(null);


        lyt2.setX(-width);
        lyt2.setRotationY(270);

        lyt3.setY(-width);
        lyt3.setRotationX(270);

        lyt4.setY(0);
        lyt4.setRotationX(-90);

        lyt1.setPivotY(width/2);
        lyt2.setPivotY(width/2);
        lyt3.setPivotX(width/2);
        lyt4.setPivotX(width/2);

        lyt4.setPivotY(width);


        ObjectAnimator upRight1 = ObjectAnimator.ofFloat(lyt3, View.ROTATION, 0, 90);
        upRight1.setDuration(1000);
        upRight1.setInterpolator(null);
        lyt3.setPivotX(width/2);
        ObjectAnimator upRightTran1 = ObjectAnimator.ofFloat(lyt3, View.TRANSLATION_X, 0,width);
        upRightTran1.setDuration(1000);
        upRightTran1.setInterpolator(null);

        ObjectAnimator downRight1 = ObjectAnimator.ofFloat(lyt4, View.ROTATION, 0, -90);
        downRight1.setDuration(1000);
        downRight1.setInterpolator(null);
        lyt4.setPivotX(width/2);
        ObjectAnimator downRightTran1 = ObjectAnimator.ofFloat(lyt4, View.TRANSLATION_X, 0,width);
        downRightTran1.setDuration(1000);
        downRightTran1.setInterpolator(null);

        propose = new Propose(this);
        propose.motionRight.play(rot1).with(tran1).with(rot2).with(tran2)
                .with(upRight1).with(upRightTran1)
                .with(downRight1).with(downRightTran1);
        propose.motionUp.play(rot3).with(tran3).with(up1).with(upTran1).with(up2).with(upTran2);
        propose.motionDown.play(rot4).with(tran4).with(down1).with(downTran1).with(down2).with(downTran2);

        propose.setMotionDistanceAll((int)width/2);

        rot1.addListener(new JwAnimatorListener() {
            @Override
            public void onStart(Animator animation) {
                lyt3.setPivotX(0);
                lyt4.setPivotX(0);
            }
            @Override
            public void onReverseStart(Animator animation) {
            }
            @Override
            public void onReverseEnd(Animator animation) {
            }
            @Override
            public void onEnd(Animator animation) {
            }
        });
        rot3.addListener(new JwAnimatorListener() {
            @Override
            public void onStart(Animator animation) {
                lyt1.setPivotY(width);
                lyt2.setPivotY(width);
            }
            @Override
            public void onReverseStart(Animator animation) {
            }
            @Override
            public void onReverseEnd(Animator animation) {
            }
            @Override
            public void onEnd(Animator animation) {
            }
        });

        rot4.addListener(new JwAnimatorListener() {
            @Override
            public void onStart(Animator animation) {
                lyt1.setPivotY(0);
                lyt2.setPivotY(0);
            }
            @Override
            public void onReverseStart(Animator animation) {
            }
            @Override
            public void onReverseEnd(Animator animation) {
            }
            @Override
            public void onEnd(Animator animation) {
            }
        });

        touch_lyt.setOnTouchListener(propose);
    }

    private void getVocabularyLocal(int vocabularyID){
        Call<VocabularyModel> call_vocabulary = (new APIRetrofit()).getAPIService().getVocabularyService(
                vocabularyID,
                SharedPrefManager.getInstance(VocabularyActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(VocabularyActivity.this).getUser().getAuthentication_token()
        );
        call_vocabulary.enqueue(new Callback<VocabularyModel>() {
            @Override
            public void onResponse(Call<VocabularyModel> call, Response<VocabularyModel> response) {
                str_japanese = response.body().getJapanese();
                str_spell = response.body().getSpell();
                str_mean = response.body().getMean();
                str_picture = response.body().getPicture();

                tv1_1.setText(str_japanese);
                tv1_2.setText(str_spell);
                tv4_1.setText(str_mean);

                Glide.with(VocabularyActivity.this)
                        .load(str_picture)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(img3_1);

                getSentencesLocal(vocabularyID);
            }

            @Override
            public void onFailure(Call<VocabularyModel> call, Throwable t) {

            }
        });
    }

    private void getSentencesLocal(int vocabularyID){
        Call<ArrayList<SentenceModel>> call_sentences = (new APIRetrofit()).getAPIService()
                .getSentencesService(
                        SharedPrefManager.getInstance(VocabularyActivity.this).getUser().getEmail(),
                        SharedPrefManager.getInstance(VocabularyActivity.this).getUser().getAuthentication_token(),
                        vocabularyID
                );
        call_sentences.enqueue(new Callback<ArrayList<SentenceModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SentenceModel>> call, Response<ArrayList<SentenceModel>> response) {
                if (response.body().size() == 1) {
                    tv2_1.setText(response.body().get(0).getContent());
                    tv2_2.setText(response.body().get(0).getMean());
                }
                else if (response.body().size() >1){
                    tv2_1.setText(response.body().get(0).getContent());
                    tv2_2.setText(response.body().get(0).getMean());
                    tv2_3.setText(response.body().get(1).getContent());
                    tv2_4.setText(response.body().get(1).getMean());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SentenceModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.vocabulary_feedback_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                return true;
            case  R.id.action_feedback:
                dialogFeedbackVocabulary();
                return true;
            case R.id.action_look_feedbacks:
                getFeedbackLocal(vocabularyID);
                return true;
        }
        return false;
    }

    private void dialogFeedbackVocabulary(){
        View v = LayoutInflater.from(VocabularyActivity.this).inflate(R.layout.dialog_create_feedback, null);
        EditText edtFeedback = (EditText) v.findViewById(R.id.edtFeedback);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(VocabularyActivity.this);
        alertDialog.setView(v);
        alertDialog.setTitle("Phản hồi");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                createFeedbackVocabularyLocal(vocabularyID, edtFeedback.getText().toString());
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
    private void createFeedbackVocabularyLocal(int mVocabularyID, String content){
        final String object_class = "vocabulary";
        Call<FeedbackModel> call_feedback_vocabulary = (new APIRetrofit()).getAPIService().createFeedbackService(
                SharedPrefManager.getInstance(VocabularyActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(VocabularyActivity.this).getUser().getAuthentication_token(),
                object_class, mVocabularyID, content
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
    private void getFeedbackLocal(int mVocabularyID){
        final String object_class = "vocabulary";
        Call<ArrayList<FeedbackModel>> call_feedbacks = (new APIRetrofit()).getAPIService().getFeedbacksService(
                SharedPrefManager.getInstance(VocabularyActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(VocabularyActivity.this).getUser().getAuthentication_token(),
                object_class, mVocabularyID
        );
        call_feedbacks.enqueue(new Callback<ArrayList<FeedbackModel>>() {
            @Override
            public void onResponse(Call<ArrayList<FeedbackModel>> call, Response<ArrayList<FeedbackModel>> response) {
                View v = LayoutInflater.from(VocabularyActivity.this).inflate(R.layout.dialog_feedbacks, null);
                RecyclerView rclFeedbacks = (RecyclerView) v.findViewById(R.id.rclFeedbacks);
                rclFeedbacks.setHasFixedSize(true);
                rclFeedbacks.setLayoutManager(new LinearLayoutManager(VocabularyActivity.this));

                FeedbackAdapter feedbackAdapter = new FeedbackAdapter(VocabularyActivity.this, response.body());
                rclFeedbacks.setAdapter(feedbackAdapter);
                feedbackAdapter.notifyDataSetChanged();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(VocabularyActivity.this);
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
