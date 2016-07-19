package com.yifan.jotting2.base;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.ViewStubCompat;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.yifan.jotting2.R;
import com.yifan.jotting2.base.impl.PagerImpl;
import com.yifan.jotting2.base.impl.TitleImpl;
import com.yifan.jotting2.widget.LoadingView;
import com.yifan.jotting2.widget.TitleBar;

/**
 * TitleBarActivity 封装了TitleBar的Acitity，主要负责提供了封装的TitleBar及与其相关的界面处理，其他功能函数应放在{@link BaseActivity}中实现
 * <p/>
 * Created by yifan on 2016/7/18.
 */
public abstract class TitleBarActivity extends BaseActivity implements TitleImpl {

    /**
     * 包含TitleBar的根布局
     */
    private ViewGroup mRootView;

    /**
     * 加载视图的布局
     */
    private ViewStubCompat mContentLayout;

    /**
     * 拓展布局
     */
    private ViewStubCompat mExpandStub;

    /**
     * ToolBar联动布局
     */
    private AppBarLayout mAppBarLayout;

    /**
     * 主布局
     */
    private View mContentView;

    /**
     * TitleBar控件
     */
    private TitleBar mTitleBar;

    /**
     * 正在加载布局
     */
    private LoadingView mLoadingView;

    /**
     * ToolBar是否联动
     */
    private boolean isToolBarLinkage;

    /**
     * 主题资源ID
     */
    private int mStyleID;

    @Override
    public void setContentView(View v, ViewGroup.LayoutParams params) {
        super.setContentView(initContentView(v, 0, 0, false), params);
    }

    @Override
    public void setContentView(int id) {
        setContentView(id, 0, false);
    }

    @Override
    public void setContentView(View v) {
        setContentView(v, 0, false);
    }

    /**
     * 加载布局
     *
     * @param id               布局ID
     * @param isToolBarLinkage ToolBar是否联动
     */
    public void setContentView(int id, int styleID, boolean isToolBarLinkage) {
        super.setContentView(initContentView(null, id, styleID, isToolBarLinkage));
    }

    public void setContentView(View v, int styleID, boolean isToolBarLinkage) {
        super.setContentView(initContentView(v, 0, styleID, isToolBarLinkage));
    }

    /**
     * 初始化布局
     *
     * @param v
     * @param styleID
     * @param isToolBarLinkage
     * @return
     */
    private View initContentView(View v, int layoutID, int styleID, boolean isToolBarLinkage) {
        //初始化布局加载适配器
        LayoutInflater inflater = getLayoutInflater();
        //判断当前Fragment是否需要改变主题,即改变Titlebar颜色主题
        if (styleID > 0) {
            mStyleID = styleID;
            // 构建新主题的Context上下文环境对象
            Context themeContext = new ContextThemeWrapper(getApplicationContext(), styleID);
            // 通过context构建新的inflater
            // 再通过应用新主题的inflater加载视图
            inflater = inflater.cloneInContext(themeContext);
        }
        //判断是否联动TitleBar
        if (isToolBarLinkage) {
            if (hasNavigationView()) {
                mRootView = (DrawerLayout) inflater.inflate(R.layout.activity_navigation, null);
                mRootView.addView(inflater.inflate(R.layout.activity_titlebar_linkage, null), 0);
            } else {
                mRootView = (ViewGroup) inflater.inflate(R.layout.activity_titlebar_linkage, null);
            }
            mAppBarLayout = (AppBarLayout) mRootView.findViewById(R.id.abl_toolbar_activity);
            mExpandStub = (ViewStubCompat) mRootView.findViewById(R.id.vsc_toolbar_activity);
        } else {//非联动
            if (hasNavigationView()) {
                mRootView = (DrawerLayout) inflater.inflate(R.layout.activity_navigation, null);
                mRootView.addView(inflater.inflate(R.layout.activity_titlebar, null), 0);
            } else {
                mRootView = (ViewGroup) inflater.inflate(R.layout.activity_titlebar, null);
            }
        }
        //初始化TitleBar
        mTitleBar = (TitleBar) mRootView.findViewById(R.id.titlebar_toolbar_activity);
        setSupportActionBar(mTitleBar);
        //主布局容器
        mContentLayout = (ViewStubCompat) mRootView.findViewById(R.id.vsc_toolbar_activity_content);
        //初始化并添加加载提示布局
        mLoadingView = (LoadingView) mRootView.findViewById(R.id.loadingview_toolbar_activity);
        //矫正TitleBar联动时，LoadingView的居中问题
        if (isToolBarLinkage) {
            mLoadingView.offsetMarginTop();
        }
        //加载主布局
        //判断是否映射解析布局文件，还是直接添加布局
        if (layoutID > 0) {
            //解析主布局
            mContentLayout.setLayoutInflater(inflater);
            mContentLayout.setLayoutResource(layoutID);
            mContentView = mContentLayout.inflate();
        } else if (v != null) {
            //直接添加布局
            mContentView = v;
            if (isToolBarLinkage) {
                mRootView.addView(mContentView, 0);
            } else {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.BELOW, mTitleBar.getId());
                mRootView.addView(mContentView, 2, layoutParams);
            }
        }
        return mRootView;
    }

    /**
     * 获取TitleBar对象
     *
     * @return
     */
    @Override
    public TitleBar getSupportTitleBar() {
        return mTitleBar;
    }

    /**
     * 获取拓展布局ViewStubCompat，可能为空，非联动布局下为空
     *
     * @return @nullable ViewStubCompat
     */
    @Override
    public ViewStubCompat getBarExpandStub() {
        return mExpandStub;
    }

    /**
     * 获取AppBar高度
     *
     * @return
     */
    @Override
    public int getAppBarHeight() {
        if (mAppBarLayout != null) {
            return mAppBarLayout.getHeight();
        } else if (mTitleBar != null) {
            return mTitleBar.getHeight();
        }
        return 0;
    }

    /**
     * 获取AppBarLayout ,未联动情况下获取为空
     *
     * @return
     */
    @Override
    public AppBarLayout getAppBarLayout() {
        return mAppBarLayout;
    }

    /**
     * 获取包含抽屉的布局
     *
     * @return
     */
    public DrawerLayout getDrawerLayout() {
        return (DrawerLayout) findViewById(R.id.drawer_layout);
    }

    /**
     * 获取抽屉控件
     *
     * @return
     */
    public NavigationView getNavigationView() {
        return (NavigationView) findViewById(R.id.nav_view);
    }

    /**
     * 获取根布局
     *
     * @return
     */
    @Override
    public View getContentView() {
        return mRootView;
    }


    @Override
    public void createLoadingdialog() {
        if (isActived()) {
            createLoadingdialog(getString(R.string.loading));
        }
    }

    @Override
    public void createLoadingdialog(String message) {
        createLoadingdialog(message, true, true);
    }

    @Override
    public void createLoadingdialog(String message, boolean canelable, boolean touchOutsideCancelable) {
        if (mLoadingView != null && !mLoadingView.isShowing()) {
            mLoadingView.setMessage(message);
            mLoadingView.show();
        }
    }

    @Override
    public void dissmissLoadingDialog() {
        if (mLoadingView != null && mLoadingView.isShowing()) {
            mContentView.setVisibility(View.VISIBLE);
            mLoadingView.hide();
        }
    }

    @Override
    public abstract String getTAG();

    /**
     * 是否具有抽屉
     *
     * @return
     */
    public boolean hasNavigationView() {
        return false;
    }

}
