package com.example.admin.nihongotaisaku.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.models.AlphabetModel;

import java.util.ArrayList;

public class AlphabetImageAdapter extends RecyclerView.Adapter<AlphabetImageAdapter.HiraganaHolder>{
    Activity context;
    ArrayList<AlphabetModel> arrAlph;
    private OnItemClick onItemClick;
    AlphabetModel element = new AlphabetModel();

    public AlphabetImageAdapter() {
    }

    public AlphabetImageAdapter(Activity context, ArrayList<AlphabetModel> arrAlph, int classify) {
        this.context = context;
        this.arrAlph = arrAlph;
        element.setJapanese(""); element.setSpell("");
        element.setImage_compare(""); element.setImage_writing("");
        if (classify == 0 || classify == 1){
            arrAlph.add(36, element);
            arrAlph.add(38, element);
            arrAlph.add(46, element);
            arrAlph.add(47, element);
            arrAlph.add(48, element);
            arrAlph.add(50, element);
            arrAlph.add(51, element);
            arrAlph.add(52, element);
            arrAlph.add(53, element);
        } else if (classify == 2 || classify == 3){
            arrAlph.add(26, element);
            arrAlph.add(28, element);
            arrAlph.add(31, element);
            arrAlph.add(33, element);
            arrAlph.add(36, element);
            arrAlph.add(38, element);
            arrAlph.add(41, element);
            arrAlph.add(43, element);
            arrAlph.add(46, element);
            arrAlph.add(48, element);
            arrAlph.add(51, element);
            arrAlph.add(53, element);
            arrAlph.add(56, element);
            arrAlph.add(58, element);
            arrAlph.add(61, element);
            arrAlph.add(63, element);
            arrAlph.add(66, element);
            arrAlph.add(68, element);
            arrAlph.add(71, element);
            arrAlph.add(73, element);
            arrAlph.add(76, element);
            arrAlph.add(78, element);
        }

    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    @Override
    public HiraganaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hiragana, parent, false);
        HiraganaHolder hiraganaHolder = new HiraganaHolder(view);
        return hiraganaHolder;
    }

    @Override
    public void onBindViewHolder(final HiraganaHolder holder, final int position) {
        holder.tvJapanese.setText(arrAlph.get(position).getJapanese());
        holder.tvSpell.setText(arrAlph.get(position).getSpell());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrAlph.size();
    }

    public static class HiraganaHolder extends RecyclerView.ViewHolder {
        public TextView tvJapanese;
        public TextView tvSpell;
        public CardView cardView;
        public HiraganaHolder(View itemView) {
            super(itemView);
            tvJapanese = (TextView) itemView.findViewById(R.id.japanese);
            tvSpell = (TextView) itemView.findViewById(R.id.spell);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
