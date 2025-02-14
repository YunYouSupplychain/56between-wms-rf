package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.CheckModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.CheckActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = CheckModule.class, dependencies = AppComponent.class)
public interface CheckComponent {
    void inject(CheckActivity activity);
}