package com.lanou3g.newdemo.investment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.activity.NewsCheckActivity;
import com.lanou3g.newdemo.base.BaseFragment;
import com.lanou3g.newdemo.investment.adapter.InvestAdapter;
import com.lanou3g.newdemo.news.NewsFragment;

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
public class InvestFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private InvestAdapter investAdapter;
    private ImageView invest_check_img;
    private ImageView invest_gift_img;

    @Override
    protected int setLayout() {
        return R.layout.fragment_invest;
    }

    @Override
    protected void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.invest_view_pager);
        tabLayout = (TabLayout) view.findViewById(R.id.invest_tab);
        invest_check_img = (ImageView) view.findViewById(R.id.invest_check_img);
        invest_gift_img = (ImageView) view.findViewById(R.id.invest_gift_img);



    }



    @Override
    protected void initData() {
        invest_check_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewsCheckActivity.class);
                startActivity(intent);

            }
        });

        investAdapter = new InvestAdapter(getChildFragmentManager());

        viewPager.setAdapter(investAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(Color.BLUE,Color.GREEN);

        invest_gift_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity( new Intent(getActivity(),GiftActivity.class));

                invest_gift_img.setVisibility(View.INVISIBLE);




            }
        });

    }
}
