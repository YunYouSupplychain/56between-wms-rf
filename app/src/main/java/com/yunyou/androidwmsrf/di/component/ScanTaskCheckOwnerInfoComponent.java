package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTaskCheckOwnerInfoModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTaskCheckOwnerInfoActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTaskCheckOwnerInfoModule.class, dependencies = AppComponent.class)
public interface ScanTaskCheckOwnerInfoComponent {
    void inject(ScanTaskCheckOwnerInfoActivity activity);
}