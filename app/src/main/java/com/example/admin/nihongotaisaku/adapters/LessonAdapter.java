package com.example.admin.nihongotaisaku.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.models.LessonModel;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonHolder>{
    ArrayList<LessonModel> mListLesson = new ArrayList<>();
    Activity mContext;
    private OnItemClick onItemClick;

    public LessonAdapter(Activity mContext, ArrayList<LessonModel> mListLesson) {
        this.mContext = mContext;
        this.mListLesson = mListLesson;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    @Override
    public LessonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lesson, parent, false);
        return new LessonHolder(v);
    }

    @Override
    public void onBindViewHolder(final LessonHolder holder, final int position) {
        holder.tvLesson.setText(mListLesson.get(position).getContent());
        holder.cardLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListLesson.size();
    }

    public static class LessonHolder extends RecyclerView.ViewHolder{
        public TextView tvLesson;
        public CardView cardLesson;

        public LessonHolder(View itemView) {
            super(itemView);
            tvLesson = (TextView) itemView.findViewById(R.id.tvLesson);
            cardLesson = (CardView) itemView.findViewById(R.id.cardLesson);
        }
    }
}
