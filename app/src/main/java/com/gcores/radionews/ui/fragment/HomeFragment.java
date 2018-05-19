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
import android.widget.TextView;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.api.NewsService;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.model.news.Results;
import com.gcores.radionews.ui.model.news.Top;
import com.gcores.radionews.ui.model.news.TopListList;
import com.gcores.radionews.ui.resmoel.TopRes;
import com.gcores.radionews.ui.view.base.adapter.NewsHeaderAdapter;
import com.gcores.radionews.ui.view.base.adapter.NewsItemAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends AppFragment {

    private RecyclerView topList;
    private RecyclerView topHeaderList;

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

    private NewsHeaderAdapter mNewsHeaderAdapter;


    public HomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGson = new Gson();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.fragment_home,container,false);
        mHeanderView = inflater.inflate(R.layout.view_item_header,null);
        topList = root.findViewById(R.id.toplist);
        fectchData();
        return root;
    }



    //请求头条数据

    private void fectchData() {
//       mNewsTop.clear();
       Retrofit retrofit =  RetrofitClient.getRetrofit(UrlPath.base_url_api);
       newsService = retrofit.create(NewsService.class);
       Call<TopRes> call =  newsService.getTopNews();

       call.enqueue(new Callback<TopRes>() {
           @Override
           public void onResponse(Call<TopRes> call, Response<TopRes> response) {


               Log.e("eee",response.message());
               if (response.body().getStatus()==UrlPath.NET_SUCESS){
                   mNewsTop =  response.body().getResults();
                   setHeaderNews(mNewsTop.get(0));
                   mNewsTop.remove(0);
//                   Gson go = new Gson();
                   itemJson =  mGson.toJson(mNewsTop,ArrayList.class);
                   mNewsTopItemList = mGson.fromJson(itemJson,new TypeToken<List<Top>>() {}.getType());
                   for (int x=0;x<mNewsTopItemList.size();x++){
                       if (mNewsTopItemList.get(x).getData().getType().equals("Article")){
                           mNewsTopItemList.get(x).setItemType(Top.ARTICLE);
                       }else{
                           mNewsTopItemList.get(x).setItemType(Top.VIDEO);
                       }
                   }
                   setTopNews(mNewsTopItemList);
                   /*if(ss.length() > 4000) {
                       for(int i=0;i<ss.length();i+=4000){
                           if(i+4000<ss.length())
                               Log.e("eee"+i,ss.substring(i, i+4000));
                           else
                               Log.e("eee"+i,ss.substring(i, ss.length()));
                       }
                   } else {
                       Log.e("eee", ss);
                   }*/
               }

                   /*mNewsTop =  response.body().getResults();
                   mNewsTop.remove(0);
                   setHeaderNews(mNewsTop.get(0));
//                   mNewsTop.remove(0);

                   //遍历数据并且进行布局
                   for (TopListList itemList:mNewsTop){
                        Top top = new Top();
                        top.setType(itemList.getType());
//                        if (itemList.getData() instanceof Results){
                            if (resBean instanceof Results){
                                if (res.getType().equals("Article")){
                                    top.setItemType(Top.ARTICLE);
                                }else{
                                    top.setItemType(Top.VIDEO);
                                }
                                top.setData(res);
                            }



//                        }

                        mNewsTopItemList.add(top);
                   }
                   setTopNews(mNewsTopItemList);*/

           }

           @Override
           public void onFailure(Call<TopRes> call, Throwable t) {
               Log.e("eee",t.getMessage());
           }
       });
    }

    //头条
    private void setHeaderNews(TopListList topListList) {
        headerJson =  mGson.toJson(topListList.getData(),ArrayList.class);
      List<Results>   resultsList = mGson.fromJson(headerJson,new TypeToken<List<Results>>() {}.getType());
        for (int x=0;x<resultsList.size();x++){
            Top top = new Top();
            top.setItemType(Top.HEADER);
            top.setData(resultsList.get(x));
            mNewsHeaderList.add(top);
        }
        topHeaderList =   mHeanderView.findViewById(R.id.top_header);
        ((TextView)mHeanderView.findViewById(R.id.tv_top_header)).setText("新闻联播");
        NewsHeaderAdapter newsHeaderAdapter = new NewsHeaderAdapter(mNewsHeaderList,mHeanderView.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        topHeaderList.setLayoutManager(linearLayoutManager);
        topHeaderList.setAdapter(newsHeaderAdapter);
    }

    private void setTopNews(List<Top> mNewsTop) {
        NewsItemAdapter newsItemAdapter = new NewsItemAdapter(mNewsTop,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        topList.setLayoutManager(linearLayoutManager);
        newsItemAdapter.addHeaderView(mHeanderView);
//        topList.addItemDecoration(new SpaceItemDecoration(5,newsItemAdapter.getItemCount(),getActivity()));
        topList.setAdapter(newsItemAdapter);
    }
}
