package com.example.admin.nihongotaisaku.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.nihongotaisaku.fragments.GrammarFragment;
import com.example.admin.nihongotaisaku.fragments.VocabularyFragment;

public class VocabularyAndGrammarAdapter extends FragmentPagerAdapter{
    public VocabularyAndGrammarAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new VocabularyFragment();
            case 1:
                return new GrammarFragment();
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
                return "Vocabulary";
            case 1:
                return "Grammar";
            default:
                return null;
        }
    }
}
