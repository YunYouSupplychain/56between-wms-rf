package com.yunyou.androidwmsrf.di.component;

import dagger.Component;

import com.yunyou.androidwmsrf.di.module.MainModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.Main2Activity;
import com.jess.arms.di.component.AppComponent;

import com.jess.arms.di.scope.ActivityScope;
import com.yunyou.androidwmsrf.mvp.ui.activity.MainActivity;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);

    void inject2(Main2Activity activity);
}