package com.maksym.guitarnotebook;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
public class Drawer_layout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawer;

    private Chords chordsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Intent intent = getIntent();
        int message = R.id.nav_songs;

        if(intent.hasExtra(MainActivity.EXTRA_MESSAGE))
            message = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, R.id.nav_songs);

            setFragment(message);
            navigationView.setCheckedItem(message);

    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        setFragment(menuItem.getItemId());
        return true;
    }


    private void setFragment(int itemId)
    {
        switch (itemId)
        {
            case R.id.nav_collection:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Collection()).commit();
                getSupportActionBar().setTitle(R.string.collectionButton);
                break;
            case R.id.nav_songs:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new SongSearch()).commit();
                getSupportActionBar().setTitle(R.string.songsButton);
                break;
            case R.id.nav_chords:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Chords()).commit();
                getSupportActionBar().setTitle(R.string.chordsButton);
                break;
            case R.id.nav_tuner:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Tuner()).commit();
                getSupportActionBar().setTitle(R.string.tunerButton);
                break;
            case R.id.nav_metronome:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Metronome()).commit();
                getSupportActionBar().setTitle(R.string.metronomeButton);
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Settings()).commit();
                getSupportActionBar().setTitle(R.string.settingsButton);
                break;
            case R.integer.nav_song:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Song()).commit();
                getSupportActionBar().setTitle(R.string.songButton);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

    }

    public void toAbout(View view)
    {
        Intent it = new Intent(this, About.class);
        startActivity(it);
    }


    public void toChord(View view)
    {
        Intent it = new Intent(this, Chord.class);
        startActivity(it);
    }




    public void byNamePressed(View view)
    {
        Intent intent = new Intent(this, Songs.class);
        startActivity(intent);
    }


    public void byGenrePressed(View view)
    {
        Intent intent = new Intent(this, Genres.class);
        startActivity(intent);
    }


    public void byArtistPressed(View view)
    {
        Intent intent = new Intent(this, Artists.class);
        startActivity(intent);
    }
}
