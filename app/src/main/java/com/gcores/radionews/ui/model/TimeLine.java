package com.gcores.radionews.ui.model;

public class TimeLine {

    /**
     * id : 72849
     * at : 30
     * title : 开场 BGM
     * asset_url : https://alioss.g-cores.com/uploads/timeline/079b212a-3cf9-4f95-9c0d-595be925f1c9_limit.png
     * content : 大家现在听到的开场 BGM 是《神界：原罪 2》的主题曲《Divinity - Original Sin 2 (Main Theme)》。
     * quote_href : http://music.163.com/song?id=518088344&userid=114128692
     */

    private int id;
    private int at;
    private String title;
    private String asset_url;
    private String content;
    private String quote_href;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAsset_url() {
        return asset_url;
    }

    public void setAsset_url(String asset_url) {
        this.asset_url = asset_url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuote_href() {
        return quote_href;
    }

    public void setQuote_href(String quote_href) {
        this.quote_href = quote_href;
    }


}
