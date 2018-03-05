package com.example.admin.nihongotaisaku.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.LessonAdapter;
import com.example.admin.nihongotaisaku.data.LessonData;
import com.example.admin.nihongotaisaku.models.ListLesson;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class LessonActivity extends AppCompatActivity {
    ArrayList<ListLesson> arrListLesson;
    LessonAdapter lessonAdapter;
    RecyclerView rclLesson;
    private LessonData lessonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        rclLesson = findViewById(R.id.rclListLesson);
        arrListLesson = new ArrayList<>();
        lessonData = new LessonData();
        lessonData.execute();
        try {
            arrListLesson = lessonData.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rclLesson.setLayoutManager(layoutManager);
        lessonAdapter = new LessonAdapter((Activity) LessonActivity.this, arrListLesson);
        rclLesson.setAdapter(lessonAdapter);
        lessonAdapter.notifyDataSetChanged();

        lessonAdapter.setOnItemClick(new LessonAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent_vocabulary_grammar = new Intent(LessonActivity.this, VocabularyAndGrammarActivity.class);
                Log.d("$$$$$", arrListLesson.get(position).getLessonHref());
            }
        });
    }
}
