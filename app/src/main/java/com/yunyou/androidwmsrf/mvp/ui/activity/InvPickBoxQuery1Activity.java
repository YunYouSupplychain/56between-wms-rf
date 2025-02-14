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
import com.yunyou.androidwmsrf.di.component.DaggerInvPickBoxQuery1Component;
import com.yunyou.androidwmsrf.di.module.InvPickBoxQuery1Module;
import com.yunyou.androidwmsrf.mvp.contract.InvPickBoxQuery1Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.PickBoxHeaderInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickBoxHeaderRequest;
import com.yunyou.androidwmsrf.mvp.presenter.InvPickBoxQuery1Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 拣货箱查询1
 *
 * @author WMJ
 * @version 2019/07/17
 */
public class InvPickBoxQuery1Activity extends BaseActivity<InvPickBoxQuery1Presenter> implements InvPickBoxQuery1Contract.View, IToolbar {
    @BindView(R.id.et_pick_box_no)
    EditText etPickBoxNo;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mPickBoxNo;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerInvPickBoxQuery1Component
                .builder()
                .appComponent(appComponent)
                .invPickBoxQuery1Module(new InvPickBoxQuery1Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_inv_pick_box_query1;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        etPickBoxNo.requestFocus();
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
        mPickBoxNo = etPickBoxNo.getText().toString().trim();
        if (TextUtils.isEmpty(mPickBoxNo)) {
            showMessage(getResources().getString(R.string.please_input_or_scan_pick_box_no));
            AppLifecyclesImpl.playSound();
            return;
        }

        mPresenter.queryPickBoxHeader(new QueryPickBoxHeaderRequest(null, mPickBoxNo));
    }

    @Override
    public void goToInvPickBoxQuery2Activity(PickBoxHeaderInfo info) {
        Intent intent = new Intent(InvPickBoxQuery1Activity.this, InvPickBoxQuery2Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_INV_PICK_BOX_HEADER_INFO, info);
        intent.putExtra(Constants.INTENT_PARAMETER_INV_PICK_BOX_QUERY_ID, mPickBoxNo);
        launchActivity(intent);
    }
}