package com.lanou3g.newdemo.news;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseFragment;
import com.lanou3g.newdemo.base.StringUrl;
import com.lanou3g.newdemo.news.adapter.NewsLBAdapter;
import com.lanou3g.newdemo.news.adapter.NewsListAdapter;
import com.lanou3g.newdemo.news.bean.NewsLBBean;
import com.lanou3g.newdemo.news.bean.NewsListBean;

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
public class NewsFragment extends BaseFragment implements View.OnClickListener {
    private ImageView news_more_img, iv_back;
    private DrawerLayout drawerLayout;
    private NewsLBAdapter newsListAdapter;


    private ListView listView;
    private NewsListAdapter newsAdapter;
    private ViewPager viewPager;
    private Handler mHandler;
    private boolean mFalg = true;
    private boolean flag = true;
    private View headView;


    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView(View view) {
        news_more_img = (ImageView) view.findViewById(R.id.news_more_img);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.news_drawer);
        //头布局
        listView = (ListView) view.findViewById(R.id.news_list_view);
        headView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_news_lunbo, null);
        viewPager = (ViewPager) headView.findViewById(R.id.news_lunbo_view_pager);
        listView.addHeaderView(headView);


    }

    @Override
    protected void initData() {
        newsListAdapter = new NewsLBAdapter(getContext());
        newsAdapter = new NewsListAdapter(getContext());
        initLunBo();
        initSpeed();
        initList();
        news_more_img.setOnClickListener(this);
        iv_back.setOnClickListener(this);

    }

    private void initList() {
        RequestQueue queue1 = Volley.newRequestQueue(getContext());
        StringRequest stringRequest1 = new StringRequest(StringUrl.newsFragmentList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewsListBean bean = gson.fromJson(response, NewsListBean.class);
                newsAdapter.setNewsListBean(bean);
                listView.setAdapter(newsAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue1.add(stringRequest1);

    }

    private void initLunBo() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(StringUrl.newsFragmentLB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                NewsLBBean bean = gson.fromJson(response, NewsLBBean.class);
                Log.d("12", "bean:" + bean);
                newsListAdapter.setNewsLBBean(bean);
                viewPager.setAdapter(newsListAdapter);
                newsListAdapter.setPager(viewPager);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

    }

    private void initSpeed() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                return false;
            }
        });
        if (flag) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mFalg) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (mFalg) {
                            mHandler.sendEmptyMessage(0);
                        }
                    }
                }
            }).start();
            flag = false;
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.news_more_img:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.iv_back:
                drawerLayout.closeDrawers();


        }


    }
}
