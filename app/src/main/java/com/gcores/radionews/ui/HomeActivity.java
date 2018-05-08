package com.gcores.radionews.ui;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.gcores.radionews.R;
import com.gcores.radionews.ui.bean.MenuBean;
import com.gcores.radionews.ui.view.base.BaseActivity;
import com.gcores.radionews.ui.view.base.adapter.LeftMenuAdapter;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {


    private CoordinatorLayout coordinatorLayout;
    private long exitTime = 0;
    private final long COUNT = 2000;//两秒退出
    private SlidingRootNav slidingRootNav;
    private List<MenuBean> menuBeanList = new ArrayList<>();
    private RecyclerView menuList;
    private LeftMenuAdapter leftMenuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar =  initThemeToolBar();

//        Toolbar toolbar =  findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        coordinatorLayout = findViewById(R.id.container_bottom);

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        menuList = findViewById(R.id.menu_list);
        initMenu(menuList);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initMenu(RecyclerView menuList) {
        menuBeanList = getMenuList();
        menuList.setLayoutManager(new LinearLayoutManager(this));
        leftMenuAdapter = new LeftMenuAdapter(menuBeanList);
        menuList.setAdapter(leftMenuAdapter);
    }

    private List<MenuBean> getMenuList() {
        menuBeanList.clear();
        String[] menuNames =  getResources().getStringArray(R.array.main_menu_name);
        for (int x = 0;x<menuNames.length;x++){
            MenuBean menuBean = new MenuBean();
            menuBean.setText(menuNames[x]);
            menuBeanList.add(menuBean);
        }
        return menuBeanList;
    }

    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApplication(HomeActivity.this);
        }
        return false;
    }

    /**
     * 退出
     *
     * @param homeActivity
     */
    protected void exitApplication(HomeActivity homeActivity) {

        long currentTime = System.currentTimeMillis();
        if (currentTime - exitTime >= COUNT) {
            exitTime = currentTime;
            Snackbar.make(coordinatorLayout, "再按一次退出", Snackbar.LENGTH_SHORT).show();
        } else {
            HomeActivity.this.finishAffinity();
        }


    }

}
