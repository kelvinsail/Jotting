package com.yifan.jotting2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yifan.jotting2.base.BaseActivity;
import com.yifan.jotting2.base.BaseFragment;
import com.yifan.jotting2.ui.FilesManagerFragment;
import com.yifan.jotting2.ui.projects.ProjectsFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "MainActivity";

    /**
     * Intent操作key
     */
    public static final String INTENT_MAIN_ACTION = "intent_main_ation";
    /**
     * Intent操作,退出App
     */
    public static final int INTENT_MAIN_ACTION_EXIT = 1;
    /**
     * Intent操作,返回首页
     */
    public static final int INTENT_MAIN_ACTION_PROJECTS = 2;
    /**
     * Intent操作,跳转到文件列表页面
     */
    public static final int INTENT_MAIN_ACTION_FILES = 3;
    /**
     * 导航栏
     */
    public Toolbar mToolBar;
    /**
     * 包含抽屉菜单的整体布局
     */
    private DrawerLayout mDrawerLayout;

    /**
     * Fragment事务
     */
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switchFragment(INTENT_MAIN_ACTION_PROJECTS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != mFragmentTransaction) {
            mFragmentTransaction.commit();
            mFragmentTransaction = null;
        }
    }

    @Override
    public String getTAG() {
        return TAG;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            mToolBar.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 100);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_menu_prijects:
                switchFragment(INTENT_MAIN_ACTION_PROJECTS);
                break;
            case R.id.nav_main_file_manager:
                switchFragment(INTENT_MAIN_ACTION_FILES);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode, @Nullable Bundle options) {
        if (!checkFromIntent(intent)) {
            super.startActivityFromFragment(fragment, intent, requestCode, options);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        checkFromIntent(intent);
    }

    /**
     * 处理来自Intent的页面跳转事件
     *
     * @param intent
     * @return
     */
    private boolean checkFromIntent(Intent intent) {
        //接收并判断主界面操作
        if (null != intent && null != intent.getExtras()) {
            Bundle data = intent.getExtras();
            int position = data.getInt(INTENT_MAIN_ACTION, -111);
            switch (position) {
                case INTENT_MAIN_ACTION_EXIT://退出APP
                    finish();
                    return true;
                case INTENT_MAIN_ACTION_PROJECTS://返回首页

                    return true;
                default:
                    break;
            }
        }
        return false;
    }

    /**
     * 判断Fragment事务需要延迟提交还是立即执行
     *
     * @param ft
     */
    private void startFragment(FragmentTransaction ft) {
        if (isActived()) {
            ft.commit();
        } else {
            mFragmentTransaction = ft;
        }
    }

    /**
     * 切换Fragment
     */
    public void switchFragment(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        BaseFragment baseFragment = null;
        Fragment fragment = null;
        switch (position) {
            case INTENT_MAIN_ACTION_PROJECTS:
                fragment = getSupportFragmentManager().findFragmentByTag(ProjectsFragment.TAG);
                if (null == fragment || (null != fragment && !(fragment instanceof ProjectsFragment))) {
                    fragment = ProjectsFragment.newInstance();
                }
                baseFragment = (BaseFragment) fragment;
                break;
            case INTENT_MAIN_ACTION_FILES:
                fragment = getSupportFragmentManager().findFragmentByTag(FilesManagerFragment.TAG);
                if (null == fragment || (null != fragment && !(fragment instanceof FilesManagerFragment))) {
                    fragment = FilesManagerFragment.newInstance();
                }
                baseFragment = (BaseFragment) fragment;
                break;
        }
        if (baseFragment.isAdded()) {
            if (baseFragment.isActived()) {
                // isShowing,do nothing
            } else {
                ft.show(baseFragment);
            }
        } else {
            ft.replace(R.id.layout_main_content, baseFragment, baseFragment.getTAG());
        }
        mToolBar.setTitle(baseFragment.getTitleName());
        ft.addToBackStack(baseFragment.getTAG());
        startFragment(ft);
    }
}
