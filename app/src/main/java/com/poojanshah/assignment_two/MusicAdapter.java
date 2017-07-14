package com.poojanshah.assignment_two;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        String a = music.getResults().get(position).getTrackName();
        holder.tvTitle.setText(a);
    }

    @Override
    public int getItemCount() {
        return music.getResults().size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.tvTitle);

        }
    }
}

