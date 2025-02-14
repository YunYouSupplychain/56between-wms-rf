package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.ScanTrayPutaway2Module;
import com.yunyou.androidwmsrf.mvp.ui.activity.ScanTrayPutaway2Activity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = ScanTrayPutaway2Module.class, dependencies = AppComponent.class)
public interface ScanTrayPutaway2Component {
    void inject(ScanTrayPutaway2Activity activity);
}