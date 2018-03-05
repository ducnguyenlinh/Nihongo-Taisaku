package com.example.admin.nihongotaisaku.fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.admin.nihongotaisaku.R;

import java.io.IOException;
import java.util.ArrayList;

public class KatakanaCardFrontFragment extends Fragment{
    ImageView imgWritingFront;
    ArrayList<Integer> arrWriting;
    int position;
    ImageButton imgSound;
    ArrayList<String> arrSound;
    MediaPlayer mediaPlayer;

    public KatakanaCardFrontFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hiragana_cardfront, container, false);

        imgWritingFront = (ImageView) v.findViewById(R.id.imgWritingFont);
        imgSound = (ImageButton) v.findViewById(R.id.imgSound);

        position = getArguments().getInt("position");

        arrSound = new ArrayList<>();

        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/aa.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ii.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/uu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ee.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/oo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ka.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ki.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ku.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ke.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ko.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/sa.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/si.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/su.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/se.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/so.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ta.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ti.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/tu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/te.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/to.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/na.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ni.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/nu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ne.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/no.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ha.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/hi.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/hu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/he.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ho.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ma.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/mi.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/mu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/me.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/mo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/yu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/yo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ra.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ri.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ru.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/re.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ro.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/wa.mp3");
        arrSound.add("");
        arrSound.add("");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/wo.mp3");
        arrSound.add("");
        arrSound.add("");
        arrSound.add("");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/nn.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ga.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/gi.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/gu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ge.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/go.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/za.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ji.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/zu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ze.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/zo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/da.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ji.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/zu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/de.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/do.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ba.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/bi.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/bu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/be.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/bo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/pa.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/pi.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/pu.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/pe.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/po.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/kya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/kyu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/kyo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/sha.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/shu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/sho.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/cha.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/chu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/cho.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/nya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/nyu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/nyo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/hya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/hyu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/hyo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/mya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/myu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/myo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/rya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ryu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ryo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/gya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/gyu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/gyo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ja.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/ju.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/jo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/bya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/byu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/byo.mp3");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/pya.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/pyu.mp3");
        arrSound.add("");
        arrSound.add("https://www.nhk.or.jp/lesson/mp3/syllabary/pyo.mp3");


        arrWriting = new ArrayList<>();

        arrWriting.add(R.drawable.katakana_write_a);arrWriting.add(R.drawable.katakana_write_i);
        arrWriting.add(R.drawable.katakana_write_u);arrWriting.add(R.drawable.katakana_write_e);
        arrWriting.add(R.drawable.katakana_write_o);

        arrWriting.add(R.drawable.katakana_write_ka);arrWriting.add(R.drawable.katakana_write_ki);
        arrWriting.add(R.drawable.katakana_write_ku);arrWriting.add(R.drawable.katakana_write_ke);
        arrWriting.add(R.drawable.katakana_write_ko);

        arrWriting.add(R.drawable.katakana_write_sa);arrWriting.add(R.drawable.katakana_write_shi);
        arrWriting.add(R.drawable.katakana_write_su);arrWriting.add(R.drawable.katakana_write_se);
        arrWriting.add(R.drawable.katakana_write_so);

        arrWriting.add(R.drawable.katakana_write_ta);arrWriting.add(R.drawable.katakana_write_chi);
        arrWriting.add(R.drawable.katakana_write_tsu);arrWriting.add(R.drawable.katakana_write_te);
        arrWriting.add(R.drawable.katakana_write_to);

        arrWriting.add(R.drawable.katakana_write_na);arrWriting.add(R.drawable.katakana_write_ni);
        arrWriting.add(R.drawable.katakana_write_nu);arrWriting.add(R.drawable.katakana_write_ne);
        arrWriting.add(R.drawable.katakana_write_no);

        arrWriting.add(R.drawable.katakana_write_ha);arrWriting.add(R.drawable.katakana_write_hi);
        arrWriting.add(R.drawable.katakana_write_fu);arrWriting.add(R.drawable.katakana_write_he);
        arrWriting.add(R.drawable.katakana_write_ho);

        arrWriting.add(R.drawable.katakana_write_ma);arrWriting.add(R.drawable.katakana_write_mi);
        arrWriting.add(R.drawable.katakana_write_mu);arrWriting.add(R.drawable.katakana_write_me);
        arrWriting.add(R.drawable.katakana_write_mo);

        arrWriting.add(R.drawable.katakana_write_ya);arrWriting.add(R.drawable.katakana_write_ri);
        arrWriting.add(R.drawable.katakana_write_yu);arrWriting.add(R.drawable.katakana_write_re);
        arrWriting.add(R.drawable.katakana_write_yo);

        arrWriting.add(R.drawable.katakana_write_ra);arrWriting.add(R.drawable.katakana_write_ri);
        arrWriting.add(R.drawable.katakana_write_ru);arrWriting.add(R.drawable.katakana_write_re);
        arrWriting.add(R.drawable.katakana_write_ro);

        arrWriting.add(R.drawable.katakana_write_wa);arrWriting.add(R.drawable.katakana_write_i);
        arrWriting.add(R.drawable.katakana_write_u);arrWriting.add(R.drawable.katakana_write_e);
        arrWriting.add(R.drawable.katakana_write_wo);

        arrWriting.add(R.drawable.katakana_write_a);arrWriting.add(R.drawable.katakana_write_i);
        arrWriting.add(R.drawable.katakana_write_u);arrWriting.add(R.drawable.katakana_write_e);
        arrWriting.add(R.drawable.katakana_write_n);

        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imgWritingFront);

        Glide.with(getContext())
                .load(arrWriting.get(position))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewTarget);

        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(arrSound.get(position));
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }
}
