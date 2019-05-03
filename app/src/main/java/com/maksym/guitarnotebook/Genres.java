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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Genres extends AppCompatActivity
{
    private DatabaseReference firebaseSongs;
    private TextView[] genresLabels;
    private TextView [] dialogLabels;
    private RecyclerView[] genresRecyclers;
    private ScrollView scrollView;
    private List<String> genres;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        //display up button
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Genres");
        }

        populateLabels();

        scrollView = findViewById(R.id.genresScrollView);
        genres = new ArrayList<>();
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
                genres.clear();

                for (DataSnapshot songSnapshot : dataSnapshot.getChildren())
                {
                    //Log.d("Songs", "creating song");
                    SongModel song = songSnapshot.getValue(SongModel.class);

                    //Log.d("Songs", "new song "+songSnapshot.getValue(SongModel.class).getSongName());


                    if (!genres.contains(song.getSongGenre()))
                    {
                        genres.add(song.getSongGenre());
                        Log.d("Genres", "Adding genre "+song.getSongGenre());
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
        Log.d("Genres", "ATTEMPT TO OPEN DIALOG");
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
            if (genresRecyclers[i].getAdapter().getItemCount() != 0)
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

        Log.d("Genres", "Dialog shown");
    }



    private void scrollToLabel(int id)
    {
        for (int i = 0; i < dialogLabels.length; i++)
        {
            if ((id == dialogLabels[i].getId()))
            {
                Log.d("Artists", genresLabels[i].getText().toString());

                int vLeft = genresLabels[i].getLeft();
                int vRight = genresLabels[i].getRight();
                int sWidth = genresLabels[i].getWidth();
                int vTop = genresLabels[i].getTop();
                scrollView.smoothScrollTo(((vLeft + vRight - sWidth) / 2), vTop);
            }
        }
    }



    private void populateLabels()
    {
        genresLabels = new TextView[]{
                findViewById(R.id.numberGenresLabel),
                findViewById(R.id.aGenresLabel),
                findViewById(R.id.bGenresLabel),
                findViewById(R.id.cGenresLabel),
                findViewById(R.id.dGenresLabel),
                findViewById(R.id.eGenresLabel),
                findViewById(R.id.fGenresLabel),
                findViewById(R.id.gGenresLabel),
                findViewById(R.id.hGenresLabel),
                findViewById(R.id.iGenresLabel),
                findViewById(R.id.jGenresLabel),
                findViewById(R.id.kGenresLabel),
                findViewById(R.id.lGenresLabel),
                findViewById(R.id.mGenresLabel),
                findViewById(R.id.nGenresLabel),
                findViewById(R.id.oGenresLabel),
                findViewById(R.id.pGenresLabel),
                findViewById(R.id.qGenresLabel),
                findViewById(R.id.rGenresLabel),
                findViewById(R.id.sGenresLabel),
                findViewById(R.id.tGenresLabel),
                findViewById(R.id.uGenresLabel),
                findViewById(R.id.vGenresLabel),
                findViewById(R.id.wGenresLabel),
                findViewById(R.id.xGenresLabel),
                findViewById(R.id.yGenresLabel),
                findViewById(R.id.zGenresLabel)};
    }



    private void populateRecyclers()
    {
        genresRecyclers = new RecyclerView[]{
                findViewById(R.id.numberGenresRecycler),
                findViewById(R.id.aGenresRecycler),
                findViewById(R.id.bGenresRecycler),
                findViewById(R.id.cGenresRecycler),
                findViewById(R.id.dGenresRecycler),
                findViewById(R.id.eGenresRecycler),
                findViewById(R.id.fGenresRecycler),
                findViewById(R.id.gGenresRecycler),
                findViewById(R.id.hGenresRecycler),
                findViewById(R.id.iGenresRecycler),
                findViewById(R.id.jGenresRecycler),
                findViewById(R.id.kGenresRecycler),
                findViewById(R.id.lGenresRecycler),
                findViewById(R.id.mGenresRecycler),
                findViewById(R.id.nGenresRecycler),
                findViewById(R.id.oGenresRecycler),
                findViewById(R.id.pGenresRecycler),
                findViewById(R.id.qGenresRecycler),
                findViewById(R.id.rGenresRecycler),
                findViewById(R.id.sGenresRecycler),
                findViewById(R.id.tGenresRecycler),
                findViewById(R.id.uGenresRecycler),
                findViewById(R.id.vGenresRecycler),
                findViewById(R.id.wGenresRecycler),
                findViewById(R.id.xGenresRecycler),
                findViewById(R.id.yGenresRecycler),
                findViewById(R.id.zGenresRecycler)};

        for (int i = 0; i < genresLabels.length; i++)
        {
            GenresAdapter adatper = new GenresAdapter(this);

            genresRecyclers[i].setAdapter(adatper);
            genresRecyclers[i].setLayoutManager(new LinearLayoutManager(this));

            setAdapterItems(adatper, i);
        }
    }



    // populates recycler with artists, that start on passed i (char from alphabet)
    private void setAdapterItems (GenresAdapter adapter, int position)
    {

        List<String> filteredGenres = new ArrayList<>();
        for (String genre : genres)
        {
            if (genre.charAt(0) == genresLabels[position].getText().charAt(0))
            {
                filteredGenres.add(genre);
            }
        }

        adapter.setSongs(filteredGenres);
    }



    //disable labels that has empty recyclers under them
    private void disableEmpty()
    {
        for(int i = 0; i < genresRecyclers.length; i++)
        {
            if (genresRecyclers[i].getAdapter().getItemCount() == 0)
            {
                genresLabels[i].setVisibility(View.GONE);
            }
            else genresLabels[i].setOnClickListener(v -> openDialog());
        }
    }

}
