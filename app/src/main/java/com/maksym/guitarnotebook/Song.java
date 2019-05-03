package com.maksym.guitarnotebook;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Song extends Fragment
{

    public static final String API_KEY = "AIzaSyCWnbijsmEsQX6RuqBZ4KSkaqANl5JNZms";
    private String video_id;
    private SongModel song;
    private DatabaseReference firebaseSongs;

    private String songName;
    private String songArtist;


    private TextView songNameLabel, songArtistLabel, songLyrics;
    private RecyclerView chordRecycler;
    private SongChordsAdatper adapter;
    private List<ChordEntity> chordEntities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        //default
        video_id = "pwUu5MrARtU";
        songName = "The Curse";
        songArtist = "Anges Obel";
        song = new SongModel();
        chordEntities = new ArrayList<>();

        //database setup
        firebaseSongs = FirebaseDatabase.getInstance().getReference("Songs");

        Intent intent = getActivity().getIntent();
        if (intent.hasExtra(Songs.EXTRA_SONG_ARTIST) &&
                intent.hasExtra(Songs.EXTRA_SONG_NAME))
        {
            songName = intent.getStringExtra(Songs.EXTRA_SONG_NAME);
            songArtist = intent.getStringExtra(Songs.EXTRA_SONG_ARTIST);

        }

        findReferences(view);

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d("Song", "on start");
        loadSong();
    }



    private void findReferences(View view)
    {
        songNameLabel = view.findViewById(R.id.songNameLabel);
        songArtistLabel = view.findViewById(R.id.songArtistLabel);
        songLyrics = view.findViewById(R.id.songLyrics);
        chordRecycler = view.findViewById(R.id.chordsRecycler);
        chordRecycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new SongChordsAdatper(view.getContext());
    }


    private void setContent()
    {
        songNameLabel.setText(song.getSongName());
        songArtistLabel.setText(song.getSongArtist());
        songLyrics.setText(song.getSongLyrics());
        video_id = song.getSongExample();
    }


    private void loadSong()
    {
        firebaseSongs.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot songSnapshot : dataSnapshot.getChildren())
                {
                    //Log.d("Song", "Gathering song");
                    SongModel temp = songSnapshot.getValue(SongModel.class);

                    if (temp.getSongName().equals(songName) && temp.getSongArtist().equals(songArtist))
                    {
                        song = temp;
                        //Log.d("Song", "Gathered song name "+ song.getSongName());
                        loadChords();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });
    }



    private void loadVideo()
    {
        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.youtube_layout, youTubePlayerFragment).commit();

        youTubePlayerFragment.initialize(API_KEY, new YouTubePlayer.OnInitializedListener()
        {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored)
            {
                if (!wasRestored)
                {
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                    player.cueVideo(video_id);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult error)
            {
                // YouTube error
                String errorMessage = error.toString();
                Log.d("errorMessage:", errorMessage);
            }
        });
    }



    private void loadChords()
    {
        Log.d("Song", "Loading chords");
        ChordViewModel chordViewModel = ViewModelProviders.of(this).get(ChordViewModel.class);

        for (String chord : song.getSongChords())
        {
            Log.d("Song", "Looking for chord "+chord);
            chordViewModel.getChord(chord).observe(this, chords ->
                    // Update the cached copy of the chords in the adapter.
            {
                chordEntities.add(chords.get(0));
                Log.d("Song", "Chord found! "+chords.get(0).getChordName());

                if (chordEntities.size() == song.getSongChords().size())
                    setRecycler();
            });
        }


        setContent();
        loadVideo();
    }



    private void setRecycler()
    {
        Log.d("Song", "Setting recycler");

        chordRecycler.setAdapter(adapter);
        adapter.setChords(chordEntities);
    }

}
