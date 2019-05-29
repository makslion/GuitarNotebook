package com.maksym.guitarnotebook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


//class for fragment_collection.xml
public class Collection extends Fragment
{
    private View view;
    private TextView collectionTextView;
    private RecyclerView collectionRecycler;

    private SongViewModel songViewModel;
    private List<SongModel> songs;

    private static String strSeparator = "__,__";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_collection, container, false);

        collectionTextView = view.findViewById(R.id.collectionTextView);
        collectionRecycler = view.findViewById(R.id.collectionRecycler);

        songViewModel = ViewModelProviders.of(this).get(SongViewModel.class);
        songs = new ArrayList<>();

        return view;
    }


    @Override
    public void onStart()
    {
        super.onStart();

        SongsAdapter adapter = new SongsAdapter(view.getContext());
        collectionRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));

        songViewModel.getAllSongs().observe(this, new Observer<List<SongEntity>>() {
            @Override
            public void onChanged(@Nullable List<SongEntity> songEntities)
            {
                for (SongEntity songEntity : songEntities)
                {
                    songs.add(new SongModel(
                            songEntity.getSongName(),
                            songEntity.getSongGenre(),
                            songEntity.getSongArtist(),
                            convertStringToArray(songEntity.getSongChords()),
                            songEntity.getSongLyrics(),
                            songEntity.getSongExample()));
                }
                adapter.setSongs(songs);
                collectionRecycler.setAdapter(adapter);

                if (!songs.isEmpty()) collectionTextView.setText("Here your songs");
                else  collectionTextView.setText("No songs in Collection!");
            }
        });
    }



    private static String[] convertStringToArray(String str)
    {
        String[] arr = str.split(strSeparator);
        return arr;
    }
}
