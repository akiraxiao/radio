package com.gcores.radionews.ui.wedget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.gcores.radionews.R;

public class BackImageView extends AppCompatImageView {
    public static int DEFAULT_DIM = 0x99000000;
    int mDimColor;

    public BackImageView(Context context) {
        this(context, null);
    }

    public BackImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BackImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BackImageView, defStyleAttr, 0);
        mDimColor = array.getColor(R.styleable.BackImageView_colorback, DEFAULT_DIM);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(mDimColor);
    }
}
