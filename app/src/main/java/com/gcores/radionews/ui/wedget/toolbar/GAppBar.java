package com.gcores.radionews.ui.wedget.toolbar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gcores.radionews.R;

/**
 * 标题栏
 */
public class GAppBar extends Toolbar {

//    private AppCompatImageView ivLeft;
    private TextView tvCenter;
//    private AppCompatImageView ivRight;


    public GAppBar(Context context) {
        this(context, null);
    }

    public GAppBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GAppBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//       ivLeft =   findViewById(R.id.iv_left_title);
       tvCenter =   findViewById(R.id.txt_main_title);
//       ivRight =   findViewById(R.id.iv_right_title);
    }

    //设置中间title的内容
    public void setMainTitle(String text) {
        tvCenter.setText(text);
    }

    //设置中间title的内容文字的颜色
    public void setMainTitleColor(int color) {
        tvCenter.setTextColor(color);
    }

    /*//设置title左边点击事件
    public void setLeftTitleClickListener(OnClickListener onClickListener){
        ivLeft.setOnClickListener(onClickListener);
    }

    //设置title右边点击事件
    public void setRightTitleClickListener(OnClickListener onClickListener){
        ivRight.setOnClickListener(onClickListener);
    }*/


}
