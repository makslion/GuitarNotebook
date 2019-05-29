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


//adapter for recyclers in Genres
public class GenresAdapter extends RecyclerView.Adapter <GenresAdapter.GenresViewHolder>
{
    public class GenresViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView songGenre;

        public GenresViewHolder (@NonNull View itemView)
        {
            super(itemView);

            songGenre = itemView.findViewById(R.id.genresListitem);
        }
    }


    private final LayoutInflater inflater;
    private List<String> genres;

    public GenresAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GenresAdapter.GenresViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View itemView = inflater.inflate(R.layout.genres_listitem, viewGroup, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView songGenre = v.findViewById(R.id.genresListitem);

                Intent it = new Intent(v.getContext(), Songs.class);

                it.putExtra(Songs.EXTRA_SONG_GENRE_FILTER, songGenre.getText());

                v.getContext().startActivity(it);
            }
        });

        return new GenresAdapter.GenresViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(GenresAdapter.GenresViewHolder genresViewHolder, int i)
    {
        String current = genres.get(i);
        genresViewHolder.songGenre.setText(current);
    }


    void setSongs(List<String> genres)
    {
        this.genres = genres;
    }



    @Override
    public int getItemCount()
    {
        if (genres != null)
            return genres.size();
        else
            return 0;
    }
}