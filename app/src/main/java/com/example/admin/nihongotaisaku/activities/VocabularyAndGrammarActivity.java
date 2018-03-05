package com.example.admin.nihongotaisaku.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.VocabularyAndGrammarAdapter;

public class VocabularyAndGrammarActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private VocabularyAndGrammarAdapter vocabularyAndGrammarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary_and_grammar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        vocabularyAndGrammarAdapter = new VocabularyAndGrammarAdapter(getSupportFragmentManager());
        viewPager.setAdapter(vocabularyAndGrammarAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
