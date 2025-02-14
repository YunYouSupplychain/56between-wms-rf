package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickingByOrder1Contract;
import com.yunyou.androidwmsrf.mvp.model.PickingByOrder1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickingByOrder1Module {
    private final PickingByOrder1Contract.View view;

    public PickingByOrder1Module(PickingByOrder1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickingByOrder1Contract.View providePickingByOrder1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickingByOrder1Contract.Model providePickingByOrder1Model(PickingByOrder1Model model) {
        return model;
    }
}