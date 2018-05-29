package com.example.admin.nihongotaisaku.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.nihongotaisaku.fragments.ListAlphabetFragment;

public class AlphabetAdapter extends FragmentPagerAdapter{
    private ListAlphabetFragment listAlphabetFragment;
    private Bundle bundle;

    public AlphabetAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        listAlphabetFragment = new ListAlphabetFragment();
        bundle = new Bundle();
        bundle.putInt("classify", position);
        listAlphabetFragment.setArguments(bundle);
        return listAlphabetFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Hiragana cơ bản";
            case 1:
                return "Katakana cơ bản";
            case 2:
                return "Hiragana nâng cao";
            case 3:
                return "Katakana nâng cao";
            default:
                return null;
        }
    }
}
