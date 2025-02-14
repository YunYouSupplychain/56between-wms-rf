package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickingByOrder1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickingByOrder1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickingByOrder1Module.class, dependencies = AppComponent.class)
public interface PickingByOrder1Component {
    void inject(PickingByOrder1Activity activity);
}