package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerReplenishmentComponent;
import com.yunyou.androidwmsrf.di.module.ReplenishmentModule;
import com.yunyou.androidwmsrf.mvp.contract.ReplenishmentContract;
import com.yunyou.androidwmsrf.mvp.presenter.ReplenishmentPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 补货
 *
 * @author WMJ
 * @version 2019/07/04
 */
public class ReplenishmentActivity extends BaseActivity<ReplenishmentPresenter> implements ReplenishmentContract.View, IToolbar {
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerReplenishmentComponent
                .builder()
                .appComponent(appComponent)
                .replenishmentModule(new ReplenishmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_replenishment;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void showLoading() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressbar.setVisibility(View.GONE);
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

    @OnClick({R.id.btn_scan_rp_task})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 标准移动
            case R.id.btn_scan_rp_task:
                goToScanRpTaskActivity();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    @Override
    public void goToScanRpTaskActivity() {
        launchActivity(new Intent(ReplenishmentActivity.this, ScanRpTask1Activity.class));
    }

    @Override
    public void onBackPressed() {
        launchActivity(new Intent(ReplenishmentActivity.this, MainActivity.class));
    }
}