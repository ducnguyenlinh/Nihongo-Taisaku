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

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.HistoryAdapter;
import com.example.admin.nihongotaisaku.adapters.ListVocabularyAdapter;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.HistoryModel;
import com.example.admin.nihongotaisaku.models.VocabularyModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListVocabulary_Activity extends AppCompatActivity {
    ListVocabularyAdapter listVocabularyAdapter;
    private int lessonID, classifyLesson;

    RecyclerView rclVocabularies, rclHistories;
    HistoryAdapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_vocabulary_);

        lessonID = getIntent().getIntExtra("lesson_id", 0);
        classifyLesson = getIntent().getIntExtra("classifyLesson", 0);
        setTitle(getIntent().getStringExtra("lesson_content"));

        rclVocabularies = (RecyclerView) findViewById(R.id.rclListVocabulary);
        rclVocabularies.setHasFixedSize(true);
        rclVocabularies.setLayoutManager(new GridLayoutManager(this, 2));

        getListVocabularyLocal(lessonID);
    }

    //Get list vocabylary
    private void getListVocabularyLocal(int lessonID){
        Call<ArrayList<VocabularyModel>> call_vocabularies = (new APIRetrofit()).getAPIService().getVocabulariesService(
                SharedPrefManager.getInstance(ListVocabulary_Activity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(ListVocabulary_Activity.this).getUser().getAuthentication_token(),
                lessonID
        );

        call_vocabularies.enqueue(new Callback<ArrayList<VocabularyModel>>() {
            @Override
            public void onResponse(Call<ArrayList<VocabularyModel>> call, Response<ArrayList<VocabularyModel>> response) {
                listVocabularyAdapter = new ListVocabularyAdapter(ListVocabulary_Activity.this, response.body());
                rclVocabularies.setAdapter(listVocabularyAdapter);
                rclVocabularies.getAdapter().notifyDataSetChanged();

                listVocabularyAdapter.setOnItemClick(new ListVocabularyAdapter.OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        createHistoryVocabulary(response.body().get(position).getId(),
                                response.body().get(position).getJapanese(), classifyLesson);
                        Intent it_vocabularyActivity = new Intent(ListVocabulary_Activity.this,
                                VocabularyActivity.class);
                        it_vocabularyActivity.putExtra("vocabularyJapanese", response.body().get(position).getJapanese());
                        it_vocabularyActivity.putExtra("vocabulary_id", response.body().get(position).getId());
                        startActivity(it_vocabularyActivity);
                        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                    }
                });
            }

            @Override
            public void onFailure(Call<ArrayList<VocabularyModel>> call, Throwable t) {

            }
        });
    }

    //Create history alphabet
    private void createHistoryVocabulary(int lessonID, String objectContent, int mClassifyLesson){
        String object_class = "";
        if (mClassifyLesson == 0)
            object_class = "vocabulary_0";
        else if (mClassifyLesson == 1)
            object_class = "vocabulary_1";

        Call<HistoryModel> call_history_lesson = (new APIRetrofit()).getAPIService().createHistoryService(
                SharedPrefManager.getInstance(ListVocabulary_Activity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(ListVocabulary_Activity.this).getUser().getAuthentication_token(),
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
    private void getHistoryVocabulary(int mClassifyLesson){
        String object_class = "";
        if (mClassifyLesson == 0)
            object_class = "vocabulary_0";
        else if (mClassifyLesson == 1)
            object_class = "vocabulary_1";

        Call<ArrayList<HistoryModel>> call_histories_lesson = (new APIRetrofit()).getAPIService().getHistoriesService(
                SharedPrefManager.getInstance(ListVocabulary_Activity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(ListVocabulary_Activity.this).getUser().getAuthentication_token(),
                object_class
        );

        call_histories_lesson.enqueue(new Callback<ArrayList<HistoryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HistoryModel>> call, Response<ArrayList<HistoryModel>> response) {

                View v = LayoutInflater.from(ListVocabulary_Activity.this).inflate(R.layout.dialog_history, null);
                rclHistories = (RecyclerView) v.findViewById(R.id.rclHistories);
                rclHistories.setHasFixedSize(true);
                rclHistories.setLayoutManager(new LinearLayoutManager(ListVocabulary_Activity.this));

                historyAdapter = new HistoryAdapter((Activity) ListVocabulary_Activity.this,
                        response.body());
                rclHistories.setAdapter(historyAdapter);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListVocabulary_Activity.this);
                alertDialog.setView(v);
                alertDialog.setTitle("Vocabulary History");
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
                getHistoryVocabulary(classifyLesson);
                return true;
        }
        return false;
    }
}
