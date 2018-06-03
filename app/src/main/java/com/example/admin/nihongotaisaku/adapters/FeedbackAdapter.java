package com.example.admin.nihongotaisaku.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.models.FeedbackModel;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackHoleder>{
    Context mContext;
    ArrayList<FeedbackModel> listFeedbacks;

    public FeedbackAdapter(Context mContext, ArrayList<FeedbackModel> listFeedbacks) {
        this.mContext = mContext;
        this.listFeedbacks = listFeedbacks;
    }

    @Override
    public FeedbackHoleder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_feedbacks, parent, false);
        FeedbackHoleder feedbackHoleder = new FeedbackHoleder(v);
        return feedbackHoleder;
    }

    @Override
    public void onBindViewHolder(FeedbackHoleder holder, int position) {
        holder.tvFeedbackContent.setText(listFeedbacks.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return listFeedbacks.size();
    }

    public class FeedbackHoleder extends RecyclerView.ViewHolder {
        public TextView tvFeedbackContent;

        public FeedbackHoleder(View itemView) {
            super(itemView);
            tvFeedbackContent = (TextView) itemView.findViewById(R.id.tvFeedbackContent);
        }
    }
}
