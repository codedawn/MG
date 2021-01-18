package com.codedawn.mg.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author codedawn
 * @date 2021-01-13 23:15
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentList = fragmentList;
        //fragment的添加和替换都是通过manager来管理的.
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
        //用于展示的fragment
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
        //展示页面的个数
    }
}

