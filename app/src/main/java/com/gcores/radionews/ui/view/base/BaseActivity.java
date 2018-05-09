package com.gcores.radionews.ui.view.base;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.wedget.GAppBar;

public class BaseActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }*/
//        setTranslucentStatus();
        mContext = this;
    }

    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


    protected GAppBar initThemeToolBar() {
//        initStatusBar();
        GAppBar toolbar = findViewById(R.id.toolbar);
        TextView titleMain = toolbar.findViewById(R.id.txt_main_title);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);
        titleMain.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return toolbar;
    }

    /*private void initStatusBar() {
        mStatusBar = findViewById(R.id.status_bar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = getStatusHeight(this);
            mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());
        } else {
            mStatusBar.setVisibility(View.GONE);
        }
    }*/


    /**
     * activity跳转 无过度 无参数
     *
     * @param className
     */
    protected void startActivityDertict(Class<?> className) {
        Intent intent = new Intent(mContext, className);
        startActivity(intent);
    }


    /**
     * activity跳转 过度 无参数
     *
     * @param className
     */
    protected void startActivityTrans(Class<?> className) {
        Intent intent = new Intent(mContext, className);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle());
    }
}
