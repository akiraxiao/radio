
package com.gcores.radionews.ui.model;

import com.gcores.radionews.ui.model.news.Results;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
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

}
