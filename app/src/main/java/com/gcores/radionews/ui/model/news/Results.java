package com.gcores.radionews.ui.model.news;

import com.gcores.radionews.ui.model.Category;
import com.gcores.radionews.ui.model.UserBean;

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
    private UserBean user;
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public int getLikes_num() {
        return likes_num;
    }

    public void setLikes_num(int likes_num) {
        this.likes_num = likes_num;
    }

    public int getComments_num() {
        return comments_num;
    }

    public void setComments_num(int comments_num) {
        this.comments_num = comments_num;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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
