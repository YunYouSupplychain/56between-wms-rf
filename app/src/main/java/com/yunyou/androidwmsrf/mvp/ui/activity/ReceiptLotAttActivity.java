package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.app.utils.CopyPropertiesUtil;
import com.yunyou.androidwmsrf.di.component.DaggerReceiptLotAttComponent;
import com.yunyou.androidwmsrf.di.module.ReceiptLotAttModule;
import com.yunyou.androidwmsrf.mvp.contract.ReceiptLotAttContract;
import com.yunyou.androidwmsrf.mvp.model.entity.AsnDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.LotConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveAsnDetailByTraceIdRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ReceiptLotAttPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 收货批次信息
 *
 * @author WMJ
 * @version 2019/07/10
 */
public class ReceiptLotAttActivity extends BaseActivity<ReceiptLotAttPresenter> implements ReceiptLotAttContract.View, IToolbar, CustomDatePickerDialogFragment.OnSelectedDateListener {
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

    private MaterialDialog mConfirmReceiptDialog;
    private AsnDetailInfo.AsnDetailEntity detailEntity;
    private int lastView;
    private final List<LinearLayout> notEmptyList = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerReceiptLotAttComponent
                .builder()
                .appComponent(appComponent)
                .receiptLotAttModule(new ReceiptLotAttModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_receipt_lot_att;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detailEntity = (AsnDetailInfo.AsnDetailEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_ASN_DETAIL);
        lastView = getIntent().getIntExtra(Constants.INTENT_PARAMETER_TRAY_OR_SKU, 1);
        initViewData();
        initLotView(detailEntity.getLotConfigs());
    }

    private void initViewData() {
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

        onLongClick(tvLotAtt01);
        onLongClick(tvLotAtt02);
        onLongClick(tvLotAtt03);
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

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.iv_lot_att01, R.id.iv_lot_att02, R.id.iv_lot_att03})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 取消
            case R.id.btn_cancel:
                killMyself();
                break;

            // 确认
            case R.id.btn_confirm:
                showConfirmReceiptDialog();
                break;

            case R.id.iv_lot_att01:
                showDatePickDialog(R.id.tv_lot_att01);
                break;

            case R.id.iv_lot_att02:
                showDatePickDialog(R.id.tv_lot_att02);
                break;

            case R.id.iv_lot_att03:
                showDatePickDialog(R.id.tv_lot_att03);
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    long day = 24 * 60 * 60 * 1000;

    private void showDatePickDialog(int viewId) {
        CustomDatePickerDialogFragment fragment = new CustomDatePickerDialogFragment();
        fragment.setOnSelectedDateListener(this);
        Bundle bundle = new Bundle();
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTimeInMillis(System.currentTimeMillis());
        bundle.putSerializable(CustomDatePickerDialogFragment.CURRENT_DATE, currentDate);

        long start = currentDate.getTimeInMillis() - day * 365 * 10;
        long end = currentDate.getTimeInMillis() + day * 365 * 10;
        Calendar startDate = Calendar.getInstance();
        startDate.setTimeInMillis(start);
        Calendar endDate = Calendar.getInstance();
        endDate.setTimeInMillis(end);
        bundle.putSerializable(CustomDatePickerDialogFragment.START_DATE, startDate);
        bundle.putSerializable(CustomDatePickerDialogFragment.END_DATE, endDate);
        bundle.putSerializable(CustomDatePickerDialogFragment.CURRENT_VIEW_ID, viewId);

        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), CustomDatePickerDialogFragment.class.getSimpleName());
    }

    private void initLotView(List<LotConfigInfo> lotConfigs) {
        sortLotConfigs(lotConfigs);
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

    private void sortLotConfigs(List<LotConfigInfo> lotConfigs) {
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
    }

    private void setViewStatus(LotConfigInfo configInfo, LinearLayout linearLayout, TextView textView) {
        if (!"F".equals(configInfo.getIsNeed())) {
            linearLayout.setVisibility(View.VISIBLE);
            textView.setText(configInfo.getLotTitle() + ":");
            if ("R".equals(configInfo.getIsNeed())) {
                notEmptyList.add(linearLayout);
            }
        }
    }

    private void showConfirmReceiptDialog() {
        if (mConfirmReceiptDialog == null) {
            mConfirmReceiptDialog = new MaterialDialog.Builder(this)
                    .title(R.string.hint)
                    .content(R.string.whether_to_confirm_receipt)
                    .positiveText(R.string.confirm)
                    .negativeText(R.string.cancel)
                    .onPositive((dialog, which) -> callConfirmEvent()).build();

            // 弹出对话框时，处理 PDA 实体回车键
            mConfirmReceiptDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event.getAction() && event.getRepeatCount() == 0) {
                        if (mConfirmReceiptDialog.isShowing()) {
                            callConfirmEvent();
                            dialog.dismiss();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        mConfirmReceiptDialog.show();
    }

    public void callConfirmEvent() {
        if (notEmptyList.size() > 0) {
            for (LinearLayout layout : notEmptyList) {
                LinearLayout ly = (LinearLayout) layout.getChildAt(0);
                TextView labelView = (TextView) ly.getChildAt(0);
                TextView displayView = (TextView) ly.getChildAt(1);
                if (TextUtils.isEmpty(displayView.getText().toString().trim())) {
                    showMessage(labelView.getText().toString().replace(":", "") + "不能为空");
                    AppLifecyclesImpl.playSound();
                    return;
                }
            }
        }

        SaveAsnDetailByTraceIdRequest request = new SaveAsnDetailByTraceIdRequest();
        CopyPropertiesUtil.copyProperties(detailEntity, request);
        request.setCurrentQtyRcvEa(detailEntity.getQtyRcvEa());
        setLotAttValue(request);
        mPresenter.saveAsnDetailByTraceId(request);
    }

    private void setLotAttValue(SaveAsnDetailByTraceIdRequest request) {
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

    @Override
    public void afterSave() {
        if (1 == lastView) {
            Intent intent = new Intent(ReceiptLotAttActivity.this, ScanTrayReceipt2Activity.class);
            intent.putExtra(Constants.INTENT_PARAMETER_ASN, detailEntity.getAsnNo());
            launchActivity(intent);
        } else {
            Intent intent = new Intent(ReceiptLotAttActivity.this, ScanSkuReceipt2Activity.class);
            intent.putExtra(Constants.INTENT_PARAMETER_ASN, detailEntity.getAsnNo());
            launchActivity(intent);
        }
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onSelectedDate(int year, int monthOfYear, int dayOfMonth, int viewId) {
        String dateShow = year + "-" + String.format("%02d", monthOfYear) + "-" + String.format("%02d", dayOfMonth);
        TextView textView = (TextView) findViewById(viewId);
        textView.setText(dateShow);
    }

    private void onLongClick(TextView textView) {
        textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textView.setText("");
                return true;
            }
        });
    }
}