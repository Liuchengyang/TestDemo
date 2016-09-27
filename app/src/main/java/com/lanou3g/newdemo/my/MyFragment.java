package com.lanou3g.newdemo.my;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.activity.MessActivity;
import com.lanou3g.newdemo.activity.MyPhoneActivity;
import com.lanou3g.newdemo.activity.MySetActivity;
import com.lanou3g.newdemo.base.BaseFragment;


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
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout my_hotline_layout;
    private ImageView my_system_img;
    private RelativeLayout my_relative_layout;
    @Override
    protected int setLayout() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView(View view) {
        my_hotline_layout = (RelativeLayout) view.findViewById(R.id.my_hotline_layout);
        my_system_img = (ImageView) view.findViewById(R.id.my_system_img);
        my_relative_layout = (RelativeLayout) view.findViewById(R.id.my_relative_layout);

    }

    @Override
    protected void initData() {
        my_hotline_layout.setOnClickListener(this);
        my_system_img.setOnClickListener(this);
        my_relative_layout.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_hotline_layout:
                Intent intent =new Intent(MyFragment.this.getActivity(),MyPhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.my_system_img:
                Intent intentSet =new Intent(MyFragment.this.getActivity(),MySetActivity.class);
                startActivity(intentSet);
                break;
            case R.id.my_relative_layout:
                Intent intentTo  =new Intent(getActivity(), MessActivity.class);
                startActivityForResult(intentTo,100);
                break;

        }

    }
}
