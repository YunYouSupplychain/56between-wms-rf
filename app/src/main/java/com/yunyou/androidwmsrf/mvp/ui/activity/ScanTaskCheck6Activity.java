package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.app.utils.CopyPropertiesUtil;
import com.yunyou.androidwmsrf.di.component.DaggerScanTaskCheck6Component;
import com.yunyou.androidwmsrf.di.module.ScanTaskCheck6Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck6Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.LotConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.TaskCountDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.AddNewTaskCountRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTaskCheck6Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 扫描任务盘点4
 *
 * @author WMJ
 * @version 2019/07/05
 */
public class ScanTaskCheck6Activity extends BaseActivity<ScanTaskCheck6Presenter> implements ScanTaskCheck6Contract.View, IToolbar {
    @BindView(R.id.tv_lot_num)
    TextView tvLotNum;

    @BindView(R.id.tv_lot_att01)
    TextView tvLotAtt01;

    @BindView(R.id.tv_lot_att02)
    TextView tvLotAtt02;

    @BindView(R.id.tv_lot_att03)
    TextView tvLotAtt03;

    @BindView(R.id.et_lot_att04)
    EditText etLotAtt04;

    @BindView(R.id.et_lot_att05)
    EditText etLotAtt05;

    @BindView(R.id.et_lot_att06)
    EditText etLotAtt06;

    @BindView(R.id.et_lot_att07)
    EditText etLotAtt07;

    @BindView(R.id.et_lot_att08)
    EditText etLotAtt08;

    @BindView(R.id.et_lot_att09)
    EditText etLotAtt09;

    @BindView(R.id.et_lot_att10)
    EditText etLotAtt10;

    @BindView(R.id.et_lot_att11)
    EditText etLotAtt11;

    @BindView(R.id.et_lot_att12)
    EditText etLotAtt12;

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
    private MaterialDialog mConfirmCheckDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTaskCheck6Component
                .builder()
                .appComponent(appComponent)
                .scanTaskCheck6Module(new ScanTaskCheck6Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_task_check6;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detailEntity = (TaskCountDetailInfo.TaskCountDetailEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN);
        tvLotNum.setText(detailEntity.getLotNum());
        tvLotAtt01.setText(detailEntity.getLotAtt01());
        tvLotAtt02.setText(detailEntity.getLotAtt02());
        tvLotAtt03.setText(detailEntity.getLotAtt03());
        etLotAtt04.setText(detailEntity.getLotAtt04());
        etLotAtt05.setText(detailEntity.getLotAtt05());
        etLotAtt06.setText(detailEntity.getLotAtt06());
        etLotAtt07.setText(detailEntity.getLotAtt07());
        etLotAtt08.setText(detailEntity.getLotAtt08());
        etLotAtt09.setText(detailEntity.getLotAtt09());
        etLotAtt10.setText(detailEntity.getLotAtt10());
        etLotAtt11.setText(detailEntity.getLotAtt11());
        etLotAtt12.setText(detailEntity.getLotAtt12());
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

    @OnClick({R.id.btn_cancel, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 取消
            case R.id.btn_cancel:
                killMyself();
                break;

            // 确认
            case R.id.btn_confirm:
                showConfirmCheckDialog();
                break;
        }
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

    @Override
    public void afterSave() {
        Intent intent = new Intent(ScanTaskCheck6Activity.this, ScanTaskCheck5Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN, detailEntity);
        launchActivity(intent);
    }

    private void showConfirmCheckDialog() {
        if (mConfirmCheckDialog == null) {
            mConfirmCheckDialog = new MaterialDialog.Builder(this)
                    .title(R.string.hint)
                    .content(R.string.whether_to_confirm_check)
                    .positiveText(R.string.confirm)
                    .negativeText(R.string.cancel)
                    .onPositive((dialog, which) -> callConfirmEvent()).build();

            // 弹出对话框时，处理 PDA 实体回车键
            mConfirmCheckDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event.getAction() && event.getRepeatCount() == 0) {
                        if (mConfirmCheckDialog.isShowing()) {
                            callConfirmEvent();
                            dialog.dismiss();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        mConfirmCheckDialog.show();
    }

    private void callConfirmEvent() {
        AddNewTaskCountRequest request = new AddNewTaskCountRequest();
        CopyPropertiesUtil.copyProperties(detailEntity, request);
        setLotAtt(request);
        mPresenter.addNewTaskCount(request);
    }

    private void setLotAtt(AddNewTaskCountRequest request) {
        request.setLotAtt01(tvLotAtt01.getText().toString().trim());
        request.setLotAtt02(tvLotAtt02.getText().toString().trim());
        request.setLotAtt03(tvLotAtt03.getText().toString().trim());
        request.setLotAtt04(etLotAtt04.getText().toString().trim());
        request.setLotAtt05(etLotAtt05.getText().toString().trim());
        request.setLotAtt06(etLotAtt06.getText().toString().trim());
        request.setLotAtt07(etLotAtt07.getText().toString().trim());
        request.setLotAtt08(etLotAtt08.getText().toString().trim());
        request.setLotAtt09(etLotAtt09.getText().toString().trim());
        request.setLotAtt10(etLotAtt10.getText().toString().trim());
        request.setLotAtt11(etLotAtt11.getText().toString().trim());
        request.setLotAtt12(etLotAtt12.getText().toString().trim());
    }
}