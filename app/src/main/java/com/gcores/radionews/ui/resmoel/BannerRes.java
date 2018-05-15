package com.gcores.radionews.ui.resmoel;

import com.gcores.radionews.ui.model.news.Banner;

import java.util.List;

public class BannerRes {

    private int status;
    private List<Banner> results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Banner> getResults() {
        return results;
    }

    public void setResults(List<Banner> results) {
        this.results = results;
    }
}
