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
import com.yunyou.androidwmsrf.di.component.DaggerScanTaskCheck4Component;
import com.yunyou.androidwmsrf.di.module.ScanTaskCheck4Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck4Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.LotConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.TaskCountDetailInfo;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTaskCheck4Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 扫描任务盘点4
 *
 * @author WMJ
 * @version 2019/07/05
 */
public class ScanTaskCheck4Activity extends BaseActivity<ScanTaskCheck4Presenter> implements ScanTaskCheck4Contract.View, IToolbar {
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

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private TaskCountDetailInfo.TaskCountDetailEntity detailEntity;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTaskCheck4Component
                .builder()
                .appComponent(appComponent)
                .scanTaskCheck4Module(new ScanTaskCheck4Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_task_check4;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detailEntity = (TaskCountDetailInfo.TaskCountDetailEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN);
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

        setViewStatus(lotConfigs.get(0), llLotAtt01);
        setViewStatus(lotConfigs.get(1), llLotAtt02);
        setViewStatus(lotConfigs.get(2), llLotAtt03);
        setViewStatus(lotConfigs.get(3), llLotAtt04);
        setViewStatus(lotConfigs.get(4), llLotAtt05);
        setViewStatus(lotConfigs.get(5), llLotAtt06);
        setViewStatus(lotConfigs.get(6), llLotAtt07);
        setViewStatus(lotConfigs.get(7), llLotAtt08);
        setViewStatus(lotConfigs.get(8), llLotAtt09);
        setViewStatus(lotConfigs.get(9), llLotAtt10);
        setViewStatus(lotConfigs.get(10), llLotAtt11);
        setViewStatus(lotConfigs.get(11), llLotAtt12);
    }

    private void setViewStatus(LotConfigInfo configInfo, LinearLayout linearLayout) {
        if (!"F".equals(configInfo.getIsNeed())) {
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

}