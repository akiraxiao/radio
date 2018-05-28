package com.gcores.radionews.ui.view.base.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.inter.AdapterClickListener;
import com.gcores.radionews.ui.model.User;
import com.gcores.radionews.ui.model.news.CateBanner;
import com.gcores.radionews.ui.model.news.HomeItem;
import com.gcores.radionews.ui.model.news.Results;
import com.gcores.radionews.ui.model.news.Top;

import java.util.List;

public class HomeItemAdapter extends BaseQuickAdapter<HomeItem,BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {

    private Context mContext;

//    private View headerView;
    private RecyclerView topHeaderList;
    private RecyclerView bodyView;
    private TextView tvTitle;
    private LinearLayout llTop;

    private NewsHeaderAdapter mNewsHeaderAdapter;
    private NewsItemAdapter mNewsItemAdapter;
    private CateHeaderAdapter cateHeaderAdapter;
    private LinearLayoutManager linearLayoutManagerHeander;
    private LayoutInflater inflate;
    private AdapterClickListener adapterClickListener;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeItemAdapter(List<HomeItem> data, Context context) {
        super(R.layout.item_home_list,data);
//        addItemType(HomeItem.NEWS, R.layout.item_home_list);
//        addItemType(HomeItem.CATE,R.layout.item_home_list);
//        addItemType(HomeItem.ART,R.layout.item_home_list);
//        addItemType(HomeItem.USERS,R.layout.item_home_list);
//        addItemType(HomeItem.DEFAULT,R.layout.item_home_list_noheader);
        this.mContext = context;
        inflate = LayoutInflater.from(mContext);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
//        headerView =  helper.getView(R.id.top_header);
//        linearLayoutManagerHeander = new LinearLayoutManager(mContext);
//        linearLayoutManagerHeander.setOrientation(LinearLayoutManager.HORIZONTAL);
//        headerView.setLayoutManager(linearLayoutManagerHeander);
//        headerView = inflate.inflate(R.layout.view_item_header,null);
        topHeaderList = helper.getView(R.id.top_header);

        bodyView =  helper.getView(R.id.toplist_item);
        tvTitle =  helper.getView(R.id.tv_top_header);
        mNewsItemAdapter = new NewsItemAdapter(item.getData(),mContext);
        mNewsItemAdapter.setOnItemClickListener(this);
        //解决滑动冲突 来源:https://stackoverflow.com/questions/37423763/recycler-view-inside-recycler-view-not-scrolling
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        bodyView.setLayoutManager(linearLayoutManager);
//        mNewsItemAdapter.addHeaderView(mHeanderView);
//        topList.addItemDecoration(new SpaceItemDecoration(5,newsItemAdapter.getItemCount(),getActivity()));
        //开启加载动画
        mNewsItemAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        bodyView.setAdapter(mNewsItemAdapter);
        llTop = helper.getView(R.id.ll_header);
        llTop.setVisibility(View.VISIBLE);
        switch (item.getItemType()) {
            case HomeItem.NEWS:
//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);

//                topHeaderList =   headerView.findViewById(R.id.top_header);
//                ((TextView)headerView.findViewById(R.id.tv_top_header)).setText("新闻联播");
                tvTitle.setText("新闻联播");
                mNewsHeaderAdapter = new NewsHeaderAdapter(item.getNewsHeader(),mContext);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
                linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
                topHeaderList.setLayoutManager(linearLayoutManager1);
             /*   topHeaderList.setLayoutManager(linearLayoutManager1);
                topHeaderList.setAdapter(newsHeaderAdapter);
                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getNewsHeader(),mContext);*/

             topHeaderList.setAdapter(mNewsHeaderAdapter);
            mNewsHeaderAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Results results = (Results) adapter.getItem(position);
                    int topId =  results.getId();
                    adapterClickListener.onNewsClick(topId);
                }
            });
//                mNewsItemAdapter.addHeaderView(headerView);
                break;
            case HomeItem.CATE:
