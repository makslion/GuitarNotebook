package com.maksym.guitarnotebook;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

//SQLite thing for work with chords
public class ChordViewModel extends AndroidViewModel
{
    private Repository repository;



    public ChordViewModel(@NonNull Application application)
    {
        super(application);

        repository = new Repository(application);
    }



    public LiveData<List<ChordEntity>> getChordList (char note)
    {
        return repository.getChordList(note);
    }

    public LiveData<String> getChordAddress(String chordName)
    {
        return repository.getChordAddress(chordName);
    }

    public LiveData<List<ChordEntity>> getChord(String chordName)
    {
        return  repository.getChord(chordName);
    }


    public void insert (ChordEntity chord)
    {
        repository.insert(chord);
    }
}
