package com.example.admin.nihongotaisaku.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.badoualy.stepperindicator.StepperIndicator;
import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.AlphabetAdapter;
import com.example.admin.nihongotaisaku.adapters.HistoryAdapter;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.HistoryModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.history_menu, menu);
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
                getAlphabetHistories();
                return true;
        }
        return false;
    }

    // Get history alphabet
    private void getAlphabetHistories(){
        String object_class = "alphabet";
        Call<ArrayList<HistoryModel>> call_histories_lesson = (new APIRetrofit()).getAPIService().getHistoriesService(
                SharedPrefManager.getInstance(AlphabetActivity.this).getUser().getEmail(),
                SharedPrefManager.getInstance(AlphabetActivity.this).getUser().getAuthentication_token(),
                object_class
        );

        call_histories_lesson.enqueue(new Callback<ArrayList<HistoryModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HistoryModel>> call, Response<ArrayList<HistoryModel>> response) {
                View v = LayoutInflater.from(AlphabetActivity.this).inflate(R.layout.dialog_history, null);
                RecyclerView rclHistories = (RecyclerView) v.findViewById(R.id.rclHistories);
                rclHistories.setHasFixedSize(true);
                rclHistories.setLayoutManager(new LinearLayoutManager(AlphabetActivity.this));

                HistoryAdapter historyAdapter = new HistoryAdapter((Activity) AlphabetActivity.this,
                        response.body());
                rclHistories.setAdapter(historyAdapter);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlphabetActivity.this);
                alertDialog.setView(v);
                alertDialog.setTitle("Alphabet History");
                alertDialog.setPositiveButton("OK", null);
                alertDialog.show();
            }

            @Override
            public void onFailure(Call<ArrayList<HistoryModel>> call, Throwable t) {

            }
        });
    }
}
