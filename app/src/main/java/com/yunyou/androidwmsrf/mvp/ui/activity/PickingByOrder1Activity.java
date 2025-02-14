package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerPickingByOrder1Component;
import com.yunyou.androidwmsrf.di.module.PickingByOrder1Module;
import com.yunyou.androidwmsrf.mvp.contract.PickingByOrder1Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.PickDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickDetailRequest;
import com.yunyou.androidwmsrf.mvp.presenter.PickingByOrder1Presenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.yunyou.androidwmsrf.app.global.Constants.INTENT_PARAMETER_PICK_DETAIL_INFO;
import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 按单拣货1
 *
 * @author WMJ
 * @version 2019/07/03
 */
public class PickingByOrder1Activity extends BaseActivity<PickingByOrder1Presenter> implements PickingByOrder1Contract.View, IToolbar {
    @BindView(R.id.et_so_no)
    EditText etSoNo;

    @BindView(R.id.et_pick_no)
    EditText etPickNo;

    @BindView(R.id.et_pick_task_id)
    EditText etPickTaskId;

    @BindView(R.id.et_wave_no)
    EditText etWaveNo;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mSoNo;
    private String mPickNo;
    private String pickTaskId;
    private String mWaveNo;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerPickingByOrder1Component
                .builder()
                .appComponent(appComponent)
                .pickingByOrder1Module(new PickingByOrder1Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_picking_by_order1;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        etSoNo.requestFocus();
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

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        mSoNo = etSoNo.getText().toString().trim();
        mPickNo = etPickNo.getText().toString().trim();
        pickTaskId = etPickTaskId.getText().toString().trim();
        mWaveNo = etWaveNo.getText().toString().trim();
        if (TextUtils.isEmpty(mSoNo) && TextUtils.isEmpty(mPickNo) && TextUtils.isEmpty(pickTaskId) && TextUtils.isEmpty(mWaveNo)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_so_no_or_pick_no_or_pick_task_id_or_wave_no));
            AppLifecyclesImpl.playSound();
            return;
        }
        mPresenter.queryPickDetail(new QueryPickDetailRequest(mSoNo, mPickNo, pickTaskId, "1", mWaveNo));
    }

    @Override
    public void goToPickingByOrder2Activity(PickDetailInfo info) {
        Intent intent = new Intent(PickingByOrder1Activity.this, PickingByOrder2Activity.class);
        intent.putExtra(INTENT_PARAMETER_PICK_DETAIL_INFO, info);
        launchActivity(intent);
    }

    @Override
    public void onBackPressed() {
        launchActivity(new Intent(PickingByOrder1Activity.this, PickingActivity.class));
    }
}