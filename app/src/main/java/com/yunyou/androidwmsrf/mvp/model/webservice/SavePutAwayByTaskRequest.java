package com.yunyou.androidwmsrf.mvp.model.webservice;

import java.util.Date;

/**
 * @author WMJ
 * @version 2019/07/02
 */
public class SavePutAwayByTaskRequest {
    private String id;
    private String paId;
    private String lineNo;
    private String ownerName;
    private String skuName;
    private String toLoc;
    private String toId;
    private String packDesc;
    private String uomDesc;
    private Double currentPaQtyEa;
    private String paName;
    private Date paTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaId() {
        return paId;
    }

    public void setPaId(String paId) {
        this.paId = paId;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getToLoc() {
        return toLoc;
    }

    public void setToLoc(String toLoc) {
        this.toLoc = toLoc;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getPackDesc() {
        return packDesc;
    }

    public void setPackDesc(String packDesc) {
        this.packDesc = packDesc;
    }

    public String getUomDesc() {
        return uomDesc;
    }

    public void setUomDesc(String uomDesc) {
        this.uomDesc = uomDesc;
    }

    public Double getCurrentPaQtyEa() {
        return currentPaQtyEa;
    }

    public void setCurrentPaQtyEa(Double currentPaQtyEa) {
        this.currentPaQtyEa = currentPaQtyEa;
    }

    public String getPaName() {
        return paName;
    }

    public void setPaName(String paName) {
        this.paName = paName;
    }

    public Date getPaTime() {
        return paTime;
    }

    public void setPaTime(Date paTime) {
        this.paTime = paTime;
    }
}