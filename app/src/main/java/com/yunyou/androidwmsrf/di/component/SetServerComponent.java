package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.SetServerModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.yunyou.androidwmsrf.mvp.ui.activity.SetServerActivity;

import dagger.Component;

@ActivityScope
@Component(modules = SetServerModule.class, dependencies = AppComponent.class)
public interface SetServerComponent {
    void inject(SetServerActivity activity);
}