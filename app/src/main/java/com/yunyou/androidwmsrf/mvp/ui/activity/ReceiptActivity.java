package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerReceiptComponent;
import com.yunyou.androidwmsrf.di.module.ReceiptModule;
import com.yunyou.androidwmsrf.mvp.contract.ReceiptContract;
import com.yunyou.androidwmsrf.mvp.presenter.ReceiptPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 收货
 *
 * @author WMJ
 * @version 2019/06/29
 */
public class ReceiptActivity extends BaseActivity<ReceiptPresenter> implements ReceiptContract.View, IToolbar {
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerReceiptComponent
                .builder()
                .appComponent(appComponent)
                .receiptModule(new ReceiptModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_receipt;
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

    @OnClick({R.id.btn_scan_tray_receipt, R.id.btn_scan_sku_receipt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 扫描托盘收货
            case R.id.btn_scan_tray_receipt:
                goToScanTrayReceipt1Activity();
                break;

            // 扫描商品收货
            case R.id.btn_scan_sku_receipt:
                goToScanSkuReceipt1Activity();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    @Override
    public void goToScanTrayReceipt1Activity() {
        launchActivity(new Intent(ReceiptActivity.this, ScanTrayReceipt1Activity.class));
    }

    @Override
    public void goToScanSkuReceipt1Activity() {
        launchActivity(new Intent(ReceiptActivity.this, ScanSkuReceipt1Activity.class));
    }

    @Override
    public void onBackPressed() {
        launchActivity(new Intent(ReceiptActivity.this, MainActivity.class));
    }
}