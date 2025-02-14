package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.InvLotAttModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.InvLotAttActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = InvLotAttModule.class, dependencies = AppComponent.class)
public interface InvLotAttComponent {
    void inject(InvLotAttActivity activity);
}