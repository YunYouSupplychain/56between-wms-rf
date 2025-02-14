package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.InvPickBoxQuery1Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.InvPickBoxQuery1Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = InvPickBoxQuery1Module.class, dependencies = AppComponent.class)
public interface InvPickBoxQuery1Component {
    void inject(InvPickBoxQuery1Activity activity);
}