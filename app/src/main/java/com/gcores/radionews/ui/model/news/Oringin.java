package com.gcores.radionews.ui.model.news;

import com.gcores.radionews.ui.model.Category;
import com.gcores.radionews.ui.model.Media;
import com.gcores.radionews.ui.model.User;

public class Oringin {


    /**
     * id : 99058
     * type : Volume
     * title : E3你最期待的游戏是？GadioNews6.1~6.7
     * desc : 当然也有上周的新闻内容
     * thumb_url : https://image.g-cores.com/a659df15-d9b2-4ef3-b7d0-cddae9017a2d.jpg?x-oss-process=style/original_hsat2x
     * cover_url : https://image.g-cores.com/a659df15-d9b2-4ef3-b7d0-cddae9017a2d.jpg?x-oss-process=style/original_hsat2x
     * permalink : https://www.g-cores.com/originals/99058
     * vol : 90
     * likes_num : 178
     * comments_num : 237
     * created_at : 2018-06-09 10:00:00
     * timellines_images_url : https://alioss.g-cores.com/timelines_images/99058-1528517423.zip
     * duration : 4939
     * user : {"id":385,"nickname":"Hardy","thumb_url":"https://alioss.g-cores.com/uploads/user/ee2a192c-983d-4985-9939-7c1cdcaa779e_normal.jpg","location":"北京","is_fresh":false}
     * category : {"id":45,"name":"GADIO News","desc":"","show_name":"GADIO News","type":"Show","specific_type":"audio","logo_url":"https://image.g-cores.com/efdeb43f-a16d-4afd-8068-61b8d57f23b0.png","background_url":"https://image.g-cores.com/de09daa7-5fbd-48c1-98c6-f6326b8da07b.jpg","subscriptors_num":4970}
     * media : {"mp3":["https://alioss.g-cores.com/uploads/audio/bf2eba43-9799-4c24-8163-90832d8d8c10.mp3"]}
     * is_like : false
     * is_mark : false
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
    private String timellines_images_url;
    private int duration;
    private User user;
    private Category category;
    private Media media;
    private boolean is_like;
    private boolean is_mark;

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

    public String getTimellines_images_url() {
        return timellines_images_url;
    }

    public void setTimellines_images_url(String timellines_images_url) {
        this.timellines_images_url = timellines_images_url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public boolean isIs_like() {
        return is_like;
    }

    public void setIs_like(boolean is_like) {
        this.is_like = is_like;
    }

    public boolean isIs_mark() {
        return is_mark;
    }

    public void setIs_mark(boolean is_mark) {
        this.is_mark = is_mark;
    }
}
