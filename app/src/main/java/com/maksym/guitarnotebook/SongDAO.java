package com.maksym.guitarnotebook;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SongDAO
{
    @Insert
    void insert(SongEntity song);

    @Query( "DELETE FROM songs_table")
    void deleteAll();

    @Query("DELETE FROM songs_table WHERE SongName =:songName AND SongsArtist =:songArtist")
    void deleteSong(String songName, String songArtist);

    @Query("SELECT * FROM songs_table  WHERE SongName =:songName AND SongsArtist =:songArtist")
    LiveData<List<SongEntity>> getSong(String songName, String songArtist);

    @Query("SELECT * FROM songs_table")
    LiveData<List<SongEntity>> getAllSongs();
}
