package com.example.admin.nihongotaisaku.activities;

import android.app.AlertDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.api.APIRetrofit;
import com.example.admin.nihongotaisaku.fragments.AlphabetLearnFragment;
import com.example.admin.nihongotaisaku.fragments.AlphabetWriteFragment;
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

public class AlphabetLearn_WriteActivity extends AppCompatActivity {
    AlphabetLearnFragment alphabetLearnFragment;
    AlphabetWriteFragment alphabetWriteFragment;
    Bundle bundle;
    MediaPlayer mediaPlayer;

    FabSpeedDial fabSpeedDial;

    String soundAlphabet, image_writingAlphabet, image_compareAlphabet;
    int alphabetID = 0, classifyAlphabet = 0;

    private static final String TAG = "OCVSample::Activity";

    public AlphabetLearn_WriteActivity() {
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
        image_writingAlphabet = bundle.getString("image_writingAlphabet");
        image_compareAlphabet = bundle.getString("image_compareAlphabet");
        soundAlphabet = bundle.getString("soundAlphabet");
        classifyAlphabet = bundle.getInt("classifyAlphabet", 0);
        alphabetID = bundle.getInt("alphabet_id_data", 0);

        getSupportActionBar().setTitle(bundle.getString("alphabet_japanese_data"));

        if (savedInstanceState == null){
            View v = LayoutInflater.from(AlphabetLearn_WriteActivity.this).inflate(R.layout.dialog_learn_and_write, null);
            Button btnLearn = (Button) v.findViewById(R.id.btnLearn);
            Button btnWrite = (Button) v.findViewById(R.id.btnWrite);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AlphabetLearn_WriteActivity.this);
            AlertDialog OptionDialog = alertDialog.create();
            OptionDialog.setMessage("Hãy chọn chức năng bạn muốn học");
            OptionDialog.setView(v);
            OptionDialog.setTitle("Message");
            OptionDialog.show();

            if (classifyAlphabet == 2 || classifyAlphabet == 3){
                btnLearn.setEnabled(false);
            }
            btnLearn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alphabetLearnFragment = new AlphabetLearnFragment();
                    alphabetLearnFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.alphabet_image_container, alphabetLearnFragment)
                            .commit();
                    OptionDialog.dismiss();
                }
            });
            btnWrite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (classifyAlphabet == 2 || classifyAlphabet == 3){
                        fabSpeedDial.setEnabled(false);
                    }
                    alphabetWriteFragment = new AlphabetWriteFragment();
                    alphabetWriteFragment.setArguments(bundle);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.alphabet_image_container, alphabetWriteFragment)
                            .commit();
                    OptionDialog.dismiss();
                }
            });
        }

        fabSpeedDial.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_learn_fab:
                        alphabetLearnFragment = new AlphabetLearnFragment();
                        alphabetLearnFragment.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .add(R.id.alphabet_image_container, alphabetLearnFragment)
                                .commit();
                        break;
                    case R.id.action_write_fab:
                        alphabetWriteFragment = new AlphabetWriteFragment();
                        alphabetWriteFragment.setArguments(bundle);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.alphabet_image_container, alphabetWriteFragment)
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
                    mediaPlayer.setDataSource(soundAlphabet);
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
        }
        return false;
    }

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(AlphabetLearn_WriteActivity.this) {
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
