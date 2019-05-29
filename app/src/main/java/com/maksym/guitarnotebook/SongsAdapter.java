package com.maksym.guitarnotebook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


//Adapter for list of songs in Songs
public class SongsAdapter extends RecyclerView.Adapter <SongsAdapter.SongViewHolder>
{
    public class SongViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView songNameListitem;
        private final TextView songArtistListitem;

        public SongViewHolder(@NonNull View itemView)
        {
            super(itemView);

            songNameListitem = itemView.findViewById(R.id.songNameListitem);
            songArtistListitem = itemView.findViewById(R.id.songArtistListitem);
        }
    }


    private final LayoutInflater inflater;
    private List<SongModel> songList;


    public SongsAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = inflater.inflate(R.layout.song_listitem, viewGroup, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView songName = v.findViewById(R.id.songNameListitem);
                TextView songArtist = v.findViewById(R.id.songArtistListitem);

                Intent it = new Intent(v.getContext(), Drawer_layout.class);

                it.putExtra(MainActivity.EXTRA_MESSAGE, R.integer.nav_song);
                it.putExtra(Songs.EXTRA_SONG_NAME, songName.getText());
                it.putExtra(Songs.EXTRA_SONG_ARTIST, songArtist.getText());

                v.getContext().startActivity(it);
            }
        });

        return new SongsAdapter.SongViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(SongViewHolder songViewHolder, int i)
    {
        SongModel current = songList.get(i);
        songViewHolder.songNameListitem.setText(current.getSongName());
        songViewHolder.songArtistListitem.setText(current.getSongArtist());
    }


    void setSongs(List<SongModel> songs)
    {
        songList = songs;

        for (SongModel song : songs)
            Log.d("SongsAdapter", "Setting songs "+song.getSongName());
    }



    @Override
    public int getItemCount()
    {
        if (songList != null)
            return songList.size();
        else
            return 0;
    }
}
