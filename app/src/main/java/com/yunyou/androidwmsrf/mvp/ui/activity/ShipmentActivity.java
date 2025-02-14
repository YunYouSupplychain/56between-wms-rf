package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerShipmentComponent;
import com.yunyou.androidwmsrf.di.module.ShipmentModule;
import com.yunyou.androidwmsrf.mvp.contract.ShipmentContract;
import com.yunyou.androidwmsrf.mvp.presenter.ShipmentPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 发货
 *
 * @author WMJ
 * @version 2019/07/03
 */
public class ShipmentActivity extends BaseActivity<ShipmentPresenter> implements ShipmentContract.View, IToolbar {
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerShipmentComponent
                .builder()
                .appComponent(appComponent)
                .shipmentModule(new ShipmentModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_shipment;
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

    @OnClick({R.id.btn_shipment_by_trace_id, R.id.btn_shipment_by_order, R.id.btn_shipment_by_face_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 按箱发货
            case R.id.btn_shipment_by_trace_id:
                goToShipmentByTraceIdActivity();
                break;

            // 按单发货
            case R.id.btn_shipment_by_order:
                goToShipmentByOrderActivity();
                break;

            // 按面单发货
            case R.id.btn_shipment_by_face_no:
                goToShipmentByTrackingNoActivity();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    @Override
    public void goToShipmentByTraceIdActivity() {
        launchActivity(new Intent(ShipmentActivity.this, ShipmentByTraceIdActivity.class));
    }

    @Override
    public void goToShipmentByOrderActivity() {
        launchActivity(new Intent(ShipmentActivity.this, ShipmentByOrderActivity.class));
    }

    @Override
    public void goToShipmentByTrackingNoActivity() {
        launchActivity(new Intent(ShipmentActivity.this, ShipmentByTrackingNoActivity.class));
    }
}