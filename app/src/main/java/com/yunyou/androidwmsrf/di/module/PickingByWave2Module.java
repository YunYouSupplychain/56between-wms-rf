package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickingByWave2Contract;
import com.yunyou.androidwmsrf.mvp.model.PickingByWave2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickingByWave2Module {
    private final PickingByWave2Contract.View view;

    public PickingByWave2Module(PickingByWave2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickingByWave2Contract.View providePickingByWave2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickingByWave2Contract.Model providePickingByWave2Model(PickingByWave2Model model) {
        return model;
    }
}