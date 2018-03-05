package com.example.admin.nihongotaisaku.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.nihongotaisaku.R;

public class VocabularyAdapter extends RecyclerView.Adapter<VocabularyAdapter.VocabularyViewHolder>{

    @Override
    public VocabularyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vocabulary, parent, false);
        VocabularyViewHolder vocabularyViewHolder = new VocabularyViewHolder(v);

        return vocabularyViewHolder;
    }

    @Override
    public void onBindViewHolder(VocabularyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class VocabularyViewHolder extends RecyclerView.ViewHolder {

        public VocabularyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
