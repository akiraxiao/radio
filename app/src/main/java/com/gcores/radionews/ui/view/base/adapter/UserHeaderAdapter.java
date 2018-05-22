package com.gcores.radionews.ui.view.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.model.User;

import java.util.List;

//各路大神
public class UserHeaderAdapter extends BaseQuickAdapter<User,BaseViewHolder> {



//    private BackImageView mBack;
//    private ImageView centerView;
//    private RoundedImageView ivAuthor;
//    private TextView mAuthorName;


    private Context mContext;

    public UserHeaderAdapter(@Nullable List<User> data, Context mContext) {
        super(R.layout.item_header_user,data);
        this.mContext = mContext;

//        addItemType(Top.ARTICLE,R.layout.item_news_article);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {

//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);
        helper.setText(R.id.item_user_name,item.getNickname());
        Glide.with(mContext).load(item.getThumb_url()).into((ImageView) helper.getView(R.id.iv_author));


    }
}
