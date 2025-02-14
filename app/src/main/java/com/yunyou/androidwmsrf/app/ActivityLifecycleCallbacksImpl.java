package com.yunyou.androidwmsrf.app;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.yunyou.androidwmsrf.R;

import timber.log.Timber;

/**
 * 展示 {@link Application.ActivityLifecycleCallbacks} 的用法
 */
public class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Timber.w("%s - onActivityCreated", activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Timber.w("%s - onActivityStarted", activity);
        if (!activity.getIntent().getBooleanExtra("isInitToolbar", false)) {
            // 由于加强框架的兼容性,故将 setContentView 放到 onActivityCreated 之后,onActivityStarted 之前执行
            // 而 findViewById 必须在 Activity setContentView() 后才有效,所以将以下代码从之前的 onActivityCreated 中移动到 onActivityStarted 中执行
            activity.getIntent().putExtra("isInitToolbar", true);
            // 这里全局给Activity设置toolbar和title,你想象力有多丰富,这里就有多强大,以前放到BaseActivity的操作都可以放到这里
            if (activity.findViewById(R.id.toolbar) != null) {
                if (activity instanceof AppCompatActivity) {
                    // 设置Toolbar
                    ((AppCompatActivity) activity).setSupportActionBar((Toolbar) activity.findViewById(R.id.toolbar));
                    // 隐藏标题栏
                    ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
                    if ((activity instanceof IToolbar) && ((IToolbar) activity).isShowBackButton()) {
                        // 显示返回按钮
                        if (activity.findViewById(R.id.toolbar_back) != null) {
                            activity.findViewById(R.id.toolbar_back).setVisibility(View.VISIBLE);
                        }
                    } else {
                        // 隐藏返回按钮
                        if (activity.findViewById(R.id.toolbar_back) != null) {
                            activity.findViewById(R.id.toolbar_back).setVisibility(View.GONE);
                        }
                    }
                } else {
                    // 设置Toolbar
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        // 设置Toolbar
                        activity.setActionBar((android.widget.Toolbar) activity.findViewById(R.id.toolbar));
                        // 隐藏标题栏
                        activity.getActionBar().setDisplayShowTitleEnabled(false);
                        if ((activity instanceof IToolbar) && ((IToolbar) activity).isShowBackButton()) {
                            // 显示返回按钮
                            if (activity.findViewById(R.id.toolbar_back) != null) {
                                activity.findViewById(R.id.toolbar_back).setVisibility(View.VISIBLE);
                            }
                        } else {
                            // 隐藏返回按钮
                            if (activity.findViewById(R.id.toolbar_back) != null) {
                                activity.findViewById(R.id.toolbar_back).setVisibility(View.GONE);
                            }
                        }
                    }
                }
            }

            // 找到 Toolbar 的标题栏并设置标题名
            if (activity.findViewById(R.id.toolbar_title) != null) {
                ((TextView) activity.findViewById(R.id.toolbar_title)).setText(activity.getTitle());
            }

            // 找到 Toolbar 的返回按钮,并且设置点击事件,点击关闭这个 Activity
            if (activity.findViewById(R.id.toolbar_back) != null) {
                activity.findViewById(R.id.toolbar_back).setOnClickListener(v -> {
                    activity.onBackPressed();
                });
            }
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Timber.w("%s - onActivityResumed", activity);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Timber.w("%s - onActivityPaused", activity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Timber.w("%s - onActivityStopped", activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Timber.w("%s - onActivitySaveInstanceState", activity);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Timber.w("%s - onActivityDestroyed", activity);
        // 横竖屏切换或配置改变时, Activity 会被重新创建实例, 但 Bundle 中的基础数据会被保存下来,移除该数据是为了保证重新创建的实例可以正常工作
        activity.getIntent().removeExtra("isInitToolbar");
    }
}