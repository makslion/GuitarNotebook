package com.maksym.guitarnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Genres extends AppCompatActivity
{

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
    }


    public void genre1(View view)
    {
        Intent intent = new Intent(this, Songs.class);
        intent.putExtra(Songs.SONG_FILTER, "genre 1");
        startActivity(intent);
    }

    public void genre2(View view)
    {
        Intent intent = new Intent(this, Songs.class);
        intent.putExtra(Songs.SONG_FILTER, "genre 2");
        startActivity(intent);
    }

    public void genre3(View view)
    {
        Intent intent = new Intent(this, Songs.class);
        intent.putExtra(Songs.SONG_FILTER, "genre 3");
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
