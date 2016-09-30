package com.lanou3g.newdemo.investment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.investment.bean.InvestAllBean;
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
public class InvestAllAdapter extends BaseAdapter {

    private InvestAllBean investAllBean;

    private Context context;

    public InvestAllAdapter(Context context) {
        this.context = context;
    }

    public void setInvestAllBean(InvestAllBean investAllBean) {
        this.investAllBean = investAllBean;
    }

    @Override
    public int getCount() {
        return investAllBean.getData().getData().size();
    }

    @Override
    public Object getItem(int i) {
        return investAllBean.getData().getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder =null;
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.invest_all_fragment_list_item,viewGroup,false);
           viewHolder =new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(investAllBean.getData().getData().get(i).getCompany_logo()).into(viewHolder.imageViewlogal);
        viewHolder.textViewcompanyname.setText(investAllBean.getData().getData().get(i).getCompany_name());
        viewHolder.textViewsingmeting.setText(investAllBean.getData().getData().get(i).getCompany_brief());
        Picasso.with(context).load(investAllBean.getData().getData().get(i).getFile_list_img()).into(viewHolder.imageViewbigpic);
        viewHolder.textViewsecondcreat.setText(investAllBean.getData().getData().get(i).getCf_advantage().get(0).getAdname());
        viewHolder.textViewthirdteamnumber.setText(investAllBean.getData().getData().get(i).getCf_advantage().get(1).getAdname());
        viewHolder.textViewleadname.setText(investAllBean.getData().getData().get(i).getLead_name());
        viewHolder.textViewadcontent.setText(investAllBean.getData().getData().get(i).getCf_advantage().get(0).getAdcontent());
        viewHolder.textViewthird.setText(investAllBean.getData().getData().get(i).getCf_advantage().get(1).getAdcontent());
        viewHolder. textViewing.setText(investAllBean.getData().getData().get(i).getFundStatus().getDesc());
//        viewHolder.textViewmoney.setText("已募资" + (int) (investAllBean.getData().getData().get(i).getRate() * 100) + "%");

        viewHolder.progressBar.setProgress((int)(investAllBean.getData().getData().get(i).getRate() * 100));







        return view;
    }

    class ViewHolder{

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
        public ViewHolder(View view){
            imageViewlogal = (ImageView) view.findViewById(R.id.iv_logal_item_fragmentmoney_viewpager_fragment_listview);
            textViewcompanyname = (TextView) view.findViewById(R.id.tv_companyname_item_fragmentmoney_viewpager_fragment_listview);
            textViewsingmeting = (TextView) view.findViewById(R.id.tv_singmeet);
            buttoncare = (ImageView) view.findViewById(R.id.btn_care);
            imageViewbigpic = (ImageView) view.findViewById(R.id.iv_bigpic_item_fragmentmoney_viewpager_fragment_listview);
            textViewfirstperson = (TextView) view.findViewById(R.id.first_person);
            textViewsecondcreat = (TextView) view.findViewById(R.id.second_creat);
            textViewthirdteamnumber = (TextView) view.findViewById(R.id.third_teamnumber);
            textViewleadname = (TextView) view.findViewById(R.id.tv_leadname_item_fragmentmoney_viewpager_fragment_listview);
            textViewadcontent = (TextView) view.findViewById(R.id.tv_adcontent_item_fragmentmoney_viewpager_fragment_listview);
            textViewthird = (TextView) view.findViewById(R.id.tv_third_item_fragmentmoney_viewpager_fragment_listview);
            textViewing = (TextView) view.findViewById(R.id.tv_ing_item_fragmentmoney_viewpager_fragment_listview);
            progressBar = (ProgressBar) view.findViewById(R.id.seekbar_item_fragmentmoney_viewpager_fragment_listview);
            textViewmoney = (TextView) view.findViewById(R.id.tv_money_item_fragmentmoney_viewpager_fragment_listview);
            buttonsure = (ImageView) view.findViewById(R.id.btn_sure);



        }
    }
}
