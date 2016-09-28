package com.lanou3g.newdemo.found.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.found.bean.FoundInvestBean;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
public class FoundInvestAdapter extends BaseAdapter {
    Context context;
    FoundInvestBean bean;

    public FoundInvestAdapter(Context context) {
        this.context = context;
    }

    public void setBean(FoundInvestBean bean) {
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.found_invest_list_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Picasso.with(context).load(bean.getData().getData().get(i).getUser().getAvatar()).transform(new CircleTransform()).into(holder.imageView);
        holder.found_list_name_text.setText(bean.getData().getData().get(i).getUser().getName());

        int bath = bean.getData().getData().get(i).getFocusIndustry().size();
        int path = bean.getData().getData().get(i).getInvestPhases().size();
        if (bean.getData().getData().get(i).getFocusIndustry().isEmpty()) {
            holder.found_list_focus_content.setText("未披露");

        } else {
            if (bath == 3) {

                holder.found_list_focus_content.setText(bean.getData().getData().get(i).getFocusIndustry().get(0) + " " + bean.getData().getData().get(i).getFocusIndustry().get(1) + " " + bean.getData().getData().get(i).getFocusIndustry().get(2));
            } else if (bath == 2) {

                holder.found_list_focus_content.setText(bean.getData().getData().get(i).getFocusIndustry().get(0) + " " + bean.getData().getData().get(i).getFocusIndustry().get(1));

            } else if (bath == 1) {
                holder.found_list_focus_content.setText(bean.getData().getData().get(i).getFocusIndustry().get(0));

            }

        }
        if (bean.getData().getData().get(i).getInvestPhases().isEmpty()) {
            holder.found_list_nvestment_content.setText("未披露");
        } else {
            if (path == 3) {

                holder.found_list_nvestment_content.setText(bean.getData().getData().get(i).getInvestPhases().get(0) + " " + bean.getData().getData().get(i).getInvestPhases().get(1) + " " + bean.getData().getData().get(i).getInvestPhases().get(2));
            } else if (path == 2) {
                holder.found_list_nvestment_content.setText(bean.getData().getData().get(i).getInvestPhases().get(0) + " " + bean.getData().getData().get(i).getInvestPhases().get(1));
            } else if (path == 1) {
                holder.found_list_nvestment_content.setText(bean.getData().getData().get(i).getInvestPhases().get(0));
            }

        }


        return view;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView found_list_name_text, found_list_focus_content, found_list_nvestment_content;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.found_list_person_img);
            found_list_name_text = (TextView) view.findViewById(R.id.found_list_name_text);
            found_list_focus_content = (TextView) view.findViewById(R.id.found_list_focus_content);
            found_list_nvestment_content = (TextView) view.findViewById(R.id.found_list_nvestment_content);


        }
    }


    class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }

    }
}