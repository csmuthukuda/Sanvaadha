package com.csmprojects.sanvaada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by chathuranga on 8/12/17.
 */
public class MainGridViewPagerAdapter extends FragmentPagerAdapter {


    public MainGridViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new MainGridViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("itemlistno",position);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
