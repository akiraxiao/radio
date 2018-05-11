package com.gcores.radionews.ui.view.base.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.model.MenuBean;

import java.util.List;

public class LeftMenuAdapter extends BaseQuickAdapter<MenuBean,BaseViewHolder> {

    public LeftMenuAdapter( @Nullable List data) {
        super(R.layout.adapter_main_menu, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MenuBean item) {
        TextView mTvName=helper.getView(R.id.tv_name);
        mTvName.setText(item.getText());
    }
}
