package com.gcores.radionews.ui.model;

/**
 * Auto-generated: 2018-05-20 19:20:27
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class User {

    private int id;
    private String nickname;
    private String thumb_url;
    private String location;
    private boolean is_fresh;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getNickname() {
        return nickname;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }
    public String getThumb_url() {
        return thumb_url;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getLocation() {
        return location;
    }

    public void setIs_fresh(boolean is_fresh) {
        this.is_fresh = is_fresh;
    }
    public boolean getIs_fresh() {
        return is_fresh;
    }

}