package com.gcores.radionews.ui.view.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.model.news.Top;
import com.gcores.radionews.ui.utils.Gutil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class NewsItemAdapter extends BaseMultiItemQuickAdapter<Top,BaseViewHolder> {


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

    public NewsItemAdapter(@Nullable List<Top> data, Context mContext) {
        super(data);
        this.mContext = mContext;
        addItemType(Top.VIDEO,R.layout.item_news_video);
        addItemType(Top.ARTICLE,R.layout.item_news_article);
    }

    @Override
    protected void convert(BaseViewHolder helper, Top item) {

        switch (helper.getItemViewType()) {
            case Top.VIDEO:
                mCate =    helper.getView(R.id.item_top_categroy);
                mFav =  helper.getView(R.id.view_tv_fav);
                mReply =   helper.getView(R.id.view_tv_reply);
                mTitle =   helper.getView(R.id.item_news_title);
                mBack =  helper.getView(R.id.iv_news_video);
                mDesc = helper.getView(R.id.item_news_desc);

                helper.setText(R.id.item_top_time, Gutil.convertToSencond(item.getData().getDuration()));
                mCate.setText(item.getData().getCategory().getName());
                mFav.setText(item.getData().getLikes_num()+"");
                mReply.setText(item.getData().getComments_num()+"");
                mTitle.setText(item.getData().getTitle());
                mDesc.setText(item.getData().getDesc());

//                mBack.setColorFilter(Color.parseColor("#30000000"), PorterDuff.Mode.LIGHTEN);//指定滤镜颜色以及混合模式
                Glide.with(mContext).load(item.getData().getThumb_url()).into(mBack);
                break;
            case Top.ARTICLE:

                ivAuthor = helper.getView(R.id.iv_author);
                mAuthorName = helper.getView(R.id.item_ariticle_author);
                mCreateTime = helper.getView(R.id.item_ariticle_time);
                mCate =    helper.getView(R.id.item_top_categroy);
                mFav =  helper.getView(R.id.view_tv_fav);
                mReply =   helper.getView(R.id.view_tv_reply);
                mTitle =   helper.getView(R.id.item_news_title);
                mBack =  helper.getView(R.id.iv_news_video);
                mDesc = helper.getView(R.id.item_news_desc);


                Glide.with(mContext).load(item.getData().getUser().getThumb_url()).into(ivAuthor);
                mAuthorName.setText(item.getData().getUser().getNickname());
                mCreateTime.setText(item.getData().getCreated_at());
                mCate.setText(item.getData().getCategory().getName());
                mFav.setText(item.getData().getLikes_num()+"");
                mReply.setText(item.getData().getComments_num()+"");
                mTitle.setText(item.getData().getTitle());
                mDesc.setText(item.getData().getDesc());
                Glide.with(mContext).load(item.getData().getThumb_url()).into(mBack);
                break;
        }




    }
}
