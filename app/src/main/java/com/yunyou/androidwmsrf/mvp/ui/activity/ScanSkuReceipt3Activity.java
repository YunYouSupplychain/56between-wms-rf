package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.app.utils.CopyPropertiesUtil;
import com.yunyou.androidwmsrf.app.utils.NumberUtil;
import com.yunyou.androidwmsrf.di.component.DaggerScanSkuReceipt3Component;
import com.yunyou.androidwmsrf.di.module.ScanSkuReceipt3Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceipt3Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.AsnDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.LotConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.PackageConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveAsnDetailByTraceIdRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanSkuReceipt3Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.sherlockshi.widget.SherlockSpinner;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 扫描商品收货3
 *
 * @author WMJ
 * @version 2019/06/29
 */
public class ScanSkuReceipt3Activity extends BaseActivity<ScanSkuReceipt3Presenter> implements ScanSkuReceipt3Contract.View, IToolbar {
    @BindView(R.id.tv_asn_no)
    TextView tvAsnNo;

    @BindView(R.id.tv_trace_no)
    TextView tvTraceNo;

    @BindView(R.id.tv_sku_code)
    TextView rvSkuCode;

    @BindView(R.id.tv_sku_name)
    TextView rvSkuName;

    @BindView(R.id.tv_pack_desc)
    TextView rvPackDesc;

    @BindView(R.id.tv_ti_hi)
    TextView tvTiHi;

    @BindView(R.id.tv_fUom)
    TextView tvfUom;

    @BindView(R.id.tv_plan_qty)
    TextView tvPlanQty;

    @BindView(R.id.sp_tUom)
    SherlockSpinner sptUom;

    @BindView(R.id.et_rcv_qty)
    EditText etRcvQty;

    @BindView(R.id.et_rcv_loc)
    EditText etRcvLoc;

    @BindView(R.id.et_rcv_trace_no)
    EditText etRcvTraceNo;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private AsnDetailInfo.AsnDetailEntity detailEntity;
    private int uomQty = 1;
    private String mRcvQty;
    private String mRcvLoc;
    private String mRcvTraceNo;
    private MaterialDialog mConfirmReceiptDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanSkuReceipt3Component
                .builder()
                .appComponent(appComponent)
                .scanSkuReceipt3Module(new ScanSkuReceipt3Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_sku_receipt3;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detailEntity = (AsnDetailInfo.AsnDetailEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_ASN_DETAIL);
        initUomQty(detailEntity.getUom());
        tvAsnNo.setText(detailEntity.getAsnNo());
        tvTraceNo.setText(detailEntity.getPlanId());
        rvSkuCode.setText(detailEntity.getSkuCode());
        rvSkuName.setText(detailEntity.getSkuName());
        rvPackDesc.setText(detailEntity.getPackDesc());
        String tiHi = detailEntity.getCdprTI() + "*" + detailEntity.getCdprHI();
        tvTiHi.setText(tiHi);
        tvfUom.setText(detailEntity.getUomDesc());
        tvPlanQty.setText(String.format("%s", new BigDecimal(detailEntity.getQtyPlanEa() / uomQty).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue()));
        initSelectUom(detailEntity.getPackageConfigs());
        initRcvTraceNoInput();
        sptUom.setText(detailEntity.getUomDesc());
        etRcvQty.setText(tvPlanQty.getText().toString());
        etRcvLoc.setText(detailEntity.getToLoc());
        etRcvTraceNo.setText(detailEntity.getToId());
        etRcvLoc.requestFocus();
    }

    private void initSelectUom(List<PackageConfigInfo> packageConfigs) {
        Collections.sort(packageConfigs, new Comparator<PackageConfigInfo>() {
            public int compare(PackageConfigInfo o1, PackageConfigInfo o2) {
                int start = Integer.parseInt(o1.getSeq());
                int end = Integer.parseInt(o2.getSeq());
                if (start < end) {
                    return -1;
                } else if (start > end) {
                    return 1;
                }
                return 0;
            }
        });

        ArrayAdapter<PackageConfigInfo> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, packageConfigs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sptUom.setAdapter(adapter);
        sptUom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                PackageConfigInfo info = (PackageConfigInfo) adapterView.getItemAtPosition(position);
                uomQty = info.getContainerValue();
            }
        });

    }

    private void initUomQty(String uom) {
        List<PackageConfigInfo> packageConfigs = detailEntity.getPackageConfigs();
        if (null != packageConfigs && packageConfigs.size() > 0) {
            for (PackageConfigInfo info : packageConfigs) {
                if (uom.equals(info.getPackageCode())) {
                    uomQty = info.getContainerValue();
                    break;
                }
            }
        }
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
                validate();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void initRcvTraceNoInput() {
        etRcvTraceNo.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                validate();
            }
            return false;
        });
    }

    /**
     * 确认按钮验证事件
     */
    private void validate() {
        mRcvQty = etRcvQty.getText().toString().trim();
        mRcvLoc = etRcvLoc.getText().toString().trim();
        mRcvTraceNo = etRcvTraceNo.getText().toString().trim();
        double rcvQty;

        if (TextUtils.isEmpty(mRcvLoc)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_rcv_loc));
            etRcvLoc.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (TextUtils.isEmpty(mRcvTraceNo)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_rcv_trace_no));
            etRcvTraceNo.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (TextUtils.isEmpty(mRcvQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_rcv_qty));
            etRcvQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (!NumberUtil.isDouble(mRcvQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_rcv_qty));
            etRcvQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (BigDecimal.valueOf(Double.valueOf(mRcvQty)).compareTo(BigDecimal.ZERO) <= 0) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_rcv_qty));
            etRcvQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        rcvQty = Double.valueOf(mRcvQty) * uomQty;
        if (BigDecimal.valueOf(rcvQty).compareTo(BigDecimal.valueOf(detailEntity.getQtyPlanEa())) > 0) {
            showMessage(getResources().getString(R.string.rcv_qty_can_not_more_than_plan_qty));
            etRcvQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }

        detailEntity.setQtyRcvEa(rcvQty);
        detailEntity.setToLoc(mRcvLoc);
        detailEntity.setToId(mRcvTraceNo);
        List<LotConfigInfo> lotConfigs = detailEntity.getLotConfigs();
        if (null != lotConfigs && lotConfigs.size() > 0) {
            goToReceiptLotAttActivity(detailEntity);
        } else {
            showConfirmReceiptDialog();
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

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        SaveAsnDetailByTraceIdRequest request = new SaveAsnDetailByTraceIdRequest();
        CopyPropertiesUtil.copyProperties(detailEntity, request);
        request.setCurrentQtyRcvEa(detailEntity.getQtyRcvEa());
        mPresenter.saveAsnDetailByTraceId(request);
    }

    public void afterReceiptConfirm() {
        Intent intent = new Intent(ScanSkuReceipt3Activity.this, ScanSkuReceipt2Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_ASN, tvAsnNo.getText().toString());
        launchActivity(intent);
    }

    private void goToReceiptLotAttActivity(AsnDetailInfo.AsnDetailEntity entity) {
        Intent intent = new Intent(ScanSkuReceipt3Activity.this, ReceiptLotAttActivity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_ASN_DETAIL, entity);
        intent.putExtra(Constants.INTENT_PARAMETER_TRAY_OR_SKU, 2);
        launchActivity(intent);
    }

}