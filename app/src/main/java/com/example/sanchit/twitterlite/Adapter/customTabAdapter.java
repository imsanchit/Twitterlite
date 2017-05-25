package com.example.sanchit.twitterlite.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sanchit.twitterlite.Fragment.Fragment1;
import com.example.sanchit.twitterlite.Fragment.Fragment2;
import com.example.sanchit.twitterlite.Fragment.Fragment3;


public class customTabAdapter extends FragmentPagerAdapter {
    public customTabAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Page1";
            case 1:
                return "Page2";
            case 2:
                return "Page3";
        }
        return "Default";
    }
}