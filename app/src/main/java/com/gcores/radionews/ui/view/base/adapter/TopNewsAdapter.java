package com.gcores.radionews.ui.view.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.model.news.Results;

import java.util.List;

public class TopNewsAdapter extends BaseQuickAdapter<Results,BaseViewHolder> {


    private TextView mTitle;
    private TextView mType;
    private TextView mTime;
    private TextView mFav;
    private TextView mReply;
    private ImageView mBack;

    private Context mContext;

    public TopNewsAdapter(@Nullable List<Results> data, Context mContext) {
        super(R.layout.item_topnews,data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, Results item) {
        mType =    helper.getView(R.id.item_top_type);
        mTitle =   helper.getView(R.id.item_top_title);
        mTime =  helper.getView(R.id.item_top_time);
        mFav =  helper.getView(R.id.view_tv_fav);
        mReply =   helper.getView(R.id.view_tv_reply);
        mBack =  helper.getView(R.id.iv_news);
        mType.setText("新闻");
        mTitle.setText(item.getTitle());
        mTime.setText(item.getCreated_at());
        mFav.setText(item.getLikes_num());
        mReply.setText(item.getComments_num());
        Glide.with(mContext).load(item.getThumb_url()).into(mBack);
    }
}
