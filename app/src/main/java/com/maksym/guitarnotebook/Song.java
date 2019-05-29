package com.maksym.guitarnotebook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


//Activity to display song
public class Song extends Fragment
{
    //key should be moved from here
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

    private MenuItem collectionMI;
    private boolean songInCollection;

    private SongViewModel songViewModel;
    private List<SongModel> songs;
    private static String strSeparator = "__,__";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_song, container, false);
        setHasOptionsMenu(true);

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

        songViewModel = ViewModelProviders.of(this).get(SongViewModel.class);
        songs = new ArrayList<>();

        songViewModel.getSong(songName, songArtist).observe(this, new Observer<List<SongEntity>>() {
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

                songInCollection = !songs.isEmpty();
            }
        });

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



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        Log.d("Song", "on create option menu");

        inflater.inflate(R.menu.favorite_menu, menu);

        collectionMI = menu.findItem(R.id.collectionMI);
        setCollectionButton();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.collectionMI)
        {
            collectionButtonActed();
            Log.d("Song", "favorite button action");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void setCollectionButton()
    {
        if(songInCollection)
        {
            collectionMI.setTitle("Remove from Collection");
            collectionMI.setIcon(R.drawable.ic_collection_filled);


        }
        else
        {
            collectionMI.setTitle("Add to Collection");
            collectionMI.setIcon(R.drawable.ic_collection_empty);
        }
    }



    private void collectionButtonActed()
    {
        if(songInCollection)
        {
            songViewModel.deleeteSong(new SongEntity(
                    song.getSongArtist(),
                    convertArrayToString(song.getSongChords()),
                    song.getSongExample(),
                    song.getSongGenre(),
                    song.getSongLyrics(),
                    song.getSongName()));

            songInCollection = false;
            Toast toast = Toast.makeText(getContext(), "Removed from collection", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            songViewModel.insert(new SongEntity(
                    song.getSongArtist(),
                    convertArrayToString(song.getSongChords()),
                    song.getSongExample(),
                    song.getSongGenre(),
                    song.getSongLyrics(),
                    song.getSongName()));

            songInCollection = true;
            Toast toast = Toast.makeText(getContext(), "Added to collection", Toast.LENGTH_LONG);
            toast.show();
        }

        setCollectionButton();
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
        if (!songInCollection)
        {
            firebaseSongs.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot songSnapshot : dataSnapshot.getChildren()) {
                        //Log.d("Song", "Gathering song");
                        SongModel temp = songSnapshot.getValue(SongModel.class);

                        if (temp.getSongName().equals(songName) && temp.getSongArtist().equals(songArtist)) {
                            song = temp;
                            //Log.d("Song", "Gathered song name "+ song.getSongName());
                            loadChords();
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else
            {
                song = songs.get(0); //result of query should be single song. If no - load first occurrence. More then one if there is songs with the same name and the dame artist
                loadChords(); //keep inside brackets. If song not in collection it takes time to load. If in collection by this tine already loaded
            }


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
                //loadChords();
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
            if (chord.endsWith("b"))
                chord = Character.toString(chord.charAt(0));

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



    private static String convertArrayToString(List<String> stringList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : stringList) {
            stringBuilder.append(str).append(strSeparator);
        }

        // Remove last separator
        stringBuilder.setLength(stringBuilder.length() - strSeparator.length());

        return stringBuilder.toString();
    }



    private static String[] convertStringToArray(String str)
    {
        String[] arr = str.split(strSeparator);
        return arr;
    }

}
