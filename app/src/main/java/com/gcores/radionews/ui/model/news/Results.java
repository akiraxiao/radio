package com.gcores.radionews.ui.model.news;

import com.gcores.radionews.ui.model.Category;
import com.gcores.radionews.ui.model.UserBean;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {





    /**
     * id : 98328
     * type : Volume
     * title : 传说中的秘银了解一下
     * desc : 禁拉又禁拽，禁蹬又禁踹
     * thumb_url : https://image.g-cores.com/410ec6d3-35e7-485b-a924-cb165dbfb9d4.jpg?x-oss-process=style/original_hsat2x
     * cover_url : https://image.g-cores.com/410ec6d3-35e7-485b-a924-cb165dbfb9d4.jpg?x-oss-process=style/original_hsat2x
     * permalink : https://www.g-cores.com/originals/98328
     * vol : 52
     * likes_num : 17
     * comments_num : 38
     * created_at : 2018-05-10 08:00:27
     * user : {"id":21327,"nickname":"Ryoma","thumb_url":"https://alioss.g-cores.com/uploads/user/aa080c8b-0b93-4370-96af-1e06afef697d_normal.png","location":"北京","is_fresh":false}
     * category : {"id":51,"name":"差点儿一分钟","desc":"短视频节目","show_name":"差点儿一分钟","type":"Show","specific_type":"video","logo_url":"https://image.g-cores.com/d61c2e3a-2bbd-4917-82d0-ba145d9fe1c0.png","background_url":"https://image.g-cores.com/1fc61946-1762-41ab-b515-c032658e5462.jpg","subscriptors_num":1868}
     */

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("thumb_url")
    @Expose
    private String thumbUrl;
    @SerializedName("cover_url")
    @Expose
    private String coverUrl;
    @SerializedName("permalink")
    @Expose
    private String permalink;
    @SerializedName("vol")
    @Expose
    private Object vol;
    @SerializedName("likes_num")
    @Expose
    private Integer likesNum;
    @SerializedName("comments_num")
    @Expose
    private Integer commentsNum;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("user")
    @Expose
    private UserBean user;
    @SerializedName("category")
    @Expose
    private Category category;

    @SerializedName("duration")
    @Expose
    private int duration;

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Object getVol() {
        return vol;
    }

    public void setVol(Object vol) {
        this.vol = vol;
    }

    public Integer getLikesNum() {
        return likesNum;
    }

    public void setLikesNum(Integer likesNum) {
        this.likesNum = likesNum;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }



}
