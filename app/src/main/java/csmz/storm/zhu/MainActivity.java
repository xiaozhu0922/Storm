package csmz.storm.zhu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import csmz.storm.zhu.adapters.ViewPagerAdapter;
import csmz.storm.zhu.app.CommonActivity;
import csmz.storm.zhu.diary.DiaryFragment;
import csmz.storm.zhu.duanzi.DuanZiFragment;
import csmz.storm.zhu.utils.CommonInterfaceUtil;
import csmz.storm.zhu.utils.CommonTabLayoutUtil;
import csmz.storm.zhu.utils.CommonUtil;
import csmz.storm.zhu.wallpaper.WallpaperFragment;

public class MainActivity extends CommonActivity
        implements NavigationView.OnNavigationItemSelectedListener, CommonInterfaceUtil.ViewPageSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ViewPager vp_main;
    private CommonTabLayout tl_main;
    private List<Fragment> fragments;
    private ArrayList<CustomTabEntity> mTabTitles = new ArrayList<>();
    private String[] mainTitles;
    private int[] mainIcons, mainSelectedIcons;
    private ViewPagerAdapter mainViewPagerAdapter;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        vp_main = findView(R.id.view_pager);
        tl_main = findView(R.id.tab_layout);
        drawerLayout = findView(R.id.drawer_layout);
        navigationView = findView(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initData() {

        initVierPager();
        initTabLayout();
    }

    private void initVierPager() {

        fragments = new ArrayList<>();
        fragments.add(new DiaryFragment());
        fragments.add(new DuanZiFragment());
        fragments.add(new WallpaperFragment());
        mainViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vp_main.setOffscreenPageLimit(4);
        vp_main.setAdapter(mainViewPagerAdapter);
    }

    private void initTabLayout() {
        mainTitles = getResources().getStringArray(R.array.mainTitles);
        mainIcons = CommonUtil.getArrayId(getResources().obtainTypedArray(R.array.mainIcons));
        mainSelectedIcons = CommonUtil.getArrayId(getResources().obtainTypedArray(R.array.mainSelectedIcons));

        mTabTitles = CommonTabLayoutUtil.initTabTitles(mainTitles, mainIcons, mainSelectedIcons);
        CommonTabLayoutUtil.setOnTabSelectListener(tl_main, vp_main);
        CommonUtil.addOnPageChangeListener(vp_main, this);
        tl_main.setTabData(mTabTitles);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {
        } else if (id == R.id.nav_share) {
        } else if (id == R.id.nav_send) {
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onSelect(int position) {
        tl_main.setCurrentTab(position);
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if ((currentTime - exitTime) < 2000) {
            super.onBackPressed();
        } else {
            Snackbar.make(drawerLayout, getString(R.string.double_click_exit), Snackbar.LENGTH_SHORT).show();
            exitTime = currentTime;
        }
    }
}
