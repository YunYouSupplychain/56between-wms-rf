package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerPickingComponent;
import com.yunyou.androidwmsrf.di.module.PickingModule;
import com.yunyou.androidwmsrf.mvp.contract.PickingContract;
import com.yunyou.androidwmsrf.mvp.presenter.PickingPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 拣货
 *
 * @author WMJ
 * @version 2019/07/02
 */
public class PickingActivity extends BaseActivity<PickingPresenter> implements PickingContract.View, IToolbar {
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerPickingComponent
                .builder()
                .appComponent(appComponent)
                .pickingModule(new PickingModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_picking;
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

    @OnClick({R.id.btn_picking_by_trace_id, R.id.btn_picking_by_order, R.id.btn_picking_by_wave, R.id.btn_picking_by_manual})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 标签拣货
            case R.id.btn_picking_by_trace_id:
                goToPickingByTraceIdActivity();
                break;

            // 按单拣货
            case R.id.btn_picking_by_order:
                goToPickingByOrder1Activity();
                break;

            // 波次拣货
            case R.id.btn_picking_by_wave:
                goToPickingByWave1Activity();
                break;

            // 手工拣货
            case R.id.btn_picking_by_manual:
                goToPickingByManual1Activity();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    @Override
    public void goToPickingByTraceIdActivity() {
        launchActivity(new Intent(PickingActivity.this, PickingByTraceIdActivity.class));
    }

    @Override
    public void goToPickingByOrder1Activity() {
        launchActivity(new Intent(PickingActivity.this, PickingByOrder1Activity.class));
    }

    @Override
    public void goToPickingByWave1Activity() {
        launchActivity(new Intent(PickingActivity.this, PickingByWave1Activity.class));
    }

    @Override
    public void goToPickingByManual1Activity() {
        launchActivity(new Intent(PickingActivity.this, PickingByManual1Activity.class));
    }

    @Override
    public void onBackPressed() {
        launchActivity(new Intent(PickingActivity.this, MainActivity.class));
    }

}