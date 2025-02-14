package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.PickingByOrder2Contract;
import com.yunyou.androidwmsrf.mvp.model.PickingByOrder2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PickingByOrder2Module {
    private final PickingByOrder2Contract.View view;

    public PickingByOrder2Module(PickingByOrder2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    PickingByOrder2Contract.View providePickingByOrder2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    PickingByOrder2Contract.Model providePickingByOrder2Model(PickingByOrder2Model model) {
        return model;
    }
}