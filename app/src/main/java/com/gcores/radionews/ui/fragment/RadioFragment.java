package com.gcores.radionews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.Constant;
import com.gcores.radionews.ui.DetailActvity;
import com.gcores.radionews.ui.api.NewsApi;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.inter.BannerListner;
import com.gcores.radionews.ui.model.news.Radio;
import com.gcores.radionews.ui.resmoel.RadioRes;
import com.gcores.radionews.ui.view.base.adapter.RadioAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RadioFragment extends AppFragment implements OnRefreshListener, OnLoadMoreListener {

    private RecyclerView topList;
    //private RecyclerView topHeaderList;*/

    private NewsApi newsApi;

    public BannerListner mListener;
//    private List<Top> mNewsTopItemList = new ArrayList<>();
//    private List<Top> mNewsHeaderList = new ArrayList<>();



    private View mHeanderView;//头部布局

//    private Gson mGson;


    private View root;


//    private HomeItem item;
    //    private NewsHeaderAdapter mNewsHeaderAdapter;
//    private NewsItemAdapter mNewsItemAdapter;
    private RefreshLayout mRefreshLayout;
    private boolean fristLoad = true;

    private int currentPage = 1;//当前页数

    private RadioAdapter mRadioAdapter;
    //    private HomeSimpleAdapter mHomeSimpleAdapter;
    List<Radio> mRadioItems = new ArrayList<>();

    private boolean loadCompelete;//加载是否完毕

    private final int TOTAL_COUNTER = 10;

    private int current_counter;

//    public BannerListner mListener;

    public RadioFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (mGson == null)
            mGson = new Gson();*/

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.fragment_home, container, false);

//        mHeanderView = inflater.inflate(R.layout.view_item_header,null);
        topList = root.findViewById(R.id.toplist);
        mRefreshLayout = root.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);

        LinearLayoutManager linearLayoutManagerHeander = new LinearLayoutManager(getActivity());
        linearLayoutManagerHeander.setOrientation(LinearLayoutManager.VERTICAL);
        topList.setLayoutManager(linearLayoutManagerHeander);
        mRadioAdapter = new RadioAdapter(mRadioItems, getActivity());
        /*mHomeItemAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore(mRefreshLayout);
            }
        },topList);*/
//        mHomeSimpleAdapter = new HomeSimpleAdapter(mHomeItems, getActivity());

//        mNewsItemAdapter.addHeaderView(mHeanderView);
//        topList.addItemDecoration(new SpaceItemDecoration(5,newsItemAdapter.getItemCount(),getActivity()));
//        mHomeItemAdapter.bindToRecyclerView(topList);
//        mHomeItemAdapter.disableLoadMoreIfNotFullPage();
//          mHomeItemAdapter.disableLoadMoreIfNotFullPage(topList);
        topList.setAdapter(mRadioAdapter);
        mRadioAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Radio itemRadio = (Radio) adapter.getItem(position);
                int topId =  itemRadio.getId();
                String url = "https://www.g-cores.com/api/originals/"+topId+"/html_content?auth_exclusive="+Constant.AUTH_EXCLUSIVE+"&quickdownload=1&auth_token="+Constant.AUTH_TOKEN;
                Intent intent = new Intent(getActivity(), DetailActvity.class);
                intent.putExtra("url",url);
                intent.putExtra("commentnum",itemRadio.getComments_num());
                intent.putExtra("userid",itemRadio.getUser().getId());
                startActivity(intent);
            }
        });
