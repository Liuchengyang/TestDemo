package com.lanou3g.newdemo.message.secondinterface;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseAty;

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
public class DirectMesActivity  extends BaseAty implements View.OnClickListener {
    private RelativeLayout activity_direct_focus_layout;
    private ImageView activity_direct_message_back;
    @Override
    protected int setLayout() {
        return R.layout.activity_direct_message;
    }

    @Override
    protected void initView() {
        activity_direct_focus_layout = (RelativeLayout) findViewById(R.id.activity_direct_focus_layout);
        activity_direct_message_back = (ImageView) findViewById(R.id.activity_direct_message_back);

    }

    @Override
    protected void initData() {
        activity_direct_focus_layout.setOnClickListener(this);
        activity_direct_message_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_direct_focus_layout:
                Intent intent  =new Intent(DirectMesActivity.this,FocusMesActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_direct_message_back:
                finish();
                break;
        }

    }
}
