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

//list of all artists. Used to implement filter to Songs
public class Artists extends AppCompatActivity
{
    private DatabaseReference firebaseSongs;
    private TextView[] artistsLabels;
    private TextView [] dialogLabels;
    private RecyclerView[] artistsRecyclers;
    private ScrollView scrollView;
    private List<String> artists;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        //display up button
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Artists");
        }

        populateLabels();

        scrollView = findViewById(R.id.artistsScrollView);
        artists = new ArrayList<>();
        firebaseSongs = FirebaseDatabase.getInstance().getReference("Songs");
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
                artists.clear();

                for (DataSnapshot songSnapshot : dataSnapshot.getChildren())
                {
                    //Log.d("Songs", "creating song");
                    SongModel song = songSnapshot.getValue(SongModel.class);

                    //Log.d("Songs", "new song "+songSnapshot.getValue(SongModel.class).getSongName());


                    if (!artists.contains(song.getSongArtist()))
                    {
                        artists.add(song.getSongArtist());
                        //Log.d("Artists", "Adding artist "+song.getSongArtist());
                    }
                }

                populateRecyclers();
                disableEmpty();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        };

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



    private void openDialog()
    {
        Log.d("Artists", "ATTEMPT TO OPEN DIALOG");
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.songs_dialog);   //using from songs because they are equal

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
            if (artistsRecyclers[i].getAdapter().getItemCount() != 0)
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

        Log.d("Artists", "Dialog shown");
    }



    private void scrollToLabel(int id)
    {
        for (int i = 0; i < dialogLabels.length; i++)
        {
            if ((id == dialogLabels[i].getId()))
            {
                Log.d("Artists", artistsLabels[i].getText().toString());

                int vLeft = artistsLabels[i].getLeft();
                int vRight = artistsLabels[i].getRight();
                int sWidth = artistsLabels[i].getWidth();
                int vTop = artistsLabels[i].getTop();
                scrollView.smoothScrollTo(((vLeft + vRight - sWidth) / 2), vTop);
            }
        }
    }



    private void populateLabels()
    {
        artistsLabels = new TextView[]{
                findViewById(R.id.numberArtistsLabel),
                findViewById(R.id.aArtistsLabel),
                findViewById(R.id.bArtistsLabel),
                findViewById(R.id.cArtistsLabel),
                findViewById(R.id.dArtistsLabel),
                findViewById(R.id.eArtistsLabel),
                findViewById(R.id.fArtistsLabel),
                findViewById(R.id.gArtistsLabel),
                findViewById(R.id.hArtistsLabel),
                findViewById(R.id.iArtistsLabel),
                findViewById(R.id.jArtistsLabel),
                findViewById(R.id.kArtistsLabel),
                findViewById(R.id.lArtistsLabel),
                findViewById(R.id.mArtistsLabel),
                findViewById(R.id.nArtistsLabel),
                findViewById(R.id.oArtistsLabel),
                findViewById(R.id.pArtistsLabel),
                findViewById(R.id.qArtistsLabel),
                findViewById(R.id.rArtistsLabel),
                findViewById(R.id.sArtistsLabel),
                findViewById(R.id.tArtistsLabel),
                findViewById(R.id.uArtistsLabel),
                findViewById(R.id.vArtistsLabel),
                findViewById(R.id.wArtistsLabel),
                findViewById(R.id.xArtistsLabel),
                findViewById(R.id.yArtistsLabel),
                findViewById(R.id.zArtistsLabel)};
    }



    private void populateRecyclers()
    {
        artistsRecyclers = new RecyclerView[]{
                findViewById(R.id.numberArtistRecycler),
                findViewById(R.id.aArtistRecycler),
                findViewById(R.id.bArtistRecycler),
                findViewById(R.id.cArtistRecycler),
                findViewById(R.id.dArtistRecycler),
                findViewById(R.id.eArtistRecycler),
                findViewById(R.id.fArtistRecycler),
                findViewById(R.id.gArtistRecycler),
                findViewById(R.id.hArtistRecycler),
                findViewById(R.id.iArtistRecycler),
                findViewById(R.id.jArtistRecycler),
                findViewById(R.id.kArtistRecycler),
                findViewById(R.id.lArtistRecycler),
                findViewById(R.id.mArtistRecycler),
                findViewById(R.id.nArtistRecycler),
                findViewById(R.id.oArtistRecycler),
                findViewById(R.id.pArtistRecycler),
                findViewById(R.id.qArtistRecycler),
                findViewById(R.id.rArtistRecycler),
                findViewById(R.id.sArtistRecycler),
                findViewById(R.id.tArtistRecycler),
                findViewById(R.id.uArtistRecycler),
                findViewById(R.id.vArtistRecycler),
                findViewById(R.id.wArtistRecycler),
                findViewById(R.id.xArtistRecycler),
                findViewById(R.id.yArtistRecycler),
                findViewById(R.id.zArtistRecycler)};

        for (int i = 0; i < artistsLabels.length; i++)
        {
            ArtistsAdapter adatper = new ArtistsAdapter(this);

            artistsRecyclers[i].setAdapter(adatper);
            artistsRecyclers[i].setLayoutManager(new LinearLayoutManager(this));

            setAdapterItems(adatper, i);
        }
    }



    // populates recycler with artists, that start on passed i (char from alphabet)
    private void setAdapterItems (ArtistsAdapter adapter, int position)
    {

        List<String> filteredArtists = new ArrayList<>();
        for (String artist : artists)
        {
            if (artist.charAt(0) == artistsLabels[position].getText().charAt(0))
            {
                filteredArtists.add(artist);
            }
        }

        adapter.setSongs(filteredArtists);
    }



    //disable labels that has empty recyclers under them
    private void disableEmpty()
    {
        for(int i = 0; i < artistsRecyclers.length; i++)
        {
            if (artistsRecyclers[i].getAdapter().getItemCount() == 0)
            {
                artistsLabels[i].setVisibility(View.GONE);
            }
            else artistsLabels[i].setOnClickListener(v -> openDialog());
        }
    }
}
