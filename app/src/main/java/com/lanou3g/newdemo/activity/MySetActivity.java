package com.lanou3g.newdemo.activity;

import android.view.View;
import android.widget.ImageView;

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
public class MySetActivity  extends BaseAty implements View.OnClickListener {
    private ImageView iv_findgoodproject_back_setting;
    @Override
    protected int setLayout() {
        return R.layout.activity_my_set;
    }

    @Override
    protected void initView() {
        iv_findgoodproject_back_setting = (ImageView) findViewById(R.id.iv_findgoodproject_back_setting);

    }

    @Override
    protected void initData() {
        iv_findgoodproject_back_setting.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_findgoodproject_back_setting:
                finish();
                break;
        }

    }
}
