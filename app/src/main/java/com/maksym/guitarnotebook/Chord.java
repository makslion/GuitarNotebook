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
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

//class  for Chord activity
public class Chord extends AppCompatActivity
{
    private TextView chordName;
    private ChordViewModel chordViewModel;

    private ViewPager chordVariants;
    private String [] chordImagesAdress;

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

        chordVariants = findViewById(R.id.chordVariations);


        chordName = findViewById(R.id.chordName);

        chordViewModel = ViewModelProviders.of(this).get(ChordViewModel.class);
        Intent it = getIntent();

        if(it.hasExtra(EXTRA_CHORD_NAME))
        {
            String chord = it.getStringExtra(EXTRA_CHORD_NAME);

            chordName.setText(chord);

            fillChordImages(ChordImages.getImages(chord));

//            chordViewModel.getChordAddress(chord).observe(this, new Observer<String>() {
//                @Override
//                public void onChanged(@Nullable String s) {
//                    //new DownLoadImageTask(chordImage).execute(s);
//                    Log.d("Chord", s);
//                }
//            });
        }

    }


    private void fillChordImages(String [] images)
    {
        DetailsViewPagerAdapter adapter = new DetailsViewPagerAdapter(this);
        adapter.setImages(images);
        chordVariants.setAdapter(adapter);
    }

    //implement up button action
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }



//    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap>
//    {
//        ImageView imageView;
//
//        public DownLoadImageTask(ImageView imageView)
//        {
//            this.imageView = imageView;
//        }
//
//        /*
//            doInBackground(Params... params)
//                Override this method to perform a computation on a background thread.
//         */
//        protected Bitmap doInBackground(String...urls)
//        {
//            String urlOfImage = urls[0];
//            Bitmap logo = null;
//            try{
//                InputStream is = new URL(urlOfImage).openStream();
//                /*
//                    decodeStream(InputStream is)
//                        Decode an input stream into a bitmap.
//                 */
//                logo = BitmapFactory.decodeStream(is);
//            }catch(Exception e){ // Catch the download exception
//                e.printStackTrace();
//            }
//            return logo;
//        }
//
//        /*
//            onPostExecute(Result result)
//                Runs on the UI thread after doInBackground(Params...).
//         */
//        protected void onPostExecute(Bitmap result){
//            imageView.setImageBitmap(result);
//        }
//    }
}


