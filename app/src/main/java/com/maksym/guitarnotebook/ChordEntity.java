package com.maksym.guitarnotebook;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.util.Log;


//SQLite thing for chord table
@Entity (tableName = "chords_table")
public class ChordEntity
{
    @NonNull
    @PrimaryKey (autoGenerate = true)
    private int chordId;

    @NonNull
    @ColumnInfo (name = "chordName")
    private String chordName;

    @NonNull
    @ColumnInfo (name = "chordAddress")
    private String chordAddress;

    @NonNull
    @ColumnInfo (name = "chordNote")
    private char chordNote;



    public ChordEntity(@NonNull String chordName, @NonNull String chordAddress, char chordNote)
    {
        this.chordName = chordName;
        this.chordAddress = chordAddress;
        this.chordNote = chordNote;
    }



    @NonNull
    String getChordAddress() { return chordAddress; }

    @NonNull
    String getChordName() {
        return chordName;
    }

    public char getChordNote() { return chordNote; }

    public int getChordId() { return chordId; }


    public void setChordId(int chordId) { this.chordId = chordId; }
}
