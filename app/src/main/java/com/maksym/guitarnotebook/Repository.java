package com.maksym.guitarnotebook;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class Repository
{
    private ChordDAO chordDAO;
    private SongDAO songDAO;


    public Repository (Application application)
    {
        GuitarRoomDatabase db = GuitarRoomDatabase.getDatabase(application);
        chordDAO = db.chordDAO();
        songDAO = db.songDAO();
    }



    //chords
    public LiveData<List<ChordEntity>> getChordList (char note)
    {
        return chordDAO.getChordList(note);
    }

    public LiveData<String> getChordAddress(String chordName)
    {
        return chordDAO.getChordAddress(chordName);
    }

    public LiveData<List<ChordEntity>> getChord (String chordName)
    {
        return  chordDAO.getChord(chordName);
    }


    public void insert (ChordEntity chord)
    {
        new insertAsyncTask(chordDAO).execute(chord);
    }



    private static class insertAsyncTask extends AsyncTask<ChordEntity, Void, Void>
    {

        private ChordDAO chordDAO;

        insertAsyncTask(ChordDAO dao)
        {
            chordDAO = dao;
        }

        @Override
        protected Void doInBackground(final ChordEntity... params)
        {
            chordDAO.insert(params[0]);
            return null;
        }
    }





    //songs
    public void deleeteSong (SongEntity song)
    {
        new deleteSongAsyncTask(songDAO).execute(song);
    }


    public LiveData<List<SongEntity>> getSong(String songName, String songArtist )
    {
        return songDAO.getSong(songName, songArtist);
    }


    public LiveData<List<SongEntity>> getAllSongs()
    {
        return songDAO.getAllSongs();
    }

    public void insert(SongEntity song){ new insertSongAsyncTask(songDAO).execute(song);}

    private static class insertSongAsyncTask extends AsyncTask<SongEntity, Void, Void>
    {

        private SongDAO songDAO;

        insertSongAsyncTask(SongDAO dao)
        {
            songDAO = dao;
        }

        @Override
        protected Void doInBackground(final SongEntity... params)
        {
            songDAO.insert(params[0]);
            return null;
        }
    }



    private static class deleteSongAsyncTask extends AsyncTask<SongEntity, Void, Void>
    {

        private SongDAO songDAO;

        deleteSongAsyncTask(SongDAO dao)
        {
            songDAO = dao;
        }

        @Override
        protected Void doInBackground(final SongEntity... params)
        {
            songDAO.deleteSong(params[0].getSongName(), params[0].getSongArtist());
            return null;
        }
    }
}
