package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickingModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickingActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickingModule.class, dependencies = AppComponent.class)
public interface PickingComponent {
    void inject(PickingActivity activity);
}