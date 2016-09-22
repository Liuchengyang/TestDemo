package com.lanou3g.newdemo.news.adapter;

import android.content.Context;
import android.content.res.ObbInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.news.bean.NewsListBean;
import com.squareup.picasso.Picasso;

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
public class NewsListAdapter extends BaseAdapter {
    private NewsListBean newsListBean;
    private Context context;
    Date date = new Date(System.currentTimeMillis());
    SimpleDateFormat format =new SimpleDateFormat(" HH:mm:ss");


    public NewsListAdapter(Context context) {
        this.context = context;
    }

    public void setNewsListBean(NewsListBean newsListBean) {
        this.newsListBean = newsListBean;
    }

    @Override
    public int getCount() {
        return newsListBean.getData().getData().size();
    }

    @Override
    public Object getItem(int i) {
        return newsListBean.getData().getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder =null;
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.fragment_news_list_item,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);

        }else {
            holder = (ViewHolder) view.getTag();
        }
        Picasso.with(context).load(newsListBean.getData().getData().get(i).getFeatureImg()).into(holder.news_list_item_img);

        holder.news_list_item_title.setText(newsListBean.getData().getData().get(i).getTitle());
        if (newsListBean.getData().getData().get(i).getUser() != null) {
            Log.d("NewsListAdapter", "bukong");
        }
        else {
            Log.d("NewsListAdapter", "kong");
        }
//        holder.news_list_item_name.setText(newsListBean.getData().getData().get(i).getUser().getName());
        holder.news_list_item_time.setText(format.format(date));
        holder.news_list_item_comname.setText(newsListBean.getData().getData().get(i).getColumnName());
        return view;
    }

    class ViewHolder{
        private ImageView news_list_item_img;
        private TextView news_list_item_title,news_list_item_name,news_list_item_time,news_list_item_comname;
        public ViewHolder(View view){
            news_list_item_img = (ImageView) view.findViewById(R.id.news_list_item_img);
            news_list_item_title = (TextView) view.findViewById(R.id.news_list_item_title);
            news_list_item_name = (TextView) view.findViewById(R.id.news_list_item_name);
            news_list_item_time = (TextView) view.findViewById(R.id.news_list_item_time);
            news_list_item_comname = (TextView) view.findViewById(R.id.news_list_item_comname);

        }
    }
}
