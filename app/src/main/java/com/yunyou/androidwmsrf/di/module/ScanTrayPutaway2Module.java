package com.yunyou.androidwmsrf.di.module;

import com.yunyou.androidwmsrf.mvp.contract.ScanTrayPutaway2Contract;
import com.yunyou.androidwmsrf.mvp.model.ScanTrayPutaway2Model;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ScanTrayPutaway2Module {
    private final ScanTrayPutaway2Contract.View view;

    public ScanTrayPutaway2Module(ScanTrayPutaway2Contract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    ScanTrayPutaway2Contract.View provideScanTrayPutaway2View() {
        return this.view;
    }

    @ActivityScope
    @Provides
    ScanTrayPutaway2Contract.Model provideScanTrayPutaway2Model(ScanTrayPutaway2Model model) {
        return model;
    }
}