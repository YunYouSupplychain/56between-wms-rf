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
import com.yunyou.androidwmsrf.app.utils.NumberUtil;
import com.yunyou.androidwmsrf.di.component.DaggerScanRpTask2Component;
import com.yunyou.androidwmsrf.di.module.ScanRpTask2Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanRpTask2Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.PackageConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.ReplenishmentDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveReplenishmentRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanRpTask2Presenter;
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
 * 扫描补货任务2
 *
 * @author WMJ
 * @version 2019/07/04
 */
public class ScanRpTask2Activity extends BaseActivity<ScanRpTask2Presenter> implements ScanRpTask2Contract.View, IToolbar {
    @BindView(R.id.tv_fm_loc)
    TextView tvFmLoc;

    @BindView(R.id.tv_fm_id)
    TextView tvFmId;

    @BindView(R.id.tv_sku_code)
    TextView tvSkuCode;

    @BindView(R.id.tv_sku_name)
    TextView tvSkuName;

    @BindView(R.id.tv_pack_desc)
    TextView tvPackDesc;

    @BindView(R.id.tv_to_loc)
    TextView tvToLoc;

    @BindView(R.id.tv_to_id)
    TextView tvToId;

    @BindView(R.id.tv_f_uom)
    TextView tvfUom;

    @BindView(R.id.tv_rp_uom)
    TextView tvRpUom;

    @BindView(R.id.sp_t_uom)
    SherlockSpinner sptUom;

    @BindView(R.id.et_rp_qty)
    EditText etRpQty;

    @BindView(R.id.et_confirm_loc)
    EditText etConfirmLoc;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private ReplenishmentDetailInfo.ReplenishmentDetailEntity detailEntity;
    private int uomQty;
    private String uom;
    private String mRpQty;
    private String mRpLoc;
    private Double rpQty;
    private MaterialDialog mConfirmRpDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanRpTask2Component
                .builder()
                .appComponent(appComponent)
                .scanRpTask2Module(new ScanRpTask2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_rp_task2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detailEntity = (ReplenishmentDetailInfo.ReplenishmentDetailEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_RP_DETAIL);
        tvFmLoc.setText(detailEntity.getFmLoc());
        tvFmId.setText(detailEntity.getFmId());
        tvSkuCode.setText(detailEntity.getSkuCode());
        tvSkuName.setText(detailEntity.getSkuName());
        tvPackDesc.setText(detailEntity.getPackCode());
        tvToLoc.setText(detailEntity.getToLoc());
        tvToId.setText(detailEntity.getToId());
        tvfUom.setText("EA");
        tvRpUom.setText(String.valueOf(detailEntity.getQtyRpUom()));
        initSelectUom(detailEntity.getPackageConfigs());
        initRpLocInput();
        sptUom.setText(getEaDesc());
        etRpQty.setText(String.valueOf(detailEntity.getQtyRpEa()));
        etConfirmLoc.requestFocus();
    }

    private void initSelectUom(List<PackageConfigInfo> packageConfigs) {
        Collections.sort(packageConfigs, new Comparator<PackageConfigInfo>() {
            public int compare(PackageConfigInfo o1, PackageConfigInfo o2) {
                int start = Integer.valueOf(o1.getSeq());
                int end = Integer.valueOf(o2.getSeq());
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

    private String getEaDesc() {
        String result = "件";
        List<PackageConfigInfo> packageConfigs = detailEntity.getPackageConfigs();
        if (null != packageConfigs && packageConfigs.size() > 0) {
            for (PackageConfigInfo info : packageConfigs) {
                if (Integer.parseInt(info.getSeq()) == 1) {
                    result = info.getPackageName();
                    break;
                }
            }
        }
        return result;
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
                showConfirmRpDialog();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void initRpLocInput() {
        etConfirmLoc.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                // 处理事件
                showConfirmRpDialog();
            }
            return false;
        });
    }

    private void showConfirmRpDialog() {
        mRpLoc = etConfirmLoc.getText().toString().trim();
        mRpQty = etRpQty.getText().toString().trim();
        if (TextUtils.isEmpty(mRpLoc)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_confirm_loc));
            etConfirmLoc.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (TextUtils.isEmpty(mRpQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_rp_qty));
            etRpQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (!NumberUtil.isDouble(mRpQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_rp_qty));
            etRpQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (BigDecimal.valueOf(Double.valueOf(mRpQty)).compareTo(BigDecimal.ZERO) <= 0) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_rp_qty));
            etRpQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        rpQty = Double.valueOf(mRpQty) * uomQty;
        if (BigDecimal.valueOf(rpQty).compareTo(BigDecimal.valueOf(detailEntity.getQtyRpEa())) > 0) {
            showMessage(getResources().getString(R.string.rcv_qty_can_not_more_than_plan_qty));
            etRpQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }

        if (mConfirmRpDialog == null) {
            mConfirmRpDialog = new MaterialDialog.Builder(this)
                    .title(R.string.hint)
                    .content(R.string.whether_to_confirm_rp)
                    .positiveText(R.string.confirm)
                    .negativeText(R.string.cancel)
                    .onPositive((dialog, which) -> callConfirmEvent()).build();

            // 弹出对话框时，处理 PDA 实体回车键
            mConfirmRpDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event.getAction() && event.getRepeatCount() == 0) {
                        if (mConfirmRpDialog.isShowing()) {
                            callConfirmEvent();
                            dialog.dismiss();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        mConfirmRpDialog.show();
    }

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        mPresenter.saveReplenishment(new SaveReplenishmentRequest(detailEntity.getId(), uom, rpQty / uomQty, rpQty, mRpLoc));
    }

    @Override
    public void afterSave() {
        launchActivity(new Intent(ScanRpTask2Activity.this, ScanRpTask1Activity.class));
    }
}