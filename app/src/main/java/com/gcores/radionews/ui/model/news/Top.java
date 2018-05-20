package com.gcores.radionews.ui.model.news;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class Top implements MultiItemEntity {

    //视频
    public static final int VIDEO = 1;
    //文章
    public static final int ARTICLE = 2;




    private int itemType;
    public Top(){
//        this.itemType = itemType;
    }

    private String type;
    private Results data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Results getData() {
        return data;
    }

    public void setData(Results data) {
        this.data = data;
    }

    public void setItemType(int itemType){
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        /*int layoutType = -1;
        if (getData().getType().equals("Article")){
            layoutType = Top.ARTICLE;
        }else{
            layoutType = Top.VIDEO;
        }*/
//        return layoutType;
        return itemType;
    }
}