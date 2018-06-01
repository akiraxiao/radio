package com.gcores.radionews.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.api.NewsApi;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.inter.BannerListner;
import com.gcores.radionews.ui.model.CateMenu;
import com.gcores.radionews.ui.model.news.Results;
import com.gcores.radionews.ui.resmoel.ArticleRes;
import com.gcores.radionews.ui.resmoel.CateMenuHeadRes;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.gcores.radionews.ui.view.base.adapter.ArticleAdapter;
import com.gcores.radionews.ui.wedget.BackImageView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class CateDetailActvity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    private RecyclerView topList;
    //private RecyclerView topHeaderList;*/
    private CateMenu cateMenu;
    private NewsApi newsApi;

    public BannerListner mListener;

    private LinearLayout llBackTop;


    private RefreshLayout mRefreshLayout;
    private boolean fristLoad = true;

//    private int currentPage = 1;//当前页数

    private ArticleAdapter cateAdapter;
    //    private HomeSimpleAdapter mHomeSimpleAdapter;
    List<Results> mCateItems = new ArrayList<>();

    private boolean loadCompelete;//加载是否完毕
    private int currentPage = 1;


    private BackImageView mBack;
    private ImageView centerView;
    private TextView tvArtCount;
    private TextView tvScriCount;

    private Context mContext;
    private View detailHeader;

    private final int TOTAL_COUNTER = 10;

    private int current_counter;

    private int cateid;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catedetail);
        mContext = CateDetailActvity.this;
        cateMenu = (CateMenu) getIntent().getSerializableExtra("cateMenu");
        cateid = getIntent().getIntExtra("cateid",0);

        topList = findViewById(R.id.toplist);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(true);
        LinearLayoutManager linearLayoutManagerHeander = new LinearLayoutManager(this);
        linearLayoutManagerHeander.setOrientation(LinearLayoutManager.VERTICAL);
        topList.setLayoutManager(linearLayoutManagerHeander);
        cateAdapter = new ArticleAdapter(mCateItems, this);

        topList.setAdapter(cateAdapter);
        cateAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Results results = (Results) adapter.getItem(position);
                int topId = results.getId();
                String url = "https://www.g-cores.com/api/originals/" + topId + "/html_content?auth_exclusive=" + Constant.AUTH_EXCLUSIVE + "&quickdownload=1&auth_token=" + Constant.AUTH_TOKEN;
                Intent intent = new Intent(CateDetailActvity.this, DetailActvity.class);
                intent.putExtra("url", url);
                intent.putExtra("commentnum", results.getComments_num());
                intent.putExtra("userid", results.getUser().getId());
                startActivity(intent);
            }
        });

        llBackTop = findViewById(R.id.ll_backtop);
        llBackTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        detailHeader = LayoutInflater.from(mContext).inflate(R.layout.view_catedetailheader,null);
        cateAdapter.addHeaderView(detailHeader);
        mBack = detailHeader.findViewById(R.id.iv_news_video);
        centerView = detailHeader.findViewById(R.id.iv_item_catename);
        tvArtCount = detailHeader.findViewById(R.id.item_ariticle_count);
        tvScriCount = detailHeader.findViewById(R.id.item_subcribe_count);
        mRefreshLayout.autoRefresh();
    }


    //请求头条数据
    int mCateId;
    private void fectchData(int page,RefreshLayout refreshLayout) {
        loadCompelete = !loadCompelete;
        cateAdapter.setEnableLoadMore(false);
        Retrofit retrofit = RetrofitClient.getRetrofit(UrlPath.base_url_api);
        newsApi = retrofit.create(NewsApi.class);
        if (cateMenu!=null){
            mCateId = cateMenu.getId();
        }else{
            mCateId = cateid;
        }


        Observable observable =  newsApi.getCateDetailHead(mCateId, Constant.AUTH_EXCLUSIVE, Constant.AUTH_TOKEN);

        observable.subscribeOn(Schedulers.io())               //在IO线程进行网络请求
        .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求注册结果
                .doOnNext(new Consumer<CateMenuHeadRes>() {
                    @Override
                    public void accept(CateMenuHeadRes cateMenuHeadRes) throws Exception {
                        //先根据注册的响应结果去做一些操作
                        tvScriCount.setText("订阅 "+cateMenuHeadRes.getResults().getSubscriptors_num());
                        tvArtCount.setText("文章 "+cateMenuHeadRes.getResults().getOriginals_num());
                        Glide.with(mContext).load(cateMenuHeadRes.getResults().getBackground_url()).into(mBack);
                        Glide.with(mContext).load(cateMenuHeadRes.getResults().getLogo_url()).into(centerView);
                    }
                }).observeOn(Schedulers.io())                 //回到IO线程去发起登录请求
                .flatMap(new Function<CateMenuHeadRes, ObservableSource<ArticleRes>>() {
                    @Override
                    public ObservableSource<ArticleRes> apply(CateMenuHeadRes cateMenuHeadRes) throws Exception {
                        return newsApi.getCateDetailList(mCateId, page, Constant.AUTH_EXCLUSIVE, Constant.AUTH_TOKEN);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求登录的结果
                .subscribe(new Consumer<ArticleRes>() {
                    @Override
                    public void accept(ArticleRes articleRes) throws Exception {

                        if (articleRes.getStatus()==UrlPath.NET_SUCESS){
                            mCateItems = articleRes.getResults();
                            current_counter = mCateItems.size();
                            if (currentPage > 1) {
                                refreshLayout.finishLoadMore();
//                        mHomeItemAdapter.setNewData(mHomeItems);
//                        mHomeItemAdapter.setEnableLoadMore(false);
                                setCateNews(false, mCateItems);
//                        mHomeItemAdapter.setEnableLoadMore(true);
                            } else {
//                        mHomeItemAdapter.setEnableLoadMore(true);
                                setCateNews(true,mCateItems);
                                refreshLayout.finishRefresh();
                            }

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private void setCateNews(boolean isRefresh,List<Results> listItem) {
        if (isRefresh) {
            cateAdapter.setNewData(listItem);
            cateAdapter.setEnableLoadMore(true);
//            mHomeSimpleAdapter.setData(listItem);
        } else {
            cateAdapter.addData(listItem);
            cateAdapter.setEnableLoadMore(true);
//                mHomeSimpleAdapter.addData(itemHome);
        }

        if (current_counter < TOTAL_COUNTER) {
            //第一页如果不够一页就不显示没有更多数据布局
            cateAdapter.loadMoreEnd(isRefresh);
//            Toast.makeText(getActivity(), "no more data", Toast.LENGTH_SHORT).show();
            Snackbar.make(findViewById(R.id.container), getString(R.string.nomore_data), Snackbar.LENGTH_SHORT).show();
        } else {
//            int count =  mHomeItemAdapter.getData().size();
            cateAdapter.loadMoreComplete();
        }
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
        currentPage = 1;
        fectchData(currentPage,refreshLayout);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        currentPage++;
//        mHomeItemAdapter.setEnableLoadMore(true);
        fectchData(currentPage, refreshLayout);
    }
}

