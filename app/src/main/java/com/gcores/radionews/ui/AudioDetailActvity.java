package com.gcores.radionews.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.api.AudioApi;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.model.TimeLine;
import com.gcores.radionews.ui.resmoel.TimeLineRes;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.gcores.radionews.ui.view.base.adapter.TimeLineAdapter;
import com.gcores.radionews.ui.wedget.BackImageView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AudioDetailActvity extends BaseActivity implements OnRefreshListener {

    private RecyclerView topList;
    //private RecyclerView topHeaderList;*/
    private AudioApi audioApi;


    private LinearLayout llBackTop;


    private RefreshLayout mRefreshLayout;

//    private int currentPage = 1;//当前页数

    private TimeLineAdapter timeLineAdapter;
    //    private HomeSimpleAdapter mHomeSimpleAdapter;
    List<TimeLine> mTimeLines = new ArrayList<>();

    private boolean loadCompelete;//加载是否完毕


    private BackImageView mBack;

    private Context mContext;
    private int volumeId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiodetail);
        mContext = AudioDetailActvity.this;
        volumeId = getIntent().getIntExtra("volumeid",0);
        topList = findViewById(R.id.toplist);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setEnableLoadMore(true);
        LinearLayoutManager linearLayoutManagerHeander = new LinearLayoutManager(this);
        linearLayoutManagerHeander.setOrientation(LinearLayoutManager.VERTICAL);
        topList.setLayoutManager(linearLayoutManagerHeander);
        timeLineAdapter = new TimeLineAdapter(mTimeLines, this);

        topList.setAdapter(timeLineAdapter);
        timeLineAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                /*Results results = (Results) adapter.getItem(position);
                int topId = results.getId();
                String url = "https://www.g-cores.com/api/originals/" + topId + "/html_content?auth_exclusive=" + Constant.AUTH_EXCLUSIVE + "&quickdownload=1&auth_token=" + Constant.AUTH_TOKEN;
                Intent intent = new Intent(AudioDetailActvity.this, DetailActvity.class);
                intent.putExtra("url", url);
                intent.putExtra("commentnum", results.getComments_num());
                intent.putExtra("userid", results.getUser().getId());
                startActivity(intent);*/
            }
        });

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
        Retrofit retrofit = RetrofitClient.getRetrofit(UrlPath.base_url_api);
        audioApi = retrofit.create(AudioApi.class);
        Call<TimeLineRes> call =  audioApi.getVolumeTimeLine(volumeId, Constant.AUTH_EXCLUSIVE, Constant.AUTH_TOKEN);
        call.enqueue(new Callback<TimeLineRes>() {
            @Override
            public void onResponse(Call<TimeLineRes> call, Response<TimeLineRes> response) {
                if (response.body().getStatus()==UrlPath.NET_SUCESS){
                    mTimeLines = response.body().getResults();
                    setTimeLines(mTimeLines);
                    refreshLayout.finishRefresh();

                }
            }

            @Override
            public void onFailure(Call<TimeLineRes> call, Throwable t) {
                refreshLayout.finishRefresh();
            }
        });



    }

    private void setTimeLines(List<TimeLine> listItem) {
            timeLineAdapter.setNewData(listItem);
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
        mTimeLines.clear();
        fectchData(refreshLayout);
    }


}
