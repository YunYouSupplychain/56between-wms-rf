package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.InvQuery2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.InvQuery2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = InvQuery2Module.class, dependencies = AppComponent.class)
public interface InvQuery2Component {
    void inject(InvQuery2Activity activity);
}