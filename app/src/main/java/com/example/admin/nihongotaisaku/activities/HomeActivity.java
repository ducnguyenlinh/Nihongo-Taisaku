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

        Glide.with(HomeActivity.this).load(R.mipmap.ic_te)
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
        int classify;
        Intent intent = new Intent(HomeActivity.this, LessonActivity.class);

        switch (item.getItemId()){
            case R.id.nav_alphabet:
                startActivity(new Intent(getApplicationContext(), AlphabetActivity.class));
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.nav_minanoNihongo:
                classify = 0;
                intent.putExtra("classify", classify);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
                break;
            case R.id.nav_mimikaraOboeruN3:
                classify = 1;
                intent.putExtra("classify", classify);
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
        int[] covers = new int[]{
                R.mipmap.ic_xeco, R.mipmap.ic_chaohoi, R.mipmap.ic_raucu, R.mipmap.ic_thethao,
                R.mipmap.ic_dongvat, R.mipmap.ic_hoa
        };

        AlbumModel a = new AlbumModel("XE CỘ", covers[0], 10);
        arrAlbum.add(a);

        a = new AlbumModel("CHÀO HỎI", covers[1], 10);
        arrAlbum.add(a);

        a = new AlbumModel("RAU CỦ", covers[2], 5);
        arrAlbum.add(a);

        a = new AlbumModel("THỂ THAO", covers[3], 8);
        arrAlbum.add(a);

        a = new AlbumModel("ĐỘNG VẬT", covers[4], 6);
        arrAlbum.add(a);

        a = new AlbumModel("HOA", covers[5], 15);
        arrAlbum.add(a);

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
