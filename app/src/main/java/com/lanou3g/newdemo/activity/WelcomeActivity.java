package com.lanou3g.newdemo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.newdemo.MainActivity;
import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.base.BaseAty;
import com.lanou3g.newdemo.base.StringUrl;
import com.squareup.picasso.Picasso;

import java.util.Timer;
import java.util.TimerTask;

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
public class WelcomeActivity extends BaseAty {
    private TextView textView;
    private ImageView imageView;
    private Timer timer;
    private TimerTask timerTask;
    private int index = 5;

    @Override
    protected int setLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        textView = (TextView) findViewById(R.id.welcome_text);
        imageView = (ImageView) findViewById(R.id.welcome_img);


    }

    private Handler handler =new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            textView.setText(index -- +"");
            if (index ==0){
                startNext();
            }
            return false;
        }
    });


    @Override
    protected void initData() {
//        Picasso.with(this).load("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=背景图片&step_word=&hs=0&pn=43&spn=0&di=20326383110&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1050640791%2C3506016418&os=3672440944%2C1993125477&simid=4018504269%2C836096649&adpicid=0&ln=1989&fr=&fmq=1476233432161_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fsc.jb51.net%2Fuploads%2Fallimg%2F141011%2F11-141011092929648.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Ffv_z%26e3B3kc8_z%26e3BgjpAzdH3FDjft2gAzdH3Frf1AzdH3Fcnmcm_z%26e3Bip4&gsm=0&rpstart=0&rpnum=0").into(imageView);
//        Log.d("11", "imageView:" + imageView);
        //初始化计时器相关的对象
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {

                handler.sendEmptyMessage(0);
            }
        };

        //开始执行计时器,.每隔1000毫秒都会执行一下TimerTask对象里面的任务
        timer.schedule(timerTask,0,1000);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNext();
            }
        });

    }

    //跳转到主界面,并停止计时器/
    private void startNext() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        timer.cancel();
        finish();


    }



}
