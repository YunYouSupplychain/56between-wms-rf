package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerScanTrayReceiptTrayInfoComponent;
import com.yunyou.androidwmsrf.di.module.ScanTrayReceiptTrayInfoModule;
import com.yunyou.androidwmsrf.mvp.contract.ScanTrayReceiptTrayInfoContract;
import com.yunyou.androidwmsrf.mvp.model.entity.AsnDetailInfo;
import com.yunyou.androidwmsrf.mvp.presenter.ScanTrayReceiptTrayInfoPresenter;
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
 * 扫描托盘收货TrayInfo
 *
 * @author WMJ
 * @version 2019/07/01
 */
public class ScanTrayReceiptTrayInfoActivity extends BaseActivity<ScanTrayReceiptTrayInfoPresenter> implements ScanTrayReceiptTrayInfoContract.View, IToolbar {
    @BindView(R.id.searchView)
    SearchView mSearchView;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @BindView(R.id.rc_trace_info)
    RecyclerView rcTrayInfo;

    private BaseQuickAdapter<AsnDetailInfo.AsnDetailEntity, BaseViewHolder> mAdapterTrayInfo;
    private List<AsnDetailInfo.AsnDetailEntity> listData;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanTrayReceiptTrayInfoComponent
                .builder()
                .appComponent(appComponent)
                .scanTrayReceiptTrayInfoModule(new ScanTrayReceiptTrayInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_tray_receipt_tray_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
        setAdapterTrayInfo((AsnDetailInfo) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_ASN_DETAIL));
        initSearchInput();
    }

    private void initRecyclerView() {
        rcTrayInfo.setLayoutManager(new LinearLayoutManager(ScanTrayReceiptTrayInfoActivity.this));
        mAdapterTrayInfo = new BaseQuickAdapter<AsnDetailInfo.AsnDetailEntity, BaseViewHolder>(R.layout.rv_tray_info) {
            @Override
            protected void convert(BaseViewHolder helper, AsnDetailInfo.AsnDetailEntity item) {
                // 跟踪号
                helper.setText(R.id.tv_trace_no, item.getPlanId());
                // 商品名称
                helper.setText(R.id.tv_sku_name, item.getSkuName());
                // 包装规格
                helper.setText(R.id.tv_pack_desc, item.getPackDesc());
            }
        };
        mAdapterTrayInfo.setOnItemClickListener((adapter, view, position) -> {
            AsnDetailInfo.AsnDetailEntity item = (AsnDetailInfo.AsnDetailEntity) adapter.getItem(position);
            gotoScanTrayReceipt3Activity(item);
        });
        rcTrayInfo.setAdapter(mAdapterTrayInfo);
    }

    private void setAdapterTrayInfo(AsnDetailInfo asnDetailInfo) {
        listData = asnDetailInfo.getDetailEntityList();
        mAdapterTrayInfo.setNewData(listData);
    }

    private void initSearchInput() {
        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    List<AsnDetailInfo.AsnDetailEntity> result = new ArrayList<>();
                    // 检索当前的列表
                    for (int i = 0, size = listData.size(); i < size; i++) {
                        if (listData.get(i).getPlanId().contains(newText) || listData.get(i).getSkuName().contains(newText)) {
                            result.add(listData.get(i));
                        }
                    }
                    mAdapterTrayInfo.setNewData(result);
                } else {
                    mAdapterTrayInfo.setNewData(listData);
                }
                return false;
            }
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

    private void gotoScanTrayReceipt3Activity(AsnDetailInfo.AsnDetailEntity asnDetailInfo) {
        Intent intent = new Intent(ScanTrayReceiptTrayInfoActivity.this, ScanTrayReceipt3Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_ASN_DETAIL, asnDetailInfo);
        launchActivity(intent);
    }

}