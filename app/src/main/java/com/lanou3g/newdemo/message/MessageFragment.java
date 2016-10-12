package com.lanou3g.newdemo.message;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseFragment;
import com.lanou3g.newdemo.message.secondinterface.CommentActivity;
import com.lanou3g.newdemo.message.secondinterface.DirectMesActivity;
import com.lanou3g.newdemo.message.secondinterface.OtherActivity;
import com.lanou3g.newdemo.message.secondinterface.PraiseActivity;

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
public class MessageFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout mess_direct_messages_layout,mess_comments_layout,mess_praise_layout,mess_assistant_layout,mess_other_layout;

    @Override
    protected int setLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View view) {
        mess_direct_messages_layout = (RelativeLayout) view.findViewById(R.id.mess_direct_messages_layout);
        mess_comments_layout = (RelativeLayout) view.findViewById(R.id.mess_comments_layout);
        mess_praise_layout = (RelativeLayout) view.findViewById(R.id.mess_praise_layout);
        mess_assistant_layout = (RelativeLayout) view.findViewById(R.id.mess_assistant_layout);
        mess_other_layout = (RelativeLayout) view.findViewById(R.id.mess_other_layout);




    }

    @Override
    protected void initData() {
        mess_direct_messages_layout.setOnClickListener(this);
        mess_comments_layout.setOnClickListener(this);
        mess_praise_layout.setOnClickListener(this);
        mess_assistant_layout.setOnClickListener(this);
        mess_other_layout.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mess_direct_messages_layout:
                Intent intentDirect =new Intent(getActivity(),DirectMesActivity.class);
                startActivity(intentDirect);
                break;
            case R.id.mess_comments_layout:
                Intent intentComments = new Intent(getActivity(),CommentActivity.class);
                startActivity(intentComments);
                break;
            case R.id.mess_praise_layout:
                Intent intentPraise =new Intent(getActivity(),PraiseActivity.class);
                startActivity(intentPraise);
                break;
            case R.id.mess_assistant_layout:
                break;
            case R.id.mess_other_layout:
                Intent intentOther  = new Intent(getActivity(),OtherActivity.class);
                startActivity(intentOther);
                break;
        }

    }
}
