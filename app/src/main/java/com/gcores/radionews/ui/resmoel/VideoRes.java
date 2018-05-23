package com.gcores.radionews.ui.resmoel;

import com.gcores.radionews.ui.model.news.Results;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoRes {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("results")
    @Expose
    private List<Results> results = null;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
