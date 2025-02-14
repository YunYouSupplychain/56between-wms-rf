package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.InvQuery1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.InvQuery1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = InvQuery1Module.class, dependencies = AppComponent.class)
public interface InvQuery1Component {
    void inject(InvQuery1Activity activity);
}