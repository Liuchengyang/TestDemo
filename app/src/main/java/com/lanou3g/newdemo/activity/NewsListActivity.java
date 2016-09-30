package com.lanou3g.newdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.MenuPopupWindow;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.lanou3g.newdemo.MyImageListener;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.UrlDrawable;
import com.lanou3g.newdemo.base.BaseAty;
import com.lanou3g.newdemo.news.adapter.DownAdapter;
import com.lanou3g.newdemo.news.bean.DownBean;
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
    private RelativeLayout news_list_relative_layout;

    private TextView tv_time;
    private TextView tv_author, tv_title, tv_context, iv_brief;
    private ImageView iv_pic;
    private Date date;
    private SimpleDateFormat dateFormat;
    private Html.ImageGetter imageGetter;

    private ImageView iv_back, iv_comment, iv_share, iv_more;
    private ImageView iv_down;

    private PopupWindow mPopupWindow;
    private String downUrl;

    @Override
    protected int setLayout() {
        return R.layout.activity_news_list;
    }

    @Override
    protected void initView() {
        news_list_relative_layout = (RelativeLayout) findViewById(R.id.news_list_relative_layout);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_author = (TextView) findViewById(R.id.tv_author);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_context = (TextView) findViewById(R.id.tv_context);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        iv_brief = (TextView) findViewById(R.id.iv_brief);


        iv_down = (ImageView) findViewById(R.id.iv_down);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_comment = (ImageView) findViewById(R.id.iv_comment);
        iv_share = (ImageView) findViewById(R.id.iv_share);
        iv_more = (ImageView) findViewById(R.id.iv_more);
        iv_back.setOnClickListener(this);
        iv_more.setOnClickListener(this);
        iv_down.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        news_list_relative_layout.setOnClickListener(this);


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


        downUrl = intent.getStringExtra("downUrl");


        publishTime = intent.getLongExtra("publishTime", 0);
        date = new Date(publishTime);
        dateFormat = new SimpleDateFormat("mm:ss");
        String time = dateFormat.format(date);
        RequestQueue requestQueue = VolleySingleton.getVolleySingleton().getRequestQueue();
        final ImageLoader imageLoader = new ImageLoader(requestQueue, new MemoryCache());
        imageLoader.get(pictureUrl, new MyImageListener(iv_pic));
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
                Intent intent = new Intent(NewsListActivity.this, MoreActivity.class);
                startActivity(intent);
                break;

            case R.id.news_list_relative_layout:
                iv_down.setBackgroundResource(R.mipmap.iv_down1);
                showPopupWindow(iv_down);
                break;
            case R.id.iv_share:
                Intent intentShare = new Intent(NewsListActivity.this, ShareActivity.class);
                startActivity(intentShare);

        }


    }


    private void showPopupWindow(View v) {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.activity_down, null);


        final TextView down_article = (TextView) contentView.findViewById(R.id.down_article);
        final TextView down_browse = (TextView) contentView.findViewById(R.id.down_browse);
        final ListView listViewDown = (ListView) contentView.findViewById(R.id.iv_down_list_view);




        final DownAdapter downAdapter = new DownAdapter(this);
        VolleySingleton.addRequest(downUrl, DownBean.class, new Response.Listener<DownBean>() {

            @Override
            public void onResponse(DownBean response) {
                String brief = response.getData().getBrief();
                iv_brief.setText(brief);
                int count =response.getData().getTotalCount();
                down_article.setText(count +"篇");
                int totalView =response.getData().getTotalView()  ;
                Log.d("111", "totalView:" + totalView);
                down_browse.setText(totalView/10000 + "万");
                downAdapter.setBean(response);
                listViewDown.setAdapter(downAdapter);
                listViewDown.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                });





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

//        WindowManager windowManager = this.getWindowManager();
//        Display display = windowManager.getDefaultDisplay();
//        mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果
//        popupWindow.setAnimationStyle(R.style.Animation_ZoomLight);

        //点击其他地方消失
        mPopupWindow.showAsDropDown(v);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
       mPopupWindow.setBackgroundDrawable(dw);
        backgroundAlpha(NewsListActivity.this,0.5f);//0.0-1.0
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                backgroundAlpha(NewsListActivity.this, 1f);
            }
        });


        contentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                closePopupWindow();
                iv_down.setBackgroundResource(R.mipmap.icon_down);
                return false;
            }
        });


    }

    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    private void closePopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.alpha = 1f;
            this.getWindow().setAttributes(params);
        }

    }


    abstract class MyRunnable implements Runnable {
        protected Bitmap bitmap;

        public MyRunnable(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }


}
