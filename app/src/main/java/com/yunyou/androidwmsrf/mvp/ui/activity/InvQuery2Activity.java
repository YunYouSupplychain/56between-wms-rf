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
import com.yunyou.androidwmsrf.di.component.DaggerInvQuery2Component;
import com.yunyou.androidwmsrf.di.module.InvQuery2Module;
import com.yunyou.androidwmsrf.mvp.contract.InvQuery2Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.SkuInvDetailInfo;
import com.yunyou.androidwmsrf.mvp.presenter.InvQuery2Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 库存查询2
 *
 * @author WMJ
 * @version 2019/07/05
 */
public class InvQuery2Activity extends BaseActivity<InvQuery2Presenter> implements InvQuery2Contract.View, IToolbar {
    @BindView(R.id.tv_owner_name)
    TextView tvOwnerName;

    @BindView(R.id.tv_sku_code)
    TextView tvSkuCode;

    @BindView(R.id.tv_sku_name)
    TextView tvSkuName;

    @BindView(R.id.tv_loc_code)
    TextView tvLocCode;

    @BindView(R.id.tv_trace_id)
    TextView tvTraceId;

    @BindView(R.id.tv_lot_num)
    TextView tvLotNum;

    @BindView(R.id.tv_total_qty)
    TextView tvTotalQty;

    @BindView(R.id.tv_available_qty)
    TextView tvAvailableQty;

    @BindView(R.id.tv_hold_qty)
    TextView tvHoldQty;

    @BindView(R.id.tv_alloc_qty)
    TextView tvAllocQty;

    @BindView(R.id.tv_pk_qty)
    TextView tvPkQty;

    @BindView(R.id.tv_pa_in_qty)
    TextView tvPaInQty;

    @BindView(R.id.tv_pa_out_qty)
    TextView tvPaOutQty;

    @BindView(R.id.tv_rp_in_qty)
    TextView tvRpInQty;

    @BindView(R.id.tv_rp_out_qty)
    TextView tvRpOutQty;

    @BindView(R.id.tv_mv_in_qty)
    TextView tvMvInQty;

    @BindView(R.id.tv_mv_out_qty)
    TextView tvMvOutQty;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private List<SkuInvDetailInfo.SkuInvDetailEntity> detailEntityList;
    private SkuInvDetailInfo.SkuInvDetailEntity detailEntity;
    private int index = 0;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerInvQuery2Component
                .builder()
                .appComponent(appComponent)
                .invQuery2Module(new InvQuery2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_inv_query2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        SkuInvDetailInfo info = (SkuInvDetailInfo) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_INV_SKU_INFO);
        detailEntityList = info.getDetailEntityList();
        detailEntity = detailEntityList.get(0);
        initViewData();
    }

    private void initViewData() {
        tvOwnerName.setText(detailEntity.getOwnerName());
        tvSkuCode.setText(detailEntity.getSkuCode());
        tvSkuName.setText(detailEntity.getSkuName());
        tvLocCode.setText(detailEntity.getLocCode());
        tvTraceId.setText(detailEntity.getTraceId());
        tvLotNum.setText(detailEntity.getLotNum());
        tvTotalQty.setText(String.valueOf(detailEntity.getQty()));
        tvAvailableQty.setText(String.valueOf(detailEntity.getQtyAvailable()));
        tvHoldQty.setText(String.valueOf(detailEntity.getQtyHold()));
        tvAllocQty.setText(String.valueOf(detailEntity.getQtyAlloc()));
        tvPkQty.setText(String.valueOf(detailEntity.getQtyPk()));
        tvPaInQty.setText(String.valueOf(detailEntity.getQtyPaIn()));
        tvPaOutQty.setText(String.valueOf(detailEntity.getQtyPaOut()));
        tvRpInQty.setText(String.valueOf(detailEntity.getQtyRpIn()));
        tvRpOutQty.setText(String.valueOf(detailEntity.getQtyRpOut()));
        tvMvInQty.setText(String.valueOf(detailEntity.getQtyMvIn()));
        tvMvOutQty.setText(String.valueOf(detailEntity.getQtyMvOut()));
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

    @OnClick({R.id.btn_view, R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 选择批次
            case R.id.btn_view:
                goToInvLotAttActivity();
                break;

            // 上一条
            case R.id.btn_last:
                lastEvent();
                break;

            // 下一条
            case R.id.btn_next:
                nextEvent();
                break;
        }
    }

    private void lastEvent() {
        if (index == 0) {
            showMessage("已经是第一条记录");
            return;
        }
        index--;
        detailEntity = detailEntityList.get(index);
        initViewData();
    }

    private void nextEvent() {
        if (index == detailEntityList.size() - 1) {
            showMessage("已经是最后一条记录");
            return;
        }
        index++;
        detailEntity = detailEntityList.get(index);
        initViewData();
    }

    private void goToInvLotAttActivity() {
        Intent intent = new Intent(InvQuery2Activity.this, InvLotAttActivity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_INV_SKU, detailEntity);
        launchActivity(intent);
    }

}