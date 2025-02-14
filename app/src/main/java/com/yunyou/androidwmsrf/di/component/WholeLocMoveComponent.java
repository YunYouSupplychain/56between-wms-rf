package com.yunyou.androidwmsrf.di.component;

import com.yunyou.androidwmsrf.di.module.WholeLocMoveModule;
import com.yunyou.androidwmsrf.mvp.ui.activity.WholeLocMoveActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = WholeLocMoveModule.class, dependencies = AppComponent.class)
public interface WholeLocMoveComponent {
    void inject(WholeLocMoveActivity activity);
}