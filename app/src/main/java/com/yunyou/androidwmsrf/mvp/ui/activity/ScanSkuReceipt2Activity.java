package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerScanSkuReceipt2Component;
import com.yunyou.androidwmsrf.di.module.ScanSkuReceipt2Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceipt2Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.AsnDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryAsnDetailByTraceIdOrSkuRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanSkuReceipt2Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 扫描商品收货2
 *
 * @author WMJ
 * @version 2019/06/29
 */
public class ScanSkuReceipt2Activity extends BaseActivity<ScanSkuReceipt2Presenter> implements ScanSkuReceipt2Contract.View, IToolbar {
    @BindView(R.id.tv_asn_no)
    TextView tvAsnNo;

    @BindView(R.id.et_sku_code)
    EditText etSkuCode;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mAsnNo;
    private String mSkuCode;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanSkuReceipt2Component
                .builder()
                .appComponent(appComponent)
                .scanSkuReceipt2Module(new ScanSkuReceipt2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_sku_receipt2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mAsnNo = getIntent().getStringExtra(Constants.INTENT_PARAMETER_ASN);
        tvAsnNo.setText(mAsnNo);
        initSkuCodeInput();
        etSkuCode.requestFocus();
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

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.btn_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 取消
            case R.id.btn_cancel:
                killMyself();
                break;

            // 确认
            case R.id.btn_confirm:
                callConfirmEvent();
                break;

            // 选择商品
            case R.id.btn_select:
                callSelectEvent();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void initSkuCodeInput() {
        etSkuCode.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                callConfirmEvent();
            }
            return false;
        });
    }

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        mSkuCode = etSkuCode.getText().toString().trim();
        if (TextUtils.isEmpty(mSkuCode)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_sku_code));
            etSkuCode.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }

        mPresenter.queryAsnDetailByTraceIdOrSku(new QueryAsnDetailByTraceIdOrSkuRequest(mAsnNo, "2", "", mSkuCode));
    }

    private void callSelectEvent() {
        mPresenter.selectSkuInfo(new QueryAsnDetailByTraceIdOrSkuRequest(mAsnNo, "2", "", ""));
    }

    @Override
    public void afterSelectSkuInfo(AsnDetailInfo info) {
        Intent intent = new Intent(ScanSkuReceipt2Activity.this, ScanSkuReceiptSkuInfoActivity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_ASN_DETAIL, info);
        launchActivity(intent);
    }

    @Override
    public void afterQueryBySku(AsnDetailInfo info) {
        Intent intent = new Intent(ScanSkuReceipt2Activity.this, ScanSkuReceipt3Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_ASN_DETAIL, info.getDetailEntityList().get(0));
        launchActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScanSkuReceipt2Activity.this, ScanSkuReceipt1Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_ASN, mAsnNo);
        launchActivity(intent);
    }

}