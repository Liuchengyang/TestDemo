package com.lanou3g.newdemo.found.secondinterface;

import android.content.Context;
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
import com.lanou3g.newdemo.base.BaseAty;
import com.lanou3g.newdemo.base.StringUrl;
import com.lanou3g.newdemo.found.adapter.FoundInvestAdapter;
import com.lanou3g.newdemo.found.bean.FoundInvestBean;

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
public class FoundInvestActivity  extends BaseAty implements View.OnClickListener {
    private ImageView found_invest_back_img;
    private ListView listView;
    private FoundInvestAdapter foundInvestAdapter;
    @Override
    protected int setLayout() {
        return R.layout.activity_found_invest;
    }

    @Override
    protected void initView() {
        found_invest_back_img = (ImageView) findViewById(R.id.found_invest_back_img);
        listView = (ListView) findViewById(R.id.found_invest_list_view);
        foundInvestAdapter = new FoundInvestAdapter(this);

    }

    @Override
    protected void initData() {
        found_invest_back_img.setOnClickListener(this);
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(StringUrl.stringFoundInvestActivity, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson =new Gson();
                FoundInvestBean bean =gson.fromJson(response,FoundInvestBean.class);
                foundInvestAdapter.setBean(bean);
                listView.setAdapter(foundInvestAdapter);

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
        switch (view.getId()) {
            case R.id.found_invest_back_img:
                finish();
                break;

        }

    }
}
