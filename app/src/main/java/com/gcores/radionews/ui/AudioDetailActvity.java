package com.gcores.radionews.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gcores.radionews.R;
import com.gcores.radionews.ui.api.AudioApi;
import com.gcores.radionews.ui.api.RetrofitClient;
import com.gcores.radionews.ui.api.UrlPath;
import com.gcores.radionews.ui.inter.TimeLineListener;
import com.gcores.radionews.ui.model.TimeLine;
import com.gcores.radionews.ui.resmoel.TimeLineRes;
import com.gcores.radionews.ui.utils.Gutil;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.gcores.radionews.ui.view.base.adapter.TimeLineAdapter;
import com.gcores.radionews.ui.wedget.BackImageView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AudioDetailActvity extends BaseActivity implements OnRefreshListener, Player.EventListener, TimeLineListener {

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


    private String imageUrl;
    private String radiotitle;
    private String audioUrl;

    private ImageView ivImage;
    private TextView tvAudioTitle;

    private DefaultRenderersFactory mDefaultRenderersFactory;
    private DefaultBandwidthMeter mDefaultBandwidthMeter;
    private  TrackSelection.Factory trackSelectionFactory;
    private TrackSelector mTrackSelector;

    private SimpleExoPlayer player;

    private Handler mainHandler;
    private DataSource.Factory dataSourceFactory;
    private ExtractorsFactory extractorsFactory;
    private ExtractorMediaSource mediaSource;
    private ExtractorMediaSource.Factory mediaSourceFactory;

    private ImageView ivPlay;

    private boolean playReady = true;

    private String commentnum;
    private TextView tvCommentNum;

    private Long currentTime= 0l;
    private TextView tvPlayState;
    private TextView tvPlayTime;
    private boolean durationSet  = false;
    private SeekBar mSeekBar;

    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvPlayTime.setText(Gutil.convertToSencondAudio((int) (player.getContentPosition()/1000))+"/"+Gutil.convertToSencondAudio((int) (player.getDuration()/1000)));
                    mSeekBar.setProgress((int) player.getContentPosition());
                }
            });
        }
    };
    private Timer mTimer ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audiodetail);
        mContext = AudioDetailActvity.this;
        mTimer = new Timer();
        mTimer.scheduleAtFixedRate(mTimerTask,1000,1000);
        volumeId = getIntent().getIntExtra("volumeid",0);
        radiotitle =    getIntent().getStringExtra("radiotitle");
        imageUrl = getIntent().getStringExtra("imageurl");
        audioUrl = getIntent().getStringExtra("audiourl");
        commentnum = getIntent().getStringExtra("commentnum");
        topList = findViewById(R.id.toplist);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setEnableLoadMore(true);
        LinearLayoutManager linearLayoutManagerHeander = new LinearLayoutManager(this);
        linearLayoutManagerHeander.setOrientation(LinearLayoutManager.VERTICAL);
        topList.setLayoutManager(linearLayoutManagerHeander);
        timeLineAdapter = new TimeLineAdapter(mTimeLines, this);
        timeLineAdapter.setTimeLineListener(this);
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

        ivImage = findViewById(R.id.iv_audio_image);
        tvAudioTitle = findViewById(R.id.tv_audio_title);
        ivPlay = findViewById(R.id.iv_play);
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playReady = !playReady;
                player.setPlayWhenReady(playReady);

            }
        });

        tvCommentNum = findViewById(R.id.tv_comment_num);
        tvCommentNum.setText(commentnum);
        tvPlayState = findViewById(R.id.tv_play_state);
        tvPlayTime = findViewById(R.id.tv_timeplay);
        mSeekBar = findViewById(R.id.seek_bar_aduio);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });
        Glide.with(this).load(imageUrl).into(ivImage);
        tvAudioTitle.setText(radiotitle);
        if (Constant.PLAYER!=null){
            currentTime = Constant.PLAYER.getCurrentPosition();
            Constant.PLAYER.release();
        }
        /*if (!Constant.playState){*/
            //        mDefaultRenderersFactory = new DefaultRenderersFactory(getApplicationContext());
            mDefaultBandwidthMeter = new DefaultBandwidthMeter(); //Provides estimates of the currently available bandwidth.
            trackSelectionFactory = new AdaptiveTrackSelection.Factory(mDefaultBandwidthMeter);

            mTrackSelector = new DefaultTrackSelector(trackSelectionFactory);

            LoadControl loadControl = new DefaultLoadControl();

            // Measures bandwidth during playback. Can be null if not required.
            DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
            // Produces DataSource instances through which media data is loaded.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "yourApplicationName"), defaultBandwidthMeter);
            // Produces Extractor instances for parsing the media data.
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ExtractorMediaSource(Uri.parse(audioUrl), dataSourceFactory, extractorsFactory, null, null);
            // SimpleExoPlayer
            player = ExoPlayerFactory.newSimpleInstance(this, mTrackSelector,loadControl);

            player.prepare(videoSource);
       /* }else{
            player = Constant.PLAYER;
            if (player.getPlayWhenReady()){
                ivPlay.setImageResource(R.drawable.ic_pause);
            }else{
                ivPlay.setImageResource(R.drawable.ic_play);
            }
        }*/
        player.addListener(this);

        if (player.getPlayWhenReady()){
            ivPlay.setImageResource(R.drawable.ic_pause);
        }else{
            ivPlay.setImageResource(R.drawable.ic_play);
        }

        if (Constant.ADUIOURL.equals(audioUrl)){
            if (currentTime!=0)
                player.seekTo(currentTime);
        }else{
            player.seekTo(0);
        }
        player.setPlayWhenReady(playReady);



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
    protected void onStop() {
        super.onStop();
    }



    @Override
    protected void onPause() {
        super.onPause();
        Constant.playState = playReady;
        Constant.imageurl = imageUrl;
        Constant.PLAYER = player;
        Constant.ADUIOURL = audioUrl;
        Constant.VOLUMEID =  volumeId+"";
        Constant.RADIOTITLE =radiotitle;
        Constant.COMMENTNUM = commentnum;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refresh(refreshLayout);
    }

    private void refresh(RefreshLayout refreshLayout) {
        mTimeLines.clear();
        fectchData(refreshLayout);
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

        if (playbackState == Player.STATE_READY&& !durationSet) {
//            long realDurationMillis = player.getDuration();
            durationSet = true;
            mSeekBar.setMax((int) player.getDuration());
            tvPlayTime.setText("00:00/"+Gutil.convertToSencondAudio((int) (player.getDuration()/1000)));
        }
            if (playWhenReady){
                ivPlay.setImageResource(R.drawable.ic_pause);
                tvPlayState.setText("播放中");
            }else{
                ivPlay.setImageResource(R.drawable.ic_play);
                tvPlayState.setText("暂停播放");
            }
//        tvPlayTime.setText(Gutil.convertToSencondAudio((int) (player.getContentPosition()/1000))+"/"+Gutil.convertToSencondAudio((int) (player.getDuration()/1000)));
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
    }

    @Override
    public void onPositionDiscontinuity(int reason) {
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override
    public void onSeekProcessed() {
    }

    @Override
    public void onSourceClick(String link,String radiotitle) {
        //点击链接
        //其他文章
        Intent intent = new Intent(AudioDetailActvity.this, UrlActicity.class);
        intent.putExtra("url", link);
        intent.putExtra("title",radiotitle);
        startActivity(intent);
    }

    @Override
    public void onPlayerSeekListen(int duration) {
        player.setPlayWhenReady(false);
        player.seekTo(duration*1000);
        player.setPlayWhenReady(true);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

}
