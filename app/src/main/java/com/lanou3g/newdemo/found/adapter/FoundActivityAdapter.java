package com.lanou3g.newdemo.found.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.found.bean.FoundImgBean;
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
public class FoundActivityAdapter extends BaseAdapter {
    private Context context;
    private FoundImgBean bean;

    public FoundActivityAdapter(Context context) {
        this.context = context;
    }

    public void setBean(FoundImgBean bean) {
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
        FoundViewHolder holder =null;
        if (view ==null) {
            view  = LayoutInflater.from(context).inflate(R.layout.activity_list_item,viewGroup,false);
            holder =new FoundViewHolder(view);
            view.setTag(holder);

        }else {
            holder = (FoundViewHolder) view.getTag();
        }
        Picasso.with(context).load(bean.getData().getData().get(i).getActivityImg()).into(holder.item_big_img_found);
        holder.item_title_found.setText(bean.getData().getData().get(i).getActivityName());
        holder.item_content_found.setText(bean.getData().getData().get(i).getActivityDesc());
        holder.item_sign_found.setText(bean.getData().getData().get(i).getActivityStatus());
        holder.item_city_found.setText(bean.getData().getData().get(i).getActivityCity());
        holder.item_time_found.setText(bean.getData().getData().get(i).getActivityTime());

        return view;
    }
    class FoundViewHolder{
        private ImageView item_big_img_found;
        private TextView item_title_found,item_content_found,item_sign_found,
                item_city_found,item_time_found;
        public FoundViewHolder(View view){
            item_big_img_found = (ImageView) view.findViewById(R.id.item_big_img_found);
            item_title_found = (TextView) view.findViewById(R.id.item_title_found);
            item_content_found = (TextView) view.findViewById(R.id.item_content_found);
            item_sign_found = (TextView) view.findViewById(R.id.item_sign_found);
            item_city_found = (TextView) view.findViewById(R.id.item_city_found);
            item_time_found = (TextView) view.findViewById(R.id.item_time_found);



        }
    }
}
