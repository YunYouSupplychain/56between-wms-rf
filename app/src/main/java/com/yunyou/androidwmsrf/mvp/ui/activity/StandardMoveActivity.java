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
import com.yunyou.androidwmsrf.di.component.DaggerStandardMoveComponent;
import com.yunyou.androidwmsrf.di.module.StandardMoveModule;
import com.yunyou.androidwmsrf.mvp.contract.StandardMoveContract;
import com.yunyou.androidwmsrf.mvp.model.entity.SkuInvDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryMovementRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveMovementRequest;
import com.yunyou.androidwmsrf.mvp.presenter.StandardMovePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 标准移动
 *
 * @author WMJ
 * @version 2019/07/03
 */
public class StandardMoveActivity extends BaseActivity<StandardMovePresenter> implements StandardMoveContract.View, IToolbar {
    @BindView(R.id.et_fm_loc)
    EditText etFmLoc;

    @BindView(R.id.et_fm_id)
    EditText etFmId;

    @BindView(R.id.et_sku_code)
    EditText etSkuCode;

    @BindView(R.id.et_lot_num)
    EditText etLotNum;

    @BindView(R.id.et_move_qty)
    EditText etMoveQty;

    @BindView(R.id.et_to_loc)
    EditText etToLoc;

    @BindView(R.id.et_to_id)
    EditText etToId;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mFmLoc;
    private String mFmId;
    private String mSkuCode;
    private String mLotNum;
    private String mMoveQty;
    private String mToLoc;
    private String mToId;
    private SkuInvDetailInfo.SkuInvDetailEntity skuInvDetailEntity;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerStandardMoveComponent
                .builder()
                .appComponent(appComponent)
                .standardMoveModule(new StandardMoveModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_standard_move;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initSkuInput();
        etFmLoc.requestFocus();
        etFmId.setText("*");
        etToId.setText("*");
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

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.btn_select_sku, R.id.btn_select_lot_num})
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

            // 商品选择
            case R.id.btn_select_sku:
                selectSkuInfo(null);
                break;

            // 批次选择
            case R.id.btn_select_lot_num:
                selectSkuInfo(mSkuCode);
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void initSkuInput() {
        etSkuCode.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                mSkuCode = etSkuCode.getText().toString().trim();
                if (TextUtils.isEmpty(mSkuCode)) {
                    showMessage(getResources().getString(R.string.please_scan_or_input_sku));
                    etSkuCode.requestFocus();
                    AppLifecyclesImpl.playSound();
                    return false;
                }
                // 处理事件
                selectSkuInfo(mSkuCode);
            }
            return false;
        });
    }

    private void selectSkuInfo(String skuCode) {
        mFmLoc = etFmLoc.getText().toString().trim();
        mFmId = etFmId.getText().toString().trim();
        if (TextUtils.isEmpty(mFmLoc)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_fm_loc));
            etFmLoc.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (TextUtils.isEmpty(mFmId)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_fm_id));
            etFmId.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mPresenter.queryMovement(new QueryMovementRequest(skuCode, mFmId, mFmLoc, null));
    }

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        mFmLoc = etFmLoc.getText().toString().trim();
        if (TextUtils.isEmpty(mFmLoc)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_fm_loc));
            etFmLoc.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mFmId = etFmId.getText().toString().trim();
        if (TextUtils.isEmpty(mFmId)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_fm_id));
            etFmId.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mSkuCode = etSkuCode.getText().toString().trim();
        if (TextUtils.isEmpty(mSkuCode)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_sku));
            etSkuCode.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mLotNum = etLotNum.getText().toString().trim();
        if (TextUtils.isEmpty(mLotNum)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_lot_num));
            etLotNum.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mMoveQty = etMoveQty.getText().toString().trim();
        if (TextUtils.isEmpty(mMoveQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_move_qty));
            etMoveQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (!NumberUtil.isDouble(mMoveQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_move_qty));
            etMoveQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (BigDecimal.valueOf(Double.valueOf(mMoveQty)).compareTo(BigDecimal.ZERO) <= 0) {
            showMessage(getResources().getString(R.string.please_scan_or_input_correct_move_qty));
            etMoveQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mToLoc = etToLoc.getText().toString().trim();
        if (TextUtils.isEmpty(mToLoc)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_to_loc));
            etToLoc.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        mToId = etToId.getText().toString().trim();
        if (TextUtils.isEmpty(mToId)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_to_id));
            etToId.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (null == skuInvDetailEntity) {
            showMessage(getResources().getString(R.string.please_input_sku_enter));
            etSkuCode.requestFocus();
            AppLifecyclesImpl.playSound();
        }

        List<SaveMovementRequest> requests = new ArrayList<>();
        SaveMovementRequest request = new SaveMovementRequest();
        request.setFmLoc(mFmLoc);
        request.setFmTraceId(mFmId);
        request.setSkuCode(mSkuCode);
        request.setLotNum(mLotNum);
        request.setPackCode(skuInvDetailEntity.getPackCode());
        request.setToLoc(mToLoc);
        request.setToTraceId(mToId);
        request.setToQty(Double.valueOf(mMoveQty));
        request.setToUom(skuInvDetailEntity.getPrintUom());
        request.setCdprQuantity(skuInvDetailEntity.getQtyUom());
        requests.add(request);
        mPresenter.saveMovement(requests);
    }

    @Override
    public void afterQuery(SkuInvDetailInfo info) {
        Intent intent = new Intent(this, InvQueryByMoveActivity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_INV_SKU_INFO, info);
        startActivityForResult(intent, 0x002);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0x001) {
            switch (requestCode) {
                case 0x002:
                    afterSelectInv(data);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void afterSelectInv(Intent intent) {
        skuInvDetailEntity = (SkuInvDetailInfo.SkuInvDetailEntity) intent.getSerializableExtra(Constants.INTENT_PARAMETER_INV_SKU);
        mSkuCode = skuInvDetailEntity.getSkuCode();
        etSkuCode.setText(skuInvDetailEntity.getSkuCode());
        etLotNum.setText(skuInvDetailEntity.getLotNum());
        etMoveQty.requestFocus();
    }

    @Override
    public void afterSave() {
        skuInvDetailEntity = null;
        etFmLoc.setText("");
        etFmId.setText("");
        etSkuCode.setText("");
        mSkuCode = "";
        etLotNum.setText("");
        etMoveQty.setText("");
        etToLoc.setText("");
        etToId.setText("");
    }
}