package com.gcores.radionews.ui.model;

import com.gcores.radionews.ui.model.news.Results;

import java.util.List;

public class DataBean {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Results> getData() {
        return data;
    }

    public void setData(List<Results> data) {
        this.data = data;
    }

    private String type;
    private List<Results> data;
}
