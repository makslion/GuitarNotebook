package com.maksym.guitarnotebook;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ArtistsAdapter  extends RecyclerView.Adapter <ArtistsAdapter.ArtistViewHolder>
{
    public class ArtistViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView songArtist;

        public ArtistViewHolder (@NonNull View itemView)
        {
            super(itemView);

            songArtist = itemView.findViewById(R.id.artistListitem);
        }
    }


    private final LayoutInflater inflater;
    private List<String> artists;

    public ArtistsAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ArtistsAdapter.ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = inflater.inflate(R.layout.artist_listitem, viewGroup, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView songArtist = v.findViewById(R.id.artistListitem);

                Intent it = new Intent(v.getContext(), Songs.class);

                it.putExtra(Songs.EXTRA_SONG_ARTIST_FILTER, songArtist.getText());

                v.getContext().startActivity(it);
            }
        });

        return new ArtistsAdapter.ArtistViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArtistsAdapter.ArtistViewHolder artistViewHolder, int i)
    {
        String current = artists.get(i);
        artistViewHolder.songArtist.setText(current);
    }


    void setSongs(List<String> artists)
    {
        this.artists = artists;
    }



    @Override
    public int getItemCount()
    {
        if (artists != null)
            return artists.size();
        else
            return 0;
    }
}