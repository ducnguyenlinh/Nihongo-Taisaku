package com.example.admin.nihongotaisaku.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.fragments.HiraganaCardBackFragment;
import com.example.admin.nihongotaisaku.fragments.HiraganaCardFrontFragment;
import com.example.admin.nihongotaisaku.fragments.KatakanaCardFrontFragment;

public class WritingKatakanaActivity extends AppCompatActivity {
    Boolean mShowingBack = false;
    KatakanaCardFrontFragment frontFragment = new KatakanaCardFrontFragment();
    Bundle bundle = new Bundle();
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_katakana);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bd = getIntent().getBundleExtra("positionData");
        position = bd.getInt("position");
        bundle.putInt("position", position);
        frontFragment.setArguments(bundle);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.katakana_container, frontFragment)
                    .commit();
        }
    }

}
