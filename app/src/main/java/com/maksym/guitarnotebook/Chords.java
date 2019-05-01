package com.maksym.guitarnotebook;

import android.app.Dialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class Chords extends Fragment
{
    private ChordViewModel chordViewModel;
    private View view;
    private TextView [] dialogLabels;
    private TextView [] chordsLabels;
    private ScrollView scrollView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_chords, container, false);

        // Get a new or existing ViewModel from the ViewModelProvider.
        chordViewModel = ViewModelProviders.of(this).get(ChordViewModel.class);

        scrollView = view.findViewById(R.id.chordsScrollView);

        //shorten version. I will left full version in case it will be needed
        char [] notes = {'A','B','C','D','E','F','G'};
        RecyclerView [] chordsRecycles = {
                view.findViewById(R.id.aChordsList),
                view.findViewById(R.id.bChordsList),
                view.findViewById(R.id.cChordsList),
                view.findViewById(R.id.dChordsList),
                view.findViewById(R.id.eChordsList),
                view.findViewById(R.id.fChordsList),
                view.findViewById(R.id.gChordsList)};

        for (int i = 0; i< chordsRecycles.length; i++)
        {
            ChordsAdapter adapter = new ChordsAdapter((view.getContext()));

            chordsRecycles[i].setAdapter(adapter);
            chordsRecycles[i].setLayoutManager(new LinearLayoutManager(view.getContext()));

            // Update the cached copy of the chords in the adapter.
            chordViewModel.getChordList(notes[i]).observe(this, adapter::setChords);
        }

//        RecyclerView aChords = view.findViewById(R.id.aChordsList);
//        RecyclerView bChords = view.findViewById(R.id.bChordsList);
//        RecyclerView cChords = view.findViewById(R.id.cChordsList);
//        RecyclerView dChords = view.findViewById(R.id.dChordsList);
//        RecyclerView eChords = view.findViewById(R.id.eChordsList);
//        RecyclerView fChords = view.findViewById(R.id.fChordsList);
//        RecyclerView gChords = view.findViewById(R.id.gChordsList);
//
//        final ChordsAdapter adapterA = new ChordsAdapter(view.getContext());
//        final ChordsAdapter adapterB = new ChordsAdapter(view.getContext());
//        final ChordsAdapter adapterC = new ChordsAdapter(view.getContext());
//        final ChordsAdapter adapterD = new ChordsAdapter(view.getContext());
//        final ChordsAdapter adapterE = new ChordsAdapter(view.getContext());
//        final ChordsAdapter adapterF = new ChordsAdapter(view.getContext());
//        final ChordsAdapter adapterG = new ChordsAdapter(view.getContext());
//
//        aChords.setAdapter(adapterA);
//        bChords.setAdapter(adapterB);
//        cChords.setAdapter(adapterC);
//        dChords.setAdapter(adapterD);
//        eChords.setAdapter(adapterE);
//        fChords.setAdapter(adapterF);
//        gChords.setAdapter(adapterG);
//
//        aChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        bChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        cChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        dChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        eChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        fChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        gChords.setLayoutManager(new LinearLayoutManager(view.getContext()));
//
//
//
//
//
//
//        // Add an observer on the LiveData returned by getChordList(char).
//        // The onChanged() method fires when the observed data changes and the activity is
//        // in the foreground.
//        chordViewModel.getChordList('A').observe(this, chords ->
//            // Update the cached copy of the chords in the adapter.
//            adapterA.setChords(chords));
//        chordViewModel.getChordList('B').observe(this, chords ->
//                // Update the cached copy of the chords in the adapter.
//                adapterB.setChords(chords));
//        chordViewModel.getChordList('C').observe(this, chords ->
//                // Update the cached copy of the chords in the adapter.
//                adapterC.setChords(chords));
//        chordViewModel.getChordList('D').observe(this, chords ->
//                // Update the cached copy of the chords in the adapter.
//                adapterD.setChords(chords));
//        chordViewModel.getChordList('E').observe(this, chords ->
//                // Update the cached copy of the chords in the adapter.
//                adapterE.setChords(chords));
//        chordViewModel.getChordList('F').observe(this, chords ->
//                // Update the cached copy of the chords in the adapter.
//                adapterF.setChords(chords));
//        chordViewModel.getChordList('G').observe(this, chords ->
//                // Update the cached copy of the chords in the adapter.
//                adapterG.setChords(chords));



        chordsLabels = new TextView[]{
                view.findViewById(R.id.aChordsLabel),
                view.findViewById(R.id.bChordsLabel),
                view.findViewById(R.id.cChordsLabel),
                view.findViewById(R.id.dChordsLabel),
                view.findViewById(R.id.eChordsLabel),
                view.findViewById(R.id.fChordsLabel),
                view.findViewById(R.id.gChordsLabel)};

        for (int i =0; i < chordsRecycles.length; i++)
        {
            chordsLabels[i].setOnClickListener(e -> openDialog());
        }

        return view;
    }



    private void openDialog()
    {
        Log.d("Chords", "ATTEMPT TO OPEN DIALOG");
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.chodrs_dialog);

        dialogLabels = new TextView[]{
                dialog.findViewById(R.id.aChordsDialogLabel),
                dialog.findViewById(R.id.bChordsDialogLabel),
                dialog.findViewById(R.id.cChordsDialogLabel),
                dialog.findViewById(R.id.dChordsDialogLabel),
                dialog.findViewById(R.id.eChordsDialogLabel),
                dialog.findViewById(R.id.fChordsDialogLabel),
                dialog.findViewById(R.id.gChordsDialogLabel)};

        for (int i = 0; i < dialogLabels.length; i++)
        {
            dialogLabels[i].setOnClickListener(e ->
            {
                scrollToLabel(e.getId());
                dialog.dismiss();
            });
        }
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();

        Log.d("Chords", "Dialog shown");
    }



    private void scrollToLabel(int id)
    {
        for (int i = 0; i < dialogLabels.length; i++)
        {
            if ((id == dialogLabels[i].getId()))
            {
                Log.d("Chords", chordsLabels[i].getText().toString());

                int vLeft = chordsLabels[i].getLeft();
                int vRight = chordsLabels[i].getRight();
                int sWidth = scrollView.getWidth();
                int vTop = chordsLabels[i].getTop();
                scrollView.smoothScrollTo(((vLeft + vRight - sWidth) / 2), vTop);
            }
        }
    }


}
