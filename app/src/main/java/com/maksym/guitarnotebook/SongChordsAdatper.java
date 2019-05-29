package com.maksym.guitarnotebook;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

//adapter for chord recycler in song activity
public class SongChordsAdatper extends RecyclerView.Adapter <SongChordsAdatper.ChordViewHolder>
{

    class ChordViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView chordTextView;
        private final ImageView chordImageView;

        private ChordViewHolder(View itemView)
        {
            super(itemView);
            chordTextView = itemView.findViewById(R.id.chordFull_chordName);
            chordImageView = itemView.findViewById(R.id.chordFull_chordImage);
        }
    }


    private final LayoutInflater inflater;

    private List<ChordEntity> chords; // Cached copy of words


    public SongChordsAdatper(Context context)
    {
        inflater = LayoutInflater.from(context);
    }



    @Override
    public SongChordsAdatper.ChordViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = inflater.inflate(R.layout.chord_full_listitem, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView chord = v.findViewById(R.id.chordFull_chordName);
                Intent it = new Intent(v.getContext(), Chord.class);
                it.putExtra(Chord.EXTRA_CHORD_NAME, chord.getText());
                v.getContext().startActivity(it);
            }
        });

        return new SongChordsAdatper.ChordViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(SongChordsAdatper.ChordViewHolder holder, int position)
    {
        ChordEntity current = chords.get(position);
        holder.chordTextView.setText(current.getChordName());

        new DownLoadImageTask(holder.chordImageView).execute(current.getChordAddress());

    }



    void setChords(List<ChordEntity> chords)
    {
        this.chords = chords;
        notifyDataSetChanged();
    }



    // getItemCount() is called many times, and when it is first called,
    // chords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount()
    {
        if (chords != null)
            return chords.size();
        else return 0;
    }



    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap>
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
