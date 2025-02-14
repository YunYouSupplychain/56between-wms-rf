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
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerMainComponent;
import com.yunyou.androidwmsrf.di.module.MainModule;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.mvp.contract.MainContract;
import com.yunyou.androidwmsrf.mvp.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 首页
 *
 * @author WMJ
 * @version 2019/06/29
 */
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.toolbar_right_text)
    TextView toolbarRightText;

    // 声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMainComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
//        setContentView(R.layout.activity_main);
//        LinearLayout mLl_parent = findViewById(R.id.llyt_parent);
//
//        for (int i = 0; i < 3; i++) {
//            LinearLayout view = getHeaderLinear();
//            view.addView(getOneMenu());
//            view.addView(getVLine());
//            view.addView(getOneMenu());
//            mLl_parent.addView(view);
//            mLl_parent.addView(getHLine());
//        }
//
//        return 0;
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    private LinearLayout getHeaderLinear() {
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0, 1));
        view.setOrientation(LinearLayout.HORIZONTAL);
        return view;
    }

    private LinearLayout getOneMenu() {
        LinearLayout receipt = new LinearLayout(this);
        receipt.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1));//设置布局参数
        receipt.setBackground(this.getResources().getDrawable(R.drawable.bg_translucent_selector));
        receipt.setGravity(Gravity.CENTER);
        receipt.setOrientation(LinearLayout.VERTICAL);
        receipt.setOnClickListener((View view) -> {
            launchActivity(new Intent(MainActivity.this, ReceiptActivity.class));
        });

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(dip2px(this, 60), dip2px(this, 60)));
        imageView.setImageResource(R.mipmap.ic_launcher);

        TextView textView = new TextView(this);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT);
        params.topMargin = dip2px(this, 2);
        textView.setLayoutParams(params);
        textView.setText("收货");
        textView.setTextSize(16);

        receipt.addView(imageView);
        receipt.addView(textView);

        return receipt;
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
//        toolbarRightText = this.findViewById(R.id.toolbar_right_text);
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
        launchActivity(new Intent(MainActivity.this, LoginActivity.class));
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

    @OnClick({R.id.llyt_receipt, R.id.llyt_putaway, R.id.llyt_picking, R.id.llyt_ship, R.id.llyt_move, R.id.llyt_replenishment, R.id.llyt_check, R.id.llyt_inventory_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 收货
            case R.id.llyt_receipt:
                launchActivity(new Intent(MainActivity.this, ReceiptActivity.class));
                break;
            // 上架
            case R.id.llyt_putaway:
                launchActivity(new Intent(MainActivity.this, PutawayActivity.class));
                break;
            // 拣货
            case R.id.llyt_picking:
                launchActivity(new Intent(MainActivity.this, PickingActivity.class));
                break;
            // 发货
            case R.id.llyt_ship:
                launchActivity(new Intent(MainActivity.this, ShipmentActivity.class));
                break;
            // 移动
            case R.id.llyt_move:
                launchActivity(new Intent(MainActivity.this, MoveActivity.class));
                break;
            // 补货
            case R.id.llyt_replenishment:
                launchActivity(new Intent(MainActivity.this, ReplenishmentActivity.class));
                break;
            // 盘点
            case R.id.llyt_check:
                launchActivity(new Intent(MainActivity.this, CheckActivity.class));
                break;

            // 库存查询
            case R.id.llyt_inventory_query:
                launchActivity(new Intent(MainActivity.this, InventoryQueryActivity.class));
                break;
        }
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