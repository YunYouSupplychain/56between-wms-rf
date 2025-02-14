package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ReceiptModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ReceiptActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ReceiptModule.class, dependencies = AppComponent.class)
public interface ReceiptComponent {
    void inject(ReceiptActivity activity);
}