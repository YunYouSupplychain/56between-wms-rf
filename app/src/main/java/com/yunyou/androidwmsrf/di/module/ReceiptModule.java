package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ReceiptContract;
import com.yunyou.androidwmsrf.mvp.model.ReceiptModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceiptModule {
    private final ReceiptContract.View view;

    public ReceiptModule(ReceiptContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ReceiptContract.View provideReceiptView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ReceiptContract.Model provideReceiptModel(ReceiptModel model) {
        return model;
    }
}