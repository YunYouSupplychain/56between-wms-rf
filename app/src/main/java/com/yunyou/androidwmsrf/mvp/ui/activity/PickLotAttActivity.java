package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerPickLotAttComponent;
import com.yunyou.androidwmsrf.di.module.PickLotAttModule;
import com.yunyou.androidwmsrf.mvp.contract.PickLotAttContract;
import com.yunyou.androidwmsrf.mvp.model.entity.LotConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.PickDetailInfo;
import com.yunyou.androidwmsrf.mvp.presenter.PickLotAttPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 收货批次信息
 *
 * @author WMJ
 * @version 2019/07/10
 */
public class PickLotAttActivity extends BaseActivity<PickLotAttPresenter> implements PickLotAttContract.View, IToolbar {
    @BindView(R.id.tv_lot_num)
    TextView tvLotNum;

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

    @BindView(R.id.ll_lot_att01)
    LinearLayout llLotAtt01;

    @BindView(R.id.ll_lot_att02)
    LinearLayout llLotAtt02;

    @BindView(R.id.ll_lot_att03)
    LinearLayout llLotAtt03;

    @BindView(R.id.ll_lot_att04)
    LinearLayout llLotAtt04;

    @BindView(R.id.ll_lot_att05)
    LinearLayout llLotAtt05;

    @BindView(R.id.ll_lot_att06)
    LinearLayout llLotAtt06;

    @BindView(R.id.ll_lot_att07)
    LinearLayout llLotAtt07;

    @BindView(R.id.ll_lot_att08)
    LinearLayout llLotAtt08;

    @BindView(R.id.ll_lot_att09)
    LinearLayout llLotAtt09;

    @BindView(R.id.ll_lot_att10)
    LinearLayout llLotAtt10;

    @BindView(R.id.ll_lot_att11)
    LinearLayout llLotAtt11;

    @BindView(R.id.ll_lot_att12)
    LinearLayout llLotAtt12;

    @BindView(R.id.tt_lot_att01)
    TextView ttLotAtt01;

    @BindView(R.id.tt_lot_att02)
    TextView ttLotAtt02;

    @BindView(R.id.tt_lot_att03)
    TextView ttLotAtt03;

    @BindView(R.id.tt_lot_att04)
    TextView ttLotAtt04;

    @BindView(R.id.tt_lot_att05)
    TextView ttLotAtt05;

    @BindView(R.id.tt_lot_att06)
    TextView ttLotAtt06;

    @BindView(R.id.tt_lot_att07)
    TextView ttLotAtt07;

    @BindView(R.id.tt_lot_att08)
    TextView ttLotAtt08;

    @BindView(R.id.tt_lot_att09)
    TextView ttLotAtt09;

    @BindView(R.id.tt_lot_att10)
    TextView ttLotAtt10;

    @BindView(R.id.tt_lot_att11)
    TextView ttLotAtt11;

    @BindView(R.id.tt_lot_att12)
    TextView ttLotAtt12;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private PickDetailInfo.PickDetailEntity detailEntity;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerPickLotAttComponent
                .builder()
                .appComponent(appComponent)
                .pickLotAttModule(new PickLotAttModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_pick_lot_att;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detailEntity = (PickDetailInfo.PickDetailEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_PICK_DETAIL);
        tvLotNum.setText(detailEntity.getLotNum());
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
        initLotView();
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

    private void initLotView() {
        List<LotConfigInfo> lotConfigs = detailEntity.getLotConfigs();
        Collections.sort(lotConfigs, new Comparator<LotConfigInfo>() {
            public int compare(LotConfigInfo o1, LotConfigInfo o2) {
                int start = Integer.valueOf(o1.getLotCode().substring(o1.getLotCode().length() - 2));
                int end = Integer.valueOf(o2.getLotCode().substring(o2.getLotCode().length() - 2));
                if (start < end) {
                    return -1;
                } else if (start > end) {
                    return 1;
                }
                return 0;
            }
        });

        setViewStatus(lotConfigs.get(0), llLotAtt01, ttLotAtt01);
        setViewStatus(lotConfigs.get(1), llLotAtt02, ttLotAtt02);
        setViewStatus(lotConfigs.get(2), llLotAtt03, ttLotAtt03);
        setViewStatus(lotConfigs.get(3), llLotAtt04, ttLotAtt04);
        setViewStatus(lotConfigs.get(4), llLotAtt05, ttLotAtt05);
        setViewStatus(lotConfigs.get(5), llLotAtt06, ttLotAtt06);
        setViewStatus(lotConfigs.get(6), llLotAtt07, ttLotAtt07);
        setViewStatus(lotConfigs.get(7), llLotAtt08, ttLotAtt08);
        setViewStatus(lotConfigs.get(8), llLotAtt09, ttLotAtt09);
        setViewStatus(lotConfigs.get(9), llLotAtt10, ttLotAtt10);
        setViewStatus(lotConfigs.get(10), llLotAtt11, ttLotAtt11);
        setViewStatus(lotConfigs.get(11), llLotAtt12, ttLotAtt12);
    }

    private void setViewStatus(LotConfigInfo configInfo, LinearLayout linearLayout, TextView textView) {
        if (!"F".equals(configInfo.getIsNeed())) {
            linearLayout.setVisibility(View.VISIBLE);
            textView.setText(configInfo.getLotTitle() + ":");
        }
    }
}