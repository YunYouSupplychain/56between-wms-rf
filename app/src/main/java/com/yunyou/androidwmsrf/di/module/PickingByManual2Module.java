package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickingByManual2Contract;
import com.yunyou.androidwmsrf.mvp.model.PickingByManual2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickingByManual2Module {
    private final PickingByManual2Contract.View view;

    public PickingByManual2Module(PickingByManual2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickingByManual2Contract.View providePickingByManual2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickingByManual2Contract.Model providePickingByManual2Model(PickingByManual2Model model) {
        return model;
    }
}