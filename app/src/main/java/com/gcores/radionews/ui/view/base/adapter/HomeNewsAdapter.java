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

/**
 * 新闻adapter
 */
public class HomeNewsAdapter extends BaseQuickAdapter<Results,BaseViewHolder>{


    private TextView mTitle;
    private TextView mCate;
    private TextView mDesc;
    private TextView mFav;
    private TextView mReply;
    private ImageView mBack;
    private Context mContext;


    public HomeNewsAdapter(@Nullable List<Results> data,Context mContext) {
        super(R.layout.item_news_news,data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, Results item) {
        /*switch (helper.getItemViewType()) {
            case Top.VIDEO:*/
        mBack =  helper.getView(R.id.news_back);
        mCate =    helper.getView(R.id.item_top_categroy);
        mFav =  helper.getView(R.id.view_tv_fav);
        mReply =   helper.getView(R.id.view_tv_reply);
        mTitle =   helper.getView(R.id.item_news_title);
        mDesc = helper.getView(R.id.item_news_desc);


        mCate.setText(item.getCategory().getName());
        mFav.setText(item.getLikes_num()+"");
        mReply.setText(item.getComments_num()+"");
        mTitle.setText(item.getTitle());
        mDesc.setText(item.getDesc());
        Glide.with(mContext).load(item.getThumb_url()).into(mBack);
    }
}
