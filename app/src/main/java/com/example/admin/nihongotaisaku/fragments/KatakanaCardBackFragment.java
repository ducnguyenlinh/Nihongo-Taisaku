package com.example.admin.nihongotaisaku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.nihongotaisaku.R;

import java.util.ArrayList;

public class KatakanaCardBackFragment extends Fragment{
    ImageView imgWriting, img_paper_1, img_paper_2, img_paper_3;
    int position;
    ArrayList<Integer> arrWriting, arrPaper_1, arrPaper_2, arrPaper_3;
    ViewGroup move_lyt, book_lyt;

    public KatakanaCardBackFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hiragana_cardback, container, false);

        imgWriting = (ImageView) v.findViewById(R.id.imgWriting);
        img_paper_1 = (ImageView) v.findViewById(R.id.img_paper_1);
        img_paper_2 = (ImageView) v.findViewById(R.id.img_paper_2);
        img_paper_3 = (ImageView) v.findViewById(R.id.img_paper_3);

        position = getArguments().getInt("position");

        return v;
    }
}
