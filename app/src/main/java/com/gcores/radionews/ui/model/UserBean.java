package com.gcores.radionews.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class UserBean {
    /**
     * id : 21327
     * nickname : Ryoma
     * thumb_url : https://alioss.g-cores.com/uploads/user/aa080c8b-0b93-4370-96af-1e06afef697d_normal.png
     * location : 北京
     * is_fresh : false
     */

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("is_fresh")
    @Expose
    private Boolean isFresh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIsFresh() {
        return isFresh;
    }

    public void setIsFresh(Boolean isFresh) {
        this.isFresh = isFresh;
    }

}