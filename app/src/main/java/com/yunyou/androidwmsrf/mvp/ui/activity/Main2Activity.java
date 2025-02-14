package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerMainComponent;
import com.yunyou.androidwmsrf.di.module.MainModule;
import com.yunyou.androidwmsrf.mvp.contract.MainContract;
import com.yunyou.androidwmsrf.mvp.presenter.MainPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 首页
 *
 * @author WMJ
 * @version 2019/06/29
 */
public class Main2Activity extends BaseActivity<MainPresenter> implements MainContract.View {
    TextView toolbarRightText;

    // 声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;
    private final String[] menuList = {"收货", "上架", "拣货", "发货", "移动", "补货", "盘点", "库存查询"};

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject2(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main2);
        LinearLayout mLl_parent = findViewById(R.id.llyt_parent);

        LinearLayout view = getHeaderLinear();
        for (int j = 0; j < menuList.length; j++) {
            if ((j + 1) % 2 == 1) {
                view = getHeaderLinear();
                mLl_parent.addView(view);
            }
            view.addView(getOneMenu(menuList[j]));
        }

        return 0;
    }

    private LinearLayout getHeaderLinear() {
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        view.setOrientation(LinearLayout.HORIZONTAL);
        return view;
    }

    private LinearLayout getOneMenu(String name) {
        LinearLayout receipt = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
        lp.topMargin = dip2px(this, 20);
        receipt.setLayoutParams(lp);//设置布局参数
        receipt.setBackground(this.getResources().getDrawable(R.drawable.bg_translucent_selector));
        receipt.setGravity(Gravity.CENTER);
        receipt.setOrientation(LinearLayout.VERTICAL);
        receipt.setOnClickListener((View view) -> {
            launchActivity(new Intent(Main2Activity.this, getActivity(name)));
        });

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(dip2px(this, 60), dip2px(this, 60)));
        imageView.setImageResource(R.mipmap.ic_launcher);

        TextView textView = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.topMargin = dip2px(this, 10);
        textView.setLayoutParams(params);
        textView.setText(name);
        textView.setTextSize(16);

        receipt.addView(imageView);
        receipt.addView(textView);

        return receipt;
    }

    private Class getActivity(String name) {
        switch (name) {
            case "收货":
                return ReceiptActivity.class;
            case "上架":
                return PutawayActivity.class;
            case "拣货":
                return PickingActivity.class;
            case "发货":
                return ShipmentActivity.class;
            case "移动":
                return MoveActivity.class;
            case "补货":
                return ReplenishmentActivity.class;
            case "盘点":
                return CheckActivity.class;
            case "库存查询":
                return InventoryQueryActivity.class;
        }
        return null;
    }

    private TextView getVLine() {
        TextView widView = new TextView(this);
        widView.setLayoutParams(new ViewGroup.LayoutParams(dip2px(this, 1), ViewGroup.LayoutParams.MATCH_PARENT));
        widView.setBackground(this.getResources().getDrawable(R.color.divider));
        return widView;
    }

    private TextView getHLine() {
        TextView widView = new TextView(this);
        widView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(this, 1)));
        widView.setBackground(this.getResources().getDrawable(R.color.divider));
        return widView;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initToolbarRightText();
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initToolbarRightText() {
        toolbarRightText = this.findViewById(R.id.toolbar_right_text);
        toolbarRightText.setVisibility(View.VISIBLE);

        toolbarRightText.setText(R.string.logout);
        toolbarRightText.setOnClickListener((e) -> {
            String userName = SPUtils.getInstance().getString(Constants.USER_NAME);
            mPresenter.logout(userName);
        });
    }

    @Override
    public void afterLogout() {
        SPUtils.getInstance().put(Constants.USER_SESSION_ID, Constants.USER_ID_DEFAULT_VALUE);
        launchActivity(new Intent(Main2Activity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.makeText(this, message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                // 大于2000ms则认为是误操作，使用Toast进行提示
                ToastUtils.showShort(R.string.press_again_to_quit);
                // 并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                // 小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}