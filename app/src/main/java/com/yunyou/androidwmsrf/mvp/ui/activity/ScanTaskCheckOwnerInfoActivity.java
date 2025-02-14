package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerScanTaskCheckOwnerInfoComponent;
import com.yunyou.androidwmsrf.di.module.ScanTaskCheckOwnerInfoModule;
import com.yunyou.androidwmsrf.mvp.contract.ScanTaskCheckOwnerInfoContract;
import com.yunyou.androidwmsrf.mvp.model.entity.OwnerInfo;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTaskCheckOwnerInfoPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 新增盘点
 *
 * @author WMJ
 * @version 2019/07/19
 */
public class ScanTaskCheckOwnerInfoActivity extends BaseActivity<ScanTaskCheckOwnerInfoPresenter> implements ScanTaskCheckOwnerInfoContract.View, IToolbar {
    @BindView(R.id.et_owner_code)
    EditText etOwnerCode;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @BindView(R.id.rv_owner_info)
    RecyclerView rvOwnerInfo;

    private BaseQuickAdapter<OwnerInfo.OwnerEntity, BaseViewHolder> mAdapterOwnerInfo;
    private List<OwnerInfo.OwnerEntity> listData;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTaskCheckOwnerInfoComponent
                .builder()
                .appComponent(appComponent)
                .scanTaskCheckOwnerInfoModule(new ScanTaskCheckOwnerInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_task_check_owner_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
        setAdapterOwnerInfo((OwnerInfo) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_TASK_COUNT_OWNER_INFO));
        initOwnerCodeInput();
    }

    private void initRecyclerView() {
        rvOwnerInfo.setLayoutManager(new LinearLayoutManager(ScanTaskCheckOwnerInfoActivity.this));
        mAdapterOwnerInfo = new BaseQuickAdapter<OwnerInfo.OwnerEntity, BaseViewHolder>(R.layout.rv_sku_info) {
            @Override
            protected void convert(BaseViewHolder helper, OwnerInfo.OwnerEntity item) {
                // 货主编码
                helper.setText(R.id.tv_owner_code, item.getOwnerCode());
                // 货主名称
                helper.setText(R.id.tv_owner_name, item.getOwnerName());
            }
        };
        mAdapterOwnerInfo.setOnItemLongClickListener((adapter, view, position) -> {
            OwnerInfo.OwnerEntity item = (OwnerInfo.OwnerEntity) adapter.getItem(position);
            gotoScanTaskCheck5Activity(item.getOwnerCode());
            return true;
        });
        rvOwnerInfo.setAdapter(mAdapterOwnerInfo);
    }

    private void setAdapterOwnerInfo(OwnerInfo ownerInfo) {
        listData = ownerInfo.getDetailEntityList();
        mAdapterOwnerInfo.setNewData(listData);
    }

    private void initOwnerCodeInput() {
        etOwnerCode.setOnEditorActionListener((TextView v, int actionId, KeyEvent event) -> {
            // 当actionId == XX_SEND 或者 XX_DONE时都触发
            // 或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
            // 注意，这是一定要判断event != null。因为在某些输入法上会返回null。
            if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                String ownerCode = etOwnerCode.getText().toString();
                if (!TextUtils.isEmpty(ownerCode)) {
                    List<OwnerInfo.OwnerEntity> result = new ArrayList<>();
                    // 检索当前的列表
                    for (int i = 0, size = listData.size(); i < size; i++) {
                        if (ownerCode.equals(listData.get(i).getOwnerCode())) {
                            result.add(listData.get(i));
                        }
                    }
                    mAdapterOwnerInfo.setNewData(result);
                }
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

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void gotoScanTaskCheck5Activity(String ownerCode) {
        Intent intent = new Intent(ScanTaskCheckOwnerInfoActivity.this, ScanTaskCheck5Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_TASK_COUNT_OWNER, ownerCode);
        launchActivity(intent);
    }

}