package com.lanou3g.newdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
public class MoreActivity extends Activity implements View.OnClickListener {
    private TextView textViewSmall;
    private TextView textViewMiddle;
    private TextView textViewBig;
    private TextView textViewSuperBig;
    private SeekBar seekBar;
    private RelativeLayout relativeLayout;

    public static final String ACTION_CHANGE_SMALL = "com.lanou3g.newdemo.activity.ACTION_CHANGE_SMALL";
    public static final String ACTION_CHANGE_MIDDLE = "com.lanou3g.newdemo.activity.ACTION_CHANGE_MIDDLE";
    public static final String ACTION_CHANGE_BIG = "com.lanou3g.newdemo.activity.ACTION_CHANGE_BIG";
    public static final String ACTION_CHANGE_SUPER = "com.lanou3g.newdemo.activity.ACTION_CHANGE_SUPER";
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);


        intent =new Intent();
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_more_layout);
        textViewSmall = (TextView) findViewById(R.id.tv_small);
        textViewMiddle = (TextView) findViewById(R.id.tv_middle);
        textViewBig = (TextView) findViewById(R.id.tv_big);
        textViewSuperBig = (TextView) findViewById(R.id.tv_verybig);
        seekBar = (SeekBar) findViewById(R.id.sb_light);


        textViewSmall.setOnClickListener(this);
        textViewMiddle.setOnClickListener(this);
        textViewBig.setOnClickListener(this);
        textViewSuperBig.setOnClickListener(this);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBarChangeListenerImp());
        getWindow().setLayout(ViewPager.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MoreActivity.this, "提示：点击窗口外部关闭窗口！", Toast.LENGTH_SHORT).show();
            }
        });


    }
        @Override
        public void onClick (View view){
            switch (view.getId()) {
                case R.id.tv_small:
                    //在过滤器中添加action
                    intent.setAction(ACTION_CHANGE_SMALL);
                    sendBroadcast(intent);
                    break;
                case R.id.tv_middle:
                    intent.setAction(ACTION_CHANGE_MIDDLE);
                    sendBroadcast(intent);
                    break;
                case R.id.tv_big:
                    intent.setAction(ACTION_CHANGE_BIG);
                    sendBroadcast(intent);
                    break;
                case R.id.tv_verybig:
                    intent.setAction(ACTION_CHANGE_SUPER);
                    sendBroadcast(intent);
                    break;



            }
            finish();

        }

    private void setScreenBrightness(float num) {
        WindowManager.LayoutParams layoutParams = super.getWindow().getAttributes();
        layoutParams.screenBrightness = num;
        super.getWindow().setAttributes(layoutParams);
    }

    public class SeekBarChangeListenerImp implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            int cur = seekBar.getProgress();
            Log.d("SeekBarChangeListenerIm", "cur:" + cur);
            MoreActivity.this.setScreenBrightness(cur * 1.0f / 100);
            //Toast.makeText(ShareActivity.this, "当前屏幕亮度" + cur * 1.0f / 100, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }
}


