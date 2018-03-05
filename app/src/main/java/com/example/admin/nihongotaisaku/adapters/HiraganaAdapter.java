package com.example.admin.nihongotaisaku.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.models.Syllable;

import java.util.ArrayList;

public class HiraganaAdapter extends RecyclerView.Adapter<HiraganaAdapter.HiraganaHolder>{
    Activity context;
    ArrayList<Syllable> arrSyl;
    private OnItemClick onItemClick;

    public HiraganaAdapter(Activity context, ArrayList<Syllable> arrSyl) {
        this.context = context;
        this.arrSyl = arrSyl;
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
        holder.tvNihongo.setText(arrSyl.get(position).getNihongo());
        holder.tvRomanji.setText(arrSyl.get(position).getRomaji());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrSyl.size();
    }

    public static class HiraganaHolder extends RecyclerView.ViewHolder {
        public TextView tvNihongo;
        public TextView tvRomanji;
        public CardView cardView;
        public HiraganaHolder(View itemView) {
            super(itemView);
            tvNihongo = (TextView) itemView.findViewById(R.id.nihongo);
            tvRomanji = (TextView) itemView.findViewById(R.id.romanji);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
