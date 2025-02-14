package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ShipmentModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ShipmentActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ShipmentModule.class, dependencies = AppComponent.class)
public interface ShipmentComponent {
    void inject(ShipmentActivity activity);
}