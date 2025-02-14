package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerScanTaskCheck2Component;
import com.yunyou.androidwmsrf.di.module.ScanTaskCheck2Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheck2Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.TaskCountDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryTaskCountDetailRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTaskCheck2Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 扫描任务盘点2
 *
 * @author WMJ
 * @version 2019/07/05
 */
public class ScanTaskCheck2Activity extends BaseActivity<ScanTaskCheck2Presenter> implements ScanTaskCheck2Contract.View, IToolbar {
    @BindView(R.id.tv_count_no)
    TextView tvCountNo;

    @BindView(R.id.et_zone_code)
    EditText etZoneCode;

    @BindView(R.id.et_lane)
    EditText etLane;

    @BindView(R.id.et_loc_code)
    EditText etLocCode;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mCountNo;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTaskCheck2Component
                .builder()
                .appComponent(appComponent)
                .scanTaskCheck2Module(new ScanTaskCheck2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_task_check2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mCountNo = getIntent().getStringExtra(Constants.INTENT_PARAMETER_COUNT_NO);
        initLocCodeInput();
        tvCountNo.setText(mCountNo);
        etZoneCode.requestFocus();
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
                callConfirmEvent();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void initLocCodeInput() {
        etLocCode.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
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
        String zoneCode = etZoneCode.getText().toString().trim();
        String lane = etLane.getText().toString().trim();
        String locCode = etLocCode.getText().toString().trim();
        mPresenter.queryTaskCountDetail(new QueryTaskCountDetailRequest(mCountNo, zoneCode, lane, locCode));
    }

    @Override
    public void goToScanTaskCheck3Activity(TaskCountDetailInfo info) {
        Intent intent = new Intent(ScanTaskCheck2Activity.this, ScanTaskCheck3Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_TASK_COUNT_DETAIN_INFO, info);
        launchActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ScanTaskCheck2Activity.this, ScanTaskCheck1Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_COUNT_NO, mCountNo);
        launchActivity(intent);
    }

}