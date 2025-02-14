package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickLotAttModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickLotAttActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickLotAttModule.class, dependencies = AppComponent.class)
public interface PickLotAttComponent {
    void inject(PickLotAttActivity activity);
}