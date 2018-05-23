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
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class ArticleAdapter extends BaseQuickAdapter<Results,BaseViewHolder> {


    private TextView mTitle;
    private TextView mCate;
    private TextView mDesc;
    private TextView mFav;
    private TextView mReply;
    private ImageView mBack;
    private RoundedImageView ivAuthor;
    private TextView mAuthorName;
    private TextView mCreateTime;
    private Context mContext;

    public ArticleAdapter(@Nullable List<Results> data, Context mContext) {
        super(R.layout.item_news_article,data);
        this.mContext = mContext;
//        addItemType(Top.VIDEO,R.layout.item_news_video);
//        addItemType(Top.ARTICLE,R.layout.item_news_article);
    }

    @Override
    protected void convert(BaseViewHolder helper, Results item) {

        /*switch (helper.getItemViewType()) {
            case Top.VIDEO:*/
        ivAuthor = helper.getView(R.id.iv_author);
        mAuthorName = helper.getView(R.id.item_ariticle_author);
        mCreateTime = helper.getView(R.id.item_ariticle_time);
        mCate =    helper.getView(R.id.item_top_categroy);
        mFav =  helper.getView(R.id.view_tv_fav);
        mReply =   helper.getView(R.id.view_tv_reply);
        mTitle =   helper.getView(R.id.item_news_title);
        mBack =  helper.getView(R.id.iv_news_video);
        mDesc = helper.getView(R.id.item_news_desc);


        Glide.with(mContext).load(item.getUser().getThumb_url()).into(ivAuthor);
        mAuthorName.setText(item.getUser().getNickname());
        mCreateTime.setText(item.getCreated_at());
        mCate.setText(item.getCategory().getName());
        mFav.setText(item.getLikes_num()+"");
        mReply.setText(item.getComments_num()+"");
        mTitle.setText(item.getTitle());
        mDesc.setText(item.getDesc());
        Glide.with(mContext).load(item.getThumb_url()).into(mBack);

    }





}

