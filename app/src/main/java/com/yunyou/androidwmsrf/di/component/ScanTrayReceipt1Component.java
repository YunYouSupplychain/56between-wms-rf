package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTrayReceipt1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTrayReceipt1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTrayReceipt1Module.class, dependencies = AppComponent.class)
public interface ScanTrayReceipt1Component {
    void inject(ScanTrayReceipt1Activity activity);
}