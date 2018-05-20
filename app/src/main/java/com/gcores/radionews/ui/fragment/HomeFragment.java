package com.gcores.radionews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.Constant;
import com.gcores.radionews.ui.api.NewsService;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
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

    private List<TopListList> mNewsTop ;
    private List<Top> mNewsTopItemList = new ArrayList<>();
    private List<Top> mNewsHeaderList = new ArrayList<>();

    private TopListList topListList;

    private Results res;

    private View mHeanderView;//头部布局

    private Gson mGson;

    private String itemJson;
    private String headerJson;

    private View root;

//    private NewsHeaderAdapter mNewsHeaderAdapter;
//    private NewsItemAdapter mNewsItemAdapter;
    private RefreshLayout mRefreshLayout;
    private boolean fristLoad = true;

    private int currentPage = 1;//当前页数

    private HomeItemAdapter mHomeItemAdapter;

    List<HomeItem> mHomeItems = new ArrayList<>();
    ///新闻头
    public enum TitlesHeader {

        NEWS(1,"新闻联播"),CATE(2,"精彩节目推荐"),ART(3,"节目推荐"),USERS(4,"各路大神在此集结");

        private TitlesHeader(  int page,String name  ){
            this.name = name ;
            this.page = page ;
        }

        private int page;
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getPage() {
            return page;
        }
        public void setPage(int index) {
            this.page = index;
        }

    }

        public HomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mGson==null)
        mGson = new Gson();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.fragment_home,container,false);

//        mHeanderView = inflater.inflate(R.layout.view_item_header,null);
        topList = root.findViewById(R.id.toplist);
        mRefreshLayout = root.findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        //解决滑动冲突 来源:https://stackoverflow.com/questions/37423763/recycler-view-inside-recycler-view-not-scrolling
        LinearLayoutManager linearLayoutManagerHeander = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManagerHeander.setOrientation(LinearLayoutManager.VERTICAL);
        topList.setLayoutManager(linearLayoutManagerHeander);
        mHomeItemAdapter = new HomeItemAdapter(mHomeItems,getActivity());
//        mNewsItemAdapter.addHeaderView(mHeanderView);
//        topList.addItemDecoration(new SpaceItemDecoration(5,newsItemAdapter.getItemCount(),getActivity()));
        topList.setAdapter(mHomeItemAdapter);
