package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.PutawayModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.PutawayActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = PutawayModule.class, dependencies = AppComponent.class)
public interface PutawayComponent {
    void inject(PutawayActivity activity);
}