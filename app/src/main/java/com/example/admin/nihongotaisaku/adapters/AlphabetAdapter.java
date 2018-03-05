package com.example.admin.nihongotaisaku.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.nihongotaisaku.fragments.HiraganaFragment;
import com.example.admin.nihongotaisaku.fragments.KatakanaFragment;

public class AlphabetAdapter extends FragmentPagerAdapter{
    public AlphabetAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HiraganaFragment();
            case 1:
                return new KatakanaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Hiragana";
            case 1:
                return "Katakana";
            default:
                return null;
        }
    }
}
