package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerInvLotAttComponent;
import com.yunyou.androidwmsrf.di.module.InvLotAttModule;
import com.yunyou.androidwmsrf.mvp.contract.InvLotAttContract;
import com.yunyou.androidwmsrf.mvp.model.entity.SkuInvDetailInfo;
import com.yunyou.androidwmsrf.mvp.presenter.InvLotAttPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 库存批次信息
 *
 * @author WMJ
 * @version 2019/07/11
 */
public class InvLotAttActivity extends BaseActivity<InvLotAttPresenter> implements InvLotAttContract.View, IToolbar {
    @BindView(R.id.tv_lot_att01)
    TextView tvLotAtt01;

    @BindView(R.id.tv_lot_att02)
    TextView tvLotAtt02;

    @BindView(R.id.tv_lot_att03)
    TextView tvLotAtt03;

    @BindView(R.id.tv_lot_att04)
    TextView tvLotAtt04;

    @BindView(R.id.tv_lot_att05)
    TextView tvLotAtt05;

    @BindView(R.id.tv_lot_att06)
    TextView tvLotAtt06;

    @BindView(R.id.tv_lot_att07)
    TextView tvLotAtt07;

    @BindView(R.id.tv_lot_att08)
    TextView tvLotAtt08;

    @BindView(R.id.tv_lot_att09)
    TextView tvLotAtt09;

    @BindView(R.id.tv_lot_att10)
    TextView tvLotAtt10;

    @BindView(R.id.tv_lot_att11)
    TextView tvLotAtt11;

    @BindView(R.id.tv_lot_att12)
    TextView tvLotAtt12;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerInvLotAttComponent
                .builder()
                .appComponent(appComponent)
                .invLotAttModule(new InvLotAttModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_inv_lot_att;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        SkuInvDetailInfo.SkuInvDetailEntity detailEntity = (SkuInvDetailInfo.SkuInvDetailEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_INV_SKU);
        tvLotAtt01.setText(detailEntity.getLotAtt01());
        tvLotAtt02.setText(detailEntity.getLotAtt02());
        tvLotAtt03.setText(detailEntity.getLotAtt03());
        tvLotAtt04.setText(detailEntity.getLotAtt04());
        tvLotAtt05.setText(detailEntity.getLotAtt05());
        tvLotAtt06.setText(detailEntity.getLotAtt06());
        tvLotAtt07.setText(detailEntity.getLotAtt07());
        tvLotAtt08.setText(detailEntity.getLotAtt08());
        tvLotAtt09.setText(detailEntity.getLotAtt09());
        tvLotAtt10.setText(detailEntity.getLotAtt10());
        tvLotAtt11.setText(detailEntity.getLotAtt11());
        tvLotAtt12.setText(detailEntity.getLotAtt12());
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

    @Override
    public boolean isShowBackButton() {
        return true;
    }

}