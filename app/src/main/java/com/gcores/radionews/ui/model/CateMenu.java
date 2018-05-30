package com.gcores.radionews.ui.model;

public class CateMenu {

    /**
     * id : 2
     * name : 新闻
     * desc :
     * show_name : NEWS
     * type : Column
     * specific_type : news
     * logo_url : https://image.g-cores.com/c8e56e30-9cc7-4247-9487-5bc66ed870bd.png
     * background_url : https://image.g-cores.com/c6116099-58cb-4c99-888d-c4dd895d464c.jpg
     * subscriptors_num : 10272
     * originals_num : 6157
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
    private int originals_num;

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

    public int getOriginals_num() {
        return originals_num;
    }

    public void setOriginals_num(int originals_num) {
        this.originals_num = originals_num;
    }
}
