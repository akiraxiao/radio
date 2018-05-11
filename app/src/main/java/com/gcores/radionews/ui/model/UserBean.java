package com.gcores.radionews.ui.model;

public  class UserBean {
    /**
     * id : 21327
     * nickname : Ryoma
     * thumb_url : https://alioss.g-cores.com/uploads/user/aa080c8b-0b93-4370-96af-1e06afef697d_normal.png
     * location : 北京
     * is_fresh : false
     */

    private int id;
    private String nickname;
    private String thumb_url;
    private String location;
    private boolean is_fresh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getThumb_url() {
        return thumb_url;
    }

    public void setThumb_url(String thumb_url) {
        this.thumb_url = thumb_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isIs_fresh() {
        return is_fresh;
    }

    public void setIs_fresh(boolean is_fresh) {
        this.is_fresh = is_fresh;
    }
}