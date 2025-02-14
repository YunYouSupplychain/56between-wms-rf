package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.app.utils.NumberUtil;
import com.yunyou.androidwmsrf.di.component.DaggerScanTaskCheck5Component;
import com.yunyou.androidwmsrf.di.module.ScanTaskCheck5Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck5Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.OwnerInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.PackageConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.TaskCountDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryOwnerRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTaskCheck5Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 扫描任务盘点5
 *
 * @author WMJ
 * @version 2019/07/05
 */
public class ScanTaskCheck5Activity extends BaseActivity<ScanTaskCheck5Presenter> implements ScanTaskCheck5Contract.View, IToolbar {
    @BindView(R.id.tv_loc_code)
    TextView tvLocCode;

    @BindView(R.id.et_trace_id)
    EditText etTraceId;

    @BindView(R.id.et_owner_name)
    EditText etOwnerName;

    @BindView(R.id.et_sku_code)
    EditText etSkuCode;

    @BindView(R.id.tv_pack_desc)
    TextView tvPackDesc;

    @BindView(R.id.et_whole_cs_qty)
    EditText etWholeCsQty;

    @BindView(R.id.et_whole_ea_qty)
    EditText etWholeEaQty;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mTraceId;
    private String mOwnerCode;
    private String mSkuCode;
    private TaskCountDetailInfo.TaskCountDetailEntity detailEntity;
    private String mWholeCsQty;
    private String mWholeEaQty;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTaskCheck5Component
                .builder()
                .appComponent(appComponent)
                .scanTaskCheck5Module(new ScanTaskCheck5Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_task_check5;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        detailEntity = (TaskCountDetailInfo.TaskCountDetailEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN);
        String ownerCode = getIntent().getStringExtra(Constants.INTENT_PARAMETER_TASK_COUNT_OWNER);
        tvLocCode.setText(detailEntity.getLocCode());
        tvPackDesc.setText(detailEntity.getPackDesc());
        etOwnerName.setText(ownerCode);
        initEaQtyInput();
        etTraceId.requestFocus();
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

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.btn_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 取消
            case R.id.btn_cancel:
                killMyself();
                break;

            // 确认
            case R.id.btn_confirm:
                callConfirmEvent();
                break;

            // 选择
            case R.id.btn_select:
                selectOwner();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void initEaQtyInput() {
        etWholeEaQty.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                // 处理事件
                callConfirmEvent();
            }
            return false;
        });
    }

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        mTraceId = etTraceId.getText().toString().trim();
        if (TextUtils.isEmpty(mTraceId)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_trace_id));
            etTraceId.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mOwnerCode = etOwnerName.getText().toString().trim();
        if (TextUtils.isEmpty(mOwnerCode)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_owner_code));
            etOwnerName.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mSkuCode = etSkuCode.getText().toString().trim();
        if (TextUtils.isEmpty(mSkuCode)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_sku_code));
            etSkuCode.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mWholeCsQty = etWholeCsQty.getText().toString().trim();
        if (TextUtils.isEmpty(mWholeCsQty)) {
            showMessage(getResources().getString(R.string.please_input_whole_cs_qty));
            etWholeCsQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mWholeEaQty = etWholeEaQty.getText().toString().trim();
        if (TextUtils.isEmpty(mWholeEaQty)) {
            showMessage(getResources().getString(R.string.please_input_whole_ea_qty));
            etWholeEaQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (!NumberUtil.isDouble(mWholeCsQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_whole_cs_qty));
            etWholeCsQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (BigDecimal.valueOf(Double.valueOf(mWholeCsQty)).compareTo(BigDecimal.ZERO) < 0) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_whole_cs_qty));
            etWholeCsQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (!NumberUtil.isDouble(mWholeEaQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_whole_ea_qty));
            etWholeEaQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (BigDecimal.valueOf(Double.valueOf(mWholeEaQty)).compareTo(BigDecimal.ZERO) < 0) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_whole_ea_qty));
            etWholeEaQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }

        Double countQty = Double.valueOf(mWholeCsQty) * getCsQty() + Double.valueOf(mWholeEaQty);
        Double diffQty = countQty - detailEntity.getQty();
        detailEntity.setTraceId(mTraceId);
        detailEntity.setOwnerCode(mOwnerCode);
        detailEntity.setSkuCode(mSkuCode);
        detailEntity.setQtyCountEa(countQty);
        detailEntity.setQtyDiff(diffQty);
        goToScanTaskCheck6Activity(detailEntity);
    }

    private int getCsQty() {
        int result = 1;
        List<PackageConfigInfo> packageConfigs = detailEntity.getPackageConfigs();
        if (null != packageConfigs && packageConfigs.size() > 0) {
            for (PackageConfigInfo info : packageConfigs) {
                if ("CS".equals(info.getPackageCode())) {
                    result = info.getContainerValue();
                    break;
                }
            }
        }

        return result;
    }

    private void selectOwner() {
        mPresenter.queryOwner(new QueryOwnerRequest(null));
    }

    @Override
    public void goToCheckSelectOwner(OwnerInfo ownerInfo) {
        Intent intent = new Intent(ScanTaskCheck5Activity.this, ScanTaskCheckOwnerInfoActivity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_TASK_COUNT_OWNER_INFO, ownerInfo);
        launchActivity(intent);
    }

    public void goToScanTaskCheck6Activity(TaskCountDetailInfo.TaskCountDetailEntity entity) {
        Intent intent = new Intent(ScanTaskCheck5Activity.this, ScanTaskCheck6Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN, entity);
        launchActivity(intent);
    }
}