package com.mahendra.tvprogram.models;

public class MLChannelModel {
    private String idNo;
    private String id;
    private String displayName;
    private String iconURL;

    public MLChannelModel() {}

    public MLChannelModel(String idNo, String id, String displayName, String iconURL) {
        this.idNo = idNo;
        this.id = id;
        this.displayName = displayName;
        this.iconURL = iconURL;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
}
