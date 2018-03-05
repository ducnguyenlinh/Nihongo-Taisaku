package com.example.admin.nihongotaisaku.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.fragments.HiraganaCardBackFragment;
import com.example.admin.nihongotaisaku.fragments.HiraganaCardFrontFragment;

public class WritingHiraganaActivity extends AppCompatActivity {
    Boolean mShowingBack = false;
    HiraganaCardFrontFragment frontFragment = new HiraganaCardFrontFragment();
    HiraganaCardBackFragment backFragment = new HiraganaCardBackFragment();
    Bundle bundle = new Bundle();
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_hiragana);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bd = getIntent().getBundleExtra("positionData");
        position = bd.getInt("position");
        bundle.putInt("position", position);
        frontFragment.setArguments(bundle);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.hiragana_container, frontFragment)
                    .commit();

            FrameLayout fml = (FrameLayout) findViewById(R.id.hiragana_container);
            fml.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flipCard();
                }
            });
        }
    }

    private void flipCard() {
        if (mShowingBack) {
            bundle.putInt("position", position);
            frontFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.card_flip_right_in,
                    R.anim.card_flip_right_out).replace(R.id.hiragana_container, frontFragment)
            .commit();
            mShowingBack = false;
        }
        else {
            bundle.putInt("position", position);
            backFragment.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(
                            R.anim.card_flip_left_in,
                            R.anim.card_flip_left_out)
                    .replace(R.id.hiragana_container, backFragment)
                    .commit();
            mShowingBack = true;
        }
    }

}
