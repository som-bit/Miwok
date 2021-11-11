package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MewokFragmentPagerAdaprter extends FragmentPagerAdapter {

    public MewokFragmentPagerAdaprter(FragmentManager fuck){
        super(fuck);

    }
    @Override
    public Fragment getItem(int pos){

        if (pos == 0){
            return new NumbersFragment();
        }
        else if (pos == 1){
            return  new fragment_familyMember();
        }
        else if (pos == 2){
            return new fragment_color();
        }
        else{
            return new fragment_phrases();
        }


    }

    @Override
    public int getCount() {
        return 4;
    }
}
