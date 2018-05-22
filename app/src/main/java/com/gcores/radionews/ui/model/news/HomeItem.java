package com.gcores.radionews.ui.model.news;

import com.gcores.radionews.ui.model.User;

import java.util.List;

public class HomeItem  {


    //视频
    public static final int NEWS = 1;
    //文章
    public static final int CATE = 2;

    //头部的几个布局
    public static final int ART = 3;
    public static final int USERS = 4;
    public static final int DEFAULT = 5;

//    public static final int HEADER_USER = 6;

    public HomeItem(int itemType){
        this.itemType = itemType;
//        this.header = header;
//        this.list = list;
    }




//    private int type;

    private int itemType;


    public List<Results> getNewsHeader() {
        return newsHeader;
    }

    public void setNewsHeader(List<Results> newsHeader) {
        this.newsHeader = newsHeader;
    }

    public List<CateBanner> getCateHeader() {
        return cateHeader;
    }

    public void setCateHeader(List<CateBanner> cateHeader) {
        this.cateHeader = cateHeader;
    }

    public List<Results> getArtHeader() {
        return artHeader;
    }

    public void setArtHeader(List<Results> artHeader) {
        this.artHeader = artHeader;
    }

    public List<User> getUserHeader() {
        return userHeader;
    }

    public void setUserHeader(List<User> userHeader) {
        this.userHeader = userHeader;
    }

    public List<Top> getData() {
        return data;
    }

    public void setData(List<Top> data) {
        this.data = data;
    }

    private List<Results> newsHeader;
    private List<CateBanner> cateHeader;
    private List<Results> artHeader;
    private List<User> userHeader;


    //list body数据
    private List<Top> data;





   /* public void setItemType(int itemType) {
        this.itemType = itemType;
    }*/

    public int getItemType() {
        return itemType;
    }

    /*@Override
    public int getItemType() {
        return itemType;
    }*/
}
