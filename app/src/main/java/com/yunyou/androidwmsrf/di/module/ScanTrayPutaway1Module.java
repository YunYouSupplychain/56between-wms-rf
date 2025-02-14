package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTrayPutaway1Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTrayPutaway1Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTrayPutaway1Module {
    private final ScanTrayPutaway1Contract.View view;

    public ScanTrayPutaway1Module(ScanTrayPutaway1Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTrayPutaway1Contract.View provideScanTrayPutaway1View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTrayPutaway1Contract.Model provideScanTrayPutaway1Model(ScanTrayPutaway1Model model) {
        return model;
    }
}