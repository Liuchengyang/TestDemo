package com.lanou3g.newdemo.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.activity.NewsLBActivity;
import com.lanou3g.newdemo.news.NewsFragment;
import com.lanou3g.newdemo.news.bean.NewsLBBean;
import com.squareup.picasso.Picasso;

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
public class NewsLBAdapter extends PagerAdapter {
    private NewsLBBean newsLBBean;
    private Context context;
    private ViewPager pager;

    public void setPager(ViewPager pager) {
        this.pager = pager;
    }

    public NewsLBAdapter(Context context) {
        this.context = context;
    }

    public void setNewsLBBean(NewsLBBean newsLBBean) {
        this.newsLBBean = newsLBBean;
    }

    @Override
    public int getCount() {
        return newsLBBean ==null?0:Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view =LayoutInflater.from(context).inflate(R.layout.news_fragment_lunbo_item,container,false);

        ImageView imageView = (ImageView) view.findViewById(R.id.news_item_image);


        Picasso.with(context).load(newsLBBean.getData().getPics().get(position %newsLBBean.getData().getPics().size()).getImgUrl()).into(imageView);
        container.addView(view);




        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
