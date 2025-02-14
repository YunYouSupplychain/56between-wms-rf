package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.WholeTrayMoveModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.WholeTrayMoveActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = WholeTrayMoveModule.class, dependencies = AppComponent.class)
public interface WholeTrayMoveComponent {
    void inject(WholeTrayMoveActivity activity);
}