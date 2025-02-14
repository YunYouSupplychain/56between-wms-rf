package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickingByWave2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickingByWave2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickingByWave2Module.class, dependencies = AppComponent.class)
public interface PickingByWave2Component {
    void inject(PickingByWave2Activity activity);
}