package com.maksym.guitarnotebook;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class Repository
{
    private ChordDAO chordDAO;



    public Repository (Application application)
    {
        GuitarRoomDatabase db = GuitarRoomDatabase.getDatabase(application);
        chordDAO = db.chordDAO();
    }



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
}
