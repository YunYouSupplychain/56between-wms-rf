package com.yunyou.androidwmsrf.mvp.model.api.service;

import com.yunyou.androidwmsrf.mvp.model.webservice.AddNewTaskCountRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.AddNewTaskCountResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.CheckAsnIsPalletizeRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.CheckAsnIsPalletizeResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.CheckTaskCountRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.CheckTaskCountResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.GetAppVersionResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.LoginResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.LogoutResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryAsnDetailByTraceIdOrSkuRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryAsnDetailByTraceIdOrSkuResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryMovementRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryMovementResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryOwnerRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryOwnerResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickBoxDetailRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickBoxDetailResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickBoxHeaderRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickBoxHeaderResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickDetailRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPickDetailResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPutAwayTaskByTaskNoRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPutAwayTaskByTaskNoResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPutAwayTaskByTraceIdRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryPutAwayTaskByTraceIdResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryReplenishmentDetailRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryReplenishmentDetailResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QuerySkuInvRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QuerySkuInvResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryTaskCountDetailRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.QueryTaskCountDetailResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveAsnDetailByTraceIdRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveAsnDetailByTraceIdResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveMovementRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveMovementResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByManualRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByManualResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByTraceIdRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByTraceIdResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByWaveRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickByWaveResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePickResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePutAwayByTaskRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SavePutAwayByTaskResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveReplenishmentRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveReplenishmentResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveShipmentRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveShipmentResponse;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveTaskCountRequest;
import com.yunyou.androidwmsrf.mvp.model.webservice.SaveTaskCountResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author WMJ
 * @version 2019/06/29
 */
public interface CommonService {

    // 1.登录
    @POST("login")
    Observable<LoginResponse> login(@Query("username") String username, @Query("password") String password);

    // 2.获取服务器上的APP版本信息
    @POST("wms/rf/getAppVersion")
    Observable<GetAppVersionResponse> getAppVersion(@Query("appId") String appId);

    // ========= 收货 =========
    // 1.1 校验ASN单据是否码盘
    @POST("wms/rf/checkAsnIsPalletize")
    Observable<CheckAsnIsPalletizeResponse> checkAsnIsPalletize(@Body CheckAsnIsPalletizeRequest request);

    // 1.2 根据托盘ID或sku查询收货明细
    @POST("wms/rf/queryAsnDetailByTraceIdOrSku")
    Observable<QueryAsnDetailByTraceIdOrSkuResponse> queryAsnDetailByTraceIdOrSku(@Body QueryAsnDetailByTraceIdOrSkuRequest request);

    // 1.3 根据托盘ID确认收货明细
    @POST("wms/rf/saveAsnDetailByTraceId")
    Observable<SaveAsnDetailByTraceIdResponse> saveAsnDetailByTraceId(@Body SaveAsnDetailByTraceIdRequest request);

    // ========= 上架 =========
    // 1.扫描托盘上架
    // 1.1 根据托盘号查询上架信息
    @POST("wms/rf/queryPutAwayTaskByTraceId")
    Observable<QueryPutAwayTaskByTraceIdResponse> queryPutAwayTaskByTraceId(@Body QueryPutAwayTaskByTraceIdRequest request);

    // 2.扫描任务上架
    // 2.1 根据任务查询上架信息
    @POST("wms/rf/queryPutAwayTaskByTaskNo")
    Observable<QueryPutAwayTaskByTaskNoResponse> queryPutAwayTaskByTaskNo(@Body QueryPutAwayTaskByTaskNoRequest request);

    // 3.上架确认
    @POST("wms/rf/savePutAwayByTask")
    Observable<SavePutAwayByTaskResponse> savePutAwayByTask(@Body SavePutAwayByTaskRequest request);

    // ========= 拣货 =========
    // 1.按单拣货
    // 1.1 查询拣货明细
    @POST("wms/rf/queryPickDetail")
    Observable<QueryPickDetailResponse> queryPickDetail(@Body QueryPickDetailRequest request);

    // 2.拣货确认
    @POST("wms/rf/savePick")
    Observable<SavePickResponse> savePick(@Body SavePickRequest request);

    // 3.标签拣货确认
    @POST("wms/rf/savePickByTraceId")
    Observable<SavePickByTraceIdResponse> savePickByTraceId(@Body SavePickByTraceIdRequest request);

    // 3.波次合拣确认
    @POST("wms/rf/savePickByWave")
    Observable<SavePickByWaveResponse> savePickByWave(@Body SavePickByWaveRequest request);

    // 4.手工拣货确认
    @POST("wms/rf/savePickByManual")
    Observable<SavePickByManualResponse> savePickByManual(@Body SavePickByManualRequest request);

    // ========= 发货 =========
    @POST("wms/rf/saveShipment")
    Observable<SaveShipmentResponse> saveShipment(@Body SaveShipmentRequest request);

    // ========= 移动 =========
    // 1.查询移动信息
    @POST("wms/rf/queryMovement")
    Observable<QueryMovementResponse> queryMovement(@Body QueryMovementRequest request);

    // 2.执行移动
    @POST("wms/rf/saveMovement")
    Observable<SaveMovementResponse> saveMovement(@Body List<SaveMovementRequest> request);

    // ========= 补货 =========
    // 1.查询补货信息
    @POST("wms/rf/queryReplenishmentDetail")
    Observable<QueryReplenishmentDetailResponse> queryReplenishmentDetail(@Body QueryReplenishmentDetailRequest request);

    // 2.确认补货
    @POST("wms/rf/saveReplenishment")
    Observable<SaveReplenishmentResponse> saveReplenishment(@Body SaveReplenishmentRequest request);

    // ========= 盘点 =========
    // 1.校验盘点单
    @POST("wms/rf/checkTaskCount")
    Observable<CheckTaskCountResponse> checkTaskCount(@Body CheckTaskCountRequest request);

    // 2.查询盘点明细
    @POST("wms/rf/queryTaskCountDetail")
    Observable<QueryTaskCountDetailResponse> queryTaskCountDetail(@Body QueryTaskCountDetailRequest request);

    // 3.确认盘点
    @POST("wms/rf/saveTaskCount")
    Observable<SaveTaskCountResponse> saveTaskCount(@Body SaveTaskCountRequest request);

    // 4.新增盘点
    @POST("wms/rf/addNewTaskCount")
    Observable<AddNewTaskCountResponse> addNewTaskCount(@Body AddNewTaskCountRequest request);

    // 4.查询可用货主
    @POST("wms/rf/queryOwner")
    Observable<QueryOwnerResponse> queryOwner(@Body QueryOwnerRequest request);

    // ========= 库存查询 =========
    @POST("wms/rf/querySkuInv")
    Observable<QuerySkuInvResponse> querySkuInv(@Body QuerySkuInvRequest request);

    @POST("wms/rf/queryPickBoxHeader")
    Observable<QueryPickBoxHeaderResponse> queryPickBoxHeader(@Body QueryPickBoxHeaderRequest request);

    @POST("wms/rf/queryPickBoxDetail")
    Observable<QueryPickBoxDetailResponse> queryPickBoxDetail(@Body QueryPickBoxDetailRequest request);

    // ========= 登出 =========
    @GET("logout")
    Observable<LogoutResponse> logout(@Query("username") String username);

}