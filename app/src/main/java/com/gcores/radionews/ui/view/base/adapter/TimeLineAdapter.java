package com.gcores.radionews.ui.view.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.inter.TimeLineListener;
import com.gcores.radionews.ui.model.TimeLine;
import com.gcores.radionews.ui.utils.Gutil;

import java.util.List;

public class TimeLineAdapter extends BaseQuickAdapter<TimeLine,BaseViewHolder> {



//    private BackImageView mBack;
//    private ImageView centerView;
//    private RoundedImageView ivAuthor;
//    private TextView mAuthorName;

    private Context mContext;
    private TimeLineListener mTimeLineListener;


    public TimeLineAdapter(@Nullable List<TimeLine> data, Context mContext) {
        super(R.layout.item_audio,data);
        this.mContext = mContext;

//        addItemType(Top.ARTICLE,R.layout.item_news_article);
    }

    @Override
    protected void convert(BaseViewHolder helper, TimeLine item) {

//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);

        ImageView ivSource = helper.getView(R.id.iv_audio_source);
        ImageView ivListen = helper.getView(R.id.iv_listen);
        helper.setText(R.id.item_audio_time, Gutil.convertToSencondAudio(item.getAt()));
        helper.setText(R.id.item_audio_title,item.getTitle());
        Glide.with(mContext).load(item.getAsset_url()).into((ImageView) helper.getView(R.id.iv_audio_back));
        helper.setText(R.id.item_tvdes,item.getContent());
        if (TextUtils.isEmpty(item.getQuote_href())){
            //为null
            ivSource.setImageResource(R.drawable.ic_no_link);
        }else{
            ivSource.setImageResource(R.drawable.ic_source);
        }
        //点击资源
        ivSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimeLineListener.onSourceClick(item.getQuote_href());
            }
        });
        ivListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimeLineListener.onPlayerSeekListen(item.getAt());
            }
        });
    }


    public void setTimeLineListener(TimeLineListener timeLineListener) {
        mTimeLineListener = timeLineListener;
    }
}
