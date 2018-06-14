package com.gcores.radionews.ui.resmoel;

import com.gcores.radionews.ui.model.news.Oringin;

public class OringinDetailRes {


    /**
     * status : 1
     * results : {"id":99058,"type":"Volume","title":"E3你最期待的游戏是？GadioNews6.1~6.7","desc":"当然也有上周的新闻内容","thumb_url":"https://image.g-cores.com/a659df15-d9b2-4ef3-b7d0-cddae9017a2d.jpg?x-oss-process=style/original_hsat2x","cover_url":"https://image.g-cores.com/a659df15-d9b2-4ef3-b7d0-cddae9017a2d.jpg?x-oss-process=style/original_hsat2x","permalink":"https://www.g-cores.com/originals/99058","vol":"90","likes_num":178,"comments_num":237,"created_at":"2018-06-09 10:00:00","timellines_images_url":"https://alioss.g-cores.com/timelines_images/99058-1528517423.zip","duration":4939,"user":{"id":385,"nickname":"Hardy","thumb_url":"https://alioss.g-cores.com/uploads/user/ee2a192c-983d-4985-9939-7c1cdcaa779e_normal.jpg","location":"北京","is_fresh":false},"category":{"id":45,"name":"GADIO News","desc":"","show_name":"GADIO News","type":"Show","specific_type":"audio","logo_url":"https://image.g-cores.com/efdeb43f-a16d-4afd-8068-61b8d57f23b0.png","background_url":"https://image.g-cores.com/de09daa7-5fbd-48c1-98c6-f6326b8da07b.jpg","subscriptors_num":4970},"media":{"mp3":["https://alioss.g-cores.com/uploads/audio/bf2eba43-9799-4c24-8163-90832d8d8c10.mp3"]},"is_like":false,"is_mark":false}
     */

    private int status;
    private Oringin results;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Oringin getResults() {
        return results;
    }

    public void setResults(Oringin results) {
        this.results = results;

    }
}
