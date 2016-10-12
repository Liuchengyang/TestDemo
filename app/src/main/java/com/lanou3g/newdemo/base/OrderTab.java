package com.lanou3g.newdemo.base;

import android.support.v4.app.Fragment;
import android.view.View;

import com.lanou3g.newdemo.my.order.OrderAllFragment;
import com.lanou3g.newdemo.my.order.OrderForPaymentFragment;
import com.lanou3g.newdemo.my.order.PaymentBeenFragment;

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
public class OrderTab {
    private String titles;
    private Fragment f;

    public OrderTab(String titles, Fragment f) {
        this.titles = titles;
        this.f = f;
    }



    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public Fragment getF() {
        return f;
    }

    public void setF(Fragment f) {
        this.f = f;
    }
    public static List<OrderTab>getOrderTab(){
        List<OrderTab>orderTabs =new ArrayList<>();
        orderTabs.add(new OrderTab("全部",new OrderAllFragment()));
        orderTabs.add(new OrderTab("待付款",new OrderForPaymentFragment()));
        orderTabs.add(new OrderTab("已付款",new PaymentBeenFragment()));
        return orderTabs;

    }


}
