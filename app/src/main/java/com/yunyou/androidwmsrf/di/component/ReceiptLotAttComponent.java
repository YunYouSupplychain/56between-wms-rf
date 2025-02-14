package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ReceiptLotAttModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ReceiptLotAttActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ReceiptLotAttModule.class, dependencies = AppComponent.class)
public interface ReceiptLotAttComponent {
    void inject(ReceiptLotAttActivity activity);
}