package com.gcores.radionews.ui;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.gcores.radionews.R;

public class SplashActivity extends AppCompatActivity implements Animator.AnimatorListener {
    private ImageView logo;

    AnimatorSet animatorSet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo =  findViewById(R.id.logo);
        //两秒进入主页
        scaleLogo();
        /*Handler handler = new Handler();
        handler.postDelayed(()->{


                }
                ,2000);*/

    }

    private void scaleLogo() {
        animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.logoanimator);
        animatorSet.setDuration(2000);
        animatorSet.setTarget(logo);
        animatorSet.addListener(this);
        animatorSet.start();
        //  ObjectAnimator.ofFloat(logo,"scaleX",1f,1.5f);
//        ObjectAnimator.ofFloat(logo,"scaleY",1f,1.5f);
    }

    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationEnd(Animator animator) {
        Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this).toBundle());
        finishAfterTransition();
    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
