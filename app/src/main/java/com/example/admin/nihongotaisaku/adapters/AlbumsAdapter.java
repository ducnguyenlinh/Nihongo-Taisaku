package com.example.admin.nihongotaisaku.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.nihongotaisaku.R;
import com.example.admin.nihongotaisaku.models.AlbumModel;

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
        holder.tvNameAlbum.setText(albumModel.getNameAlbum());
        holder.tvCount.setText(albumModel.getNumOfImages() + " vocabulary");
        Glide.with(mContext).load(albumModel.getThumbnail()).into(holder.imgThumbnail);
    }

    @Override
    public int getItemCount() {
        return arrAlbum.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder {
        ImageView imgThumbnail;
        TextView tvNameAlbum, tvCount;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.imgThumbnail);
            tvNameAlbum = (TextView) itemView.findViewById(R.id.tvNameAlbum);
            tvCount = (TextView) itemView.findViewById(R.id.tvCount);
        }
    }
}
