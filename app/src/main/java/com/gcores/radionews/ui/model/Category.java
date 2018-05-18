package com.gcores.radionews.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class Category {
    /**
     * id : 51
     * name : 差点儿一分钟
     * desc : 短视频节目
     * show_name : 差点儿一分钟
     * type : Show
     * specific_type : video
     * logo_url : https://image.g-cores.com/d61c2e3a-2bbd-4917-82d0-ba145d9fe1c0.png
     * background_url : https://image.g-cores.com/1fc61946-1762-41ab-b515-c032658e5462.jpg
     * subscriptors_num : 1868
     */

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("show_name")
    @Expose
    private String showName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("specific_type")
    @Expose
    private String specificType;
    @SerializedName("logo_url")
    @Expose
    private String logoUrl;
    @SerializedName("background_url")
    @Expose
    private String backgroundUrl;
    @SerializedName("subscriptors_num")
    @Expose
    private Integer subscriptorsNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecificType() {
        return specificType;
    }

    public void setSpecificType(String specificType) {
        this.specificType = specificType;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public Integer getSubscriptorsNum() {
        return subscriptorsNum;
    }

    public void setSubscriptorsNum(Integer subscriptorsNum) {
        this.subscriptorsNum = subscriptorsNum;
    }

}
