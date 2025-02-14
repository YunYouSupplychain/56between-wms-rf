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
import com.yunyou.androidwmsrf.di.component.DaggerInvPickBoxQuery2Component;
import com.yunyou.androidwmsrf.di.module.InvPickBoxQuery2Module;
import com.yunyou.androidwmsrf.mvp.contract.InvPickBoxQuery2Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.PickBoxDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.PickBoxHeaderInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickBoxDetailRequest;
import com.yunyou.androidwmsrf.mvp.presenter.InvPickBoxQuery2Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 拣货箱查询2
 *
 * @author WMJ
 * @version 2019/07/17
 */
public class InvPickBoxQuery2Activity extends BaseActivity<InvPickBoxQuery2Presenter> implements InvPickBoxQuery2Contract.View, IToolbar {
    @BindView(R.id.tv_pick_box_no)
    TextView tvPickBoxNo;

    @BindView(R.id.tv_consignee_code)
    TextView tvConsigneeCode;

    @BindView(R.id.tv_consignee_name)
    TextView tvConsigneeName;

    @BindView(R.id.tv_consignee_address)
    TextView tvConsigneeAddress;

    @BindView(R.id.tv_contract_name)
    TextView tvContractName;

    @BindView(R.id.tv_contract_tel)
    TextView tvContractTel;

    @BindView(R.id.tv_pick_status)
    TextView tvPickStatus;

    @BindView(R.id.tv_check_status)
    TextView tvCheckStatus;

    @BindView(R.id.tv_qty_sku)
    TextView tvQtySku;

    @BindView(R.id.tv_qty_total)
    TextView tvQtyTotal;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private List<PickBoxHeaderInfo.PickBoxHeaderEntity> headerEntityList;
    private PickBoxHeaderInfo.PickBoxHeaderEntity headerEntity;
    private int index = 0;
    private String mToId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerInvPickBoxQuery2Component
                .builder()
                .appComponent(appComponent)
                .invPickBoxQuery2Module(new InvPickBoxQuery2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_inv_pick_box_query2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        PickBoxHeaderInfo headerInfo = (PickBoxHeaderInfo) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_INV_PICK_BOX_HEADER_INFO);
        mToId = getIntent().getStringExtra(Constants.INTENT_PARAMETER_INV_PICK_BOX_QUERY_ID);
        headerEntityList = headerInfo.getDetailEntityList();
        headerEntity = headerEntityList.get(0);
        initViewData();
    }

    private void initViewData() {
        tvPickBoxNo.setText(mToId);
        tvConsigneeCode.setText(headerEntity.getConsigneeCode());
        tvConsigneeName.setText(headerEntity.getConsigneeName());
        tvConsigneeAddress.setText(headerEntity.getConsigneeAddr());
        tvContractName.setText(headerEntity.getContactName());
        tvContractTel.setText(headerEntity.getContactTel());
        tvPickStatus.setText(headerEntity.getStatus());
        tvCheckStatus.setText(headerEntity.getCheckStatus());
        tvQtySku.setText(String.valueOf(headerEntity.getQtySku()));
        tvQtyTotal.setText(String.valueOf(headerEntity.getQtyTotal()));
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

    @OnClick({R.id.btn_confirm, R.id.btn_cancel, R.id.btn_last, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                callConfirmEvent();
                break;

            case R.id.btn_cancel:
                killMyself();
                break;

            case R.id.btn_last:
                lastEvent();
                break;

            case R.id.btn_next:
                nextEvent();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void lastEvent() {
        if (index == 0) {
            showMessage("已经是第一条记录");
            return;
        }
        index--;
        headerEntity = headerEntityList.get(index);
        initViewData();
    }

    private void nextEvent() {
        if (index == headerEntityList.size() - 1) {
            showMessage("已经是最后一条记录");
            return;
        }
        index++;
        headerEntity = headerEntityList.get(index);
        initViewData();
    }

    private void callConfirmEvent() {
        mPresenter.queryPickBoxDetail(new QueryPickBoxDetailRequest(headerEntity.getSoNo(), mToId));
    }

    @Override
    public void goToInvPickBoxQuery3Activity(PickBoxDetailInfo info) {
        Intent intent = new Intent(InvPickBoxQuery2Activity.this, InvPickBoxQuery3Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_INV_PICK_BOX_DETAIL_INFO, info);
        launchActivity(intent);
    }
}