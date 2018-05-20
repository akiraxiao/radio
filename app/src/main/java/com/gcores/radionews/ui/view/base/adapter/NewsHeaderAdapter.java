package com.gcores.radionews.ui.view.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.model.news.Results;
import com.gcores.radionews.ui.wedget.BackImageView;

import java.util.List;

public class NewsHeaderAdapter extends BaseMultiItemQuickAdapter<Results,BaseViewHolder> {


    private TextView mTitle;
    private TextView mCate;
//    private TextView mDesc;
    private TextView mFav;
    private TextView mReply;
    private BackImageView mBack;

//    private RoundedImageView ivAuthor;
//    private TextView mAuthorName;
    private TextView mCreateTime;

    private Context mContext;

    public NewsHeaderAdapter(@Nullable List<Results> data, Context mContext) {
        super(data);
        this.mContext = mContext;
        addItemType(Results.HEADER_NEWS,R.layout.item_news_header);
//        addItemType(Results.HEADER_CATE,R.layout.item_cate_header);
        addItemType(Results.HEADER_ART,R.layout.item_news_header);
        addItemType(Results.HEADER_USER,R.layout.item_header_user);
//        addItemType(Top.ARTICLE,R.layout.item_news_article);
    }

    @Override
    protected void convert(BaseViewHolder helper, Results item) {

        switch (helper.getItemViewType()) {

            case Results.HEADER_NEWS:
//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);
                mCreateTime = helper.getView(R.id.item_ariticle_time);
                mCate =    helper.getView(R.id.item_categroy_name);
                mFav =  helper.getView(R.id.view_tv_fav);
                mReply =   helper.getView(R.id.view_tv_reply);
                mTitle =   helper.getView(R.id.tv_item_header);
                mBack =  helper.getView(R.id.iv_news_video);
//                mDesc = helper.getView(R.id.item_news_desc);


//                Glide.with(mContext).load(item.getData().getUser().getThumbUrl()).into(ivAuthor);
//                mAuthorName.setText(item.getData().getUser().getNickname());
                mCreateTime.setText(item.getCreated_at()+"");
                mCate.setText(item.getCategory().getName());
                mFav.setText(item.getLikes_num()+"");
                mReply.setText(item.getComments_num()+"");
                mTitle.setText(item.getTitle());
//                mDesc.setText(item.getData().getDesc());
                Glide.with(mContext).load(item.getThumb_url()).into(mBack);



                break;

            case Results.HEADER_ART:
//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);
                mCreateTime = helper.getView(R.id.item_ariticle_time);
                mCate =    helper.getView(R.id.item_categroy_name);
                mFav =  helper.getView(R.id.view_tv_fav);
                mReply =   helper.getView(R.id.view_tv_reply);
                mTitle =   helper.getView(R.id.tv_item_header);
                mBack =  helper.getView(R.id.iv_news_video);
//                mDesc = helper.getView(R.id.item_news_desc);


//                Glide.with(mContext).load(item.getData().getUser().getThumbUrl()).into(ivAuthor);
//                mAuthorName.setText(item.getData().getUser().getNickname());
                mCreateTime.setText(item.getCreated_at()+"");
                mCate.setText(item.getCategory().getName());
                mFav.setText(item.getLikes_num()+"");
                mReply.setText(item.getComments_num()+"");
                mTitle.setText(item.getTitle());
//                mDesc.setText(item.getData().getDesc());
                Glide.with(mContext).load(item.getThumb_url()).into(mBack);

                break;
            case Results.HEADER_USER:
//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);
//                mCreateTime = helper.getView(R.id.item_ariticle_time);
//                mCate =    helper.getView(R.id.item_categroy_name);
//                mFav =  helper.getView(R.id.view_tv_fav);
//                mReply =   helper.getView(R.id.view_tv_reply);
//                mTitle =   helper.getView(R.id.tv_item_header);
//                mBack =  helper.getView(R.id.iv_news_video);
//                mDesc = helper.getView(R.id.item_news_desc);


//                Glide.with(mContext).load(item.getData().getUser().getThumbUrl()).into(ivAuthor);
//                mAuthorName.setText(item.getData().getUser().getNickname());
//                mCreateTime.setText(item.getCreated_at()+"");
//                mCate.setText(item.getCategory().getName());
//                mFav.setText(item.getLikes_num()+"");
//                mReply.setText(item.getComments_num()+"");
//                mTitle.setText(item.getTitle());
//                mDesc.setText(item.getData().getDesc());
                helper.setText(R.id.item_user_name,item.getUser().getNickname());
                Glide.with(mContext).load(item.getUser().getThumb_url()).into((ImageView) helper.getView(R.id.iv_author));
                break;
        }




    }
}
