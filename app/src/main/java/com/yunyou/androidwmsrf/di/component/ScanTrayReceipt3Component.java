package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTrayReceipt3Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTrayReceipt3Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTrayReceipt3Module.class, dependencies = AppComponent.class)
public interface ScanTrayReceipt3Component {
    void inject(ScanTrayReceipt3Activity activity);
}