package com.example.admin.nihongotaisaku.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.models.HistoryModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder>{
    Activity mContext;
    ArrayList<HistoryModel> arrHistories;
    public static final SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz");

    public HistoryAdapter(Activity mContext, ArrayList<HistoryModel> arrHistories) {
        this.mContext = mContext;
        this.arrHistories = arrHistories;
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false);
         HistoryHolder historyHolder = new HistoryHolder(view);
        return historyHolder;
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        holder.tvHistory.setText(arrHistories.get(position).getObject_content());
        Date date = null;
        try {
            date = inputFormat.parse(arrHistories.get(position).getCreated_at());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String niceDateStr = (String) DateUtils.getRelativeTimeSpanString(date.getTime() ,
                Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS);
        holder.tvCreateAtHistory.setText(niceDateStr);
    }

    @Override
    public int getItemCount() {
        return arrHistories.size();
    }

    public static class HistoryHolder extends RecyclerView.ViewHolder{
        public TextView tvHistory;
        public TextView tvCreateAtHistory;

        public HistoryHolder(View itemView) {
            super(itemView);

            tvHistory = (TextView) itemView.findViewById(R.id.tvHistory);
            tvCreateAtHistory = (TextView) itemView.findViewById(R.id.tvCreateAtHistory);
        }
    }
}
