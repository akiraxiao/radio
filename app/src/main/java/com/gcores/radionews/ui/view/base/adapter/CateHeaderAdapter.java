package com.gcores.radionews.ui.view.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.model.news.CateBanner;
import com.gcores.radionews.ui.wedget.BackImageView;

import java.util.List;

public class CateHeaderAdapter extends BaseQuickAdapter<CateBanner,BaseViewHolder> {



    private BackImageView mBack;
    private ImageView centerView;
//    private RoundedImageView ivAuthor;
//    private TextView mAuthorName;


    private Context mContext;

    public CateHeaderAdapter(@Nullable List<CateBanner> data, Context mContext) {
        super(R.layout.item_cate_header,data);
        this.mContext = mContext;

//        addItemType(Top.ARTICLE,R.layout.item_news_article);
    }

    @Override
    protected void convert(BaseViewHolder helper, CateBanner item) {

//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);

                mBack =  helper.getView(R.id.iv_news_video);
                centerView = helper.getView(R.id.iv_item_catename);
//                mDesc = helper.getView(R.id.item_news_desc);
                helper.setText(R.id.item_ariticle_count,"文章 "+item.getOriginals_num());
                helper.setText(R.id.item_subcribe_count,"订阅 "+item.getSubscriptors_num());

//                Glide.with(mContext).load(item.getData().getUser().getThumbUrl()).into(ivAuthor);
//                mAuthorName.setText(item.getData().getUser().getNickname());

//                mDesc.setText(item.getData().getDesc());
                Glide.with(mContext).load(item.getBackground_url()).into(mBack);
                Glide.with(mContext).load(item.getLogo_url()).into(centerView);


    }
}
