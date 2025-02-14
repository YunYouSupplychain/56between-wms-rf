package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PickingByOrder2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.PickingByOrder2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PickingByOrder2Module.class, dependencies = AppComponent.class)
public interface PickingByOrder2Component {
    void inject(PickingByOrder2Activity activity);
}