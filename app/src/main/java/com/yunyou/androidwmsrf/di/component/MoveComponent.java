package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.MoveModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.MoveActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = MoveModule.class, dependencies = AppComponent.class)
public interface MoveComponent {
    void inject(MoveActivity activity);
}