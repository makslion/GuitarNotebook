package com.maksym.guitarnotebook;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class ChordsAdapter extends RecyclerView.Adapter <ChordsAdapter.ChordViewHolder>
{

    class ChordViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView chordTextView;

        private ChordViewHolder(View itemView)
        {
            super(itemView);
            chordTextView = itemView.findViewById(R.id.chordTextView);
        }
    }


    private final LayoutInflater inflater;

    private List<ChordEntity> chords; // Cached copy of words

    public ChordsAdapter(Context context)
    {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ChordViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = inflater.inflate(R.layout.chord_listitem, parent, false);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView chord = v.findViewById(R.id.chordTextView);
                Intent it = new Intent(v.getContext(), Chord.class);
                it.putExtra(Chord.EXTRA_CHORD_NAME, chord.getText());
                v.getContext().startActivity(it);
            }
        });

        return new ChordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChordViewHolder holder, int position)
    {
        ChordEntity current = chords.get(position);
        holder.chordTextView.setText(current.getChordName());
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
}
