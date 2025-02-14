package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickingByWave1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickingByWave1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickingByWave1Module.class, dependencies = AppComponent.class)
public interface PickingByWave1Component {
    void inject(PickingByWave1Activity activity);
}