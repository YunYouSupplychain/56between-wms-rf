package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTrayReceipt2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTrayReceipt2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTrayReceipt2Module.class, dependencies = AppComponent.class)
public interface ScanTrayReceipt2Component {
    void inject(ScanTrayReceipt2Activity activity);
}