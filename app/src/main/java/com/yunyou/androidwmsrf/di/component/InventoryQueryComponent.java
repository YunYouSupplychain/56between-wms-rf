package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.InventoryQueryModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.InventoryQueryActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = InventoryQueryModule.class, dependencies = AppComponent.class)
public interface InventoryQueryComponent {
    void inject(InventoryQueryActivity activity);
}