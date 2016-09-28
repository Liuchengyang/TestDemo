package com.lanou3g.newdemo.news.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.news.bean.NewsCheckBean;
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
public class NewsCheckAdapter extends BaseAdapter {
    private Context context;
    private NewsCheckBean bean;
    private SimpleDateFormat dateFormat =new SimpleDateFormat(" MM dd ");
    private Date date;

    public NewsCheckAdapter(Context context) {
        this.context = context;
    }

    public void setBean(NewsCheckBean bean) {
        this.bean = bean;
    }

    @Override
    public int getCount() {
        return bean.getData().getData().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getData().getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CheckViewHolder holder =null;
        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.news_check_list_item,viewGroup,false);
            holder =new CheckViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (CheckViewHolder) view.getTag();
        }
        Picasso.with(context).load(bean.getData().getData().get(i).getFeatureImg()).into(holder.item_check_img);
        holder.item_check_title.setText(bean.getData().getData().get(i).getTitle());
        holder.item_check_name.setText(bean.getData().getData().get(i).getUser().getName());
        date =new Date(bean.getData().getData().get(i).getPublishTime());
        String stringDate =dateFormat.format(date);
        holder.item_check_time.setText(stringDate);
        return view;
    }

    class CheckViewHolder{
        private ImageView item_check_img ;
        private TextView item_check_title,item_check_name,item_check_time;
        public CheckViewHolder(View view){
            item_check_img = (ImageView) view.findViewById(R.id.item_check_img);
            item_check_title = (TextView) view.findViewById(R.id.item_check_title);
            item_check_name = (TextView) view.findViewById(R.id.item_check_name);
            item_check_time = (TextView) view.findViewById(R.id.item_check_time);

        }
    }
}
