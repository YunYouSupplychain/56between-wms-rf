package com.yunyou.androidwmsrf.app;

import android.app.Application;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.yunyou.androidwmsrf.BuildConfig;
import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.utils.ArmsUtils;
import com.yunyou.androidwmsrf.R;
import com.yunyou.androidwmsrf.app.global.Constants;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.ButterKnife;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import timber.log.Timber;

public class AppLifecyclesImpl implements AppLifecycles {
    public static SoundPool mSoundPool;
    public static Integer mFaSoundID;
    public static Integer mSuSoundID;

    @Override
    public void attachBaseContext(Context base) {
        // MultiDex.install(base);
        // 这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    @Override
    public void onCreate(Application application) {
        String appDomain = SPUtils.getInstance().getString(Constants.APP_DOMAIN_KEY, Constants.APP_DOMAIN_DEFAULT_VALUE);
        RetrofitUrlManager.getInstance().startAdvancedModel(appDomain);
        RetrofitUrlManager.getInstance().setGlobalDomain(appDomain);

        if (LeakCanary.isInAnalyzerProcess(application)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        if (BuildConfig.LOG_DEBUG) {//Timber初始化
            // Timber 是一个日志框架容器,外部使用统一的Api,内部可以动态的切换成任何日志框架(打印策略)进行日志打印
            // 并且支持添加多个日志框架(打印策略),做到外部调用一次 Api,内部却可以做到同时使用多个策略
            // 比如添加三个策略,一个打印日志,一个将日志保存本地,一个将日志上传服务器
            Timber.plant(new Timber.DebugTree());
            // 如果你想将框架切换为 Logger 来打印日志,请使用下面的代码,如想切换为其他日志框架请根据下面的方式扩展
            // Logger.addLogAdapter(new AndroidLogAdapter());
            // Timber.plant(new Timber.DebugTree() {
            //    @Override
            //    protected void log(int priority, String tag, String message, Throwable t) {
            //        Logger.log(priority, tag, message, t);
            //    }
            // });
            ButterKnife.setDebug(true);
        }
        // leakCanary内存泄露检查
        ArmsUtils.obtainAppComponentFromContext(application).extras().put(RefWatcher.class.getName(), BuildConfig.USE_CANARY ? LeakCanary.install(application) : RefWatcher.DISABLED);
        // 扩展 AppManager 的远程遥控功能
        ArmsUtils.obtainAppComponentFromContext(application).appManager().setHandleListener((appManager, message) -> {
        });
        // AndroidUtilCode
        Utils.init(application);
        // Bugly
        CrashReport.initCrashReport(application, "972a22112a", false);
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 5);
        mFaSoundID = mSoundPool.load(application, R.raw.fail, 1);
        mSuSoundID = mSoundPool.load(application, R.raw.success, 1);
    }

    public static void playSound() {
        if (Constants.IS_PLAY_SOUND) {
            mSoundPool.play(mFaSoundID, 1, 1, 99, 0, 1);
        }
    }

    public static void playSuccessSound() {
        if (Constants.IS_PLAY_SOUND) {
            mSoundPool.play(mSuSoundID, 1, 1, 99, 0, 1);
        }
    }

    @Override
    public void onTerminate(Application application) {

    }
}