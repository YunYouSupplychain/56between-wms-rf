package com.yunyou.androidwmsrf.mvp.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author WMJ
 * @version 2019/07/19
 */
public class OwnerInfo implements Serializable {
    private List<OwnerEntity> detailEntityList;

    public List<OwnerEntity> getDetailEntityList() {
        return detailEntityList;
    }

    public void setDetailEntityList(List<OwnerEntity> detailEntityList) {
        this.detailEntityList = detailEntityList;
    }

    public static class OwnerEntity implements Serializable {
        private String ownerCode;

        private String ownerName;

        public String getOwnerCode() {
            return ownerCode;
        }

        public void setOwnerCode(String ownerCode) {
            this.ownerCode = ownerCode;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }
    }
}