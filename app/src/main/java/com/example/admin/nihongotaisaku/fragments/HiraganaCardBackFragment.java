package com.example.admin.nihongotaisaku.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.admin.nihongotaisaku.R;
import com.markjmind.propose.Propose;

import java.util.ArrayList;

public class HiraganaCardBackFragment extends Fragment {
    ImageView imgWriting, img_paper_1, img_paper_2, img_paper_3;
    int position;
    ArrayList<Integer> arrWriting, arrPaper_1, arrPaper_2, arrPaper_3;
    ViewGroup move_lyt, book_lyt;


    public HiraganaCardBackFragment() {

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

        move_lyt = (ViewGroup) v.findViewById(R.id.move_lyt);
        book_lyt = (ViewGroup) v.findViewById(R.id.book_lyt);
        move_lyt.setCameraDistance(Propose.getCameraDistanceY(getContext())*5);
        book_lyt.setCameraDistance(Propose.getCameraDistanceY(getContext())*5);

        arrWriting = new ArrayList<>();

        arrWriting.add(R.drawable.hiragana_photo_a);arrWriting.add(R.drawable.hiragana_photo_i);
        arrWriting.add(R.drawable.hiragana_photo_u);arrWriting.add(R.drawable.hiragana_photo_e);
        arrWriting.add(R.drawable.hiragana_photo_o);

        arrWriting.add(R.drawable.hiragana_photo_ka);arrWriting.add(R.drawable.hiragana_photo_ki);
        arrWriting.add(R.drawable.hiragana_photo_ku);arrWriting.add(R.drawable.hiragana_photo_ke);
        arrWriting.add(R.drawable.hiragana_photo_ko);

        arrWriting.add(R.drawable.hiragana_photo_sa);arrWriting.add(R.drawable.hiragana_photo_shi);
        arrWriting.add(R.drawable.hiragana_photo_su);arrWriting.add(R.drawable.hiragana_photo_se);
        arrWriting.add(R.drawable.hiragana_photo_so);

        arrWriting.add(R.drawable.hiragana_photo_ta);arrWriting.add(R.drawable.hiragana_photo_chi);
        arrWriting.add(R.drawable.hiragana_photo_tsu);arrWriting.add(R.drawable.hiragana_photo_te);
        arrWriting.add(R.drawable.hiragana_photo_to);

        arrWriting.add(R.drawable.hiragana_photo_na);arrWriting.add(R.drawable.hiragana_photo_ni);
        arrWriting.add(R.drawable.hiragana_photo_nu);arrWriting.add(R.drawable.hiragana_photo_ne);
        arrWriting.add(R.drawable.hiragana_photo_no);

        arrWriting.add(R.drawable.hiragana_photo_ha);arrWriting.add(R.drawable.hiragana_photo_hi);
        arrWriting.add(R.drawable.hiragana_photo_fu);arrWriting.add(R.drawable.hiragana_photo_he);
        arrWriting.add(R.drawable.hiragana_photo_ho);

        arrWriting.add(R.drawable.hiragana_photo_ma);arrWriting.add(R.drawable.hiragana_photo_mi);
        arrWriting.add(R.drawable.hiragana_photo_mu);arrWriting.add(R.drawable.hiragana_photo_me);
        arrWriting.add(R.drawable.hiragana_photo_mo);

        arrWriting.add(R.drawable.hiragana_photo_ya);arrWriting.add(R.drawable.hiragana_photo_ri);
        arrWriting.add(R.drawable.hiragana_photo_yu);arrWriting.add(R.drawable.hiragana_photo_re);
        arrWriting.add(R.drawable.hiragana_photo_yo);

        arrWriting.add(R.drawable.hiragana_photo_ra);arrWriting.add(R.drawable.hiragana_photo_ri);
        arrWriting.add(R.drawable.hiragana_photo_ru);arrWriting.add(R.drawable.hiragana_photo_re);
        arrWriting.add(R.drawable.hiragana_photo_ro);

        arrWriting.add(R.drawable.hiragana_photo_wa);arrWriting.add(R.drawable.hiragana_photo_i);
        arrWriting.add(R.drawable.hiragana_photo_u);arrWriting.add(R.drawable.hiragana_photo_e);
        arrWriting.add(R.drawable.hiragana_photo_wo);

        arrWriting.add(R.drawable.hiragana_photo_a);arrWriting.add(R.drawable.hiragana_photo_i);
        arrWriting.add(R.drawable.hiragana_photo_u);arrWriting.add(R.drawable.hiragana_photo_e);
        arrWriting.add(R.drawable.hiragana_photo_n);

        arrPaper_1 = new ArrayList<>();

        arrPaper_1.add(R.drawable.hiragana_paper_1_a);
        arrPaper_1.add(R.drawable.hiragana_paper_1_i);
        arrPaper_1.add(R.drawable.hiragana_paper_1_u);
        arrPaper_1.add(R.drawable.hiragana_paper_1_e);
        arrPaper_1.add(R.drawable.hiragana_paper_1_o);

        arrPaper_1.add(R.drawable.hiragana_paper_1_ka);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ki);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ku);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ke);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ko);

        arrPaper_1.add(R.drawable.hiragana_paper_1_sa);
        arrPaper_1.add(R.drawable.hiragana_paper_1_shi);
        arrPaper_1.add(R.drawable.hiragana_paper_1_su);
        arrPaper_1.add(R.drawable.hiragana_paper_1_se);
        arrPaper_1.add(R.drawable.hiragana_paper_1_so);

        arrPaper_1.add(R.drawable.hiragana_paper_1_ta);
        arrPaper_1.add(R.drawable.hiragana_paper_1_chi);
        arrPaper_1.add(R.drawable.hiragana_paper_1_tsu);
        arrPaper_1.add(R.drawable.hiragana_paper_1_te);
        arrPaper_1.add(R.drawable.hiragana_paper_1_to);

        arrPaper_1.add(R.drawable.hiragana_paper_1_na);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ni);
        arrPaper_1.add(R.drawable.hiragana_paper_1_nu);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ne);
        arrPaper_1.add(R.drawable.hiragana_paper_1_no);

        arrPaper_1.add(R.drawable.hiragana_paper_1_ha);
        arrPaper_1.add(R.drawable.hiragana_paper_1_hi);
        arrPaper_1.add(R.drawable.hiragana_paper_1_fu);
        arrPaper_1.add(R.drawable.hiragana_paper_1_he);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ho);

        arrPaper_1.add(R.drawable.hiragana_paper_1_ma);
        arrPaper_1.add(R.drawable.hiragana_paper_1_mi);
        arrPaper_1.add(R.drawable.hiragana_paper_1_mu);
        arrPaper_1.add(R.drawable.hiragana_paper_1_me);
        arrPaper_1.add(R.drawable.hiragana_paper_1_mo);

        arrPaper_1.add(R.drawable.hiragana_paper_1_ya);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ri);
        arrPaper_1.add(R.drawable.hiragana_paper_1_yu);
        arrPaper_1.add(R.drawable.hiragana_paper_1_re);
        arrPaper_1.add(R.drawable.hiragana_paper_1_yo);

        arrPaper_1.add(R.drawable.hiragana_paper_1_ra);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ri);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ru);
        arrPaper_1.add(R.drawable.hiragana_paper_1_re);
        arrPaper_1.add(R.drawable.hiragana_paper_1_ro);

        arrPaper_1.add(R.drawable.hiragana_paper_1_wa);
        arrPaper_1.add(R.drawable.hiragana_paper_1_i);
        arrPaper_1.add(R.drawable.hiragana_paper_1_u);
        arrPaper_1.add(R.drawable.hiragana_paper_1_e);
        arrPaper_1.add(R.drawable.hiragana_paper_1_wo);

        arrPaper_1.add(R.drawable.hiragana_paper_1_a);
        arrPaper_1.add(R.drawable.hiragana_paper_1_i);
        arrPaper_1.add(R.drawable.hiragana_paper_1_u);
        arrPaper_1.add(R.drawable.hiragana_paper_1_e);
        arrPaper_1.add(R.drawable.hiragana_paper_1_n);

        //arrPaper_2
        arrPaper_2 = new ArrayList<>();

        arrPaper_2.add(R.drawable.hiragana_paper_2_a);
        arrPaper_2.add(R.drawable.hiragana_paper_2_i);
        arrPaper_2.add(R.drawable.hiragana_paper_2_u);
        arrPaper_2.add(R.drawable.hiragana_paper_2_e);
        arrPaper_2.add(R.drawable.hiragana_paper_2_o);

        arrPaper_2.add(R.drawable.hiragana_paper_2_ka);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ki);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ku);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ke);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ko);

        arrPaper_2.add(R.drawable.hiragana_paper_2_sa);
        arrPaper_2.add(R.drawable.hiragana_paper_2_shi);
        arrPaper_2.add(R.drawable.hiragana_paper_2_su);
        arrPaper_2.add(R.drawable.hiragana_paper_2_se);
        arrPaper_2.add(R.drawable.hiragana_paper_2_so);

        arrPaper_2.add(R.drawable.hiragana_paper_2_ta);
        arrPaper_2.add(R.drawable.hiragana_paper_2_chi);
        arrPaper_2.add(R.drawable.hiragana_paper_2_tsu);
        arrPaper_2.add(R.drawable.hiragana_paper_2_te);
        arrPaper_2.add(R.drawable.hiragana_paper_2_to);

        arrPaper_2.add(R.drawable.hiragana_paper_2_na);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ni);
        arrPaper_2.add(R.drawable.hiragana_paper_2_nu);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ne);
        arrPaper_2.add(R.drawable.hiragana_paper_2_no);

        arrPaper_2.add(R.drawable.hiragana_paper_2_ha);
        arrPaper_2.add(R.drawable.hiragana_paper_2_hi);
        arrPaper_2.add(R.drawable.hiragana_paper_2_fu);
        arrPaper_2.add(R.drawable.hiragana_paper_2_he);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ho);

        arrPaper_2.add(R.drawable.hiragana_paper_2_ma);
        arrPaper_2.add(R.drawable.hiragana_paper_2_mi);
        arrPaper_2.add(R.drawable.hiragana_paper_2_mu);
        arrPaper_2.add(R.drawable.hiragana_paper_2_me);
        arrPaper_2.add(R.drawable.hiragana_paper_2_mo);

        arrPaper_2.add(R.drawable.hiragana_paper_2_ya);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ri);
        arrPaper_2.add(R.drawable.hiragana_paper_2_yu);
        arrPaper_2.add(R.drawable.hiragana_paper_2_re);
        arrPaper_2.add(R.drawable.hiragana_paper_2_yo);

        arrPaper_2.add(R.drawable.hiragana_paper_2_ra);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ri);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ru);
        arrPaper_2.add(R.drawable.hiragana_paper_2_re);
        arrPaper_2.add(R.drawable.hiragana_paper_2_ro);

        arrPaper_2.add(R.drawable.hiragana_paper_2_wa);
        arrPaper_2.add(R.drawable.hiragana_paper_2_i);
        arrPaper_2.add(R.drawable.hiragana_paper_2_u);
        arrPaper_2.add(R.drawable.hiragana_paper_2_e);
        arrPaper_2.add(R.drawable.hiragana_paper_2_wo);

        arrPaper_2.add(R.drawable.hiragana_paper_2_a);
        arrPaper_2.add(R.drawable.hiragana_paper_2_i);
        arrPaper_2.add(R.drawable.hiragana_paper_2_u);
        arrPaper_2.add(R.drawable.hiragana_paper_2_e);
        arrPaper_2.add(R.drawable.hiragana_paper_2_n);

        position = getArguments().getInt("position");
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imgWriting);
        GlideDrawableImageViewTarget drawableImageViewTarget1 = new GlideDrawableImageViewTarget(img_paper_1);
        GlideDrawableImageViewTarget drawableImageViewTarget2 = new GlideDrawableImageViewTarget(img_paper_2);
        GlideDrawableImageViewTarget drawableImageViewTarget3 = new GlideDrawableImageViewTarget(img_paper_3);

        Glide.with(getContext())
                .load(arrWriting.get(position))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewTarget);

        Glide.with(getContext())
                .load(arrPaper_1.get(position))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(drawableImageViewTarget1);

        Glide.with(getContext())
                .load(arrPaper_2.get(position))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(drawableImageViewTarget2);

        Glide.with(getContext())
                .load(arrPaper_1.get(position))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(drawableImageViewTarget3);

        /** create animator **/
        ObjectAnimator paperAnim = ObjectAnimator.ofFloat(move_lyt, View.ROTATION_Y, 0,180);
        ObjectAnimator paperUpDown = ObjectAnimator.ofFloat(move_lyt, View.ROTATION_X, -50,50);
        ObjectAnimator bookUpDown = ObjectAnimator.ofFloat(book_lyt, View.ROTATION_X, -50,50);

        /** create propose **/
        Propose propose = new Propose(getContext());
        propose.motionRight.play(paperAnim); 					 /** set right move animator **/
        propose.motionUp.play(paperUpDown,500).with(bookUpDown); /** set UpDown move animator **/
        propose.motionUp.enableFling(false).enableTabUp(false).enableSingleTabUp(false).move(250); // UpDown option
        move_lyt.setOnTouchListener(propose);  					 /** set touch listener **/
        return v;
    }
}
