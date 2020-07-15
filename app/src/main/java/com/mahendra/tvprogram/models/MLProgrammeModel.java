package com.mahendra.tvprogram.models;

import java.util.Date;

public class MLProgrammeModel {
    private Date start;
    private Date stop;
    private String channel;
    private String idNo;

    private String title;
    private String subTitle;
    private String category;
    private String description;
    private String iconURL;

    public MLProgrammeModel() {}

    public MLProgrammeModel(String channel, String idNo, Date start, Date stop,  String title, String subTitle, String category, String description, String iconURL) {
        this.start = start;
        this.stop = stop;
        this.channel = channel;
        this.idNo = idNo;
        this.title = title;
        this.subTitle = subTitle;
        this.category = category;
        this.description = description;
        this.iconURL = iconURL;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getStop() {
        return stop;
    }

    public void setStop(Date stop) {
        this.stop = stop;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }
}
