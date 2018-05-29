package com.example.admin.nihongotaisaku.activities;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.fragments.AlphabetImageFragment;
import com.example.admin.nihongotaisaku.fragments.AlphabetWritingFragment;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.AlphabetImageModel;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

import java.io.IOException;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlphabetImageActivity extends AppCompatActivity {
    AlphabetImageFragment alphabetImageFragment;
    AlphabetWritingFragment alphabetWritingFragment;
    Bundle bundle;
    MediaPlayer mediaPlayer;

    FabSpeedDial fabSpeedDial;

    String sound = "", title = "";
    int alphabetID = 0;

    private static final String TAG = "OCVSample::Activity";

    public AlphabetImageActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    static {
        OpenCVLoader.initDebug();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fabSpeedDial = (FabSpeedDial) findViewById(R.id.fab_control);

        bundle = new Bundle();
        bundle = getIntent().getBundleExtra("alphabet_data");
        title = bundle.getString("alphabet_japanese_data");
        alphabetID = bundle.getInt("alphabet_id_data", 0);

        getSupportActionBar().setTitle(title);
        getAlphabetImages(alphabetID);

        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_learn_fab:
                        alphabetImageFragment = new AlphabetImageFragment();
                        alphabetImageFragment.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.alphabet_image_container, alphabetImageFragment)
                                .commit();
                        break;
                    case R.id.action_write_fab:
                        alphabetWritingFragment = new AlphabetWritingFragment();
                        alphabetWritingFragment.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.alphabet_image_container, alphabetWritingFragment)
                                .commit();
                        break;
                }
                return true;
            }

            @Override
            public void onMenuClosed() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.alphabet_image_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
                return true;
            case R.id.action_speaker:
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(sound);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
        }
        return false;
    }


    private void getAlphabetImages(int alphabetID){
        Call<AlphabetImageModel> call_alphabet_images = (new APIRetrofit()).getAPIService().
                getAlphabetImagesService(
                        alphabetID,
                        SharedPrefManager.getInstance(AlphabetImageActivity.this).getUser().getEmail(),
                        SharedPrefManager.getInstance(AlphabetImageActivity.this).getUser().getAuthentication_token()

                );
        call_alphabet_images.enqueue(new Callback<AlphabetImageModel>() {
            @Override
            public void onResponse(Call<AlphabetImageModel> call, Response<AlphabetImageModel> response) {
                sound = response.body().getSound();
            }

            @Override
            public void onFailure(Call<AlphabetImageModel> call, Throwable t) {

            }
        });
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(AlphabetImageActivity.this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i(TAG, "OpenCV loaded successfully");
                }
                break;
                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
}
