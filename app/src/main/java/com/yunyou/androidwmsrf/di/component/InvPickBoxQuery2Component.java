package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.InvPickBoxQuery2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.InvPickBoxQuery2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = InvPickBoxQuery2Module.class, dependencies = AppComponent.class)
public interface InvPickBoxQuery2Component {
    void inject(InvPickBoxQuery2Activity activity);
}