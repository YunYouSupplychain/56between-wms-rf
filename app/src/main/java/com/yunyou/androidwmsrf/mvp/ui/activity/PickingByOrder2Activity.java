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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.StringUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.app.utils.CopyPropertiesUtil;
import com.yunyou.androidwmsrf.app.utils.NumberUtil;
import com.yunyou.androidwmsrf.di.component.DaggerPickingByOrder2Component;
import com.yunyou.androidwmsrf.di.module.PickingByOrder2Module;
import com.yunyou.androidwmsrf.mvp.contract.PickingByOrder2Contract;
import com.yunyou.androidwmsrf.mvp.model.entity.PackageConfigInfo;
import com.yunyou.androidwmsrf.mvp.model.entity.PickDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickRequest;
import com.yunyou.androidwmsrf.mvp.presenter.PickingByOrder2Presenter;
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
 * 按单拣货2
 *
 * @author WMJ
 * @version 2019/07/03
 */
public class PickingByOrder2Activity extends BaseActivity<PickingByOrder2Presenter> implements PickingByOrder2Contract.View, IToolbar {
    @BindView(R.id.tv_so_no)
    TextView tvSoNo;

    @BindView(R.id.tv_fm_loc)
    TextView tvFmLoc;

    @BindView(R.id.tv_fm_id)
    TextView tvFmId;

    @BindView(R.id.tv_sku_code)
    TextView tvFmCode;

    @BindView(R.id.tv_sku_name)
    TextView tvFmName;

    @BindView(R.id.tv_lot_num)
    TextView tvLotNum;

    @BindView(R.id.tv_pack_desc)
    TextView tvPackDesc;

    @BindView(R.id.tv_f_uom)
    TextView tvFmFUom;

    @BindView(R.id.tv_alloc_qty)
    TextView tvAllocQty;

    @BindView(R.id.sp_t_uom)
    SherlockSpinner spTUom;

    @BindView(R.id.et_pick_qty)
    EditText etPickQty;

    @BindView(R.id.et_to_sku)
    EditText etToSku;

    @BindView(R.id.et_to_loc)
    EditText etToLoc;

    @BindView(R.id.et_to_id)
    EditText etToId;

    @BindView(R.id.tv_total_desc)
    TextView tvTotalDesc;

    @BindView(R.id.ll_seq)
    LinearLayout llSeq;

    @BindView(R.id.tv_seq)
    TextView tvSeq;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    List<PickDetailInfo.PickDetailEntity> pickDetailEntityList;
    private PickDetailInfo.PickDetailEntity pickDetailEntity;
    private int uomQty = 1;
    private int index = 0;
    private String mPickQty;
    private String mToLoc;
    private String mToId;
    private MaterialDialog mConfirmPickDialog;
    private int pickCount = 0;
    private boolean isScanSku = false;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerPickingByOrder2Component
                .builder()
                .appComponent(appComponent)
                .pickingByOrder2Module(new PickingByOrder2Module(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_picking_by_order2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        PickDetailInfo detailInfo = (PickDetailInfo) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_PICK_DETAIL_INFO);
        pickDetailEntityList = detailInfo.getDetailEntityList();
        pickDetailEntity = detailInfo.getDetailEntityList().get(0);
        index = 0;
        pickCount = pickDetailEntity.getPickNum().intValue();
        initViewData();
        initToSkuInput();
        initToIdInput();
    }

