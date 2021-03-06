package com.lanou3g.newdemo.found;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.activity.NewsCheckActivity;
import com.lanou3g.newdemo.found.secondinterface.BriefcaseActivity;
import com.lanou3g.newdemo.found.secondinterface.FoundImgActivity;
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
    private ImageView found_activity_img;
    private ImageView found_cha_img;
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

        found_activity_img = (ImageView) view.findViewById(R.id.found_activity_img);
        found_cha_img = (ImageView) view.findViewById(R.id.found_cha_img);


    }

    @Override
    protected void initData() {


        found_cha_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), NewsCheckActivity.class);
                startActivity(intent);
            }
        });



        found_investment_person_linear.setOnClickListener(this);
        found_person_head_layout.setOnClickListener(this);
        found_briefcase_layout.setOnClickListener(this);
        found_activity_img.setOnClickListener(this);

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
                Intent intentInvestPerson =new Intent(getActivity(),FoundInvestActivity.class);
                startActivity(intentInvestPerson);
                break;
            case R.id. found_person_head_layout:
                Intent intentPersonHead  =new Intent(getActivity(),PersonHeadActivity.class);
                startActivity(intentPersonHead);
                break;
            case R.id. found_briefcase_layout:
                Intent intentBriefcase =new Intent(getActivity(),BriefcaseActivity.class);
                startActivity(intentBriefcase);
                break;

            case R.id.found_activity_img:
                Intent intentFoundActy =new Intent(getActivity(),FoundImgActivity.class);
                startActivity(intentFoundActy);
                break;
        }

    }
}
