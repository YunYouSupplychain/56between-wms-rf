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
import com.yunyou.androidwmsrf.di.component.DaggerScanTaskPutaway2Component;
import com.yunyou.androidwmsrf.di.module.ScanTaskPutaway2Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanTaskPutaway2Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.PaTaskInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.PackageConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePutAwayByTaskRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTaskPutaway2Presenter;
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
 * 扫描托盘上架2
 *
 * @author WMJ
 * @version 2019/07/02
 */
public class ScanTaskPutaway2Activity extends BaseActivity<ScanTaskPutaway2Presenter> implements ScanTaskPutaway2Contract.View, IToolbar {
    @BindView(R.id.tv_f_trace_id)
    TextView tvFTraceId;

    @BindView(R.id.tv_sku_code)
    TextView rvSkuCode;

    @BindView(R.id.tv_sku_name)
    TextView tvSkuName;

    @BindView(R.id.tv_pack_desc)
    TextView rvPackDesc;

    @BindView(R.id.tv_plan_loc)
    TextView tvPlanLoc;

    @BindView(R.id.tv_fUom)
    TextView tvfUom;

    @BindView(R.id.tv_pa_uom)
    TextView tvPaUom;

    @BindView(R.id.sp_tUom)
    SherlockSpinner sptUom;

    @BindView(R.id.et_pa_qty)
    EditText etPaQty;

    @BindView(R.id.et_pa_loc)
    EditText etPaLoc;

    @BindView(R.id.et_pa_trace_id)
    EditText etPaTraceId;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private PaTaskInfo.PaTaskEntity taskEntity;
    private int uomQty;
    private String mPaLoc;
    private String mPaTraceId;
    private String mPaQty;
    private Double paQty;
    private MaterialDialog mConfirmPaDialog;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTaskPutaway2Component
                .builder()
                .appComponent(appComponent)
                .scanTaskPutaway2Module(new ScanTaskPutaway2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_task_putaway2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        taskEntity = (PaTaskInfo.PaTaskEntity) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_PA_TASK_INFO);
        initUomQty(taskEntity.getUom());
        tvFTraceId.setText(taskEntity.getFmId());
        rvSkuCode.setText(taskEntity.getSkuCode());
        tvSkuName.setText(taskEntity.getSkuName());
        rvPackDesc.setText(taskEntity.getPackDesc());
        tvPlanLoc.setText(taskEntity.getSuggestLoc());
        tvfUom.setText(taskEntity.getUomDesc());
        tvPaUom.setText(String.valueOf(taskEntity.getQtyPaUom()));
        initSelectUom(taskEntity.getPackageConfigs());
        initPaTraceIdInput();
        sptUom.setText(taskEntity.getUomDesc());
        etPaQty.setText(String.valueOf(taskEntity.getQtyPaUom()));
        etPaLoc.requestFocus();
        etPaTraceId.setText(taskEntity.getToId());
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
        List<PackageConfigInfo> packageConfigs = taskEntity.getPackageConfigs();
        if (null != packageConfigs && packageConfigs.size() > 0) {
            for (PackageConfigInfo info : packageConfigs) {
                if (uom.equals(info.getPackageCode())) {
                    uomQty = info.getContainerValue();
                    break;
                }
            }
        }
    }

    private void initPaTraceIdInput() {
        etPaTraceId.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                // 处理事件
                showConfirmPaDialog();
            }
            return false;
        });
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
                showConfirmPaDialog();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void showConfirmPaDialog() {
        mPaLoc = etPaLoc.getText().toString().trim();
        mPaTraceId = etPaTraceId.getText().toString().trim();
        mPaQty = etPaQty.getText().toString().trim();
        if (TextUtils.isEmpty(mPaLoc)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_pa_loc));
            etPaLoc.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (TextUtils.isEmpty(mPaTraceId)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_pa_trace_id));
            etPaTraceId.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (TextUtils.isEmpty(mPaQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_pa_qty));
            etPaQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (!NumberUtil.isDouble(mPaQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_pa_qty));
            etPaQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (BigDecimal.valueOf(Double.valueOf(mPaQty)).compareTo(BigDecimal.ZERO) <= 0) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_pa_qty));
            etPaQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        paQty = Double.valueOf(mPaQty) * uomQty;
        if (BigDecimal.valueOf(paQty).compareTo(BigDecimal.valueOf(taskEntity.getQtyPaEa())) > 0) {
            showMessage(getResources().getString(R.string.rcv_qty_can_not_more_than_plan_qty));
            etPaQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }

        if (mConfirmPaDialog == null) {
            mConfirmPaDialog = new MaterialDialog.Builder(this)
                    .title(R.string.hint)
                    .content(R.string.whether_to_confirm_pa)
                    .positiveText(R.string.confirm)
                    .negativeText(R.string.cancel)
                    .onPositive((dialog, which) -> callConfirmEvent()).build();

            // 弹出对话框时，处理 PDA 实体回车键
            mConfirmPaDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event.getAction() && event.getRepeatCount() == 0) {
                        if (mConfirmPaDialog.isShowing()) {
                            callConfirmEvent();
                            dialog.dismiss();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        mConfirmPaDialog.show();
    }

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        SavePutAwayByTaskRequest request = new SavePutAwayByTaskRequest();
        CopyPropertiesUtil.copyProperties(taskEntity, request);
        request.setCurrentPaQtyEa(paQty);
        request.setToId(mPaTraceId);
        request.setToLoc(mPaLoc);
        mPresenter.savePutAwayByTask(request);
    }

    public void afterPutawayConfirm() {
        launchActivity(new Intent(ScanTaskPutaway2Activity.this, ScanTaskPutaway1Activity.class));
    }

}