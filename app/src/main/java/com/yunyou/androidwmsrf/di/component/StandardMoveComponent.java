package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.StandardMoveModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.StandardMoveActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = StandardMoveModule.class, dependencies = AppComponent.class)
public interface StandardMoveComponent {
    void inject(StandardMoveActivity activity);
}