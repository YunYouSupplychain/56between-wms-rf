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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.app.utils.NumberUtil;
import com.yunyou.androidwmsrf.di.component.DaggerScanTaskCheck3Component;
import com.yunyou.androidwmsrf.di.module.ScanTaskCheck3Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck3Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.PackageConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.TaskCountDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveTaskCountRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTaskCheck3Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 扫描任务盘点3
 *
 * @author WMJ
 * @version 2019/07/05
 */
public class ScanTaskCheck3Activity extends BaseActivity<ScanTaskCheck3Presenter> implements ScanTaskCheck3Contract.View, IToolbar {
    @BindView(R.id.tv_loc_code)
    TextView tvLocCode;

    @BindView(R.id.tv_trace_id)
    TextView tvTraceId;

    @BindView(R.id.tv_owner_name)
    TextView tvOwnerName;

    @BindView(R.id.tv_sku_code)
    TextView tvSkuCode;

    @BindView(R.id.tv_sku_name)
    TextView tvSkuName;

    @BindView(R.id.tv_lot_num)
    TextView tvLotNum;

    @BindView(R.id.tv_sys_inv_qty)
    TextView tvSysInvQty;

    @BindView(R.id.tv_last_count_qty)
    TextView tvLastCountQty;

    @BindView(R.id.tv_last_count_op)
    TextView tvLastCountOp;

    @BindView(R.id.tv_pack_desc)
    TextView tvPackDesc;

    @BindView(R.id.et_whole_cs_qty)
    EditText etWholeCsQty;

    @BindView(R.id.et_whole_ea_qty)
    EditText etWholeEaQty;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private List<TaskCountDetailInfo.TaskCountDetailEntity> detailEntityList;
    private TaskCountDetailInfo.TaskCountDetailEntity detailEntity;
    private int index = 0;
    private String mWholeCsQty;
    private String mWholeEaQty;
    private MaterialDialog mConfirmCheckDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTaskCheck3Component
                .builder()
                .appComponent(appComponent)
                .scanTaskCheck3Module(new ScanTaskCheck3Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_task_check3;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        TaskCountDetailInfo detailInfo = (TaskCountDetailInfo) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN_INFO);
        detailEntityList = detailInfo.getDetailEntityList();
        detailEntity = detailEntityList.get(0);
        initEaQtyInput();
        initViewData();
        etWholeCsQty.requestFocus();
    }

    private void initViewData() {
        tvLocCode.setText(detailEntity.getLocCode());
        tvTraceId.setText(detailEntity.getTraceId());
        tvOwnerName.setText(detailEntity.getOwnerName());
        tvSkuCode.setText(detailEntity.getSkuCode());
        tvSkuName.setText(detailEntity.getSkuName());
        tvLotNum.setText(detailEntity.getLotNum());
        tvLastCountQty.setText(null != detailEntity.getPreQtyCountEa() ? String.valueOf(detailEntity.getPreQtyCountEa()) : "");
        tvLastCountOp.setText(detailEntity.getPreCountOp());
        tvPackDesc.setText(detailEntity.getPackDesc());
        if ("O".equals(detailEntity.getCountMode())) {
            tvSysInvQty.setText(String.valueOf(detailEntity.getQty()));
        } else {
            tvSysInvQty.setText("");
        }
        etWholeCsQty.setText("");
        etWholeEaQty.setText("");
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

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.btn_view, R.id.btn_add, R.id.btn_last, R.id.btn_next})
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

            // 选择批次
            case R.id.btn_view:
                goToScanTaskCheck4Activity();
                break;

            // 新增
            case R.id.btn_add:
                addEvent();
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
                showConfirmCheckDialog();
            }
            return false;
        });
    }

    private void addEvent() {
        Intent intent = new Intent(ScanTaskCheck3Activity.this, ScanTaskCheck5Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN, detailEntity);
        launchActivity(intent);
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

    private void showConfirmCheckDialog() {
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

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        Double countQty = Double.valueOf(mWholeCsQty) * getCsQty() + Double.valueOf(mWholeEaQty);
        Double diffQty = countQty - detailEntity.getQty();
        mPresenter.saveTaskCount(new SaveTaskCountRequest(detailEntity.getId(), detailEntity.getCountId(), detailEntity.getCountNo(), countQty, diffQty, detailEntity.getIsSerial()));
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

    @Override
    public void goToScanTaskCheck4Activity() {
        Intent intent = new Intent(ScanTaskCheck3Activity.this, ScanTaskCheck4Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN, detailEntity);
        launchActivity(intent);
    }

    @Override
    public void afterSave() {
        detailEntityList.remove(detailEntity);
        if (detailEntityList.size() == 0) {
            launchActivity(new Intent(ScanTaskCheck3Activity.this, ScanTaskCheck2Activity.class));
        } else {
            index = 0;
            detailEntity = detailEntityList.get(index);
            initViewData();
        }
    }

}