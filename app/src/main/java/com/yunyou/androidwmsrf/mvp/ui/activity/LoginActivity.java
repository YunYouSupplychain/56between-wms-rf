package com.yunyou.androidwmsrf.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.yunyou.androidwmsrf.app.AppLifecyclesImpl;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.yunyou.androidwmsrf.di.component.DaggerLoginComponent;
import com.yunyou.androidwmsrf.di.module.LoginModule;
import com.yunyou.androidwmsrf.mvp.model.webservice.LoginRequest;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.mvp.contract.LoginContract;
import com.yunyou.androidwmsrf.mvp.model.entity.Version;
import com.yunyou.androidwmsrf.mvp.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;

/**
 * 登录
 *
 * @author WMJ
 * @version 2019/06/29
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.et_account)
    EditText etAccount;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.iv_visible)
    ImageView ivVisible;

    @BindView(R.id.tv_version)
    TextView tvVersion;

    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    @BindView(R.id.iv_banner)
    ImageView ivBanner;

    // 声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;

    private DownloadBuilder mDownloadBuilder;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        // 如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tvVersion.setText(AppUtils.getAppVersionName());
        SPUtils.getInstance().put(Constants.USER_SESSION_ID, "");
        mPresenter.getAppVersion("WMS_RF");

        ivBanner.setOnLongClickListener((e) -> {
            startActivity(new Intent(LoginActivity.this, SetServerActivity.class));
            return true;
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

    @OnClick({R.id.iv_visible, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            // 密码可见
            case R.id.iv_visible:
                if (etPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                    // 设置密码不可见
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    ivVisible.setImageResource(R.drawable.icon_invisible);
                } else {
                    // 设置密码可见
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    ivVisible.setImageResource(R.drawable.icon_visible);
                }
                break;

            // 登录
            case R.id.btn_login:
                String userName = etAccount.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    showMessage(getResources().getString(R.string.please_input_account));
                    etAccount.requestFocus();
                    AppLifecyclesImpl.playSound();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    showMessage(getResources().getString(R.string.please_input_password));
                    etPassword.requestFocus();
                    AppLifecyclesImpl.playSound();
                    return;
                }

                mPresenter.login(new LoginRequest(userName, password));
                break;
        }
    }

    @Override
    public void updateAppVersion(Version version) {
        if (version == null) {
            return;
        }

        String localVersionName = AppUtils.getAppVersionName();
        String serverVersionName = version.getVersionName();

        // 服务器版本有更新
        if (localVersionName.compareTo(serverVersionName) < 0) {
            // 显示更新对话框
            mDownloadBuilder = AllenVersionChecker
                    .getInstance()
                    .downloadOnly(crateUIData(version.getVersionName(), version.getDownloadAddress(), version.getVersionInfo()));
            mDownloadBuilder.setForceRedownload(true);
            mDownloadBuilder.executeMission(LoginActivity.this);
        } else {
            // 当前已是最新版本
            ToastUtils.showShort(R.string.current_version_is_latest);
        }
    }

    /**
     * 使用请求版本功能，可以在这里设置downloadUrl
     * 这里可以构造UI需要显示的数据
     * UIData 内部是一个Bundle
     */
    private UIData crateUIData(String versionName, String downloadAddress, String versionInfo) {
        UIData uiData = UIData.create();
        uiData.setTitle(getString(R.string.there_is_new_version) + " V" + versionName);
        uiData.setDownloadUrl(downloadAddress);
        uiData.setContent(versionInfo);
        return uiData;
    }

    @Override
    public void goToMainActivity() {
        launchActivity(new Intent(LoginActivity.this, MainActivity.class));
        killMyself();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                // 大于2000ms则认为是误操作，使用Toast进行提示
                ToastUtils.showShort(R.string.press_again_to_quit);
                // 并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                // 小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}