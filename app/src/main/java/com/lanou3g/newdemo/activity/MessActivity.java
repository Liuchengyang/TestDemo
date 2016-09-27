package com.lanou3g.newdemo.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.activity.adapter.MessFragmentAdapter;
import com.lanou3g.newdemo.base.BaseAty;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　　　 ████━████ ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * Created by 刘城羊 on 16/7/10.
 */
public class MessActivity extends BaseAty {
    private ImageView iv_finish;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MessFragmentAdapter messFragmentAdapteradapter;
    @Override
    protected int setLayout() {
        return R.layout.activity_mess;
    }

    @Override
    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.mess_view_pager);
        tabLayout = (TabLayout) findViewById(R.id.mess_tab_layout);
        iv_finish = (ImageView) findViewById(R.id.iv_finish);

    }

    @Override
    protected void initData() {
        messFragmentAdapteradapter = new MessFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(messFragmentAdapteradapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLUE,Color.WHITE);
        iv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
