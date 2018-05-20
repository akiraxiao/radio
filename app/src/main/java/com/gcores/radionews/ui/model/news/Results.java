package com.gcores.radionews.ui.model.news;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.gcores.radionews.ui.model.Category;
import com.gcores.radionews.ui.model.User;

/**
 * Copyright 2018 bejson.com
 */

/**
 * Auto-generated: 2018-05-20 19:20:27
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Results implements MultiItemEntity {

    private int id;
    private String type;
    private String title;
    private String desc;
    private String thumb_url;
    private String cover_url;
    private String permalink;
    private String vol;
    private int likes_num;
    private int comments_num;
    private String created_at;
    private int duration;
    private User user;
    private Category category;



    private int itemType;
    //头部的几个布局
    public static final int HEADER_NEWS = 1;
//    public static final int HEADER_CATE = 2;
    public static final int HEADER_ART = 3;
    public static final int HEADER_USER = 4;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getVol() {
        return vol;
    }

    public void setLikes_num(int likes_num) {
        this.likes_num = likes_num;
    }

    public int getLikes_num() {
        return likes_num;
    }

    public void setComments_num(int comments_num) {
        this.comments_num = comments_num;
    }

    public int getComments_num() {
        return comments_num;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {
        return itemType;
    }
}