package com.lanou3g.newdemo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.lanou3g.newdemo.MyImageListener;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.UrlDrawable;
import com.lanou3g.newdemo.base.BaseAty;
import com.lanou3g.newdemo.news.bean.NewsInterfaceBean;
import com.lanou3g.newdemo.MemoryCache;
import com.lanou3g.newdemo.volley.VolleySingleton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class NewsListActivity extends BaseAty implements View.OnClickListener {

    private String pictureUrl;
    private String name;
    private String title;
    private long id;
    private String columnName;
    private String detailsUrl;
    private long publishTime;

    private TextView tv_time;
    private TextView tv_author, tv_title, tv_context;
    private ImageView iv_pic;
    private Date date;
    private SimpleDateFormat dateFormat;
    private Html.ImageGetter imageGetter;

    private ImageView iv_back,iv_comment,iv_share,iv_more,iv_down;

    @Override
    protected int setLayout() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void initView() {
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_context = (TextView) findViewById(R.id.tv_context);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);


        iv_down = (ImageView) findViewById(R.id.iv_down);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_comment = (ImageView) findViewById(R.id.iv_comment);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_more = (ImageView) findViewById(R.id.iv_more);
        iv_back.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        iv_down.setOnClickListener(this);
        iv_share.setOnClickListener(this);



    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        pictureUrl = intent.getStringExtra("pictureUrl");

        name = intent.getStringExtra("name");

        title = intent.getStringExtra("title");
        id = intent.getLongExtra("Id", -1);

        columnName = intent.getStringExtra("columnName");

        detailsUrl = intent.getStringExtra("detailsUrl");


        publishTime = intent.getLongExtra("publishTime", 0);
        date = new Date(publishTime);
        dateFormat = new SimpleDateFormat("mm:ss");
        String time = dateFormat.format(date);
        RequestQueue requestQueue = VolleySingleton.getVolleySingleton().getRequestQueue();
        final ImageLoader imageLoader = new ImageLoader(requestQueue, new MemoryCache());
        imageLoader.get(pictureUrl,new MyImageListener(iv_pic));
        tv_author.setText(name);
        tv_title.setText(title);
        tv_time.setText(time);
        VolleySingleton.addRequest(detailsUrl, NewsInterfaceBean.class, new Response.Listener<NewsInterfaceBean>() {

            @Override
            public void onResponse(NewsInterfaceBean response) {
                String details = response.getData().getContent();
                tv_context.setMovementMethod(ScrollingMovementMethod.getInstance());// 设置可滚动
                tv_context.setMovementMethod(LinkMovementMethod.getInstance());//设置超链接可以打开网页
                Log.d("123", details);
                tv_context.setText(Html.fromHtml(details, imageGetter, null));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



        imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(final String s) {
                final UrlDrawable drawable = new UrlDrawable();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Bitmap bitmap = Picasso.with(NewsListActivity.this).load(s).get();
                            runOnUiThread(new MyRunnable(bitmap) {
                                @Override
                                public void run() {
                                    int width;
                                    DisplayMetrics dm = new DisplayMetrics();

                                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                                    width = dm.widthPixels;
                                    //计算缩放比例
                                    float scaleWidth = width / bitmap.getWidth();
//获取屏幕信息
                                    // 取得想要缩放的matrix参数
                                    Matrix matrix = new Matrix();
                                    matrix.postScale(scaleWidth, scaleWidth);
                                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                                    drawable.bitmap = bitmap;
                                    drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                                    tv_context.invalidate();
                                    tv_context.setText(tv_context.getText()); // 解决图文重叠

                                }
                            });


                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }).start();

                return drawable;
            }
        };


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_more:
                Intent intent =new Intent(NewsListActivity.this,MoreActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_down:

                Intent intentDown =new Intent(NewsListActivity.this,DownActivity.class);
                startActivity(intentDown);
                break;
            case R.id.iv_share:
                Intent intentShare =new Intent(NewsListActivity.this,ShareActivity.class);
                startActivity(intentShare);

        }


    }

    abstract class MyRunnable implements Runnable {
        protected Bitmap bitmap;

        public MyRunnable(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }
}
