package com.maksym.guitarnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Artists extends AppCompatActivity
{

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
    }


    public void artist1(View view)
    {
        Intent intent = new Intent(this, Songs.class);
        intent.putExtra(Songs.SONG_FILTER, "artist 1");
        startActivity(intent);
    }

    public void artist2(View view)
    {
        Intent intent = new Intent(this, Songs.class);
        intent.putExtra(Songs.SONG_FILTER, "artist 2");
        startActivity(intent);
    }

    public void artist3(View view)
    {
        Intent intent = new Intent(this, Songs.class);
        intent.putExtra(Songs.SONG_FILTER, "artist 3");
        startActivity(intent);
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
