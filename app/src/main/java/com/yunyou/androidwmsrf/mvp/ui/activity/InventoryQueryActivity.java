package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerInventoryQueryComponent;
import com.yunyou.androidwmsrf.di.module.InventoryQueryModule;
import com.yunyou.androidwmsrf.mvp.contract.InventoryQueryContract;
import com.yunyou.androidwmsrf.mvp.presenter.InventoryQueryPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 库存查询
 *
 * @author WMJ
 * @version 2019/07/05
 */
public class InventoryQueryActivity extends BaseActivity<InventoryQueryPresenter> implements InventoryQueryContract.View, IToolbar {
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerInventoryQueryComponent
                .builder()
                .appComponent(appComponent)
                .inventoryQueryModule(new InventoryQueryModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_inventory_query;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
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

    @OnClick({R.id.btn_inv_query, R.id.btn_pick_box_query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 库存查询
            case R.id.btn_inv_query:
                goToInvQuery1Activity();
                break;

            case R.id.btn_pick_box_query:
                goToInvPickDetailQuery1Activity();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    private void goToInvQuery1Activity() {
        launchActivity(new Intent(InventoryQueryActivity.this, InvQuery1Activity.class));
    }

    private void goToInvPickDetailQuery1Activity() {
        launchActivity(new Intent(InventoryQueryActivity.this, InvPickBoxQuery1Activity.class));
    }

}