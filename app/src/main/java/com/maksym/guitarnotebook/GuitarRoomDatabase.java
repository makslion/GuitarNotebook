package com.maksym.guitarnotebook;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;


@Database(entities = {ChordEntity.class, SongEntity.class}, version = 2, exportSchema = false)
public abstract class GuitarRoomDatabase extends RoomDatabase
{
    public abstract ChordDAO chordDAO();
    public abstract SongDAO songDAO();
    private static GuitarRoomDatabase INSTANCE;

    static GuitarRoomDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (GuitarRoomDatabase.class)
            {
                if (INSTANCE == null)
                {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GuitarRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }




    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
            Log.d("RoomDatabase", "On create db");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db)
        {
            super.onOpen(db);
            Log.d("RoomDatabase", "On open db");

            // If you want to keep the data through app restarts,
            // comment out the following line.
            //new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ChordDAO chordDAO;
        private final SongDAO songDAO;

        //sample data
        private String [] chordA = {"A", "https://www.theguitarlesson.com/wp-content/uploads/chords/Amaj-chord1.jpg", "A"};
        private String [] chordAm = {"Am", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am-chord1.jpg", "A"};

        private String [] chordB = {"B", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bmaj-chord1.jpg", "B"};
        private String [] chordBm = {"Bm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm-chord1.jpg", "B"};

        private String [] chordC = {"C", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cmaj-chord1.jpg", "C"};
        private String [] chordCm = {"Cm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm-chord1.jpg", "C"};

        private String [] chordD = {"D", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dmaj-chord1.jpg", "D"};
        private String [] chordDm = {"Dm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm-chord1.jpg", "D"};

        private String [] chordE = {"E", "https://www.theguitarlesson.com/wp-content/uploads/chords/Emaj-chord1.jpg", "E"};
        private String [] chordEm = {"Em", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em-chord1.jpg", "E"};

        private String [] chordF = {"F", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fmaj-chord1.jpg", "F"};
        private String [] chordFm = {"Fm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm-chord1.jpg", "F"};

        private String [] chordG = {"G", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gmaj-chord1.jpg", "G"};
        private String [] chordGm = {"Gm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm-chord1.jpg", "G"};

        private String [][] chordsA = {chordA, chordAm};
        private String [][] chordsB = {chordB, chordBm};
        private String [][] chordsC = {chordC, chordCm};
        private String [][] chordsD = {chordD, chordDm};
        private String [][] chordsE = {chordE, chordEm};
        private String [][] chordsF = {chordF, chordFm};
        private String [][] chordsG = {chordG, chordGm};

        private String [][][] chords = {chordsA, chordsB, chordsC, chordsD, chordsE, chordsF, chordsG};




        PopulateDbAsync(GuitarRoomDatabase db)
        {
            chordDAO = db.chordDAO();
            songDAO = db.songDAO();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            chordDAO.deleteAll();
            songDAO.deleteAll();

            Log.d("RoomDatabse", "Populating database");
            for( int i = 0; i < chords.length; i++)
            {
                for (int j = 0; j< chords[i].length; j++)
                {
                    ChordEntity chord = new ChordEntity(
                            chords[i][j][0],
                            chords[i][j][1],
                            chords[i][j][2].charAt(0));

                    chordDAO.insert(chord);
                }
            }
            return null;
        }
    }
}
