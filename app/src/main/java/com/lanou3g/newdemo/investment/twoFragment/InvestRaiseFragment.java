package com.lanou3g.newdemo.investment.twoFragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseFragment;
import com.lanou3g.newdemo.base.StringUrl;
import com.lanou3g.newdemo.investment.bean.InvestFinanBean;
import com.lanou3g.newdemo.investment.bean.InvestRaiseBean;
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
public class InvestRaiseFragment extends BaseFragment {
    private ImageView imageViewlogal;
    private TextView textViewcompanyname;
    private TextView textViewsingmeting;
    private ImageView buttoncare;
    private ImageView imageViewbigpic;
    private TextView textViewfirstperson;
    private TextView textViewsecondcreat;
    private TextView textViewthirdteamnumber;
    private TextView textViewleadname;
    private TextView textViewadcontent;
    private TextView textViewthird;
    private TextView textViewing;
    private ProgressBar progressBar;
    private TextView textViewmoney;
    private ImageView buttonsure;


    @Override
    protected int setLayout() {
        return R.layout.fragment_invest_raise;
    }

    @Override
    protected void initView(View view) {
        imageViewlogal = (ImageView) view.findViewById(R.id.iv_logal_item_fragmentmoney_viewpager_fragment);
        textViewcompanyname = (TextView) view.findViewById(R.id.tv_companyname_item_fragmentmoney_viewpager_fragment);
        textViewsingmeting = (TextView) view.findViewById(R.id.tv1_singmeet);
        buttoncare = (ImageView) view.findViewById(R.id.btn1_care);
        imageViewbigpic = (ImageView) view.findViewById(R.id.iv_bigpic_item_fragmentmoney_viewpager_fragment);
        textViewfirstperson = (TextView) view.findViewById(R.id.first1_person);
        textViewsecondcreat = (TextView) view.findViewById(R.id.second1_creat);
        textViewthirdteamnumber = (TextView) view.findViewById(R.id.third1_teamnumber);
        textViewleadname = (TextView) view.findViewById(R.id.tv_leadname_item_fragmentmoney_viewpager_fragment);
        textViewadcontent = (TextView) view.findViewById(R.id.tv_adcontent_item_fragmentmoney_viewpager_fragment);
        textViewthird = (TextView) view.findViewById(R.id.tv_third_item_fragmentmoney_viewpager_fragment);
        textViewing = (TextView) view.findViewById(R.id.tv_ing_item_fragmentmoney_viewpager_fragment);
        progressBar = (ProgressBar) view.findViewById(R.id.seekbar_item_fragmentmoney_viewpager_fragment);
        textViewmoney = (TextView) view.findViewById(R.id.tv_money_item_fragmentmoney_viewpager_fragment);
        buttonsure = (ImageView) view.findViewById(R.id.btn1_sure);


    }

    @Override
    protected void initData() {





        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(StringUrl.investFragmentFundraisingIn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                InvestRaiseBean bean = gson.fromJson(response, InvestRaiseBean.class);

                Picasso.with(getContext()).load(bean.getData().getData().get(0).getCompany_logo()).into(imageViewlogal);
                textViewcompanyname.setText(bean.getData().getData().get(0).getCompany_name());
                textViewsingmeting.setText(bean.getData().getData().get(0).getCompany_brief());
                Picasso.with(getContext()).load(bean.getData().getData().get(0).getFile_list_img()).into(imageViewbigpic);
                textViewsecondcreat.setText(bean.getData().getData().get(0).getCf_advantage().get(0).getAdname());
                textViewthirdteamnumber.setText(bean.getData().getData().get(0).getCf_advantage().get(1).getAdname());
                textViewleadname.setText(bean.getData().getData().get(0).getLead_name());
                textViewadcontent.setText(bean.getData().getData().get(0).getCf_advantage().get(0).getAdcontent());
                textViewthird.setText(bean.getData().getData().get(0).getCf_advantage().get(1).getAdcontent());
                textViewing.setText(bean.getData().getData().get(0).getFundStatus().getDesc());

                textViewmoney.setText(bean.getData().getData().get(0).getFundStatus().getDesc());
                progressBar.setProgress((int) (bean.getData().getData().get(0).getRate() * 100));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        queue.add(stringRequest);


    }
}
