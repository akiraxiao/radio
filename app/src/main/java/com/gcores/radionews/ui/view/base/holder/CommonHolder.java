package com.gcores.radionews.ui.view.base.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gcores.radionews.R;

public class CommonHolder extends RecyclerView.ViewHolder {

//    private View headerView;
//    private RecyclerView topHeaderList;
    private RecyclerView bodyView;
//    private TextView tvTitle;



    public RecyclerView getBodyView() {
        return bodyView;
    }

    public void setBodyView(RecyclerView bodyView) {
        this.bodyView = bodyView;
    }

    /*public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }*/

    public CommonHolder(View itemView) {
        super(itemView);
//        tvTitle =   itemView.findViewById(R.id.tv_top_header);
//        topHeaderList =   itemView.findViewById(R.id.top_header);
        bodyView =   itemView.findViewById(R.id.toplist_item);
    }

}
