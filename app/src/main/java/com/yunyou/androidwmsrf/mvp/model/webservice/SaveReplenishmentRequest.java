package com.yunyou.androidwmsrf.mvp.model.webservice;

/**
 * @author WMJ
 * @version 2019/07/04
 */
public class SaveReplenishmentRequest {
    private String id;
    private String uom;
    private Double qtyRpUom;
    private Double qtyRpEa;
    private String toLoc;

    public SaveReplenishmentRequest(String id, String uom, Double qtyRpUom, Double qtyRpEa, String toLoc) {
        this.id = id;
        this.uom = uom;
        this.qtyRpUom = qtyRpUom;
        this.qtyRpEa = qtyRpEa;
        this.toLoc = toLoc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public Double getQtyRpUom() {
        return qtyRpUom;
    }

    public void setQtyRpUom(Double qtyRpUom) {
        this.qtyRpUom = qtyRpUom;
    }

    public Double getQtyRpEa() {
        return qtyRpEa;
    }

    public void setQtyRpEa(Double qtyRpEa) {
        this.qtyRpEa = qtyRpEa;
    }

    public String getToLoc() {
        return toLoc;
    }

    public void setToLoc(String toLoc) {
        this.toLoc = toLoc;
    }
}