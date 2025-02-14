package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.InvQueryByMoveModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.InvQueryByMoveActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = InvQueryByMoveModule.class, dependencies = AppComponent.class)
public interface InvQueryByMoveComponent {
    void inject(InvQueryByMoveActivity activity);
}