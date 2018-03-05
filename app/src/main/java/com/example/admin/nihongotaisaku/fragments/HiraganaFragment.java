package com.example.admin.nihongotaisaku.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.activities.WritingHiraganaActivity;
import com.example.admin.nihongotaisaku.adapters.HiraganaAdapter;
import com.example.admin.nihongotaisaku.data.SyllableData;
import com.example.admin.nihongotaisaku.models.Syllable;
import com.example.admin.nihongotaisaku.utils.InterfaceParseData;

import java.util.ArrayList;

public class HiraganaFragment extends Fragment implements InterfaceParseData{
    ArrayList<Syllable> arrayList;
    Context context;
    HiraganaAdapter hiraganaAdapter;
    private SyllableData syllableData;
    RecyclerView recyclerView;;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hiragana, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.rclSyllable);
        arrayList = new ArrayList<>();
        syllableData = new SyllableData(this);
        syllableData.execute("index.html#tab");

        return view;
    }

    @Override
    public void processFinish(ArrayList output){
        arrayList.addAll(output);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 5);
        recyclerView.setLayoutManager(layoutManager);
        hiraganaAdapter = new HiraganaAdapter((Activity) context, arrayList);
        recyclerView.setAdapter(hiraganaAdapter);
        hiraganaAdapter.notifyDataSetChanged();

        hiraganaAdapter.setOnItemClick(new HiraganaAdapter.OnItemClick() {
            @Override
            public void onItemClick(int position) {
                if ((position != 36) && (position != 38)
                        && (position !=46) && (position != 47)
                        && (position != 48) && (position != 50)
                        && (position != 51) && (position != 52)
                        && (position != 53) && (position != 81)
                        && (position != 83) && (position != 86)
                        && (position != 88) && (position != 91)
                        && (position != 93) && (position != 96)
                        && (position != 98) && (position != 101)
                        && (position != 103) && (position != 106)
                        && (position != 108) && (position != 111)
                        && (position != 113) && (position != 116)
                        && (position != 118) && (position != 121)
                        && (position != 123) && (position != 126)
                        && (position != 128) && (position != 131)
                        && (position != 133) ) {
                    Intent writingHiragana = new Intent(context, WritingHiraganaActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("position", position);
                    writingHiragana.putExtra("positionData", bundle);
                    startActivity(writingHiragana);
                }
            }
        });
    }
}
