package com.lanou3g.newdemo.found.secondinterface;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
public class LinkActivity  extends BaseAty implements View.OnClickListener {
    private WebView webView;
    private ImageView iv_turnpic_details_back,iv_pop;
    @Override
    protected int setLayout() {
        return R.layout.activity_link;
    }

    @Override
    protected void initView() {
        iv_turnpic_details_back = (ImageView) findViewById(R.id.iv_turnpic_details_back);
        iv_pop = (ImageView) findViewById(R.id.iv_pop);
        webView = (WebView) findViewById(R.id.web_turn_pic_details);



    }

    @Override
    protected void initData() {
        iv_turnpic_details_back.setOnClickListener(this);
        iv_pop.setOnClickListener(this);

        Intent intent =getIntent();
        String url =intent.getStringExtra("stringUrl");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        WebViewClient webViewClient =new WebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(true);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_turnpic_details_back:
                finish();
                break;
            case R.id.iv_pop:
                break;
        }

    }
}
