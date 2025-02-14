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
import com.yunyou.androidwmsrf.di.component.DaggerInvPickBoxQuery3Component;
import com.yunyou.androidwmsrf.di.module.InvPickBoxQuery3Module;
import com.yunyou.androidwmsrf.mvp.contract.InvPickBoxQuery3Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.PickBoxDetailInfo;
import com.yunyou.androidwmsrf.mvp.presenter.InvPickBoxQuery3Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 拣货箱查询3
 *
 * @author WMJ
 * @version 2019/07/18
 */
public class InvPickBoxQuery3Activity extends BaseActivity<InvPickBoxQuery3Presenter> implements InvPickBoxQuery3Contract.View, IToolbar {
    @BindView(R.id.tv_owner_name)
    TextView tvOwnerName;

    @BindView(R.id.tv_sku_code)
    TextView tvSkuCode;

    @BindView(R.id.tv_sku_name)
    TextView tvSkuName;

    @BindView(R.id.tv_lot_num)
    TextView tvLotNum;

    @BindView(R.id.tv_fm_loc)
    TextView tvFmLoc;

    @BindView(R.id.tv_to_loc)
    TextView tvToLoc;

    @BindView(R.id.tv_fm_id)
    TextView tvFmId;

    @BindView(R.id.tv_pack_desc)
    TextView tvPackDesc;

    @BindView(R.id.tv_f_uom)
    TextView tvFUom;

    @BindView(R.id.tv_qty_uom)
    TextView tvQtyUom;

    @BindView(R.id.tv_qty_ea)
    TextView tvQtyEa;

    @BindView(R.id.tv_pick_status)
    TextView tvPickStatus;

    @BindView(R.id.tv_check_status)
    TextView tvCheckStatus;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private List<PickBoxDetailInfo.PickBoxDetailEntity> detailEntityList;
    private PickBoxDetailInfo.PickBoxDetailEntity detailEntity;
    private int index = 0;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerInvPickBoxQuery3Component
                .builder()
                .appComponent(appComponent)
                .invPickBoxQuery3Module(new InvPickBoxQuery3Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_inv_pick_box_query3;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        PickBoxDetailInfo detailInfo = (PickBoxDetailInfo) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_INV_PICK_BOX_DETAIL_INFO);
        detailEntityList = detailInfo.getDetailEntityList();
        detailEntity = detailEntityList.get(0);
        initViewData();
    }

    private void initViewData() {
        tvOwnerName.setText(detailEntity.getOwnerName());
        tvSkuCode.setText(detailEntity.getSkuCode());
        tvSkuName.setText(detailEntity.getSkuName());
        tvLotNum.setText(detailEntity.getLotNum());
        tvFmLoc.setText(detailEntity.getLocCode());
        tvToLoc.setText(detailEntity.getToLoc());
        tvFmId.setText(detailEntity.getTraceId());
        tvPackDesc.setText(detailEntity.getPackDesc());
        tvFUom.setText(detailEntity.getUom());
        tvQtyUom.setText(String.valueOf(detailEntity.getQtyUom()));
        tvQtyEa.setText(String.valueOf(detailEntity.getQtyEa()));
        tvPickStatus.setText(detailEntity.getStatus());
        tvCheckStatus.setText(detailEntity.getCheckStatus());
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

    @OnClick({R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_last:
                lastEvent();
                break;

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

}