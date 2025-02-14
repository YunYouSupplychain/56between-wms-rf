package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.di.component.DaggerMoveComponent;
import com.yunyou.androidwmsrf.di.module.MoveModule;
import com.yunyou.androidwmsrf.mvp.contract.MoveContract;
import com.yunyou.androidwmsrf.mvp.presenter.MovePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 移动
 *
 * @author WMJ
 * @version 2019/07/03
 */
public class MoveActivity extends BaseActivity<MovePresenter> implements MoveContract.View, IToolbar {
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerMoveComponent
                .builder()
                .appComponent(appComponent)
                .moveModule(new MoveModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_move;
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

    @OnClick({R.id.btn_standard_move, R.id.btn_whole_tray_move, R.id.btn_whole_loc_move})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 标准移动
            case R.id.btn_standard_move:
                goToStandardMoveByActivity();
                break;

            // 整托盘
            case R.id.btn_whole_tray_move:
                goToWholeTrayMoveActivity();
                break;

            // 整库位移动
            case R.id.btn_whole_loc_move:
                goToWholeLocMoveActivity();
                break;
        }
    }

    @Override
    public boolean isShowBackButton() {
        return true;
    }

    @Override
    public void goToStandardMoveByActivity() {
        launchActivity(new Intent(MoveActivity.this, StandardMoveActivity.class));
    }

    @Override
    public void goToWholeTrayMoveActivity() {
        launchActivity(new Intent(MoveActivity.this, WholeTrayMoveActivity.class));
    }

    @Override
    public void goToWholeLocMoveActivity() {
        launchActivity(new Intent(MoveActivity.this, WholeLocMoveActivity.class));
    }
}