//                topHeaderList =   headerView.findViewById(R.id.top_header);
//                ((TextView)headerView.findViewById(R.id.tv_top_header)).setText("精彩节目推荐");
                tvTitle.setText("精彩节目推荐");
                CateHeaderAdapter cateHeaderAdapter = new CateHeaderAdapter(item.getCateHeader(),mContext);
                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
                linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
                topHeaderList.setLayoutManager(linearLayoutManager2);
             /*   topHeaderList.setLayoutManager(linearLayoutManager1);
                topHeaderList.setAdapter(newsHeaderAdapter);
                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getNewsHeader(),mContext);*/
                topHeaderList.setAdapter(cateHeaderAdapter);
                cateHeaderAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        CateBanner results = (CateBanner) adapter.getItem(position);
                        /*int topId =  results.getId();
                        adapterClickListener.onNewsClick(topId);*/
                    }
                });
//                mNewsItemAdapter.addHeaderView(headerView);
               /* LinearLayoutManager linearLayoutManagerHeander = new LinearLayoutManager(mContext);
                linearLayoutManagerHeander.setOrientation(LinearLayoutManager.HORIZONTAL);
                headerView.setLayoutManager(linearLayoutManagerHeander);*/
//                headerView.setAdapter(mNewsHeaderAdapter);
                break;
            case HomeItem.ART:
//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);

//                helper.setText(R.id.tv_top_header,"节目推荐");

//                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getArtHeader(),mContext);

//                headerView.setAdapter(mNewsHeaderAdapter);

//                topHeaderList =   headerView.findViewById(R.id.top_header);
                tvTitle.setText("节目推荐");
//                ((TextView)headerView.findViewById(R.id.tv_top_header)).setText("节目推荐");
                mNewsHeaderAdapter = new NewsHeaderAdapter(item.getArtHeader(),mContext);
                LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(mContext);
                linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
                topHeaderList.setLayoutManager(linearLayoutManager3);
             /*   topHeaderList.setLayoutManager(linearLayoutManager1);
                topHeaderList.setAdapter(newsHeaderAdapter);
                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getNewsHeader(),mContext);*/
                topHeaderList.setAdapter(mNewsHeaderAdapter);
                mNewsHeaderAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Results results = (Results) adapter.getItem(position);
                        int topId =  results.getId();
                        adapterClickListener.onNewsClick(topId);
                    }
                });
//                mNewsItemAdapter.addHeaderView(headerView);
                break;
            case HomeItem.USERS:
//                ivAuthor = helper.getView(R.id.iv_author);
//                mAuthorName = helper.getView(R.id.item_ariticle_author);
//                helper.setText(R.id.tv_top_header,"各路大神再次集结");
//                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getUserHeader(),mContext);
//                headerView.setAdapter(mNewsHeaderAdapter);

//                topHeaderList =   headerView.findViewById(R.id.top_header);
//                ((TextView)headerView.findViewById(R.id.tv_top_header)).setText("各路大神在此集结");
                tvTitle.setText("各路大神在此集结");
                UserHeaderAdapter userHeaderAdapter = new UserHeaderAdapter(item.getUserHeader(),mContext);
                LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(mContext);
                linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
                topHeaderList.setLayoutManager(linearLayoutManager4);
             /*   topHeaderList.setLayoutManager(linearLayoutManager1);
                topHeaderList.setAdapter(newsHeaderAdapter);
                mNewsHeaderAdapter= new NewsHeaderAdapter(item.getNewsHeader(),mContext);*/
                topHeaderList.setAdapter(userHeaderAdapter);
                userHeaderAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        User uItem = (User) adapter.getItem(position);
                        int topId =  uItem.getId();
                        adapterClickListener.onNewsClick(topId);
                    }
                });
//                mNewsItemAdapter.addHeaderView(headerView);
                break;
            case HomeItem.DEFAULT:
                llTop.setVisibility(View.GONE);
                break;
        }
    }


    public void setAdapterItemListener(AdapterClickListener clickListener){
        this.adapterClickListener = clickListener;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Top itemH = (Top) adapter.getItem(position);
        int topId =  itemH.getData().getId();
        adapterClickListener.onNewsClick(topId);
    }
}
