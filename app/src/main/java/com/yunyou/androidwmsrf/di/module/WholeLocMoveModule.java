package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.WholeLocMoveContract;
import com.yunyou.androidwmsrf.mvp.model.WholeLocMoveModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class WholeLocMoveModule {
    private final WholeLocMoveContract.View view;

    public WholeLocMoveModule(WholeLocMoveContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    WholeLocMoveContract.View provideWholeLocMoveView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    WholeLocMoveContract.Model provideWholeLocMoveModel(WholeLocMoveModel model) {
        return model;
    }
}