//        mRefreshLayout.setEnableLoadMore(false);
//        topHeaderList =   mHeanderView.findViewById(R.id.top_header);
//        ((TextView)mHeanderView.findViewById(R.id.tv_top_header)).setText("新闻联播");
        /*if (fristLoad) {
            mRefreshLayout.autoRefresh();
        }*/
        return root;
    }


    //请求头条数据

    private void fectchData(int mcurrentPage, RefreshLayout refreshLayout) {
       /* if (!fristLoad){
            mNewsHeaderList.clear();
            mNewsTopItemList.clear();
            mNewsHeaderAdapter.notifyDataSetChanged();
            mNewsItemAdapter.notifyDataSetChanged();
        }
        fristLoad  = false;*/
//       mNewsTop.clear();

        loadCompelete = !loadCompelete;
        mRadioAdapter.setEnableLoadMore(false);
        Retrofit retrofit = RetrofitClient.getRetrofit(UrlPath.base_url_api);
        newsApi = retrofit.create(NewsApi.class);
        Call<RadioRes> call = newsApi.getRadios(mcurrentPage, Constant.AUTH_EXCLUSIVE, Constant.AUTH_TOKEN);
        call.enqueue(new Callback<RadioRes>() {
            @Override
            public void onResponse(Call<RadioRes> call, Response<RadioRes> response) {
//               refreshLayout.finishRefresh();
//                mHomeItems.clear();
                loadCompelete = true;
                current_counter = response.body().getResults().size();
//                 = null;
                Log.e("eee", response.message());
                if (response.body().getStatus() == UrlPath.NET_SUCESS) {
                    mRadioItems = response.body().getResults();
                    if (currentPage > 1) {
                        refreshLayout.finishLoadMore();
//                        mHomeItemAdapter.setNewData(mHomeItems);
//                        mHomeItemAdapter.setEnableLoadMore(false);
                        setRadioData(false, mRadioItems);
//                        mHomeItemAdapter.setEnableLoadMore(true);
                    } else {
//                        mHomeItemAdapter.setEnableLoadMore(true);
                        setRadioData(true,mRadioItems);
                        refreshLayout.finishRefresh();
                    }
//                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<RadioRes> call, Throwable t) {
                Log.e("eee", t.getMessage());
                loadCompelete = true;
                if (currentPage > 1) {
                    refreshLayout.finishLoadMore();
//                    mHomeItemAdapter.loadMoreFail();
                } else {
//                    mHomeItemAdapter.setEnableLoadMore(false);
                    refreshLayout.finishRefresh();
                }
            }

        });
    }

    private void setRadioData(boolean isRefresh, List<Radio> listItem) {
//        final int size = item == null ? 0 : item.size();
        if (isRefresh) {
            mRadioAdapter.setNewData(listItem);
            mRadioAdapter.setEnableLoadMore(true);
//            mHomeSimpleAdapter.setData(listItem);
        } else {
            mRadioAdapter.addData(listItem);
            mRadioAdapter.setEnableLoadMore(true);
//                mHomeSimpleAdapter.addData(itemHome);
        }


            if (current_counter < TOTAL_COUNTER) {
                //第一页如果不够一页就不显示没有更多数据布局
                mRadioAdapter.loadMoreEnd(isRefresh);
//                Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
                Snackbar.make(getActivity().findViewById(R.id.container), getString(R.string.nomore_data), Snackbar.LENGTH_SHORT).show();
        } else {
//            int count =  mHomeItemAdapter.getData().size();
                mRadioAdapter.loadMoreComplete();
            }


    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mListener.requestBanner();
        refresh(refreshLayout);
    }



    private void refresh(RefreshLayout refreshLayout) {
        mRadioItems.clear();
       /* if (!fristLoad){
            mRadioItems.clear();
        }*/
        currentPage = 1;
//        mHomeItemAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        fectchData(currentPage, refreshLayout);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        currentPage++;
        fectchData(currentPage, refreshLayout);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (BannerListner) context;
    }

    @Override
    protected void onFragmentFirstVisible() {
        if (fristLoad) {
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        if (isVisible) {
            //更新界面数据，如果数据还在下载中，就显示加载框
            mRadioAdapter.notifyDataSetChanged();
            if (!loadCompelete) {
                if (currentPage == 1) {
                    refresh(mRefreshLayout);
                } else {
                    loadMore(mRefreshLayout);
                }
            } else {
                if (currentPage == 1) {
                    mRefreshLayout.finishRefresh();
                } else {
                    mRefreshLayout.finishLoadMore();
                }

            }


        }
    }

    private void loadMore(RefreshLayout refreshLayout) {
        currentPage++;
//        mHomeItemAdapter.setEnableLoadMore(true);
        fectchData(currentPage, refreshLayout);
    }


}
