package com.poojanshah.assignment_two;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;
import com.poojanshah.assignment_two.model.Music;
import com.poojanshah.assignment_two.model.Result;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        String previewUrl = music.getResults().get(position).getPreviewUrl();
        holder.tvArtistName.setText(trackName);
        holder.tvCollectionName.setText(collectionName);
        holder.tvTrackName.setText(trackName);
        holder.tvTrackPrice.setText(String.valueOf(trackPrice));
        holder.resultCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(isInternetAvailable()){
                        Uri myUri = Uri.parse(previewUrl);
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                        intent.setDataAndType(myUri, "audio/*");
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context,"You are not connected to the internet",Toast.LENGTH_LONG);
                    }
                }
                catch (Exception exc){
                    Log.i("Error", exc.getMessage());
                    Log.i("Error", String.valueOf(exc.getCause()));
                }
            }
        });
        try {
            Glide
                    .with(context)
                    .load(artworkUl60)
                    .into(holder.ivArtwork);
        }
        catch(Exception e){
            Log.i("Error", e.getMessage());
            Log.i("Error", String.valueOf(e.getCause()));
        }
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public int getItemCount() {
        return music.getResults().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTrackName, tvArtistName, tvTrackPrice, tvCollectionName;
        ImageView ivArtwork;
        CardView resultCard;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvArtistName = (TextView)itemView.findViewById(R.id.tvArtistName);
            tvTrackPrice = (TextView)itemView.findViewById(R.id.tvTrackPrice);
            tvTrackName = (TextView)itemView.findViewById(R.id.tvTrackName);
            tvCollectionName = (TextView)itemView.findViewById(R.id.tvCollectionName);
            ivArtwork = (ImageView) itemView.findViewById(R.id.ivArtwork);
            resultCard = (CardView) itemView.findViewById(R.id.resultCard);

        }
    }
}

