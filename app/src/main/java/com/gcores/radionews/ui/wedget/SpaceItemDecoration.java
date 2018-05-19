package com.gcores.radionews.ui.wedget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.gcores.radionews.ui.utils.DpUtil;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    int mSpace ;
    private int count;

    /**
     * @param space 传入的值，其单位视为dp
     */
    public SpaceItemDecoration(int space, int count, Context context) {
        this.mSpace = DpUtil.dip2px(context,space);
        this.count = count;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount  = count;
        int pos = parent.getChildAdapterPosition(view);
        outRect.left = 0;
        outRect.top = 0;
        outRect.bottom = 0;
        if (pos != (itemCount -1)) {
            outRect.right = mSpace;
        } else {
            outRect.right = 0;
        }
    }
}
