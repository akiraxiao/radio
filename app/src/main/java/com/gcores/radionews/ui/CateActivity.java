package com.gcores.radionews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.api.NewsService;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.inter.BannerListner;
import com.gcores.radionews.ui.model.CateMenu;
import com.gcores.radionews.ui.model.news.Results;
import com.gcores.radionews.ui.resmoel.CateRes;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.gcores.radionews.ui.view.base.adapter.CateMenuAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CateActivity  extends BaseActivity implements OnRefreshListener {

    private RecyclerView topList;
    //private RecyclerView topHeaderList;*/

    private NewsService newsService;

    public BannerListner mListener;
//    private List<Top> mNewsTopItemList = new ArrayList<>();
//    private List<Top> mNewsHeaderList = new ArrayList<>();

    private LinearLayout llBackTop;






    //    private HomeItem item;
    //    private NewsHeaderAdapter mNewsHeaderAdapter;
//    private NewsItemAdapter mNewsItemAdapter;
    private RefreshLayout mRefreshLayout;
    private boolean fristLoad = true;

//    private int currentPage = 1;//当前页数

    private CateMenuAdapter cateAdapter;
    //    private HomeSimpleAdapter mHomeSimpleAdapter;
    List<CateMenu> mCateItems = new ArrayList<>();

    private boolean loadCompelete;//加载是否完毕


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate);

        topList = findViewById(R.id.toplist);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManagerHeander = new LinearLayoutManager(this);
        linearLayoutManagerHeander.setOrientation(LinearLayoutManager.VERTICAL);
        topList.setLayoutManager(linearLayoutManagerHeander);
        cateAdapter = new CateMenuAdapter(mCateItems, this);

        topList.setAdapter(cateAdapter);
        cateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Results results = (Results) adapter.getItem(position);
                int topId =  results.getId();
                String url = "https://www.g-cores.com/api/originals/"+topId+"/html_content?auth_exclusive="+Constant.AUTH_EXCLUSIVE+"&quickdownload=1&auth_token="+Constant.AUTH_TOKEN;
                Intent intent = new Intent(CateActivity.this, DetailActvity.class);
                intent.putExtra("url",url);
                intent.putExtra("commentnum",results.getComments_num());
                startActivity(intent);
            }
        });
//        mRefreshLayout.setEnableLoadMore(false);
//        topHeaderList =   mHeanderView.findViewById(R.id.top_header);
//        ((TextView)mHeanderView.findViewById(R.id.tv_top_header)).setText("新闻联播");
        /*if (fristLoad) {
            mRefreshLayout.autoRefresh();
        }*/
        llBackTop = findViewById(R.id.ll_backtop);
        llBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRefreshLayout.autoRefresh();
    }




    //请求头条数据

    private void fectchData(RefreshLayout refreshLayout) {
        loadCompelete = !loadCompelete;
        cateAdapter.setEnableLoadMore(false);
        Retrofit retrofit = RetrofitClient.getRetrofit(UrlPath.base_url_api);
        newsService = retrofit.create(NewsService.class);
        Call<CateRes> call = newsService.getCate(Constant.AUTH_EXCLUSIVE, Constant.AUTH_TOKEN);
        call.enqueue(new Callback<CateRes>() {
            @Override
            public void onResponse(Call<CateRes> call, Response<CateRes> response) {
//               refreshLayout.finishRefresh();
//                mHomeItems.clear();
                loadCompelete = true;
                if (response.body().getStatus() == UrlPath.NET_SUCESS) {
                            mCateItems = response.body().getResults();
//                        mHomeItemAdapter.setEnableLoadMore(true);
                        setCateData(mCateItems);
                        refreshLayout.finishRefresh();
//                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<CateRes> call, Throwable t) {
                Log.e("eee", t.getMessage());
                loadCompelete = true;

                    refreshLayout.finishRefresh();
            }

        });
    }

    private void setCateData(List<CateMenu> listItem) {
            cateAdapter.setNewData(listItem);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refresh(refreshLayout);
    }

    private void refresh(RefreshLayout refreshLayout) {
        mCateItems.clear();
       /* if (!fristLoad){
            mRadioItems.clear();
        }*/
//        mHomeItemAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        fectchData(refreshLayout);
    }
}

