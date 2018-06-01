package com.example.admin.nihongotaisaku.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.models.AlbumModel;

import java.io.IOException;
import java.util.ArrayList;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder> {
    private Context mContext;
    private ArrayList<AlbumModel> arrAlbum;

    public AlbumsAdapter(Context mContext, ArrayList<AlbumModel> arrAlbum) {
        this.mContext = mContext;
        this.arrAlbum = arrAlbum;
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_album, parent, false);
        return new AlbumViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AlbumsAdapter.AlbumViewHolder holder, int position) {
        AlbumModel albumModel = arrAlbum.get(position);
        holder.tvJapanese.setText(albumModel.getJapanese());
        holder.tvMean.setText(albumModel.getMean());
        Glide.with(mContext)
                .load(albumModel.getImage())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgThumbnail);
        holder.imgSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(albumModel.getSound());
                    mediaPlayer.prepare();
                    mediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrAlbum.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbnail, imgSpeaker;
        TextView tvJapanese, tvMean;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            imgSpeaker = (ImageView) itemView.findViewById(R.id.imgSpeaker);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.imgThumbnail);
            tvJapanese = (TextView) itemView.findViewById(R.id.tvJapanese);
            tvMean = (TextView) itemView.findViewById(R.id.tvMean);
        }
    }
}
