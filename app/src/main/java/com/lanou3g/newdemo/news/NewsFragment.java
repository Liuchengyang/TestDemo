package com.lanou3g.newdemo.news;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.activity.NewsCheckActivity;
import com.lanou3g.newdemo.activity.NewsLBActivity;
import com.lanou3g.newdemo.activity.NewsListActivity;
import com.lanou3g.newdemo.base.BaseFragment;
import com.lanou3g.newdemo.base.StringUrl;
import com.lanou3g.newdemo.news.adapter.NewsLBAdapter;
import com.lanou3g.newdemo.news.adapter.NewsListAdapter;
import com.lanou3g.newdemo.news.bean.NewsLBBean;
import com.lanou3g.newdemo.news.bean.NewsListBean;
import com.lanou3g.newdemo.srcollView.RefreshLayout;
import com.lanou3g.newdemo.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

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
public class NewsFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView news_more_img, iv_back;
    private DrawerLayout drawerLayout;
    private NewsLBAdapter newsListAdapter;


    public static List<String> strUrl = new ArrayList<>();
    private ListView listView;
    private NewsListAdapter newsAdapter;
    private ViewPager viewPager;
    private Handler mHandler;
    private boolean mFalg = true;
    private boolean flag = true;
    private View headView;
    private TextView textViewNews;
    private ImageView iv_all;
    private ImageView iv_early;
    private ImageView iv_bturn;
    private ImageView bigcompany;
    private ImageView iv_money;
    private ImageView iv_height;
    private ImageView iv_learn;
    private ImageView iv_tv;
    private TextView iv_video;
    private NewsLBBean newsLBBean;
    private LinearLayout drawer;

    private ImageView news_item_image, news_check_img;

    private RefreshLayout refreshLayout;
    private int i;

    @Override
    protected int setLayout() {
        return R.layout.fragment_news;
    }


    @Override
    protected void initView(View view) {
        drawer = (LinearLayout) view.findViewById(R.id.drawer);

        news_more_img = (ImageView) view.findViewById(R.id.news_more_img);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.news_drawer);
        //头布局
        listView = (ListView) view.findViewById(R.id.news_list_view);
        headView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_news_lunbo, null);
        viewPager = (ViewPager) headView.findViewById(R.id.news_lunbo_view_pager);

        listView.addHeaderView(headView);


        textViewNews = (TextView) view.findViewById(R.id.news_titles);
        iv_all = (ImageView) view.findViewById(R.id.iv_all);
        iv_early = (ImageView) view.findViewById(R.id.iv_early);
        iv_bturn = (ImageView) view.findViewById(R.id.iv_bturn);
        bigcompany = (ImageView) view.findViewById(R.id.bigcompany);
        iv_money = (ImageView) view.findViewById(R.id.iv_money);
        iv_height = (ImageView) view.findViewById(R.id.iv_height);
        iv_learn = (ImageView) view.findViewById(R.id.iv_learn);
        iv_tv = (ImageView) view.findViewById(R.id.iv_tv);
        iv_video = (TextView) view.findViewById(R.id.iv_video);
        news_check_img = (ImageView) view.findViewById(R.id.news_check_img);

        refreshLayout = (RefreshLayout) view.findViewById(R.id.swipe_layout);


        drawer.setOnClickListener(this);
        news_check_img.setOnClickListener(this);
        iv_all.setOnClickListener(this);
        iv_early.setOnClickListener(this);
        iv_bturn.setOnClickListener(this);
        bigcompany.setOnClickListener(this);
        iv_money.setOnClickListener(this);
        iv_height.setOnClickListener(this);
        iv_learn.setOnClickListener(this);
        iv_tv.setOnClickListener(this);
        iv_video.setOnClickListener(this);
        listView.setOnItemClickListener(this);


    }

    @Override
    protected void initData() {


        newsListAdapter = new NewsLBAdapter(getContext());
        newsAdapter = new NewsListAdapter(getContext());
        initLunBo();
        initSpeed();
        initList();
        initRefreshMethod();
        news_check_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsFragment.this.getActivity(), NewsCheckActivity.class);
                startActivity(intent);
            }
        });
        news_more_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();

            }
        });





    }

    private void initRefreshMethod() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initList();
                Toast.makeText(mContext, "刷新数据", Toast.LENGTH_SHORT).show();
                refreshLayout.setRefreshing(false);
            }
        });



        refreshLayout.setmOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                Toast.makeText(mContext, "加载", Toast.LENGTH_SHORT).show();
                refreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        i++;
                        VolleySingleton.addRequest(StringUrl.STRINGS[i], NewsListBean.class,
                                new Response.Listener<NewsListBean>() {
                                    @Override
                                    public void onResponse(NewsListBean response) {
                                        newsAdapter.setNewsListBean(response);
                                        newsAdapter.notifyDataSetChanged();
                                        refreshLayout.setRefreshing(false);


                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });






                    }
                },1500);
            }
        });
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

    public void request(String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
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
        queue.add(stringRequest);


    }


    @Override
    public void onClick(View view) {
        drawerLayout.closeDrawers();

        listView.removeHeaderView(headView);

        switch (view.getId()) {
            case R.id.iv_all:
                textViewNews.setText("新闻");
                listView.addHeaderView(headView);
                request(StringUrl.stringFragmentNEwsAll);


                break;
            case R.id.iv_early:


                textViewNews.setText("早期项目");
                request(StringUrl.stringFragmentNEwsEarly);
                break;
            case R.id.iv_bturn:


                textViewNews.setText("B轮后");
                request(StringUrl.stringFragmentNewsBTurn);
                break;
            case R.id.bigcompany:


                textViewNews.setText("大公司");
                request(StringUrl.stringFragmentNewsBigCompany);
                break;
            case R.id.iv_money:

                textViewNews.setText("资本");

                request(StringUrl.stringFragmentNewsMoney);
                break;
            case R.id.iv_height:


                textViewNews.setText("深度");
                request(StringUrl.stringFragmentNewsHeight);
                break;
            case R.id.iv_learn:


                textViewNews.setText("研究");
                request(StringUrl.stringFragmentNewsLearn);
                break;

            case R.id.iv_video:
                Intent intentVideo =new Intent(getActivity(),VideoActivity.class);
                startActivity(intentVideo);
                break;
            case R.id.drawer:
                //不会被击穿
                break;
        }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(NewsFragment.this.getActivity(), NewsListActivity.class);
        NewsListBean.DataBean.DataBean1 bean = (NewsListBean.DataBean.DataBean1) adapterView.getItemAtPosition(i);
        String pictureUrl = bean.getFeatureImg();
        intent.putExtra("pictureUrl", pictureUrl);
        String name = bean.getUser().getName();
        intent.putExtra("name", name);
        String title = bean.getTitle();
        intent.putExtra("title", title);
        String feedId = bean.getFeedId();
        String detailsUrl = "https://rong.36kr.com/api/mobi/news/" + feedId;
        intent.putExtra("feedId", feedId);
        intent.putExtra("detailsUrl", detailsUrl);
        long publishTime = bean.getPublishTime();
        intent.putExtra("publishTime", publishTime);
        long Id = bean.getUser().getSsoId();
        intent.putExtra("Id", Id);
        String columnName = bean.getColumnName();
        intent.putExtra("columnName", columnName);
        String downUrl ="https://rong.36kr.com/api/mobi/news/"+feedId+"/author-region";
        intent.putExtra("downUrl",downUrl);

        startActivity(intent);



    }
}
