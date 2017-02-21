package com.xiangsong.ticket.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xiangsong on 2016/9/11.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> datas;
    public MainViewPagerAdapter(FragmentManager fm,List<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
