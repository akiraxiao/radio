package com.gcores.radionews.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.Constant;
import com.gcores.radionews.ui.api.NewsService;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.inter.BannerListner;
import com.gcores.radionews.ui.model.User;
import com.gcores.radionews.ui.model.news.CateBanner;
import com.gcores.radionews.ui.model.news.HomeItem;
import com.gcores.radionews.ui.model.news.Results;
import com.gcores.radionews.ui.model.news.Top;
import com.gcores.radionews.ui.model.news.TopListList;
import com.gcores.radionews.ui.resmoel.TopRes;
import com.gcores.radionews.ui.view.base.adapter.HomeItemAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//首页
public class HomeFragment extends AppFragment implements OnRefreshListener, OnLoadMoreListener {

    private RecyclerView topList;
    //private RecyclerView topHeaderList;*/

    private NewsService newsService;

    private List<TopListList> mNewsTop;
    private List<Top> mNewsTopItemList = new ArrayList<>();
    private List<Top> mNewsHeaderList = new ArrayList<>();

    private TopListList topListList;

    private Results res;

    private View mHeanderView;//头部布局

    private Gson mGson;

    private String itemJson;
    private String headerJson;

    private View root;


    private HomeItem item;
    //    private NewsHeaderAdapter mNewsHeaderAdapter;
//    private NewsItemAdapter mNewsItemAdapter;
    private RefreshLayout mRefreshLayout;
    private boolean fristLoad = true;

    private int currentPage = 1;//当前页数

    private HomeItemAdapter mHomeItemAdapter;
    //    private HomeSimpleAdapter mHomeSimpleAdapter;
    List<HomeItem> mHomeItems = new ArrayList<>();

    private boolean loadCompelete;//加载是否完毕


    private final int TOTAL_COUNTER = 6;
    private final int TOTAL_LIST = 5;

    private int current_counter;

    public BannerListner mListener;

    public HomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mGson == null)
            mGson = new Gson();

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
        mHomeItemAdapter = new HomeItemAdapter(mHomeItems, getActivity());
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
        topList.setAdapter(mHomeItemAdapter);
