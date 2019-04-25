package com.maksym.guitarnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    public static final String EXTRA_MESSAGE = "FragmentId";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




    public void toCollection(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(EXTRA_MESSAGE, R.id.nav_collection);
        startActivity(it);
    }

    public void toSongs(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(EXTRA_MESSAGE, R.id.nav_songs);
        startActivity(it);
    }

    public void toChords(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(EXTRA_MESSAGE, R.id.nav_chords);
        startActivity(it);
    }

    public void toTuner(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(EXTRA_MESSAGE, R.id.nav_tuner);
        startActivity(it);
    }

    public void toMetronome(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(EXTRA_MESSAGE, R.id.nav_metronome);
        startActivity(it);
    }

    public void toSettings(View view)
    {
        Intent it = new Intent(this, Drawer_layout.class);
        it.putExtra(EXTRA_MESSAGE, R.id.nav_settings);
        startActivity(it);
    }
}
