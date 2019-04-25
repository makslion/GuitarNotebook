package com.maksym.guitarnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Songs extends AppCompatActivity
{
    TextView demoLabel;

    public static final String SONG_FILTER = "SongFilter";
    public static final String EXTRA_SONG = "SongDetails";
    private String[] songlist= {"Song 1", "Song 2", "Song 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        demoLabel = findViewById(R.id.demoLabel);

        Intent intent = getIntent();

        if(intent.hasExtra(SONG_FILTER))
            setFilter(intent.getStringExtra(SONG_FILTER));



        //display up button
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Songs");
        }
    }


    public void setFilter(String filter)
    {
        demoLabel.setText(filter);

        for (int i =0; i < songlist.length; i++)
            songlist[i] = songlist[i] +" "+filter;
    }


    public void song1(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(MainActivity.EXTRA_MESSAGE, R.integer.nav_song);
        it.putExtra(EXTRA_SONG, songlist[0]);
        startActivity(it);
    }

    public void song2(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(MainActivity.EXTRA_MESSAGE, R.integer.nav_song);
        it.putExtra(EXTRA_SONG, songlist[1]);
        startActivity(it);
    }

    public void song3(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(MainActivity.EXTRA_MESSAGE, R.integer.nav_song);
        it.putExtra(EXTRA_SONG, songlist[2]);
        startActivity(it);
    }


    //implement up button action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
