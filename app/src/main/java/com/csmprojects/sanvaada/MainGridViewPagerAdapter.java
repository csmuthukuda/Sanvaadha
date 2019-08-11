package com.csmprojects.sanvaada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by chathuranga on 8/12/17.
 */
public class MainGridViewPagerAdapter extends FragmentStatePagerAdapter {
    private  boolean isGifKbShowing;
    public MainGridViewPagerAdapter(FragmentManager fm,boolean isGifKbShowing)
    {
        super(fm);

        this.isGifKbShowing  = isGifKbShowing;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = isGifKbShowing ? new MainGridViewPagerFragment() : new WordsGridViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("itemlistno",position);
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return 17;
    }
}
