package com.gcores.radionews.ui.model.news;

import java.util.List;

public class Top {

    private String type;
    private List<Results> data;

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
}