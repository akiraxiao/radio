package com.gcores.radionews.ui.resmoel;

import com.gcores.radionews.ui.model.news.Radio;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RadioRes {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("results")
    @Expose
    private List<Radio> results = null;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Radio> getResults() {
        return results;
    }

    public void setResults(List<Radio> results) {
        this.results = results;
    }


}
