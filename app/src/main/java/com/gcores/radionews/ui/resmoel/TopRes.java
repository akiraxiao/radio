package com.gcores.radionews.ui.resmoel;

import com.gcores.radionews.ui.model.news.TopListList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopRes {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("results")
    @Expose
    private List<TopListList> results = null;


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TopListList> getResults() {
        return results;
    }

    public void setResults(List<TopListList> results) {
        this.results = results;
    }


}
