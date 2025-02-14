package com.yunyou.androidwmsrf.di.component;

import dagger.Component;

import com.yunyou.androidwmsrf.di.module.GetFrameUnBindModule;
import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.ActivityScope;
import com.yunyou.androidwmsrf.mvp.ui.activity.GetFrameUnBindActivity;

@ActivityScope
@Component(modules = GetFrameUnBindModule.class, dependencies = AppComponent.class)
public interface GetFrameUnBindComponent {
    void inject(GetFrameUnBindActivity activity);
}