package com.maksym.guitarnotebook;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Songs extends AppCompatActivity
{
    private DatabaseReference firebaseSongs;
    private List<SongModel> songs;
    private TextView [] songsLabels;
    private TextView [] dialogLabels;
    private RecyclerView [] songsRecyclers;
    private ScrollView scrollView;

    public static final String EXTRA_SONG_NAME = "ExtraSongName";
    public static final String EXTRA_SONG_ARTIST = "ExtraSongArtist";
    public static final String EXTRA_SONG_ARTIST_FILTER = "SongArtistFilter";
    public static final String EXTRA_SONG_GENRE_FILTER = "SongsGenreFilter";

    //things for querying
    private Intent incomingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        scrollView = findViewById(R.id.songsScrollView);

        firebaseSongs = FirebaseDatabase.getInstance().getReference("Songs");
        songs = new ArrayList<>();
        incomingIntent = getIntent();

        populateLabels();


        //display up button
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Songs");
        }
    }


    @Override
    protected void onStart()
    {
        super.onStart();

        ValueEventListener listener = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                songs.clear();

                for (DataSnapshot songSnapshot : dataSnapshot.getChildren())
                {
                    //Log.d("Songs", "creating song");
                    SongModel song = songSnapshot.getValue(SongModel.class);

                    //Log.d("Songs", "new song "+songSnapshot.getValue(SongModel.class).getSongName());

                    //Log.d("Songs", "Adding song");
                    songs.add(song);
                }

                populateRecyclers();
                disableEmpty();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        };


        if (incomingIntent.hasExtra(EXTRA_SONG_ARTIST_FILTER))
        {
            Query query = firebaseSongs.
                    orderByChild("SongArtist").
                    equalTo(incomingIntent.getStringExtra(EXTRA_SONG_ARTIST_FILTER));

            query.addListenerForSingleValueEvent(listener);
        }
        else if (incomingIntent.hasExtra(EXTRA_SONG_GENRE_FILTER))
        {
            Query query = firebaseSongs.
                    orderByChild("SongGenre").
                    equalTo(incomingIntent.getStringExtra(EXTRA_SONG_GENRE_FILTER));

            query.addListenerForSingleValueEvent(listener);
        }
        else    //no filter
            firebaseSongs.addValueEventListener(listener);



    }

    //implement up button action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }



    private void populateRecyclers()
    {
        songsRecyclers = new RecyclerView[]{
                findViewById(R.id.numberSongsRecycler),
                findViewById(R.id.aSongsRecycler),
                findViewById(R.id.bSongsRecycler),
                findViewById(R.id.cSongsRecycler),
                findViewById(R.id.dSongsRecycler),
                findViewById(R.id.eSongsRecycler),
                findViewById(R.id.fSongsRecycler),
                findViewById(R.id.gSongsRecycler),
                findViewById(R.id.hSongsRecycler),
                findViewById(R.id.iSongsRecycler),
                findViewById(R.id.jSongsRecycler),
                findViewById(R.id.kSongsRecycler),
                findViewById(R.id.lSongsRecycler),
                findViewById(R.id.mSongsRecycler),
                findViewById(R.id.nSongsRecycler),
                findViewById(R.id.oSongsRecycler),
                findViewById(R.id.pSongsRecycler),
                findViewById(R.id.qSongsRecycler),
                findViewById(R.id.rSongsRecycler),
                findViewById(R.id.sSongsRecycler),
                findViewById(R.id.tSongsRecycler),
                findViewById(R.id.uSongsRecycler),
                findViewById(R.id.vSongsRecycler),
                findViewById(R.id.wSongsRecycler),
                findViewById(R.id.xSongsRecycler),
                findViewById(R.id.ySongsRecycler),
                findViewById(R.id.zSongsRecycler)};

        for (int i = 0; i < songsLabels.length; i++)
        {
            SongsAdapter adatper = new SongsAdapter(this);

            songsRecyclers[i].setAdapter(adatper);
            songsRecyclers[i].setLayoutManager(new LinearLayoutManager(this));

            setAdapterItems(adatper, i);
        }
    }




    // populates recycler with songs, that start on passed i (char from alphabet)
    private void setAdapterItems (SongsAdapter adapter, int position)
    {

        List<SongModel> filteredSongs = new ArrayList<>();
        for (SongModel song : songs)
        {
            if (song.getSongName().charAt(0) == songsLabels[position].getText().charAt(0))
            {
               filteredSongs.add(song);
            }
        }

        adapter.setSongs(filteredSongs);
    }




    private void populateLabels()
    {
        songsLabels = new TextView[]{
                findViewById(R.id.numberSongsLabel),
                findViewById(R.id.aSongsLabel),
                findViewById(R.id.bSongsLabel),
                findViewById(R.id.cSongsLabel),
                findViewById(R.id.dSongsLabel),
                findViewById(R.id.eSongsLabel),
                findViewById(R.id.fSongsLabel),
                findViewById(R.id.gSongsLabel),
                findViewById(R.id.hSongsLabel),
                findViewById(R.id.iSongsLabel),
                findViewById(R.id.jSongsLabel),
                findViewById(R.id.kSongsLabel),
                findViewById(R.id.lSongsLabel),
                findViewById(R.id.mSongsLabel),
                findViewById(R.id.nSongsLabel),
                findViewById(R.id.oSongsLabel),
                findViewById(R.id.pSongsLabel),
                findViewById(R.id.qSongsLabel),
                findViewById(R.id.rSongsLabel),
                findViewById(R.id.sSongsLabel),
                findViewById(R.id.tSongsLabel),
                findViewById(R.id.uSongsLabel),
                findViewById(R.id.vSongsLabel),
                findViewById(R.id.wSongsLabel),
                findViewById(R.id.xSongsLabel),
                findViewById(R.id.ySongsLabel),
                findViewById(R.id.zSongsLabel)
        };
    }



    //disable labels that has empty recyclers under them
    private void disableEmpty()
    {
        for(int i = 0; i < songsRecyclers.length; i++)
        {
            if (songsRecyclers[i].getAdapter().getItemCount() == 0)
            {
                songsLabels[i].setVisibility(View.GONE);
            }
            else songsLabels[i].setOnClickListener(v -> openDialog());
        }
    }



    private void openDialog()
    {
        Log.d("Songs", "ATTEMPT TO OPEN DIALOG");
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.songs_dialog);

        dialogLabels = new TextView[]{
                dialog.findViewById(R.id.numberDialogLabel),
                dialog.findViewById(R.id.aDialogLabel),
                dialog.findViewById(R.id.bDialogLabel),
                dialog.findViewById(R.id.cDialogLabel),
                dialog.findViewById(R.id.dDialogLabel),
                dialog.findViewById(R.id.eDialogLabel),
                dialog.findViewById(R.id.fDialogLabel),
                dialog.findViewById(R.id.gDialogLabel),
                dialog.findViewById(R.id.hDialogLabel),
                dialog.findViewById(R.id.iDialogLabel),
                dialog.findViewById(R.id.gDialogLabel),
                dialog.findViewById(R.id.kDialogLabel),
                dialog.findViewById(R.id.lDialogLabel),
                dialog.findViewById(R.id.mDialogLabel),
                dialog.findViewById(R.id.nDialogLabel),
                dialog.findViewById(R.id.oDialogLabel),
                dialog.findViewById(R.id.pDialogLabel),
                dialog.findViewById(R.id.qDialogLabel),
                dialog.findViewById(R.id.rDialogLabel),
                dialog.findViewById(R.id.sDialogLabel),
                dialog.findViewById(R.id.tDialogLabel),
                dialog.findViewById(R.id.uDialogLabel),
                dialog.findViewById(R.id.vDialogLabel),
                dialog.findViewById(R.id.wDialogLabel),
                dialog.findViewById(R.id.xDialogLabel),
                dialog.findViewById(R.id.yDialogLabel),
                dialog.findViewById(R.id.zDialogLabel)};

        for (int i = 0; i < dialogLabels.length; i++)
        {
            if (songsRecyclers[i].getAdapter().getItemCount() != 0)
            {
                dialogLabels[i].setOnClickListener(e ->
                {
                    scrollToLabel(e.getId());
                    dialog.dismiss();
                });
            }
            else
                dialogLabels[i].setTextColor(getResources().getColor(R.color.colorInActive));
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();

        Log.d("Songs", "Dialog shown");
    }



    private void scrollToLabel(int id)
    {
        for (int i = 0; i < dialogLabels.length; i++)
        {
            if ((id == dialogLabels[i].getId()))
            {
                Log.d("Songs", songsLabels[i].getText().toString());

                int vLeft = songsLabels[i].getLeft();
                int vRight = songsLabels[i].getRight();
                int sWidth = songsLabels[i].getWidth();
                int vTop = songsLabels[i].getTop();
                scrollView.smoothScrollTo(((vLeft + vRight - sWidth) / 2), vTop);
            }
        }
    }
}
