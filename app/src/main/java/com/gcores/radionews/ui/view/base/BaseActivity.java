package com.gcores.radionews.ui.view.base;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.wedget.toolbar.GAppBar;

public class BaseActivity extends AppCompatActivity {

    private Context mContext;
    private ArgbEvaluator mArgbEvaluator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }*/
//        setTranslucentStatus();
        mContext = this;
        mArgbEvaluator = new ArgbEvaluator();
    }

    private void setTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }
    /** 根据百分比改变颜色透明度 */
    public int changeAlpha(int color, float fraction) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        int alpha = (int) (Color.alpha(color) * fraction);
        return Color.argb(alpha, red, green, blue);
    }


    /**
     * 动态改变颜色
     * @param rate 频率
     * @param startColor 开始颜色
     * @param endColor 结束颜色
     * @return
     */
    public int changeColor(float rate,int startColor,int endColor){
        int color = (int)mArgbEvaluator.evaluate(rate,startColor,endColor);
        return color;
    }



    protected GAppBar initThemeToolBar() {
//        initStatusBar();

        GAppBar toolbar = findViewById(R.id.toolbar);
        TextView titleMain = toolbar.findViewById(R.id.txt_main_title);
        AppBarLayout appBarLayout = findViewById(R.id.root_appbar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                Log.e("eee",Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange()+"ffff");
                //当appbarLayout滑动的改变
                float rate = Math.abs(verticalOffset*1.0f)/appBarLayout.getTotalScrollRange();
                toolbar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.white),rate));

                //当前的颜色为
                int currentColor = changeColor(rate,Color.WHITE,Color.parseColor("#999999"));
                //菜单
                toolbar.getNavigationIcon().setColorFilter(currentColor,PorterDuff.Mode.SRC_ATOP);
                //字体
                titleMain.setTextColor(currentColor);
            }
        });
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

    /**
     * activity跳转 过度 带参数
     *
     * @param className
     */
    protected void startActivityTrans(Class<?> className,Bundle passBundle) {
        Intent intent = new Intent(mContext, className);
//        intent.putExtra()
        intent.putExtras(passBundle);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation((Activity) mContext).toBundle());
    }
}
