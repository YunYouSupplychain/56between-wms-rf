package com.yunyou.androidwmsrf.mvp.model.entity;

import java.io.Serializable;

public class PackageConfigInfo implements Serializable {
    private String packageCode;
    private String packageName;
    private int containerValue;
    private String seq;

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getContainerValue() {
        return containerValue;
    }

    public void setContainerValue(int containerValue) {
        this.containerValue = containerValue;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return packageName;
    }
}