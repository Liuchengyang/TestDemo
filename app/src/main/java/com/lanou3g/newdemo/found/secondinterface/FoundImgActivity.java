package com.lanou3g.newdemo.found.secondinterface;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseAty;
import com.lanou3g.newdemo.base.StringUrl;
import com.lanou3g.newdemo.found.adapter.FoundActivityAdapter;
import com.lanou3g.newdemo.found.bean.FoundImgBean;
import com.lanou3g.newdemo.volley.VolleySingleton;

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
public class FoundImgActivity extends BaseAty implements View.OnClickListener {
    private ImageView activity_found__back;
    private TextView activity_found_type, activity_found_time;
    private ListView listView;
    private FoundActivityAdapter foundActivityAdapter;
    private FoundImgBean response;
    private PopupWindow popupWindow;
    private PopupWindow popupWindowTime;

    @Override
    protected int setLayout() {
        return R.layout.activity_found_img;
    }

    @Override
    protected void initView() {

        activity_found__back = (ImageView) findViewById(R.id.activity_found__back);
        activity_found_type = (TextView) findViewById(R.id.activity_found_type);
        activity_found_time = (TextView) findViewById(R.id.activity_found_time);
        listView = (ListView) findViewById(R.id.activity_found_list_view);

    }

    @Override
    protected void initData() {
        activity_found__back.setOnClickListener(this);
        activity_found_type.setOnClickListener(this);
        activity_found_time.setOnClickListener(this);
        foundActivityAdapter = new FoundActivityAdapter(this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(FoundImgActivity.this, LinkActivity.class);

                String stringUrl = response.getData().getData().get(i).getActivityLink();
                intent.putExtra("stringUrl", stringUrl);
                startActivity(intent);

            }
        });
        VolleySingleton.addRequest(StringUrl.stringFoundFutureActivity, FoundImgBean.class, new Response.Listener<FoundImgBean>() {
            @Override
            public void onResponse(FoundImgBean response) {
                foundActivityAdapter.setBean(response);
                FoundImgActivity.this.response = response;
                listView.setAdapter(foundActivityAdapter);


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_found__back:
                finish();
                break;
            case R.id.activity_found_type:


                activity_found_type.setTextColor(Color.BLUE);
                popupWindow = new PopupWindow(this);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                View viewPop = LayoutInflater.from(this).inflate(R.layout.pop_type_item, null);

                viewPop.findViewById(R.id.tv_all).setOnClickListener(this);
                viewPop.findViewById(R.id.tv_demo_day).setOnClickListener(this);
                viewPop.findViewById(R.id.tv_room).setOnClickListener(this);
                viewPop.findViewById(R.id.tv_money).setOnClickListener(this);
                viewPop.findViewById(R.id.tv_service).setOnClickListener(this);
                viewPop.findViewById(R.id.tv_quck).setOnClickListener(this);

                viewPop.setFocusable(true);
                viewPop.setFocusableInTouchMode(true);
                popupWindow.setContentView(viewPop);
                popupWindow.setFocusable(true);

                popupWindow.showAsDropDown(activity_found_type, 20, 0);

                ColorDrawable dw = new ColorDrawable(0x00000000);
                //设置SelectPicPopupWindow弹出窗体的背景
                popupWindow.setBackgroundDrawable(dw);
                backgroundAlpha(FoundImgActivity.this, 0.5f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        // TODO Auto-generated method stub
                        backgroundAlpha(FoundImgActivity.this, 1f);
                    }
                });
                viewPop.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        popupWindow.dismiss();
                        activity_found_type.setTextColor(Color.BLACK);
                        return false;
                    }
                });

//                viewPop.setOnKeyListener(new View.OnKeyListener() {
//                    @Override
//                    public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                        if (i ==KeyEvent.KEYCODE_BACK){
//                            popupWindow.dismiss();
//                        }
//
//                        return false;
//                    }
//                });
                break;


            case R.id.activity_found_time:

                activity_found_time.setTextColor(Color.BLUE);
                popupWindowTime = new PopupWindow(this);
                popupWindowTime.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindowTime.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                View viewTime  =LayoutInflater.from(this).inflate(R.layout.found_time_item,null);
                viewTime.setFocusable(true);
                viewTime.setFocusableInTouchMode(true);
                popupWindowTime.setContentView(viewTime);
                popupWindowTime.setFocusable(true);
                popupWindowTime.showAsDropDown(activity_found_time, 20, 0);

                ColorDrawable dwTime = new ColorDrawable(0x00000000);
                //设置SelectPicPopupWindow弹出窗体的背景
                popupWindowTime.setBackgroundDrawable(dwTime);
                backgroundAlpha(FoundImgActivity.this, 0.5f);
                popupWindowTime.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        // TODO Auto-generated method stub
                        backgroundAlpha(FoundImgActivity.this, 1f);
                    }
                });
                viewTime.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        closePopupWindow();
                        activity_found_time.setTextColor(Color.BLACK);
                        return false;
                    }
                });








                break;


            case R.id.tv_all:
                activity_found_type.setText("全部");
                popupWindow.dismiss();
                request(StringUrl.stringFoundFutureActivity);
                activity_found_type.setTextColor(Color.BLACK);


                break;
            case R.id.tv_demo_day:
                activity_found_type.setText("Demo");
                popupWindow.dismiss();
                request(StringUrl.stringFoundFutureDemo);
                activity_found_type.setTextColor(Color.BLACK);

                break;
            case R.id.tv_room:
                activity_found_type.setText("氪空间");
                popupWindow.dismiss();
                request(StringUrl.stringFoundFutureSpace);
                activity_found_type.setTextColor(Color.BLACK);

                break;
            case R.id.tv_money:
                activity_found_type.setText("股权投资");
                popupWindow.dismiss();
                request(StringUrl.stringFoundFutureEquity);
                activity_found_type.setTextColor(Color.BLACK);

                break;
            case R.id.tv_service:
                activity_found_type.setText("企业服务");
                popupWindow.dismiss();
                request(StringUrl.stringFoundFutureService);
                activity_found_type.setTextColor(Color.BLACK);

                break;
            case R.id.tv_quck:
                activity_found_type.setText("急速融资");
                popupWindow.dismiss();
                request(StringUrl.stringFoundFutureRapid);
                activity_found_type.setTextColor(Color.BLACK);

                break;


        }

    }

    private void request(String url) {
        VolleySingleton.addRequest(url, FoundImgBean.class, new Response.Listener<FoundImgBean>() {

            @Override
            public void onResponse(FoundImgBean response) {
                FoundImgActivity.this.response = response;
                foundActivityAdapter.setBean(response);
                listView.setAdapter(foundActivityAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

    }

    private void closePopupWindow() {
        if (popupWindowTime != null && popupWindowTime.isShowing()) {
            popupWindowTime.dismiss();
            popupWindowTime = null;
            WindowManager.LayoutParams params = this.getWindow().getAttributes();
            params.alpha = 1f;
            this.getWindow().setAttributes(params);
        }

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


}
