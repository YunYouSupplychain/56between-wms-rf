package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerInvQuery1Component;
import com.yunyou.androidwmsrf.di.module.InvQuery1Module;
import com.yunyou.androidwmsrf.mvp.contract.InvQuery1Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.SkuInvDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QuerySkuInvRequest;
import com.yunyou.androidwmsrf.mvp.presenter.InvQuery1Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 库存查询1
 *
 * @author WMJ
 * @version 2019/07/05
 */
public class InvQuery1Activity extends BaseActivity<InvQuery1Presenter> implements InvQuery1Contract.View, IToolbar {
    @BindView(R.id.et_sku_code)
    EditText etSkuCode;

    @BindView(R.id.et_loc_code)
    EditText etLocCode;

    @BindView(R.id.et_trace_id)
    EditText etTraceId;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mSkuCode;
    private String mLocCode;
    private String mTraceId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerInvQuery1Component
                .builder()
                .appComponent(appComponent)
                .invQuery1Module(new InvQuery1Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_inv_query1;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
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

    @OnClick({R.id.btn_confirm, R.id.btn_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                callConfirmEvent();
                break;

            case R.id.btn_cancel:
                killMyself();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void callConfirmEvent() {
        mSkuCode = etSkuCode.getText().toString().trim();
        mLocCode = etLocCode.getText().toString().trim();
        mTraceId = etTraceId.getText().toString().trim();
        if (TextUtils.isEmpty(mSkuCode) && TextUtils.isEmpty(mLocCode) && TextUtils.isEmpty(mTraceId)) {
            showMessage(getResources().getString(R.string.please_input_sku_code_or_loc_code_or_trace_id));
            AppLifecyclesImpl.playSound();
            return;
        }

        mPresenter.querySkuInv(new QuerySkuInvRequest(mSkuCode, mTraceId, mLocCode, null));
    }

    @Override
    public void goToInvQuery2Activity(SkuInvDetailInfo info) {
        Intent intent = new Intent(InvQuery1Activity.this, InvQuery2Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_INV_SKU_INFO, info);
        launchActivity(intent);
    }
}