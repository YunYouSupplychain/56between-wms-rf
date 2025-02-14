package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerPutawayComponent;
import com.yunyou.androidwmsrf.di.module.PutawayModule;
import com.yunyou.androidwmsrf.mvp.contract.PutawayContract;
import com.yunyou.androidwmsrf.mvp.presenter.PutawayPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 上架
 *
 * @author WMJ
 * @version 2019/07/02
 */
public class PutawayActivity extends BaseActivity<PutawayPresenter> implements PutawayContract.View, IToolbar {
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerPutawayComponent
                .builder()
                .appComponent(appComponent)
                .putawayModule(new PutawayModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_putaway;
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

    @OnClick({R.id.btn_scan_tray_putaway, R.id.btn_scan_task_putaway, R.id.btn_scan_order_putaway})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 扫描托盘上架
            case R.id.btn_scan_tray_putaway:
                goToScanTrayPutaway1Activity();
                break;

            // 扫描任务上架
            case R.id.btn_scan_task_putaway:
                goToScanTaskPutaway1Activity();
                break;

            // 扫描订单上架
            case R.id.btn_scan_order_putaway:
                goToScanOrderPutaway1Activity();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    @Override
    public void goToScanTrayPutaway1Activity() {
        launchActivity(new Intent(PutawayActivity.this, ScanTrayPutaway1Activity.class));
    }

    @Override
    public void goToScanTaskPutaway1Activity() {
        launchActivity(new Intent(PutawayActivity.this, ScanTaskPutaway1Activity.class));
    }

    @Override
    public void goToScanOrderPutaway1Activity() {
        launchActivity(new Intent(PutawayActivity.this, ScanOrderPutaway1Activity.class));
    }

    @Override
    public void onBackPressed() {
        launchActivity(new Intent(PutawayActivity.this, MainActivity.class));
    }
}