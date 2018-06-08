package com.example.admin.nihongotaisaku.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.HistoryAdapter;
import com.example.admin.nihongotaisaku.adapters.LessonAdapter;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.HistoryModel;
import com.example.admin.nihongotaisaku.models.LessonModel;
import com.example.admin.nihongotaisaku.models.ResultUserLesson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonActivity extends AppCompatActivity {
    LessonAdapter lessonAdapter;
    RecyclerView rclLesson, rclHistories;
    HistoryAdapter historyAdapter;
    private int classifyLesson = 0;
    private String object_class = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        classifyLesson = getIntent().getIntExtra("classifyLesson", 0);
        if (classifyLesson == 0)
            setTitle("Minano Nihongo");
        else if (classifyLesson == 1)
            setTitle("Mimikara Oboeru N3");
        rclLesson = findViewById(R.id.rclListLesson);
        rclLesson.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rclLesson.setLayoutManager(layoutManager);

        Call<ArrayList<LessonModel>> call = (new APIRetrofit()).getAPIService().getLessonsService(
                SharedPrefManager.getInstance(LessonActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(LessonActivity.this).getUser().getAuthentication_token(),
                classifyLesson);

        call.enqueue(new Callback<ArrayList<LessonModel>>() {
            @Override
            public void onResponse(Call<ArrayList<LessonModel>> call, Response<ArrayList<LessonModel>> response) {
                lessonAdapter = new LessonAdapter(LessonActivity.this, response.body());
                rclLesson.setAdapter(lessonAdapter);

                LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(
                        LessonActivity.this, R.anim.layout_fall_down);
                rclLesson.setLayoutAnimation(animationController);
                rclLesson.getAdapter().notifyDataSetChanged();
                rclLesson.scheduleLayoutAnimation();

                lessonAdapter.setOnItemClick(new LessonAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                       createUserLesson(response.body().get(position).getId());
                       createHistoryLesson(classifyLesson,response.body().get(position).getId(),
                               response.body().get(position).getContent());
                       Intent it_list_vocabulary = new Intent(LessonActivity.this, ListVocabulary_Activity.class);
                       it_list_vocabulary.putExtra("classifyLesson", classifyLesson);
                       it_list_vocabulary.putExtra("lesson_id", response.body().get(position).getId());
                       it_list_vocabulary.putExtra("lesson_content", response.body().get(position).getContent());
                       startActivity(it_list_vocabulary);
                       overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<LessonModel>> call, Throwable t) {

            }
        });
    }

    // Create User_Lesson
    private void createUserLesson(int lessonID){
        Call<ResultUserLesson> call_user_lesson = (new APIRetrofit()).getAPIService().createUserLessonService(
                SharedPrefManager.getInstance(LessonActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(LessonActivity.this).getUser().getAuthentication_token(),
                lessonID);
        call_user_lesson.enqueue(new Callback<ResultUserLesson>() {
            @Override
            public void onResponse(Call<ResultUserLesson> call, Response<ResultUserLesson> response) {

            }

            @Override
            public void onFailure(Call<ResultUserLesson> call, Throwable t) {

            }
        });
    }

    // Create History_Lesson
    private void createHistoryLesson(int mClassifyLesson, int lessonID, String objectContent){
        if (mClassifyLesson == 0)
            object_class = "lesson_0";
        else if (mClassifyLesson == 1)
            object_class = "lesson_1";
        Call<HistoryModel> call_history_lesson = (new APIRetrofit()).getAPIService().createHistoryService(
                SharedPrefManager.getInstance(LessonActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(LessonActivity.this).getUser().getAuthentication_token(),
                object_class, lessonID, objectContent);
        call_history_lesson.enqueue(new Callback<HistoryModel>() {
            @Override
            public void onResponse(Call<HistoryModel> call, Response<HistoryModel> response) {

            }

            @Override
            public void onFailure(Call<HistoryModel> call, Throwable t) {

            }
        });
    }

    // Get History Lesson
    private void getHistoriesLesson(int mClassifyLesson){
        if (mClassifyLesson == 0)
            object_class = "lesson_0";
        else if (mClassifyLesson == 1)
            object_class = "lesson_1";
        Call<ArrayList<HistoryModel>> call_histories_lesson = (new APIRetrofit()).getAPIService().getHistoriesService(
                SharedPrefManager.getInstance(LessonActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(LessonActivity.this).getUser().getAuthentication_token(),
                object_class
        );

        call_histories_lesson.enqueue(new Callback<ArrayList<HistoryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HistoryModel>> call, Response<ArrayList<HistoryModel>> response) {
                View v = LayoutInflater.from(LessonActivity.this).inflate(R.layout.dialog_history, null);
                rclHistories = (RecyclerView) v.findViewById(R.id.rclHistories);
                rclHistories.setHasFixedSize(true);
                rclHistories.setLayoutManager(new LinearLayoutManager(LessonActivity.this));

                historyAdapter = new HistoryAdapter((Activity) LessonActivity.this,
                        response.body());
                rclHistories.setAdapter(historyAdapter);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(LessonActivity.this);
                alertDialog.setView(v);
                alertDialog.setTitle("Lịch sử bài học");
                alertDialog.setPositiveButton("OK", null);
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<ArrayList<HistoryModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                return true;
            case R.id.action_histories:
                getHistoriesLesson(classifyLesson);
                return true;
        }
        return false;
    }
}
