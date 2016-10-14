package com.lanou3g.newdemo.investment.twoFragment;

import android.content.Intent;
import android.renderscript.Int2;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.activity.InvestAllActivity;
import com.lanou3g.newdemo.base.BaseFragment;
import com.lanou3g.newdemo.base.StringUrl;
import com.lanou3g.newdemo.investment.adapter.InvestAdapter;
import com.lanou3g.newdemo.investment.adapter.InvestAllAdapter;
import com.lanou3g.newdemo.investment.bean.InvestAllBean;

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
public class InvestAllFragment extends BaseFragment {
    private ListView listView;
    private InvestAllAdapter investAllAdapter;
    @Override
    protected int setLayout() {
        return R.layout.fragment_invest_all;
    }

    @Override
    protected void initView(View view) {
        listView = (ListView) view.findViewById(R.id.invest_all_list_view);

    }

    @Override
    protected void initData() {
        investAllAdapter =new InvestAllAdapter(getContext());
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest =new StringRequest(StringUrl.investFragmentAll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson =new Gson();
                InvestAllBean bean =gson.fromJson(response,InvestAllBean.class);
                investAllAdapter.setInvestAllBean(bean);
                listView.setAdapter(investAllAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(getActivity(),InvestAllActivity.class);
                InvestAllBean.DataBean.DataBean1 investAllBean = (InvestAllBean.DataBean.DataBean1) adapterView.getItemAtPosition(i);
                String title =investAllBean.getCompany_name();
                intent.putExtra("title",title);
                String url =investAllBean.getPayUrl();
                intent.putExtra("url",url);
                startActivity(intent);



            }
        });

    }
}
