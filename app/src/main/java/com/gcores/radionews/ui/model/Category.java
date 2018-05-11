package com.gcores.radionews.ui.model;

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

    private int id;
    private String name;
    private String desc;
    private String show_name;
    private String type;
    private String specific_type;
    private String logo_url;
    private String background_url;
    private int subscriptors_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecific_type() {
        return specific_type;
    }

    public void setSpecific_type(String specific_type) {
        this.specific_type = specific_type;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getBackground_url() {
        return background_url;
    }

    public void setBackground_url(String background_url) {
        this.background_url = background_url;
    }

    public int getSubscriptors_num() {
        return subscriptors_num;
    }

    public void setSubscriptors_num(int subscriptors_num) {
        this.subscriptors_num = subscriptors_num;
    }
}
