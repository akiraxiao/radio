package com.gcores.radionews.ui.view.base;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.CateActivity;
import com.gcores.radionews.ui.wedget.toolbar.GAppBar;

import java.lang.reflect.Field;

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

    GAppBar toolbar;

    protected GAppBar initThemeToolBar() {
//        initStatusBar();

        toolbar = findViewById(R.id.toolbar);
        TextView titleMain = toolbar.findViewById(R.id.txt_main_title);
        ImageView ivCate = toolbar.findViewById(R.id.iv_cate_home);
        ivCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseActivity.this, CateActivity.class);
                startActivity(intent);
            }
        });
        //导入字体
        Typeface   typeface= Typeface.createFromAsset(getAssets(),"fonts/streetlight.ttf"); titleMain.setTypeface(typeface);
        titleMain.setTypeface(typeface);

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
                ivCate.setColorFilter(currentColor,PorterDuff.Mode.SRC_ATOP);
            }
        });
        toolbar.setTitle("GAMEGORES");
        setSupportActionBar(toolbar);
        titleMain.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return toolbar;
    }

    public Toolbar getToolbar(){
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

    /**
     * 设置下划线宽度
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    protected void setIndicator (TabLayout tabs, int leftDip, int rightDip){
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.setMarginStart(left);
            params.setMarginEnd(right);
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    /*protected void setIndicator (TabLayout tabs){

        Class<?> tablayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tablayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout ll_tab= (LinearLayout) tabStrip.get(tl_main);
        for (int i = 0; i < ll_tab.getChildCount(); i++) {
            View child = ll_tab.getChildAt(i);
            child.setPadding(0,0,0,0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
            params.setMarginStart(DensityUtil.dip2px(20f));
            params.setMarginEnd(DensityUtil.dip2px(20f));
            child.setLayoutParams(params);
            child.invalidate();
        }


    }*/
}
