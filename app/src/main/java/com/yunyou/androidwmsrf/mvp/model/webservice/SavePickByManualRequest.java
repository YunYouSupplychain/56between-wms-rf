package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/02
 */
public class SavePickByManualRequest {
    private String id;
    private String allocId;
    private String soNo;
    private String pickNo;
    private String lineNo;
    private Double qtyPkEa;
    private Double qtyPkUom;
    private String ownerName;
    private String skuName;
    private String packDesc;
    private String uomDesc;
    private String pickOpName;
    private String checkOpName;
    private Integer recVer;
    private String toId;
    private String toLoc;
    private String traceId;

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getId() {
        return id;
    }

    public String getAllocId() {
        return allocId;
    }

    public void setAllocId(String allocId) {
        this.allocId = allocId;
    }

    public String getSoNo() {
        return soNo;
    }

    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public String getPickNo() {
        return pickNo;
    }

    public void setPickNo(String pickNo) {
        this.pickNo = pickNo;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public Double getQtyPkEa() {
        return qtyPkEa;
    }

    public void setQtyPkEa(Double qtyPkEa) {
        this.qtyPkEa = qtyPkEa;
    }

    public Double getQtyPkUom() {
        return qtyPkUom;
    }

    public void setQtyPkUom(Double qtyPkUom) {
        this.qtyPkUom = qtyPkUom;
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

    public String getPickOpName() {
        return pickOpName;
    }

    public void setPickOpName(String pickOpName) {
        this.pickOpName = pickOpName;
    }

    public String getCheckOpName() {
        return checkOpName;
    }

    public void setCheckOpName(String checkOpName) {
        this.checkOpName = checkOpName;
    }

    public Integer getRecVer() {
        return recVer;
    }

    public void setRecVer(Integer recVer) {
        this.recVer = recVer;
    }

    public String getToLoc() {
        return toLoc;
    }

    public void setToLoc(String toLoc) {
        this.toLoc = toLoc;
    }

    public void setId(String id) {
        this.id = id;
    }
}