    private void initViewData() {
        initUomQty(pickDetailEntity.getUom());
        tvSoNo.setText(pickDetailEntity.getSoNo());
        tvFmLoc.setText(pickDetailEntity.getLocCode());
        tvFmId.setText(pickDetailEntity.getTraceId());
        tvFmCode.setText(pickDetailEntity.getSkuCode());
        tvFmName.setText(pickDetailEntity.getSkuName());
        tvLotNum.setText(pickDetailEntity.getLotNum());
        tvPackDesc.setText(pickDetailEntity.getPackDesc());
        tvFmFUom.setText(pickDetailEntity.getUomDesc());
        tvAllocQty.setText(String.valueOf(pickDetailEntity.getQtyUom()));
        String desc = pickCount + "/" + pickDetailEntity.getTotalNum();
        tvTotalDesc.setText(desc);
        initSelectUom(pickDetailEntity.getPackageConfigs());
        spTUom.setText(pickDetailEntity.getUomDesc());
        etPickQty.setText(String.valueOf(pickDetailEntity.getQtyUom()));
        etToSku.setText("");
        etToLoc.setText(pickDetailEntity.getToLoc());
        etToId.setText(pickDetailEntity.getToId());
        etToSku.requestFocus();
        if (StringUtils.isTrimEmpty(pickDetailEntity.getSeq())) {
            llSeq.setVisibility(View.GONE);
        } else {
            tvSeq.setText(pickDetailEntity.getSeq());
        }
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
        spTUom.setAdapter(adapter);
        spTUom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                PackageConfigInfo info = (PackageConfigInfo) adapterView.getItemAtPosition(position);
                uomQty = info.getContainerValue();
            }
        });
    }

    private void initUomQty(String uom) {
        List<PackageConfigInfo> packageConfigs = pickDetailEntity.getPackageConfigs();
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

    @OnClick({R.id.btn_cancel, R.id.btn_confirm, R.id.btn_last, R.id.btn_next, R.id.btn_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 取消
            case R.id.btn_cancel:
                killMyself();
                break;

            // 确认
            case R.id.btn_confirm:
                showConfirmPickDialog();
                break;

            // 上一条
            case R.id.btn_last:
                lastEvent();
                break;

            // 下一条
            case R.id.btn_next:
                nextEvent();
                break;

            // 选择批次信息
            case R.id.btn_select:
                goToPickLotAttActivity();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void lastEvent() {
        if (index == 0) {
            showMessage("已经是第一条");
            return;
        }
        index--;
        pickDetailEntity = pickDetailEntityList.get(index);
        initViewData();
    }

    private void nextEvent() {
        if (index == pickDetailEntityList.size() - 1) {
            showMessage("已经是最后一条");
            return;
        }
        index++;
        pickDetailEntity = pickDetailEntityList.get(index);
        initViewData();
    }

    private void showConfirmPickDialog() {
        mPickQty = etPickQty.getText().toString().trim();
        mToLoc = etToLoc.getText().toString().trim();
        mToId = etToId.getText().toString().trim();
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
        if (TextUtils.isEmpty(mPickQty)) {
            showMessage(getResources().getString(R.string.please_scan_or_input_pick_qty));
            etPickQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (!NumberUtil.isDouble(mPickQty)) {
            showMessage(getResources().getString(R.string.please_input_correct_pick_qty));
            etPickQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        BigDecimal pickQty = BigDecimal.valueOf(Double.parseDouble(mPickQty) * uomQty);
        mPickQty = pickQty.toString();
        if (pickQty.compareTo(BigDecimal.ZERO) <= 0) {
            showMessage(getResources().getString(R.string.please_input_correct_pick_qty));
            etPickQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (pickQty.compareTo(BigDecimal.valueOf(pickDetailEntity.getQtyEa())) > 0) {
            showMessage(getResources().getString(R.string.pick_qty_can_not_more_than_alloc_qty));
            etPickQty.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        String barCode = etToSku.getText().toString().trim();
        if (TextUtils.isEmpty(barCode)) {
            showMessage(getResources().getString(R.string.please_scan_to_sku));
            etToSku.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }
        if (!isScanSku) {
            showMessage(getResources().getString(R.string.please_input_to_sku_enter));
            etToSku.requestFocus();
            AppLifecyclesImpl.playSound();
            return;
        }

        if (mConfirmPickDialog == null) {
            mConfirmPickDialog = new MaterialDialog.Builder(this)
                    .title(R.string.hint)
                    .content(R.string.whether_to_confirm_pick)
                    .positiveText(R.string.confirm)
                    .negativeText(R.string.cancel)
                    .onPositive((dialog, which) -> callConfirmEvent()).build();

            // 弹出对话框时，处理 PDA 实体回车键
            mConfirmPickDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER && KeyEvent.ACTION_DOWN == event.getAction() && event.getRepeatCount() == 0) {
                        if (mConfirmPickDialog.isShowing()) {
                            callConfirmEvent();
                            dialog.dismiss();
                            return true;
                        }
                    }
                    return false;
                }
            });
        }
        mConfirmPickDialog.show();
    }

    private void initToSkuInput() {
        etToSku.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                // 处理事件
                String toSku = etToSku.getText().toString().trim();
                String barcode = !StringUtils.isEmpty(pickDetailEntity.getBarcode()) ? (pickDetailEntity.getBarcode() + pickDetailEntity.getSkuCode()) : pickDetailEntity.getSkuCode();
                if (!barcode.contains(toSku)) {
                    isScanSku = false;
                    showMessage(getResources().getString(R.string.barcode_is_not_match_this_sku));
                    etToSku.selectAll();
                    AppLifecyclesImpl.playSound();
                } else {
                    isScanSku = true;
                }
            }
            return false;
        });
    }

    private void initToIdInput() {
        etToId.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                // 处理事件
                showConfirmPickDialog();
            }
            return false;
        });
    }

    /**
     * 确认按钮事件
     */
    private void callConfirmEvent() {
        SavePickRequest request = new SavePickRequest();
        CopyPropertiesUtil.copyProperties(pickDetailEntity, request);
        request.setQtyPkEa(Double.valueOf(mPickQty));
        request.setToLoc(mToLoc);
        request.setToId(mToId);
        mPresenter.savePick(request);
    }

    @Override
    public void afterSave() {
        pickDetailEntityList.remove(pickDetailEntity);
        if (pickDetailEntityList.size() == 0) {
            launchActivity(new Intent(PickingByOrder2Activity.this, PickingByOrder1Activity.class));
        } else {
            index = 0;
            pickDetailEntity = pickDetailEntityList.get(index);
            initViewData();
            pickCount++;
            String desc = pickCount + "/" + pickDetailEntity.getTotalNum();
            tvTotalDesc.setText(desc);
        }
    }

    private void goToPickLotAttActivity() {
        Intent intent = new Intent(PickingByOrder2Activity.this, PickLotAttActivity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_PICK_DETAIL, pickDetailEntity);
        launchActivity(intent);
    }

}