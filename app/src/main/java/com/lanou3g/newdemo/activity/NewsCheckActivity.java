package com.lanou3g.newdemo.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseAty;
import com.lanou3g.newdemo.news.adapter.NewsCheckAdapter;
import com.lanou3g.newdemo.news.bean.NewsCheckBean;

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
 * 　　　　　　　　　┃ 　　　　　　　┏┛n
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * Created by 刘城羊 on 16/7/10.
 */
public class NewsCheckActivity  extends BaseAty implements View.OnClickListener {
    private EditText editText;
    private ImageView news_check_img;
    private TextView news_check_cancel;

    private ListView listView;
    private NewsCheckAdapter checkAdapter;





    @Override
    protected int setLayout() {
        return R.layout.activity_news_check;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.news_check_list_view);
        editText = (EditText) findViewById(R.id.news_check_edit_text);

    }

    @Override
    protected void initData() {
        editText.setOnClickListener(this);
        checkAdapter =new NewsCheckAdapter(this);



    }

    @Override
    public void onClick(View view) {

    }
}
