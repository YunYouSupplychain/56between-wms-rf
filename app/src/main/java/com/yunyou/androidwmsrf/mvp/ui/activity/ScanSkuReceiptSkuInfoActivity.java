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
import com.yunyou.androidwmsrf.di.component.DaggerScanSkuReceiptSkuInfoComponent;
import com.yunyou.androidwmsrf.di.module.ScanSkuReceiptSkuInfoModule;
import com.yunyou.androidwmsrf.mvp.contract.ScanSkuReceiptSkuInfoContract;
import com.yunyou.androidwmsrf.mvp.model.entity.AsnDetailInfo;
import com.yunyou.androidwmsrf.mvp.presenter.ScanSkuReceiptSkuInfoPresenter;
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
 * 扫描商品收货SkuInfo
 *
 * @author WMJ
 * @version 2019/07/01
 */
public class ScanSkuReceiptSkuInfoActivity extends BaseActivity<ScanSkuReceiptSkuInfoPresenter> implements ScanSkuReceiptSkuInfoContract.View, IToolbar {
    @BindView(R.id.searchView)
    SearchView mSearchView;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @BindView(R.id.rv_sku_info)
    RecyclerView rvSkuInfo;

    private BaseQuickAdapter<AsnDetailInfo.AsnDetailEntity, BaseViewHolder> mAdapterSkuInfo;
    private List<AsnDetailInfo.AsnDetailEntity> listData;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerScanSkuReceiptSkuInfoComponent
                .builder()
                .appComponent(appComponent)
                .scanSkuReceiptSkuInfoModule(new ScanSkuReceiptSkuInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_scan_sku_receipt_sku_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
        setAdapterSkuInfo((AsnDetailInfo) getIntent().getSerializableExtra(Constants.INTENT_PARAMETER_ASN_DETAIL));
        // 初始化搜索框
        initSearchInput();
    }

    private void initRecyclerView() {
        rvSkuInfo.setLayoutManager(new LinearLayoutManager(ScanSkuReceiptSkuInfoActivity.this));
        mAdapterSkuInfo = new BaseQuickAdapter<AsnDetailInfo.AsnDetailEntity, BaseViewHolder>(R.layout.rv_sku_info) {
            @Override
            protected void convert(BaseViewHolder helper, AsnDetailInfo.AsnDetailEntity item) {
                // 商品编码
                helper.setText(R.id.tv_sku_code, item.getSkuCode());
                // 商品名称
                helper.setText(R.id.tv_sku_name, item.getSkuName());
                // 包装规格
                helper.setText(R.id.tv_pack_desc, item.getPackDesc());
            }
        };
        mAdapterSkuInfo.setOnItemClickListener((adapter, view, position) -> {
            AsnDetailInfo.AsnDetailEntity item = (AsnDetailInfo.AsnDetailEntity) adapter.getItem(position);
            gotoScanSkuReceipt3Activity(item);
        });
        rvSkuInfo.setAdapter(mAdapterSkuInfo);
    }

    private void setAdapterSkuInfo(AsnDetailInfo asnDetailInfo) {
        listData = asnDetailInfo.getDetailEntityList();
        mAdapterSkuInfo.setNewData(listData);
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
                        if (listData.get(i).getSkuCode().contains(newText) || listData.get(i).getSkuName().contains(newText)) {
                            result.add(listData.get(i));
                        }
                    }
                    mAdapterSkuInfo.setNewData(result);
                } else {
                    mAdapterSkuInfo.setNewData(listData);
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

    private void gotoScanSkuReceipt3Activity(AsnDetailInfo.AsnDetailEntity asnDetailInfo) {
        Intent intent = new Intent(ScanSkuReceiptSkuInfoActivity.this, ScanSkuReceipt3Activity.class);
        intent.putExtra(Constants.INTENT_PARAMETER_ASN_DETAIL, asnDetailInfo);
        launchActivity(intent);
    }

}