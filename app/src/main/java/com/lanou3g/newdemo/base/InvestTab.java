package com.lanou3g.newdemo.base;

import android.support.v4.app.Fragment;

import com.lanou3g.newdemo.investment.twoFragment.InvestAllFragment;
import com.lanou3g.newdemo.investment.twoFragment.InvestCompleteFragment;
import com.lanou3g.newdemo.investment.twoFragment.InvestFinancingFragment;
import com.lanou3g.newdemo.investment.twoFragment.InvestRaiseFragment;

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
public class InvestTab {
    private String titles;
    private Fragment f;

    public InvestTab(String titles, Fragment f) {
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

    public static List<InvestTab>getInvestTab(){
        List<InvestTab>investTabs =new ArrayList<>();
        investTabs.add(new InvestTab("全部",new InvestAllFragment()));
        investTabs.add(new InvestTab("募资中",new InvestRaiseFragment()));
        investTabs.add(new InvestTab("募资完成",new InvestCompleteFragment()));
        investTabs.add(new InvestTab("融资成功",new InvestFinancingFragment()));

        return investTabs;
    }
}
