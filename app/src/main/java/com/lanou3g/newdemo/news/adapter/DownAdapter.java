package com.lanou3g.newdemo.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.news.bean.DownBean;
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
public class DownAdapter extends BaseAdapter {
    private DownBean bean;
    private Context context;

    public DownAdapter(Context context) {
        this.context = context;
    }

    public void setBean(DownBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getData().getLatestArticle().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getData().getLatestArticle().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DownViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.down_list_item, viewGroup, false);
            holder  =new DownViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (DownViewHolder) view.getTag();

        }
        Picasso.with(context).load(bean.getData().getLatestArticle().get(i).getFeatureImg()).into(holder.down_list_item_img);
        holder.down_list_item_text.setText(bean.getData().getLatestArticle().get(i).getTitle());

        return view;
    }

    class DownViewHolder {
        private ImageView down_list_item_img;
        private TextView down_list_item_text;

        public DownViewHolder(View v) {
            down_list_item_img = (ImageView)v.findViewById(R.id.down_list_item_img);
            down_list_item_text = (TextView) v.findViewById(R.id.down_list_item_text);

        }


    }
}
