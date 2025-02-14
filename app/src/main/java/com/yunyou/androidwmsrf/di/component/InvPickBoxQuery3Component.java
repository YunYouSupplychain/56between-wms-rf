package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.InvPickBoxQuery3Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.InvPickBoxQuery3Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = InvPickBoxQuery3Module.class, dependencies = AppComponent.class)
public interface InvPickBoxQuery3Component {
    void inject(InvPickBoxQuery3Activity activity);
}