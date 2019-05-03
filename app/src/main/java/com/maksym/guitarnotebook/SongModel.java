package com.maksym.guitarnotebook;

import com.google.firebase.database.Exclude;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SongModel
{
    private String SongName;
    private String SongGenre;
    private String SongArtist;
    private List<String> SongChords;
    private String SongLyrics;
    private String SongExample;

    public SongModel() {}

    public SongModel(String songName, String songGenre, String songArtist, String [] songChords, String songLyrics, String songExample)
    {
        this.SongName = songName;
        this.SongGenre = songGenre;
        this.SongArtist = songArtist;
        this.SongChords = Arrays.asList(songChords);
        this.SongLyrics = songLyrics;
        this.SongExample = songExample;
    }



    public String getSongName(){ return SongName; }

    public String getSongGenre(){ return SongGenre; }

    public String getSongArtist(){ return SongArtist; }

    public List<String> getSongChords(){ return SongChords; }

    public String getSongLyrics(){ return SongLyrics; }

    public String getSongExample(){ return SongExample; }


    public void setSongName(String songName) {
        this.SongName = songName;
    }

    public void setSongGenre(String songGenre) {
        this.SongGenre = songGenre;
    }

    public void setSongArtist(String songArtist) {
        this.SongArtist = songArtist;
    }

    public void setSongChords(String [] songChords) {
        this.SongChords = Arrays.asList(songChords);
    }

    public void setSongLyrics(String songLyrics) {
        this.SongLyrics = songLyrics;
    }

    public void setSongExample(String songExample) {
        this.SongExample = songExample;
    }

    @Exclude //used to push object to firebase
    public Map<String, Object> toMap()
    {
        Map<String, Object> result = new HashMap<>();

        result.put("SongName", SongName);
        result.put("SongGenre", SongGenre);
        result.put("SongArtist", SongArtist);
        result.put("SongChords", SongChords);
        result.put("SongLyrics", SongLyrics);
        result.put("SongExample", SongExample);

        return result;
    }
}
