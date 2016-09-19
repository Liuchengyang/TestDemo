package com.lanou3g.newdemo.base;

import android.support.v4.app.Fragment;

import com.lanou3g.newdemo.R;
import com.lanou3g.newdemo.found.FoundFragment;
import com.lanou3g.newdemo.investment.InvestFragment;
import com.lanou3g.newdemo.message.MessageFragment;
import com.lanou3g.newdemo.my.MyFragment;
import com.lanou3g.newdemo.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

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
public class TabInfo {
    private String title;
    private int imageId;
    private Fragment f;


    public TabInfo(String title, int imageId, Fragment f) {
        this.title = title;
        this.imageId = imageId;
        this.f = f;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getF() {
        return f;
    }

    public void setF(Fragment f) {
        this.f = f;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public static List<TabInfo>getTabInfos(){
        List<TabInfo>infos = new ArrayList<>();
        infos.add(new TabInfo("新闻", R.drawable.select_news,new NewsFragment()));
        infos.add(new TabInfo("股权", R.drawable.select_invest,new InvestFragment()));
        infos.add(new TabInfo("发现", R.drawable.select_found,new FoundFragment()));
        infos.add(new TabInfo("消息", R.drawable.select_message,new MessageFragment()));
        infos.add(new TabInfo("我的", R.drawable.select_my,new MyFragment()));
        return infos;
    }

}
