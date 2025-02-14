package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.yunyou.androidwmsrf.di.component.DaggerGetFrameUnBindComponent;
import com.yunyou.androidwmsrf.di.module.GetFrameUnBindModule;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import com.yunyou.androidwmsrf.mvp.contract.GetFrameUnBindContract;
import com.yunyou.androidwmsrf.mvp.presenter.GetFrameUnBindPresenter;

import com.yunyou.androidwmsrf.R;


import static com.jess.arms.utils.Preconditions.checkNotNull;

public class GetFrameUnBindActivity extends BaseActivity<GetFrameUnBindPresenter> implements GetFrameUnBindContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerGetFrameUnBindComponent
                .builder()
                .appComponent(appComponent)
                .getFrameUnBindModule(new GetFrameUnBindModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_get_frame_un_bind; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

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
        ArmsUtils.snackbarText(message);
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
}