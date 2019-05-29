package com.maksym.guitarnotebook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Fragment where user can chose between songs list, Genres and Artists
public class SongSearch extends Fragment
{
    private DatabaseReference firebaseSongs;

    private CardView byNameCard;
    private CardView byGenreCard;
    private CardView byArtistCard;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_song_search, container, false);
        Log.d("SongSearch", "on create view");

        byNameCard = view.findViewById(R.id.byNameCard);
        byGenreCard = view.findViewById(R.id.byGenreCard);
        byArtistCard = view.findViewById(R.id.byArtistCard);

        byNameCard.setOnClickListener(v -> byNamePressed());
        byGenreCard.setOnClickListener(v -> byGenrePressed());
        byArtistCard.setOnClickListener(v -> byArtistPressed());

        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("SongSearch", "on create");

        firebaseSongs = FirebaseDatabase.getInstance().getReference("Songs");
        //putSampleData();
    }



    @Override
    public void onStart()
    {
        super.onStart();
        Log.d("SongSearch", "on start");

    }



    public void byNamePressed()
    {
        Intent intent = new Intent(getContext(), Songs.class);
        startActivity(intent);
    }


    public void byGenrePressed()
    {
        Intent intent = new Intent(getContext(), Genres.class);
        startActivity(intent);
    }


    public void byArtistPressed()
    {
        Intent intent = new Intent(getContext(), Artists.class);
        startActivity(intent);
    }


    private void putSampleData()
    {
        String [][] sampleData = SongsSampleData.getSampleSongs();

        for (int i = 0; i < sampleData.length; i +=2)
        {
            SongModel song = new SongModel(
                    sampleData[i][0],   //song name
                    sampleData[i][1],   //song genre
                    sampleData[i][2],   //song artist
                    sampleData[i+1],    //chords String[]
                    sampleData[i][3],   //song lyrics
                    sampleData[i][4]);  //song example

            String key = firebaseSongs.push().getKey();

            firebaseSongs.child(key).setValue(song.toMap());
        }
    }
}
