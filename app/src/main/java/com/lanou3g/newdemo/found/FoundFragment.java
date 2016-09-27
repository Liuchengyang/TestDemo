package com.lanou3g.newdemo.found;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.found.secondinterface.BriefcaseActivity;
import com.lanou3g.newdemo.found.secondinterface.FoundInvestActivity;
import com.lanou3g.newdemo.base.BaseFragment;
import com.lanou3g.newdemo.base.StringUrl;
import com.lanou3g.newdemo.found.adapter.FoundLBAdapter;
import com.lanou3g.newdemo.found.secondinterface.PersonHeadActivity;
import com.lanou3g.newdemo.news.bean.NewsLBBean;

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
public class FoundFragment extends BaseFragment implements View.OnClickListener {
    private ViewPager viewPager;
    private FoundLBAdapter foundLBAdapter;
    private LinearLayout found_investment_person_linear;
    private RelativeLayout found_person_head_layout,found_briefcase_layout;
    @Override
    protected int setLayout() {
        return R.layout.fragment_found;
    }

    @Override
    protected void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.found_view_pager);
        found_investment_person_linear = (LinearLayout) view.findViewById(R.id.found_investment_person_linear);
        found_person_head_layout = (RelativeLayout) view.findViewById(R.id.found_person_head_layout);
        found_briefcase_layout = (RelativeLayout) view.findViewById(R.id.found_briefcase_layout);


    }

    @Override
    protected void initData() {
        found_investment_person_linear.setOnClickListener(this);
        found_person_head_layout.setOnClickListener(this);
        found_briefcase_layout.setOnClickListener(this);

                foundLBAdapter =new FoundLBAdapter(getContext());
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest =new StringRequest(StringUrl.newsFragmentLB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson =new Gson();
                NewsLBBean bean =gson.fromJson(response,NewsLBBean.class);
                foundLBAdapter.setNewsLBBean(bean);
                viewPager.setAdapter(foundLBAdapter);


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
            case R.id.found_investment_person_linear:
                Intent intent =new Intent(getActivity(),FoundInvestActivity.class);
                startActivity(intent);
                break;
            case R.id. found_person_head_layout:
                Intent intent1  =new Intent(getActivity(),PersonHeadActivity.class);
                startActivity(intent1);
                break;
            case R.id. found_briefcase_layout:
                Intent intent2 =new Intent(getActivity(),BriefcaseActivity.class);
                startActivity(intent2);
                break;

        }

    }
}
