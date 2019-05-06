package com.maksym.guitarnotebook;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class SongViewModel extends AndroidViewModel
{
    private Repository repository;


    public SongViewModel(@NonNull Application application)
    {
        super(application);

        repository = new Repository(application);
    }




    public void deleeteSong (SongEntity song)
    {
        repository.deleeteSong(song);
    }


    public LiveData<List<SongEntity>> getSong(String songName, String songArtist )
    {
        return repository.getSong(songName, songArtist);
    }


    public LiveData<List<SongEntity>> getAllSongs()
    {
        return repository.getAllSongs();
    }

    public void insert(SongEntity song)
    {
        repository.insert(song);
    }
}
