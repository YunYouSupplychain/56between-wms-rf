package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickingByManual2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickingByManual2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickingByManual2Module.class, dependencies = AppComponent.class)
public interface PickingByManual2Component {
    void inject(PickingByManual2Activity activity);
}