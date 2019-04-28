package com.maksym.guitarnotebook;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Chords extends Fragment
{
    private ChordViewModel chordViewModel;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_chords, container, false);

        RecyclerView aChords = view.findViewById(R.id.aChordsList);
        RecyclerView bChords = view.findViewById(R.id.bChordsList);
        RecyclerView cChords = view.findViewById(R.id.cChordsList);
        RecyclerView dChords = view.findViewById(R.id.dChordsList);
        RecyclerView eChords = view.findViewById(R.id.eChordsList);
        RecyclerView fChords = view.findViewById(R.id.fChordsList);
        RecyclerView gChords = view.findViewById(R.id.gChordsList);


        final ChordsAdapter adapterA = new ChordsAdapter(view.getContext());
        final ChordsAdapter adapterB = new ChordsAdapter(view.getContext());
        final ChordsAdapter adapterC = new ChordsAdapter(view.getContext());
        final ChordsAdapter adapterD = new ChordsAdapter(view.getContext());
        final ChordsAdapter adapterE = new ChordsAdapter(view.getContext());
        final ChordsAdapter adapterF = new ChordsAdapter(view.getContext());
        final ChordsAdapter adapterG = new ChordsAdapter(view.getContext());

        aChords.setAdapter(adapterA);
        bChords.setAdapter(adapterB);
        cChords.setAdapter(adapterC);
        dChords.setAdapter(adapterD);
        eChords.setAdapter(adapterE);
        fChords.setAdapter(adapterF);
        gChords.setAdapter(adapterG);

        aChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
        bChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
        cChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
        dChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
        eChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
        fChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
        gChords.setLayoutManager(new LinearLayoutManager(view.getContext()));


        // Get a new or existing ViewModel from the ViewModelProvider.
        chordViewModel = ViewModelProviders.of(this).get(ChordViewModel.class);

        // Add an observer on the LiveData returned by getChordList(char).
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        chordViewModel.getChordList('A').observe(this, chords ->
            // Update the cached copy of the chords in the adapter.
            adapterA.setChords(chords));
        chordViewModel.getChordList('B').observe(this, chords ->
                // Update the cached copy of the chords in the adapter.
                adapterB.setChords(chords));
        chordViewModel.getChordList('C').observe(this, chords ->
                // Update the cached copy of the chords in the adapter.
                adapterC.setChords(chords));
        chordViewModel.getChordList('D').observe(this, chords ->
                // Update the cached copy of the chords in the adapter.
                adapterD.setChords(chords));
        chordViewModel.getChordList('E').observe(this, chords ->
                // Update the cached copy of the chords in the adapter.
                adapterE.setChords(chords));
        chordViewModel.getChordList('F').observe(this, chords ->
                // Update the cached copy of the chords in the adapter.
                adapterF.setChords(chords));
        chordViewModel.getChordList('G').observe(this, chords ->
                // Update the cached copy of the chords in the adapter.
                adapterG.setChords(chords));

        Log.d("testing1",chordViewModel.getChordList('A').toString());

        return view;
    }
}
