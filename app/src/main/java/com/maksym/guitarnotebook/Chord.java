package com.maksym.guitarnotebook;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
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
    private ChordViewModel chordViewModel;

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

        chordViewModel = ViewModelProviders.of(this).get(ChordViewModel.class);
        Intent it = getIntent();

        if(it.hasExtra(EXTRA_CHORD_NAME))
        {
            String chord = it.getStringExtra(EXTRA_CHORD_NAME);

            chordName.setText(chord);

            chordViewModel.getChordAddress(chord).observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    new DownLoadImageTask(chordImage).execute(s);
                    Log.d("Chord", s);
                }
            });
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



    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>
    {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView)
        {
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls)
        {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            imageView.setImageBitmap(result);
        }
    }
}


