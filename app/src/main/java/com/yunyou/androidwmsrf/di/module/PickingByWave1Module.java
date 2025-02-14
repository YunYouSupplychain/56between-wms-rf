package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickingByWave1Contract;
import com.yunyou.androidwmsrf.mvp.model.PickingByWave1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickingByWave1Module {
    private final PickingByWave1Contract.View view;

    public PickingByWave1Module(PickingByWave1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickingByWave1Contract.View providePickingByWave1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickingByWave1Contract.Model providePickingByWave1Model(PickingByWave1Model model) {
        return model;
    }
}