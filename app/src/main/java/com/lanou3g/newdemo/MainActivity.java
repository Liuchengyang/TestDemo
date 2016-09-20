package com.lanou3g.newdemo;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.lanou3g.newdemo.base.BaseAty;
import com.lanou3g.newdemo.base.TabInfo;

public class MainActivity extends BaseAty {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainAdapter mainAdapter;


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewPager =bindView(R.id.activity_view_pager);
        tabLayout =bindView(R.id.activity_tab_layout);





    }

    @Override
    protected void initData() {
        mainAdapter =new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabTextColors(Color.BLUE,Color.GREEN);
        int tabCount =tabLayout.getTabCount();

        for (int i = 0; i < tabCount; i++) {
            TabLayout.Tab tab =tabLayout.getTabAt(i);
            tab.setIcon(TabInfo.getTabInfos().get(i).getImageId());
        }




    }
}
