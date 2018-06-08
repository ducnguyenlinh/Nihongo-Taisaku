package com.example.admin.nihongotaisaku.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.GlideModule;
import com.example.admin.nihongotaisaku.R;

import java.util.ArrayList;

public class WritingKatakanaFragment extends Fragment implements GlideModule{
    ArrayList<String> arrKatakanaWriting;
    int position;

    public WritingKatakanaFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alphabet_write, container, false);

        ImageView img_writing = (ImageView) v.findViewById(R.id.img_writting);

        position = getArguments().getInt("alphabetID");

        arrKatakanaWriting = new ArrayList<String>();

        arrKatakanaWriting.add("https://imgur.com/fMC45bq");
        arrKatakanaWriting.add("https://imgur.com/JGPztzE");
        arrKatakanaWriting.add("https://imgur.com/3O4sN9A");
        arrKatakanaWriting.add("https://imgur.com/OfmziGh");
        arrKatakanaWriting.add("https://imgur.com/bOgTJ7H");

        arrKatakanaWriting.add("https://imgur.com/MefMLYa");
        arrKatakanaWriting.add("https://imgur.com/QRmvQyn");
        arrKatakanaWriting.add("https://imgur.com/MP2yUsU");
        arrKatakanaWriting.add("https://imgur.com/pbejtU5");
        arrKatakanaWriting.add("https://imgur.com/TXKgXhw");

        arrKatakanaWriting.add("https://imgur.com/Ii0x1kg");
        arrKatakanaWriting.add("https://imgur.com/juXIVRB");
        arrKatakanaWriting.add("https://imgur.com/FAgOrMz");
        arrKatakanaWriting.add("https://imgur.com/B3cG7ly");
        arrKatakanaWriting.add("https://imgur.com/DBfi7np");

        arrKatakanaWriting.add("https://imgur.com/pAi4Qed");
        arrKatakanaWriting.add("https://imgur.com/cpt8YoA");
        arrKatakanaWriting.add("https://imgur.com/v4AYM8J");
        arrKatakanaWriting.add("https://imgur.com/WsQZU9z");
        arrKatakanaWriting.add("https://imgur.com/vY1OxA9");

        arrKatakanaWriting.add("https://imgur.com/x02zamP");
        arrKatakanaWriting.add("https://imgur.com/9ZjcRKP");
        arrKatakanaWriting.add("https://imgur.com/qCe2txu");
        arrKatakanaWriting.add("https://imgur.com/SLAYPXl");
        arrKatakanaWriting.add("https://imgur.com/dXsxupj");

        arrKatakanaWriting.add("https://imgur.com/Mvgieaj");
        arrKatakanaWriting.add("https://imgur.com/aQpyPHy");
        arrKatakanaWriting.add("https://imgur.com/wDjcZJw");
        arrKatakanaWriting.add("https://imgur.com/cuKAMD1");
        arrKatakanaWriting.add("https://imgur.com/AioxUMH");

        arrKatakanaWriting.add("https://imgur.com/nSt9XCd");
        arrKatakanaWriting.add("https://imgur.com/Om7K3mX");
        arrKatakanaWriting.add("https://imgur.com/ErtTVWA");
        arrKatakanaWriting.add("https://imgur.com/1hhZmGv");
        arrKatakanaWriting.add("https://imgur.com/Mtcz9b9");

        arrKatakanaWriting.add("https://imgur.com/RAZFTnd");
        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("https://imgur.com/HI0Qud4");
        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("https://imgur.com/5G8Qgzs");

        arrKatakanaWriting.add("https://imgur.com/GBcl4gZ");
        arrKatakanaWriting.add("https://imgur.com/2YA02r3");
        arrKatakanaWriting.add("https://imgur.com/pVeJqqC");
        arrKatakanaWriting.add("https://imgur.com/BQC4ARM");
        arrKatakanaWriting.add("https://imgur.com/ypEbILD");

        arrKatakanaWriting.add("https://imgur.com/WcZ1uTa");
        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("https://imgur.com/Fc9DWOH");

        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("");
        arrKatakanaWriting.add("https://imgur.com/kv63sRk");

        Glide.with(getContext())
                .load(arrKatakanaWriting.get(position) + ".gif")
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img_writing);

        return v;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
