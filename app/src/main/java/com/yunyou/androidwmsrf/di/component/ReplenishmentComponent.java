package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ReplenishmentModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.ReplenishmentActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ReplenishmentModule.class, dependencies = AppComponent.class)
public interface ReplenishmentComponent {
    void inject(ReplenishmentActivity activity);
}