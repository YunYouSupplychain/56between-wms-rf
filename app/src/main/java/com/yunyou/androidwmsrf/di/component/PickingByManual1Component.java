package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickingByManual1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickingByManual1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickingByManual1Module.class, dependencies = AppComponent.class)
public interface PickingByManual1Component {
    void inject(PickingByManual1Activity activity);
}