package com.poojanshah.assignment_two;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.poojanshah.assignment_two.model.Music;

/**
 * Created by Poojan on 15/07/2017.
 */

class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {
    Music music;
    Context context;

    public MusicAdapter(Music music, Context context) {
        this.music = music;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_row,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String trackName = music.getResults().get(position).getTrackName();
        String collectionName = music.getResults().get(position).getCollectionName();
        String artworkUl60 = music.getResults().get(position).getArtworkUrl60();
        Double trackPrice = music.getResults().get(position).getTrackPrice();
        holder.tvArtistName.setText(trackName);
        holder.tvCollectionName.setText(collectionName);
        holder.tvTrackName.setText(trackName);
        holder.tvTrackPrice.setText(String.valueOf(trackPrice));
        Glide
                .with(context)
                .load(artworkUl60)
                .into(holder.ivArtwork);
    }

    @Override
    public int getItemCount() {
        return music.getResults().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTrackName, tvArtistName, tvTrackPrice, tvCollectionName;
        ImageView ivArtwork;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvArtistName = (TextView)itemView.findViewById(R.id.tvArtistName);
            tvTrackPrice = (TextView)itemView.findViewById(R.id.tvTrackPrice);
            tvTrackName = (TextView)itemView.findViewById(R.id.tvTrackName);
            tvCollectionName = (TextView)itemView.findViewById(R.id.tvCollectionName);
            ivArtwork = (ImageView) itemView.findViewById(R.id.ivArtwork);

        }
    }
}

