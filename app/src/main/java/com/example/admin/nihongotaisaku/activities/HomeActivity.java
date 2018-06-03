package com.example.admin.nihongotaisaku.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.adapters.AlbumsAdapter;
import com.example.admin.nihongotaisaku.api.APIService;
import com.example.admin.nihongotaisaku.api.APIUrl;
import com.example.admin.nihongotaisaku.helper.SharedPrefManager;
import com.example.admin.nihongotaisaku.models.AlbumModel;
import com.example.admin.nihongotaisaku.models.ResultUser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView nav_headerEmail;
    private RecyclerView rclHome;
    private AlbumsAdapter albumsAdapter;
    private ArrayList<AlbumModel> arrAlbum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        initCollapsingToolbar();
        rclHome = (RecyclerView) findViewById(R.id.rclHome);
        arrAlbum = new ArrayList<>();
        albumsAdapter = new AlbumsAdapter(this, arrAlbum);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rclHome.setHasFixedSize(true);
        rclHome.setLayoutManager(layoutManager);
        rclHome.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        rclHome.setItemAnimator(new DefaultItemAnimator());
        rclHome.setAdapter(albumsAdapter);

        prepareAlbums();

        Glide.with(HomeActivity.this)
                .load("https://imgur.com/vAuEmYI.png")
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into((ImageView) findViewById(R.id.backdrop));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView.getHeaderView(0);
        nav_headerEmail = headerLayout.findViewById(R.id.header_name);
        nav_headerEmail.setText(SharedPrefManager.getInstance(this).getUser().getName());

        navigationView.inflateMenu(R.menu.activity_home_drawer);
        navigationView.setNavigationItemSelectedListener(this);

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        final int classifyLesson;
        Intent intent = new Intent(HomeActivity.this, LessonActivity.class);

        switch (item.getItemId()){
            case R.id.nav_alphabet:
                startActivity(new Intent(getApplicationContext(), AlphabetActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.nav_minanoNihongo:
                classifyLesson = 0;
                intent.putExtra("classifyLesson", classifyLesson);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.nav_mimikaraOboeruN3:
                classifyLesson = 1;
                intent.putExtra("classifyLesson", classifyLesson);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.nav_logout:
                userLogout();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarMain = (AppBarLayout) findViewById(R.id.appBarMain);
        appBarMain.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarMain.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarMain.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow){
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });
    }

    private void prepareAlbums(){
        String[] listImage = new String[]{
                "https://imgur.com/VUyi5cd.png", "https://imgur.com/TVzECIL.png",
                "https://imgur.com/hv0NXNf.png", "https://imgur.com/HGVuUhj.png",
                "https://imgur.com/if7XCbF.png", "https://imgur.com/A65s0qn.png",
                "https://imgur.com/isGZ8JH.png", "https://imgur.com/ofyPAVG.png",
                "https://imgur.com/Zv0Np7x.png", "https://imgur.com/oIIVJUd.png"
        };

        String[] listJapanese = new String[]{
                "はじめまして", "おはよう", "こんにちは", "こんばんは", "さよなら", "ありがとう", "すみません",
                "おやすみ", "どういたしまして", "ひさしぶり"
        };
        String[] listMean = new String[]{
                "Rất vui khi được gặp bạn", "Chào buổi sáng", "Chào buổi trưa", "Chào buổi tối",
                "Tạm biệt", "Cảm ơn", "Xin lỗi", "Chúc ngủ ngon", "Không có gì", "Lâu rồi không gặp bạn"
        };

        String[] listSound = new String[]{
                "https://storage.dekiru.vn//Data/2016/11/15/hajimemashite-636148229080394557.mp3",
                "https://storage.dekiru.vn//Data/2016/11/15/ohayo-636148228403231171.mp3",
                "https://storage.dekiru.vn//Data/2016/11/15/konnichiwa-636148228670183242.mp3",
                "https://storage.dekiru.vn//Data/2016/11/15/konbanwa-636148228808197709.mp3",
                "https://storage.dekiru.vn//Data/2016/11/15/sayonara-636148229364447140.mp3",
                "https://storage.dekiru.vn//Data/2016/11/15/arigatou-636148229605141518.mp3",
                "https://storage.dekiru.vn/Data/2016/11/15/sumimasen-636148229215194508.mp3",
                "https://storage.dekiru.vn//Data/2016/11/15/oyasumi-636148228949170303.mp3",
                "https://storage.dekiru.vn//Data/2016/11/15/douitashimashite-636148229724401637.mp3",
                "https://storage.dekiru.vn//Data/2016/11/15/hisashiburi-636148229850998875.mp3"
        };

        for (int i = 0; i<10; i++){
            AlbumModel a = new AlbumModel(listImage[i], listJapanese[i], listMean[i], listSound[i]);
            arrAlbum.add(a);
        }

        albumsAdapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
                startActivity(new Intent(getApplicationContext(), SignInActivity.class));
            }

            @Override
            public void onFailure(Call<ResultUser> call, Throwable t) {

            }
        });
    }
}
