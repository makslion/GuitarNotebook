package com.maksym.guitarnotebook;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "songs_table")
public class SongEntity
{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int songId;

    @NonNull
    @ColumnInfo (name = "SongsArtist")
    private String songArtist;

    @NonNull
    @ColumnInfo (name = "SongChords")
    private String songChords;

    @NonNull
    @ColumnInfo (name = "SongExample")
    private String songExample;

    @NonNull
    @ColumnInfo (name = "SongsGenre")
    private String songGenre;

    @NonNull
    @ColumnInfo (name = "SongLyrics")
    private String songLyrics;

    @NonNull
    @ColumnInfo (name = "SongName")
    private String songName;


    public SongEntity(@NonNull String songArtist, @NonNull String songChords,
                      @NonNull String songExample, @NonNull String songGenre,
                      @NonNull String songLyrics, @NonNull String songName)
    {
        this.songArtist = songArtist;
        this.songChords = songChords;
        this.songExample = songExample;
        this.songGenre = songGenre;
        this.songLyrics = songLyrics;
        this.songName = songName;
    }

    @NonNull
    public String getSongArtist() {
        return songArtist;
    }

    @NonNull
    public String getSongChords() {
        return songChords;
    }

    @NonNull
    public String getSongExample() {
        return songExample;
    }

    @NonNull
    public String getSongGenre() {
        return songGenre;
    }

    @NonNull
    public String getSongLyrics() {
        return songLyrics;
    }

    @NonNull
    public String getSongName() {
        return songName;
    }

    public int getSongId() {
        return songId;
    }




    public void setSongArtist(@NonNull String songArtist) {
        this.songArtist = songArtist;
    }

    public void setSongChords(@NonNull String songChords) {
        this.songChords = songChords;
    }

    public void setSongExample(@NonNull String songExample) {
        this.songExample = songExample;
    }

    public void setSongGenre(@NonNull String songGenre) {
        this.songGenre = songGenre;
    }

    public void setSongLyrics(@NonNull String songLyrics) {
        this.songLyrics = songLyrics;
    }

    public void setSongName(@NonNull String songName) {
        this.songName = songName;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
