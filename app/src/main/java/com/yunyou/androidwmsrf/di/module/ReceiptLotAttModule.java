package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ReceiptLotAttContract;
import com.yunyou.androidwmsrf.mvp.model.ReceiptLotAttModel;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ReceiptLotAttModule {
    private final ReceiptLotAttContract.View view;

    public ReceiptLotAttModule(ReceiptLotAttContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ReceiptLotAttContract.View provideReceiptLotAttView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ReceiptLotAttContract.Model provideReceiptLotAttModel(ReceiptLotAttModel model) {
        return model;
    }
}