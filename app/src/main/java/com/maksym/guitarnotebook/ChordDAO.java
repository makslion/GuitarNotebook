package com.maksym.guitarnotebook;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

//SQLite data access object for Chord table
@Dao
public interface ChordDAO
{
    @Insert
    void insert(ChordEntity chord);

    @Query("SELECT * from chords_table WHERE chordNote = :note ")
    LiveData<List<ChordEntity>> getChordList(char note);

    @Query("SELECT chordAddress from chords_table WHERE chordName = :name ")
    LiveData<String> getChordAddress (String name);

    @Query("DELETE FROM chords_table")
    void deleteAll();

    @Query("SELECT * FROM chords_table WHERE chordName = :chordName")
    LiveData<List<ChordEntity>> getChord(String chordName);
}