//        topHeaderList =   mHeanderView.findViewById(R.id.top_header);
//        ((TextView)mHeanderView.findViewById(R.id.tv_top_header)).setText("新闻联播");
        if (fristLoad){
            mRefreshLayout.autoRefresh();
        }
        return root;
    }



    //请求头条数据

    private void fectchData( RefreshLayout refreshLayout) {
       /* if (!fristLoad){
            mNewsHeaderList.clear();
            mNewsTopItemList.clear();
            mNewsHeaderAdapter.notifyDataSetChanged();
            mNewsItemAdapter.notifyDataSetChanged();
        }
        fristLoad  = false;*/
//       mNewsTop.clear();
       Retrofit retrofit =  RetrofitClient.getRetrofit(UrlPath.base_url_api);
       newsService = retrofit.create(NewsService.class);
       Call<TopRes> call =  newsService.getTopNews(currentPage,Constant.AUTH_EXCLUSIVE,Constant.AUTH_TOKEN);
       call.enqueue(new Callback<TopRes>() {
           @Override
           public void onResponse(Call<TopRes> call, Response<TopRes> response) {

                if(currentPage>1){
                    refreshLayout.finishLoadMore();
                }
               refreshLayout.finishRefresh();
               HomeItem item = null;
               Log.e("eee",response.message());
               if (response.body().getStatus()==UrlPath.NET_SUCESS){

                   switch (currentPage){
                       case HomeItem.NEWS:
                           item = new HomeItem(currentPage);
                           mNewsTop =  response.body().getResults();
                           List<Results> headerNews =  setHeaderNews(mNewsTop.get(0));
                           item.setNewsHeader(headerNews);
                           item.setItemType(HomeItem.NEWS);
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
                           item = new HomeItem(currentPage);
                           mNewsTop =  response.body().getResults();
                           List<CateBanner> cateBanner =  setHeaderBanner(mNewsTop.get(0));
                           item.setCateHeader(cateBanner);
//                           item.setData(setTopNews(mNewsTopItemList));
                           item.setItemType(HomeItem.CATE);
                           mNewsTop.remove(0);
                           setHomeBody(item);
                           break;

                       case HomeItem.ART:

                           item = new HomeItem(currentPage);
                           mNewsTop =  response.body().getResults();
                           List<Results> headerArt =  setHeaderNews(mNewsTop.get(0));
                           item.setArtHeader(headerArt);
                           item.setItemType(HomeItem.ART);
//                           item.setData(setTopNews(mNewsTopItemList));
                           mNewsTop.remove(0);
                           setHomeBody(item);
                           break;

                       case HomeItem.USERS:
                           item = new HomeItem(currentPage);
                           mNewsTop =  response.body().getResults();
                           List<Results> headerUsers =  setHeaderNews(mNewsTop.get(0));
                           item.setUserHeader(headerUsers);
                           item.setItemType(HomeItem.USERS);
//                           item.setData(setTopNews(mNewsTopItemList));
                           mNewsTop.remove(0);
                           setHomeBody(item);
                           break;

                       case HomeItem.DEFAULT:
                           item = new HomeItem(currentPage);
                           mNewsTop =  response.body().getResults();
//                           List<Results> headerNews =  setHeaderNews(mNewsTop.get(0));
//                           item.setNewsHeader(headerNews);
//                           item.setData(setTopNews(mNewsTopItemList));
//                           mNewsTop.remove(0);
                           item.setItemType(HomeItem.DEFAULT);
                           setHomeBody(item);
                           break;
                   }

                   /*mNewsTop =  response.body().getResults();
                   setHeaderNews(currentPage,mNewsTop.get(0));
                   mNewsTop.remove(0);*/
//                   Gson go = new Gson();
                    mHomeItems.add(item);
                    if (currentPage==1){
                        mHomeItemAdapter.setNewData(mHomeItems);
                    }else{
                        mHomeItemAdapter.addData(item);
                    }

               }



           }

           @Override
           public void onFailure(Call<TopRes> call, Throwable t) {
               Log.e("eee",t.getMessage());
           }
       });
    }


    public void loadMoreData(RefreshLayout refreshLayout){

        fectchData(refreshLayout);
    }
    //头条
    private List<Results> setHeaderNews(TopListList topListList) {
        headerJson =  mGson.toJson(topListList.getData(),ArrayList.class);
        List<Results>   resultsList = mGson.fromJson(headerJson,new TypeToken<List<Results>>() {}.getType());
        for (int x=0;x<resultsList.size();x++){
            if (currentPage<=4){
                resultsList.get(x).setItemType(currentPage);
            }else{
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


    //Banner
    private List<CateBanner> setHeaderBanner(TopListList topListList) {
        headerJson =  mGson.toJson(topListList.getData(),ArrayList.class);
        List<CateBanner>   resultsList = mGson.fromJson(headerJson,new TypeToken<List<CateBanner>>() {}.getType());

//       mNewsHeaderAdapter.setNewData(mNewsHeaderList);
        return resultsList;
    }

    private List<Top> setTopNews(List<Top> mNewsTop) {
        itemJson =  mGson.toJson(mNewsTop,ArrayList.class);
       List<Top> resTop =   mNewsTopItemList = mGson.fromJson(itemJson,new TypeToken<List<Top>>() {}.getType());
        for (int x=0;x<mNewsTopItemList.size();x++){
            if (mNewsTopItemList.get(x).getData().getType().equals("Article")){
                mNewsTopItemList.get(x).setItemType(Top.ARTICLE);
            }else{
                mNewsTopItemList.get(x).setItemType(Top.VIDEO);
            }
        }
//        mNewsItemAdapter.setNewData(mNewsTop);
return  resTop;
    }


    private void  setHomeBody(HomeItem item){
        itemJson =  mGson.toJson(mNewsTop,ArrayList.class);
        mNewsTopItemList = mGson.fromJson(this.itemJson,new TypeToken<List<Top>>() {}.getType());
        for (int x=0;x<mNewsTopItemList.size();x++){
            if (mNewsTopItemList.get(x).getData().getType().equals("Article")){
                mNewsTopItemList.get(x).setItemType(Top.ARTICLE);
            }else{
                mNewsTopItemList.get(x).setItemType(Top.VIDEO);
            }
        }
        item.setData(mNewsTopItemList);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
          itemJson ="";
          headerJson = "";
          if(mGson!=null)
          mGson = null;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        currentPage =1;
        fectchData(refreshLayout);

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
            currentPage++;
            fectchData(refreshLayout);
           /* currentPage++;
            loadMoreData(refreshLayout);*/
    }
}
