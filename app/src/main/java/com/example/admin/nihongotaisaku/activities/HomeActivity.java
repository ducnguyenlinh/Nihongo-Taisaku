package com.example.admin.nihongotaisaku.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.SlidingImageAdapter;
import com.example.admin.nihongotaisaku.api.APIService;
import com.example.admin.nihongotaisaku.api.APIUrl;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.ResultUser;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView nav_headerEmail;
    ViewPager viewPagerHome;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    private static final Integer[] IMAGES= {R.mipmap.hiragana_a,R.mipmap.hiragana_i,
            R.mipmap.hiragana_u,R.mipmap.hiragana_e,R.mipmap.hiragana_o};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    /*    View headerLayout =
                navigationView.getHeaderView(0);
        nav_headerEmail = headerLayout.findViewById(R.id.nav_headerEmail);
        nav_headerEmail.setText(SharedPrefManager.getInstance(this).getUser().getEmail());*/

        navigationView.inflateMenu(R.menu.activity_home_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        setSlidingImages();

    }

    private void setSlidingImages() {
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        viewPagerHome = (ViewPager) findViewById(R.id.viewPagerHome);

        viewPagerHome.setAdapter(new SlidingImageAdapter(HomeActivity.this,ImagesArray));

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);

        indicator.setViewPager(viewPagerHome);
        (new SlidingImageAdapter(HomeActivity.this,ImagesArray)).registerDataSetObserver(indicator.getDataSetObserver());

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPagerHome.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_kanji:
                break;
            case R.id.nav_alphabet:
                startActivity(new Intent(getApplicationContext(), AlphabetActivity.class));
                break;
            case R.id.nav_vocabulary:
                startActivity(new Intent(getApplicationContext(), LessonActivity.class));
                break;
            case R.id.nav_profile:
                break;
            case R.id.nav_logout:
                userLogout();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void userLogout(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Log Out ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService service = retrofit.create(APIService.class);

        Call<ResultUser> call = service.userLogout(SharedPrefManager.getInstance(this).getUser().getId(),
                SharedPrefManager.getInstance(this).getUser().getEmail(),
                SharedPrefManager.getInstance(this).getUser().getAuthentication_token(), "DELETE");

        call.enqueue(new Callback<ResultUser>() {
            @Override
            public void onResponse(Call<ResultUser> call, Response<ResultUser> response) {
                progressDialog.dismiss();

                SharedPrefManager.getInstance(HomeActivity.this).userLogout();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }

            @Override
            public void onFailure(Call<ResultUser> call, Throwable t) {

            }
        });

    }
}
