package com.gcores.radionews.ui.view.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

public class BaseActivity extends AppCompatActivity {

    private Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        mContext = this;
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

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
