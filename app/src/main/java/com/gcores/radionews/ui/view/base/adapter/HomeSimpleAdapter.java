package com.gcores.radionews.ui.view.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.model.news.HomeItem;
import com.gcores.radionews.ui.view.base.holder.CommonHolder;

import java.util.List;

public class HomeSimpleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<HomeItem> dataList;
    private LayoutInflater inflate;
    private NewsHeaderAdapter mNewsHeaderAdapter;
    private NewsItemAdapter  mNewsItemAdapter;

    public HomeSimpleAdapter(List<HomeItem> data, Context context){
        this.mContext = context;
        this.dataList = data;
        inflate = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        View v = inflate.inflate(R.layout.item_home_list, parent, false);
        viewHolder = new CommonHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommonHolder newsHolder = (CommonHolder) holder;
        View headView = inflate.inflate(R.layout.view_item_header,null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        newsHolder.getBodyView().setLayoutManager(linearLayoutManager);
        mNewsItemAdapter = new NewsItemAdapter(dataList.get(position).getData(),mContext);
        mNewsItemAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        newsHolder.getBodyView().setAdapter(mNewsItemAdapter);
        RecyclerView topHeaderList;
        switch (holder.getItemViewType()) {
            case HomeItem.NEWS:

//                newsHolder.getBodyView().setAdapter();
                topHeaderList =   headView.findViewById(R.id.top_header);
                ((TextView)headView.findViewById(R.id.tv_top_header)).setText("新闻联播");
                mNewsHeaderAdapter = new NewsHeaderAdapter(dataList.get(position).getNewsHeader(),mContext);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
                linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                topHeaderList.setLayoutManager(linearLayoutManager1);
             /*   topHeaderList.setLayoutManager(linearLayoutManager1);
                topHeaderList.setAdapter(newsHeaderAdapter);
                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getNewsHeader(),mContext);*/
                topHeaderList.setAdapter(mNewsHeaderAdapter);
                mNewsItemAdapter.addHeaderView(headView);
                break;
            case HomeItem.CATE:
                topHeaderList =   headView.findViewById(R.id.top_header);
                ((TextView)headView.findViewById(R.id.tv_top_header)).setText("精彩节目推荐");
                CateHeaderAdapter cateHeaderAdapter = new CateHeaderAdapter(dataList.get(position).getCateHeader(),mContext);
                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
                linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                topHeaderList.setLayoutManager(linearLayoutManager2);
                topHeaderList.setAdapter(cateHeaderAdapter);
                mNewsItemAdapter.addHeaderView(headView);
                break;
            case HomeItem.ART:
                topHeaderList =   headView.findViewById(R.id.top_header);
                ((TextView)headView.findViewById(R.id.tv_top_header)).setText("节目推荐");
                mNewsHeaderAdapter = new NewsHeaderAdapter(dataList.get(position).getArtHeader(),mContext);
                LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(mContext);
                linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
                topHeaderList.setLayoutManager(linearLayoutManager3);
             /*   topHeaderList.setLayoutManager(linearLayoutManager1);
                topHeaderList.setAdapter(newsHeaderAdapter);
                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getNewsHeader(),mContext);*/
                topHeaderList.setAdapter(mNewsHeaderAdapter);
                mNewsItemAdapter.addHeaderView(headView);
                break;
            case HomeItem.USERS:
                topHeaderList =   headView.findViewById(R.id.top_header);
                ((TextView)headView.findViewById(R.id.tv_top_header)).setText("各路大神在此集结");
                UserHeaderAdapter userHeaderAdapter = new UserHeaderAdapter(dataList.get(position).getUserHeader(),mContext);
                LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(mContext);
                linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
                topHeaderList.setLayoutManager(linearLayoutManager4);
             /*   topHeaderList.setLayoutManager(linearLayoutManager1);
                topHeaderList.setAdapter(newsHeaderAdapter);
                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getNewsHeader(),mContext);*/
                topHeaderList.setAdapter(userHeaderAdapter);
                mNewsItemAdapter.addHeaderView(headView);
                break;
            default:

                break;
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).getNewsHeader().size()>0) {
            return HomeItem.NEWS;
        } else if (dataList.get(position).getCateHeader().size()>0) {
            return HomeItem.CATE;
        }else if (dataList.get(position).getArtHeader().size()>0) {
            return HomeItem.ART;
        }
        else if (dataList.get(position).getCateHeader().size()>0) {
            return HomeItem.USERS;
        }else {
            return HomeItem.DEFAULT;
        }
    }

    public void setData(List<HomeItem> homeItems){
        this.dataList = homeItems;
        notifyDataSetChanged();
    }
    public void addData(HomeItem homeItem){
        this.dataList.add(homeItem);
        notifyDataSetChanged();
    }


}
