package com.maksym.guitarnotebook;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Chord extends AppCompatActivity
{
    private TextView chordName;
    private ImageView chordImage;

    public static final String EXTRA_CHORD_NAME = "ChordName";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord);

        //display up button
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Chord");
        }

        chordName = findViewById(R.id.chordName);
        chordImage = findViewById(R.id.chordImage);

        ChordViewModel chordViewModel = ViewModelProviders.of(this).get(ChordViewModel.class);

        Intent it = getIntent();

        if(it.hasExtra(EXTRA_CHORD_NAME))
        {
            chordName.setText(it.getStringExtra(EXTRA_CHORD_NAME));


            try {
                String paramValue = chordViewModel.getChordAddress(chordName.getText().toString()).toString();
                String yourURLStr = "http://host.com?param=" + java.net.URLEncoder.encode(paramValue, "UTF-8");
                URL url = new URL(yourURLStr);
                //URL url = new URL(chordViewModel.getChordAddress(chordName.getText().toString()).toString());
                InputStream content = (InputStream) url.getContent();
                Drawable d = Drawable.createFromStream(content, "src");
                chordImage.setImageDrawable(d);

            } catch (MalformedURLException e) {
                Log.d("AAAAAAA", e.toString());
            } catch (IOException e) {
                Log.d("AAAAA!", e.toString());
            }
        }

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


