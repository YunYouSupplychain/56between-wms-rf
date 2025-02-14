package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yunyou.androidwmsrf.app.IToolbar;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerSetServerComponent;
import com.yunyou.androidwmsrf.di.module.SetServerModule;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.mvp.contract.SetServerContract;
import com.yunyou.androidwmsrf.mvp.model.api.Api;
import com.yunyou.androidwmsrf.mvp.presenter.SetServerPresenter;

import butterknife.BindView;
import butterknife.OnClick;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;

import static com.jess.arms.utils.Preconditions.checkNotNull;

public class SetServerActivity extends BaseActivity<SetServerPresenter> implements SetServerContract.View, IToolbar {

    @BindView(R.id.et_server_address)
    EditText etServerAddress;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String mServerAddress;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        // 如找不到该类,请编译一下项目
        DaggerSetServerComponent
                .builder()
                .appComponent(appComponent)
                .setServerModule(new SetServerModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_set_server; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        String appDomain = SPUtils.getInstance().getString(Constants.APP_DOMAIN_KEY, Constants.APP_DOMAIN_DEFAULT_VALUE);
        etServerAddress.setText(appDomain);
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

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        // 保存
        mServerAddress = etServerAddress.getText().toString().trim();
        if (TextUtils.isEmpty(mServerAddress)) {
            showMessage(getResources().getString(R.string.please_input_server_address));
            return;
        }
        // 服务器地址加上 【http://】 前缀
        if (!mServerAddress.startsWith("http://") && !mServerAddress.startsWith("https://")) {
            mServerAddress = "http://" + mServerAddress;
        }
        // 服务器地址增加 【/】 后缀
        if (!mServerAddress.endsWith("/")) {
            mServerAddress += "/";
        }
        Api.APP_DOMAIN = mServerAddress;
        RetrofitUrlManager.getInstance().setGlobalDomain(Api.APP_DOMAIN);
        SPUtils.getInstance().put(Constants.APP_DOMAIN_KEY, Api.APP_DOMAIN);
        ToastUtils.showShort(R.string.save_success);
        killMyself();
    }
}