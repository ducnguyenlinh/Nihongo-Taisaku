package com.example.admin.nihongotaisaku.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.models.VocabularyModel;

import java.util.ArrayList;

public class ListVocabularyAdapter extends RecyclerView.Adapter<ListVocabularyAdapter.VocabularyViewHolder>{
    ArrayList<VocabularyModel> arrVocabularies;
    Context mContext;
    private OnItemClick onItemClick;

    public ListVocabularyAdapter(Context mContext, ArrayList<VocabularyModel> arrVocabularies) {
        this.arrVocabularies = arrVocabularies;
        this.mContext = mContext;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    @Override
    public VocabularyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocabulary, parent, false);
        VocabularyViewHolder vocabularyViewHolder = new VocabularyViewHolder(v);

        return vocabularyViewHolder;
    }

    @Override
    public void onBindViewHolder(final VocabularyViewHolder holder, final int position) {
        holder.tvVocabulary.setText(arrVocabularies.get(position).getJapanese());
        holder.tvPosition.setText(position + 1 + "");
        holder.cardVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrVocabularies.size();
    }

    public static class VocabularyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvVocabulary;
        public TextView tvPosition;
        public CardView cardVocabulary;

        public VocabularyViewHolder(View itemView) {
            super(itemView);

            tvVocabulary = (TextView) itemView.findViewById(R.id.tvVocabulary);
            tvPosition = (TextView) itemView.findViewById(R.id.tvPositon);
            cardVocabulary = (CardView) itemView.findViewById(R.id.cardVocabulary);
        }
    }
}
