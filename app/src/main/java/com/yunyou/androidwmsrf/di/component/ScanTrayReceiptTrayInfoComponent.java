package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTrayReceiptTrayInfoModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTrayReceiptTrayInfoActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTrayReceiptTrayInfoModule.class, dependencies = AppComponent.class)
public interface ScanTrayReceiptTrayInfoComponent {
    void inject(ScanTrayReceiptTrayInfoActivity activity);
}