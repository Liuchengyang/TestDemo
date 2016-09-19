package com.lanou3g.newdemo;

import android.widget.TextView;

import com.lanou3g.newdemo.base.BaseAty;

public class MainActivity extends BaseAty {


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        TextView textView =bindView(R.id.text_view);

    }

    @Override
    protected void initData() {

    }
}
