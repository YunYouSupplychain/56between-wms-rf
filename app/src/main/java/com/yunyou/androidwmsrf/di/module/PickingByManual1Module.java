package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickingByManual1Contract;
import com.yunyou.androidwmsrf.mvp.model.PickingByManual1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickingByManual1Module {
    private final PickingByManual1Contract.View view;

    public PickingByManual1Module(PickingByManual1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickingByManual1Contract.View providePickingByManual1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickingByManual1Contract.Model providePickingByManual1Model(PickingByManual1Model model) {
        return model;
    }
}