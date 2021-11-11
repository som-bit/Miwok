package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MewokFragmentPagerAdaprter extends FragmentPagerAdapter {

    private String[] tabTitles = new String[]{"Numbers", "Family Members", "Colors","Phrases"};


    public MewokFragmentPagerAdaprter(FragmentManager fuck){
        super(fuck);

    }


    // overriding getPageTitle()
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
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
