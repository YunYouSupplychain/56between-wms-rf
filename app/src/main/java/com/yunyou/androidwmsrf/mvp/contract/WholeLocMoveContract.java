package com.yunyou.androidwmsrf.mvp.contract;

import com.yunyou.androidwmsrf.mvp.model.entity.SkuInvDetailInfo;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryMovementRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryMovementResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveMovementRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveMovementResponse;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;

public interface WholeLocMoveContract {
    // 对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void afterQuery(SkuInvDetailInfo info);

        void afterSave();
    }

    // Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<QueryMovementResponse> queryMovement(QueryMovementRequest requestEnvelope);

        Observable<SaveMovementResponse> saveMovement(List<SaveMovementRequest> requestEnvelope);
    }
}