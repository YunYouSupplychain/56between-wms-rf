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
import com.yunyou.androidwmsrf.app.utils.CopyPropertiesUtil;
import com.yunyou.androidwmsrf.di.component.DaggerWholeTrayMoveComponent;
import com.yunyou.androidwmsrf.di.module.WholeTrayMoveModule;
import com.yunyou.androidwmsrf.mvp.contract.WholeTrayMoveContract;
import com.yunyou.androidwmsrf.mvp.model.entity.SkuInvDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryMovementRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveMovementRequest;
import com.yunyou.androidwmsrf.mvp.presenter.WholeTrayMovePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 整托移动
 *
 * @author WMJ
 * @version 2019/07/03
 */
public class WholeTrayMoveActivity extends BaseActivity<WholeTrayMovePresenter> implements WholeTrayMoveContract.View, IToolbar {
    @BindView(R.id.et_fm_id)
    EditText etFmId;

    @BindView(R.id.et_to_loc)
    EditText etToLoc;

    @BindView(R.id.et_to_id)
    EditText etToId;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mFmId;
    private String mToLoc;
    private String mToId;
    private SkuInvDetailInfo detailInfo;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerWholeTrayMoveComponent
                .builder()
                .appComponent(appComponent)
                .wholeTrayMoveModule(new WholeTrayMoveModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_whole_tray_move;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initTraceIdInput();
        etFmId.requestFocus();
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

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.btn_view})
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

            // 查看
            case R.id.btn_view:
                selectEvent();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void initTraceIdInput() {
        etFmId.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                // 处理事件
                selectQuery();
            }
            return false;
        });
    }

    private void selectQuery() {
        mFmId = etFmId.getText().toString().trim();
        if (TextUtils.isEmpty(mFmId)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_to_id));
            etFmId.requestFocus();
            AppLifecyclesImpl.playSound();
            detailInfo = null;
            return;
        }
        mPresenter.queryMovement(new QueryMovementRequest(null, mFmId, null, null));
    }

    private void selectEvent() {
        if (null != detailInfo) {
            Intent intent = new Intent(WholeTrayMoveActivity.this, InvQuery2Activity.class);
            intent.putExtra(Constants.INTENT_PARAMETER_INV_SKU_INFO, detailInfo);
            launchActivity(intent);
        }
    }

    @Override
    public void afterQuery(SkuInvDetailInfo info) {
        detailInfo = info;
    }

    private void callConfirmEvent() {
        mFmId = etFmId.getText().toString().trim();
        mToLoc = etToLoc.getText().toString().trim();
        mToId = etToId.getText().toString().trim();
        if (TextUtils.isEmpty(mFmId)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_fm_id));
            etFmId.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (null == detailInfo) {
            showMessage(getResources().getString(R.string.please_input_fm_id_enter));
            etFmId.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (TextUtils.isEmpty(mToLoc)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_to_loc));
            etToLoc.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (TextUtils.isEmpty(mToId)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_to_id));
            etToId.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }

        List<SaveMovementRequest> requests = new ArrayList<>();
        for (SkuInvDetailInfo.SkuInvDetailEntity entity : detailInfo.getDetailEntityList()) {
            SaveMovementRequest request = new SaveMovementRequest();
            CopyPropertiesUtil.copyProperties(entity, request);
            request.setFmLoc(entity.getLocCode());
            request.setFmTraceId(mFmId);
            request.setToLoc(mToLoc);
            request.setToTraceId(mToId);
            request.setCdprQuantity(entity.getQtyUom());
            request.setToUom(entity.getPrintUom());
            request.setToQty(entity.getQtyAvailable());
            request.setCdprQuantity(entity.getQtyUom());
            requests.add(request);
        }
        mPresenter.saveMovement(requests);
    }

    @Override
    public void afterSave() {
        etFmId.setText("");
        etToLoc.setText("");
        etToId.setText("");
        detailInfo = null;
    }
}