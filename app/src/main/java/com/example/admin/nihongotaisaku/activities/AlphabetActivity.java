package com.example.admin.nihongotaisaku.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.badoualy.stepperindicator.StepperIndicator;
import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.AlphabetAdapter;

public class AlphabetActivity extends AppCompatActivity {
    AlphabetAdapter alphabetAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        alphabetAdapter = new AlphabetAdapter(getSupportFragmentManager());
        
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        assert viewPager != null;

        viewPager.setAdapter(alphabetAdapter);

        final StepperIndicator indicator = findViewById(R.id.stepper_indicator);
        indicator.setViewPager(viewPager, viewPager.getAdapter().getCount());

        indicator.showLabels(true);
        indicator.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                viewPager.setCurrentItem(step, true);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                return true;
        }
        return false;
    }

}
