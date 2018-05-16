package com.gcores.radionews.ui.view.base.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;

import java.util.List;

/**
 * rv的adapter
 */
public class RvListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public RvListAdapter(@Nullable List<String> data) {
        super(R.layout.item_rvlist, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView mTvName=helper.getView(R.id.tv_itemrv);
        mTvName.setText(item);
    }
}
