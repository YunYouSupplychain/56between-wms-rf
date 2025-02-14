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
import com.yunyou.androidwmsrf.di.component.DaggerScanTrayReceipt1Component;
import com.yunyou.androidwmsrf.di.module.ScanTrayReceipt1Module;
import com.yunyou.androidwmsrf.mvp.contract.ScanTrayReceipt1Contract;
import com.yunyou.androidwmsrf.mvp.model.webservice.CheckAsnIsPalletizeRequest;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTrayReceipt1Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 扫描托盘收货1
 *
 * @author WMJ
 * @version 2019/06/29
 */
public class ScanTrayReceipt1Activity extends BaseActivity<ScanTrayReceipt1Presenter> implements ScanTrayReceipt1Contract.View, IToolbar {
    @BindView(R.id.et_asn_no)
    EditText etAsnNo;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mAsnNo;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTrayReceipt1Component
                .builder()
                .appComponent(appComponent)
                .scanTrayReceipt1Module(new ScanTrayReceipt1Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_tray_receipt1;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initAsnNoInput();
        etAsnNo.requestFocus();
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

    private void initAsnNoInput() {
        etAsnNo.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
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
        mAsnNo = etAsnNo.getText().toString().trim();
        if (TextUtils.isEmpty(mAsnNo)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_asn_no));
            etAsnNo.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }

        mPresenter.checkAsnIsPalletize(new CheckAsnIsPalletizeRequest(mAsnNo, "1"));
    }

    @Override
    public void goToScanTrayReceipt2Activity() {
        Intent intent = new Intent(ScanTrayReceipt1Activity.this, ScanTrayReceipt2Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_ASN, mAsnNo);
        launchActivity(intent);
    }

    @Override
    public void onBackPressed() {
        launchActivity(new Intent(ScanTrayReceipt1Activity.this, ReceiptActivity.class));
    }

}