//        mRefreshLayout.setEnableLoadMore(false);
//        topHeaderList =   mHeanderView.findViewById(R.id.top_header);
//        ((TextView)mHeanderView.findViewById(R.id.tv_top_header)).setText("新闻联播");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        mHomeItemAdapter.setEnableLoadMore(false);
        Retrofit retrofit = RetrofitClient.getRetrofit(UrlPath.base_url_api);
        newsService = retrofit.create(NewsService.class);
        Call<TopRes> call = newsService.getTopNews(mcurrentPage, Constant.AUTH_EXCLUSIVE, Constant.AUTH_TOKEN);
        call.enqueue(new Callback<TopRes>() {
            @Override
            public void onResponse(Call<TopRes> call, Response<TopRes> response) {
//               refreshLayout.finishRefresh();
//                mHomeItems.clear();
                loadCompelete = true;
                current_counter = response.body().getResults().size();
//                 = null;
                Log.e("eee", response.message());
                if (response.body().getStatus() == UrlPath.NET_SUCESS) {

                    switch (mcurrentPage) {
                        case HomeItem.NEWS:
                            item = new HomeItem(HomeItem.NEWS);
                            mNewsTop = response.body().getResults();
                            List<Results> headerNews = setHeaderNews(mcurrentPage, mNewsTop.get(0));
                            item.setNewsHeader(headerNews);
//                            item.setItemType(HomeItem.NEWS);
                            mNewsTop.remove(0);
                            setHomeBody(item);
                           /*itemJson =  mGson.toJson(mNewsTop,ArrayList.class);
                           mNewsTopItemList = mGson.fromJson(itemJson,new TypeToken<List<Top>>() {}.getType());
                           for (int x=0;x<mNewsTopItemList.size();x++){
                               if (mNewsTopItemList.get(x).getData().getType().equals("Article")){
                                   mNewsTopItemList.get(x).setItemType(Top.ARTICLE);
                               }else{
                                   mNewsTopItemList.get(x).setItemType(Top.VIDEO);
                               }
                           }
                           item.setData(setTopNews(mNewsTopItemList));*/

                            break;

                        case HomeItem.CATE:
                            item = new HomeItem(HomeItem.CATE);
                            mNewsTop = response.body().getResults();
                            List<CateBanner> cateBanner = setHeaderBanner(mNewsTop.get(0));
                            item.setCateHeader(cateBanner);
//                           item.setData(setTopNews(mNewsTopItemList));
//                            item.setItemType(HomeItem.CATE);
                            mNewsTop.remove(0);
                            setHomeBody(item);
                            break;

                        case HomeItem.ART:

                            item = new HomeItem(HomeItem.ART);
                            mNewsTop = response.body().getResults();
                            List<Results> headerArt = setHeaderNews(mcurrentPage, mNewsTop.get(0));
                            item.setArtHeader(headerArt);
//                            item.setItemType(HomeItem.ART);
//                           item.setData(setTopNews(mNewsTopItemList));
                            mNewsTop.remove(0);
                            setHomeBody(item);
                            break;

                        case HomeItem.USERS:
                            item = new HomeItem(HomeItem.USERS);
                            mNewsTop = response.body().getResults();
                            List<User> headerUsers = setHeaderUser(mNewsTop.get(0));
                            item.setUserHeader(headerUsers);
//                            item.setItemType(HomeItem.USERS);
//                           item.setData(setTopNews(mNewsTopItemList));
                            mNewsTop.remove(0);
                            setHomeBody(item);
                            break;
                        default:
                            item = new HomeItem(HomeItem.DEFAULT);
                            mNewsTop = response.body().getResults();
//                           List<Results> headerNews =  setHeaderNews(mNewsTop.get(0));
//                           item.setNewsHeader(headerNews);
//                           item.setData(setTopNews(mNewsTopItemList));
//                           mNewsTop.remove(0);
//                            item.setItemType(HomeItem.DEFAULT);
                            setHomeBody(item);
                            break;
                    }


                    if (currentPage == 1) {
                        mHomeItems.add(item);
                    }

                    if (currentPage > 1) {
                        refreshLayout.finishLoadMore();
//                        mHomeItemAdapter.setNewData(mHomeItems);
//                        mHomeItemAdapter.setEnableLoadMore(false);
                        setHomeData(false, null, item);
//                        mHomeItemAdapter.setEnableLoadMore(true);
                    } else {
//                        mHomeItemAdapter.setEnableLoadMore(true);
                        setHomeData(true, mHomeItems, null);
                        refreshLayout.finishRefresh();
                    }
//                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<TopRes> call, Throwable t) {
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

    private void setHomeData(boolean isRefresh, List<HomeItem> listItem, HomeItem itemHome) {
//        final int size = item == null ? 0 : item.size();
        if (isRefresh) {
            mHomeItemAdapter.setNewData(listItem);
            mHomeItemAdapter.setEnableLoadMore(true);
//            mHomeSimpleAdapter.setData(listItem);
        } else {
            if (current_counter >= TOTAL_LIST) {
                mHomeItemAdapter.addData(itemHome);
                mHomeItemAdapter.setEnableLoadMore(true);
//                mHomeSimpleAdapter.addData(itemHome);
            }
        }

        if (currentPage < 5) {
            if (current_counter < TOTAL_COUNTER) {
                //第一页如果不够一页就不显示没有更多数据布局
                mHomeItemAdapter.loadMoreEnd(isRefresh);
                Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
            } else {
//            int count =  mHomeItemAdapter.getData().size();
                mHomeItemAdapter.loadMoreComplete();
            }

        } else {
            if (current_counter < TOTAL_LIST) {
                //第一页如果不够一页就不显示没有更多数据布局
                mHomeItemAdapter.loadMoreEnd(isRefresh);
                Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
            } else {
//            int count =  mHomeItemAdapter.getData().size();
                mHomeItemAdapter.loadMoreComplete();
            }

        }

    }


    /* public void loadMoreData(RefreshLayout refreshLayout){

         fectchData(refreshLayout);
     }*/
    //头条
    private List<Results> setHeaderNews(int page, TopListList topListList) {
        headerJson = mGson.toJson(topListList.getData(), ArrayList.class);
        List<Results> resultsList = mGson.fromJson(headerJson, new TypeToken<List<Results>>() {
        }.getType());
        for (int x = 0; x < resultsList.size(); x++) {
            if (page <= 4) {
                resultsList.get(x).setItemType(page);
            } else {
                resultsList.get(x).setItemType(5);
            }
        }
          /* for (int x=0;x<resultsList.size();x++){
              Top top = new Top();
//              top.setItemType(Top.HEADER_NEWS);
              top.setData(resultsList.get(x));
//              mNewsHeaderList.add(top);
          }*/
//       mNewsHeaderAdapter.setNewData(mNewsHeaderList);
        return resultsList;
    }

    //各路大神
    private List<User> setHeaderUser(TopListList topListList) {
        headerJson = mGson.toJson(topListList.getData(), ArrayList.class);
        List<User> userList = mGson.fromJson(headerJson, new TypeToken<List<User>>() {
        }.getType());
//       mNewsHeaderAdapter.setNewData(mNewsHeaderList);
        return userList;
    }

    //Banner
    private List<CateBanner> setHeaderBanner(TopListList topListList) {
        headerJson = mGson.toJson(topListList.getData(), ArrayList.class);
        List<CateBanner> resultsList = mGson.fromJson(headerJson, new TypeToken<List<CateBanner>>() {
        }.getType());

//       mNewsHeaderAdapter.setNewData(mNewsHeaderList);
        return resultsList;
    }

    private List<Top> setTopNews(List<Top> mNewsTop) {
        itemJson = mGson.toJson(mNewsTop, ArrayList.class);
        List<Top> resTop = mNewsTopItemList = mGson.fromJson(itemJson, new TypeToken<List<Top>>() {
        }.getType());
        for (int x = 0; x < mNewsTopItemList.size(); x++) {
            if (mNewsTopItemList.get(x).getData().getType().equals("Article")) {
                mNewsTopItemList.get(x).setItemType(Top.ARTICLE);
            } else {
                mNewsTopItemList.get(x).setItemType(Top.VIDEO);
            }
        }
//        mNewsItemAdapter.setNewData(mNewsTop);
        return resTop;
    }


    private void setHomeBody(HomeItem item) {
        itemJson = mGson.toJson(mNewsTop, ArrayList.class);
        mNewsTopItemList = mGson.fromJson(this.itemJson, new TypeToken<List<Top>>() {
        }.getType());
        for (int x = 0; x < mNewsTopItemList.size(); x++) {
            if (mNewsTopItemList.get(x).getData().getType().equals("Article")) {
                mNewsTopItemList.get(x).setItemType(Top.ARTICLE);
            } else {
                mNewsTopItemList.get(x).setItemType(Top.VIDEO);
            }
        }
        item.setData(mNewsTopItemList);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        itemJson = "";
        headerJson = "";
        if (mGson != null)
            mGson = null;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mListener.requestBanner();
        refresh(refreshLayout);
    }

    private void refresh(RefreshLayout refreshLayout) {
        if (!fristLoad) {
            mHomeItems.clear();
        }
        currentPage = 1;
//        mHomeItemAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        fectchData(currentPage, refreshLayout);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//            loadMore(currentPage,refreshLayout);
        currentPage++;
//        mHomeItemAdapter.setEnableLoadMore(false);
        fectchData(currentPage, refreshLayout);
    }

    private void loadMore(RefreshLayout refreshLayout) {
        currentPage++;
//        mHomeItemAdapter.setEnableLoadMore(true);
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
            mHomeItemAdapter.notifyDataSetChanged();
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
}
