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

//SQLite database with sample data for chords
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




        //sample data  {"Name", "default fingering", " "Note"}

        //A
        private String [] Aadd9 = {"Aadd9", "https://www.theguitarlesson.com/wp-content/uploads/chords/A-add9-chord1.jpg", "A"};
        private String [] Aaug = {"Aaug", "https://www.theguitarlesson.com/wp-content/uploads/chords/A-aug-chord1.jpg", "A"};
        private String [] Ab5 = {"Ab5", "https://www.theguitarlesson.com/wp-content/uploads/chords/A-b5-chord1.jpg", "A"};
        private String [] Adim = {"Adim", "https://www.theguitarlesson.com/wp-content/uploads/chords/A-dim-chord1.jpg", "A"};
        private String [] Asus4 = {"Asus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/A-sus4-chord1.jpg", "A"};

        private String [] A11 = {"A11", "https://www.theguitarlesson.com/wp-content/uploads/chords/A11-chord1.jpg", "A"};
        private String [] A13 = {"A13", "https://www.theguitarlesson.com/wp-content/uploads/chords/A13-chord1.jpg", "A"};
        private String [] A5 = {"A5", "https://www.theguitarlesson.com/wp-content/uploads/chords/A5-chord1.jpg", "A"};
        private String [] A6_9 = {"A6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/A6-9-chord1.jpg", "A"};
        private String [] A6 = {"A6", "https://www.theguitarlesson.com/wp-content/uploads/chords/A6-chord1.jpg", "A"};

        private String [] A7_aug_b9 = {"A7-aug-b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/A7-aug-b9-chord1.jpg", "A"};
        private String [] A7_aug = {"A7-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/A7-aug-chord1.jpg", "A"};
        private String [] A7 = {"A7", "https://www.theguitarlesson.com/wp-content/uploads/chords/A7-chord1.jpg", "A"};
        private String [] A7_sharp_9 = {"A7-sharp-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/A7-sharp-9-chord1.jpg", "A"};
        private String [] A7_sus4 = {"A7-sus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/A7-sus4-chord1.jpg", "A"};

        private String [] A7b5 = {"A7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/A7b5-chord1.jpg", "A"};
        private String [] A7b9 = {"A7b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/A7b9-chord1.jpg", "A"};
        private String [] A9_aug = {"A9-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/A9-aug-chord1.jpg", "A"};
        private String [] A9 = {"A9", "https://www.theguitarlesson.com/wp-content/uploads/chords/A9-chord1.jpg", "A"};
        private String [] A9_sharp_11 = {"A9-sharp-11", "https://www.theguitarlesson.com/wp-content/uploads/chords/A9-sharp-11-chord1.jpg", "A"};

        private String [] A9b5 = {"A9b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/A9b5-chord1.jpg", "A"};
        private String [] Am = {"Am", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am-chord1.jpg", "A"};
        private String [] Am_maj7 = {"Am-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am-maj7-chord1.jpg", "A"};
        private String [] Am11 = {"Am11", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am11-chord1.jpg", "A"};
        private String [] Am6_9 = {"Am6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am6-9-chord1.jpg", "A"};

        private String [] Am6 = {"Am6", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am6-chord1.jpg", "A"};
        private String [] Am7 = {"Am7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am7-chord1.jpg", "A"};
        private String [] Am7b5 = {"Am7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am7b5-chord1.jpg", "A"};
        private String [] Am9 = {"Am9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am9-chord1.jpg", "A"};
        private String [] Am9_maj7 = {"Am9-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Am9-maj7-chord1.jpg", "A"};

        private String [] Amaj = {"A", "https://www.theguitarlesson.com/wp-content/uploads/chords/Amaj-chord1.jpg", "A"};
        private String [] Amaj7 = {"Amaj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Amaj7-chord1.jpg", "A"};
        private String [] Amaj7b5 = {"Amaj7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Amaj7b5-chord1.jpg", "A"};
        private String [] Amaj9 = {"Amaj9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Amaj9-chord1.jpg", "A"};




        //B
        private String [] Badd9 = {"Badd9", "https://www.theguitarlesson.com/wp-content/uploads/chords/B-add9-chord1.jpg", "B"};
        private String [] Baug = {"Baug", "https://www.theguitarlesson.com/wp-content/uploads/chords/B-aug-chord1.jpg", "B"};
        private String [] Bb5 = {"Bb5", "https://www.theguitarlesson.com/wp-content/uploads/chords/B-b5-chord1.jpg", "B"};
        private String [] Bdim = {"Bdim", "https://www.theguitarlesson.com/wp-content/uploads/chords/B-dim-chord1.jpg", "B"};
        private String [] Bsus4 = {"Bsus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/B-sus4-chord1.jpg", "B"};

        private String [] B11 = {"B11", "https://www.theguitarlesson.com/wp-content/uploads/chords/B11-chord1.jpg", "B"};
        private String [] B13 = {"B13", "https://www.theguitarlesson.com/wp-content/uploads/chords/B13-chord1.jpg", "B"};
        private String [] B5 = {"B5", "https://www.theguitarlesson.com/wp-content/uploads/chords/B5-chord1.jpg", "B"};
        private String [] B6_9 = {"B6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/B6-9-chord1.jpg", "B"};
        private String [] B6 = {"B6", "https://www.theguitarlesson.com/wp-content/uploads/chords/B6-chord1.jpg", "B"};

        private String [] B7_aug_b9 = {"B7-aug-b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/B7-aug-b9-chord1.jpg", "B"};
        private String [] B7_aug = {"B7-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/B7-aug-chord1.jpg", "B"};
        private String [] B7 = {"B7", "https://www.theguitarlesson.com/wp-content/uploads/chords/B7-chord1.jpg", "B"};
        private String [] B7_sharp_9 = {"B7-sharp-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/B7-sharp-9-chord1.jpg", "B"};
        private String [] B7_sus4 = {"B7-sus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/B7-sus4-chord1.jpg", "B"};

        private String [] B7b5 = {"B7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/B7b5-chord1.jpg", "B"};
        private String [] B7b9 = {"B7b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/B7b9-chord1.jpg", "B"};
        private String [] B9_aug = {"B9-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/B9-aug-chord1.jpg", "B"};
        private String [] B9 = {"B9", "https://www.theguitarlesson.com/wp-content/uploads/chords/B9-chord1.jpg", "B"};
        private String [] B9_sharp_11 = {"B9-sharp-11", "https://www.theguitarlesson.com/wp-content/uploads/chords/B9-sharp-11-chord1.jpg", "B"};

        private String [] B9b5 = {"B9b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/B9b5-chord1.jpg", "B"};
        private String [] Bm = {"Bm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm-chord1.jpg", "B"};
        private String [] Bm_maj7 = {"Bm-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm-maj7-chord1.jpg", "B"};
        private String [] Bm11 = {"Bm11", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm11-chord1.jpg", "B"};
        private String [] Bm6_9 = {"Bm6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm6-9-chord1.jpg", "B"};

        private String [] Bm6 = {"Bm6", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm6-chord1.jpg", "B"};
        private String [] Bm7 = {"Bm7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm7-chord1.jpg", "B"};
        private String [] Bm7b5 = {"Bm7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm7b5-chord1.jpg", "B"};
        private String [] Bm9 = {"Bm9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm9-chord1.jpg", "B"};
        private String [] Bm9_maj7 = {"Bm9-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bm9-maj7-chord1.jpg", "B"};

        private String [] Bmaj = {"B", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bmaj-chord1.jpg", "B"};
        private String [] Bmaj7 = {"Bmaj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bmaj7-chord1.jpg", "B"};
        private String [] Bmaj7b5 = {"Bmaj7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bmaj7b5-chord1.jpg", "B"};
        private String [] Bmaj9 = {"Bmaj9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Bmaj9-chord1.jpg", "B"};




        //C
        private String [] Cadd9 = {"Cadd9", "https://www.theguitarlesson.com/wp-content/uploads/chords/C-add9-chord1.jpg", "C"};
        private String [] Caug = {"Caug", "https://www.theguitarlesson.com/wp-content/uploads/chords/C-aug-chord1.jpg", "C"};
        private String [] Cb5 = {"Cb5", "https://www.theguitarlesson.com/wp-content/uploads/chords/C-b5-chord1.jpg", "C"};
        private String [] Cdim = {"Cdim", "https://www.theguitarlesson.com/wp-content/uploads/chords/C-dim-chord1.jpg", "C"};
        private String [] Csus4 = {"Csus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/C-sus4-chord1.jpg", "C"};

        private String [] C11 = {"C11", "https://www.theguitarlesson.com/wp-content/uploads/chords/C11-chord1.jpg", "C"};
        private String [] C13 = {"C13", "https://www.theguitarlesson.com/wp-content/uploads/chords/C13-chord1.jpg", "C"};
        private String [] C5 = {"C5", "https://www.theguitarlesson.com/wp-content/uploads/chords/C5-chord1.jpg", "C"};
        private String [] C6_9 = {"C6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/C6-9-chord1.jpg", "C"};
        private String [] C6 = {"C6", "https://www.theguitarlesson.com/wp-content/uploads/chords/C6-chord1.jpg", "C"};

        private String [] C7_aug_b9 = {"C7-aug-b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/C7-aug-b9-chord1.jpg", "C"};
        private String [] C7_aug = {"C7-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/C7-aug-chord1.jpg", "C"};
        private String [] C7 = {"C7", "https://www.theguitarlesson.com/wp-content/uploads/chords/C7-chord1.jpg", "C"};
        private String [] C7_sharp_9 = {"C7-sharp-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/C7-sharp-9-chord1.jpg", "C"};
        private String [] C7_sus4 = {"C7-sus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/C7-sus4-chord1.jpg", "C"};

        private String [] C7b5 = {"C7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/C7b5-chord1.jpg", "C"};
        private String [] C7b9 = {"C7b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/C7b9-chord1.jpg", "C"};
        private String [] C9_aug = {"C9-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/C9-aug-chord1.jpg", "C"};
        private String [] C9 = {"C9", "https://www.theguitarlesson.com/wp-content/uploads/chords/C9-chord1.jpg", "C"};
        private String [] C9_sharp_11 = {"C9-sharp-11", "https://www.theguitarlesson.com/wp-content/uploads/chords/C9-sharp-11-chord1.jpg", "C"};

        private String [] C9b5 = {"C9b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/C9b5-chord1.jpg", "C"};
        private String [] Cm = {"Cm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm-chord1.jpg", "C"};
        private String [] Cm_maj7 = {"Cm-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm-maj7-chord1.jpg", "C"};
        private String [] Cm11 = {"Cm11", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm11-chord1.jpg", "C"};
        private String [] Cm6_9 = {"Cm6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm6-9-chord1.jpg", "C"};

        private String [] Cm6 = {"Cm6", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm6-chord1.jpg", "C"};
        private String [] Cm7 = {"Cm7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm7-chord1.jpg", "C"};
        private String [] Cm7b5 = {"Cm7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm7b5-chord1.jpg", "C"};
        private String [] Cm9 = {"Cm9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm9-chord1.jpg", "C"};
        private String [] Cm9_maj7 = {"Cm9-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cm9-maj7-chord1.jpg", "C"};

        private String [] Cmaj = {"C", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cmaj-chord1.jpg", "C"};
        private String [] Cmaj7 = {"Cmaj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cmaj7-chord1.jpg", "C"};
        private String [] Cmaj7b5 = {"Cmaj7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cmaj7b5-chord1.jpg", "C"};
        private String [] Cmaj9 = {"Cmaj9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Cmaj9-chord1.jpg", "C"};




        //D
        private String [] Dadd9 = {"Dadd9", "https://www.theguitarlesson.com/wp-content/uploads/chords/D-add9-chord1.jpg", "D"};
        private String [] Daug = {"Daug", "https://www.theguitarlesson.com/wp-content/uploads/chords/D-aug-chord1.jpg", "D"};
        private String [] Db5 = {"Db5", "https://www.theguitarlesson.com/wp-content/uploads/chords/D-b5-chord1.jpg", "D"};
        private String [] Ddim = {"Ddim", "https://www.theguitarlesson.com/wp-content/uploads/chords/D-dim-chord1.jpg", "D"};
        private String [] Dsus4 = {"Dsus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/D-sus4-chord1.jpg", "D"};

        private String [] D11 = {"D11", "https://www.theguitarlesson.com/wp-content/uploads/chords/D11-chord1.jpg", "D"};
        private String [] D13 = {"D13", "https://www.theguitarlesson.com/wp-content/uploads/chords/D13-chord1.jpg", "D"};
        private String [] D5 = {"D5", "https://www.theguitarlesson.com/wp-content/uploads/chords/D5-chord1.jpg", "D"};
        private String [] D6_9 = {"D6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/D6-9-chord1.jpg", "D"};
        private String [] D6 = {"D6", "https://www.theguitarlesson.com/wp-content/uploads/chords/D6-chord1.jpg", "D"};

        private String [] D7_aug_b9 = {"D7-aug-b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/D7-aug-b9-chord1.jpg", "D"};
        private String [] D7_aug = {"D7-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/D7-aug-chord1.jpg", "D"};
        private String [] D7 = {"D7", "https://www.theguitarlesson.com/wp-content/uploads/chords/D7-chord1.jpg", "D"};
        private String [] D7_sharp_9 = {"D7-sharp-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/D7-sharp-9-chord1.jpg", "D"};
        private String [] D7_sus4 = {"D7-sus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/D7-sus4-chord1.jpg", "D"};

        private String [] D7b5 = {"D7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/D7b5-chord1.jpg", "D"};
        private String [] D7b9 = {"D7b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/D7b9-chord1.jpg", "D"};
        private String [] D9_aug = {"D9-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/D9-aug-chord1.jpg", "D"};
        private String [] D9 = {"D9", "https://www.theguitarlesson.com/wp-content/uploads/chords/D9-chord1.jpg", "D"};
        private String [] D9_sharp_11 = {"D9-sharp-11", "https://www.theguitarlesson.com/wp-content/uploads/chords/D9-sharp-11-chord1.jpg", "D"};

        private String [] D9b5 = {"D9b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/D9b5-chord1.jpg", "D"};
        private String [] Dm = {"Dm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm-chord1.jpg", "D"};
        private String [] Dm_maj7 = {"Dm-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm-maj7-chord1.jpg", "D"};
        private String [] Dm11 = {"Dm11", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm11-chord1.jpg", "D"};
        private String [] Dm6_9 = {"Dm6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm6-9-chord1.jpg", "D"};

        private String [] Dm6 = {"Dm6", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm6-chord1.jpg", "D"};
        private String [] Dm7 = {"Dm7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm7-chord1.jpg", "D"};
        private String [] Dm7b5 = {"Dm7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm7b5-chord1.jpg", "D"};
        private String [] Dm9 = {"Dm9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm9-chord1.jpg", "D"};
        private String [] Dm9_maj7 = {"Dm9-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dm9-maj7-chord1.jpg", "D"};

        private String [] Dmaj = {"D", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dmaj-chord1.jpg", "D"};
        private String [] Dmaj7 = {"Dmaj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dmaj7-chord1.jpg", "D"};
        private String [] Dmaj7b5 = {"Dmaj7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dmaj7b5-chord1.jpg", "D"};
        private String [] Dmaj9 = {"Dmaj9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Dmaj9-chord1.jpg", "D"};




        //E
        private String [] Eadd9 = {"Eadd9", "https://www.theguitarlesson.com/wp-content/uploads/chords/E-add9-chord1.jpg", "E"};
        private String [] Eaug = {"Eaug", "https://www.theguitarlesson.com/wp-content/uploads/chords/E-aug-chord1.jpg", "E"};
        private String [] Eb5 = {"Eb5", "https://www.theguitarlesson.com/wp-content/uploads/chords/E-b5-chord1.jpg", "E"};
        private String [] Edim = {"Edim", "https://www.theguitarlesson.com/wp-content/uploads/chords/E-dim-chord1.jpg", "E"};
        private String [] Esus4 = {"Esus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/E-sus4-chord1.jpg", "E"};

        private String [] E11 = {"E11", "https://www.theguitarlesson.com/wp-content/uploads/chords/E11-chord1.jpg", "E"};
        private String [] E13 = {"E13", "https://www.theguitarlesson.com/wp-content/uploads/chords/E13-chord1.jpg", "E"};
        private String [] E5 = {"E5", "https://www.theguitarlesson.com/wp-content/uploads/chords/E5-chord1.jpg", "E"};
        private String [] E6_9 = {"E6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/E6-9-chord1.jpg", "E"};
        private String [] E6 = {"E6", "https://www.theguitarlesson.com/wp-content/uploads/chords/E6-chord1.jpg", "E"};

        private String [] E7_aug_b9 = {"E7-aug-b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/E7-aug-b9-chord1.jpg", "E"};
        private String [] E7_aug = {"E7-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/E7-aug-chord1.jpg", "E"};
        private String [] E7 = {"E7", "https://www.theguitarlesson.com/wp-content/uploads/chords/E7-chord1.jpg", "E"};
        private String [] E7_sharp_9 = {"E7-sharp-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/E7-sharp-9-chord1.jpg", "E"};
        private String [] E7_sus4 = {"E7-sus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/E7-sus4-chord1.jpg", "E"};

        private String [] E7b5 = {"E7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/E7b5-chord1.jpg", "E"};
        private String [] E7b9 = {"E7b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/E7b9-chord1.jpg", "E"};
        private String [] E9_aug = {"E9-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/E9-aug-chord1.jpg", "E"};
        private String [] E9 = {"E9", "https://www.theguitarlesson.com/wp-content/uploads/chords/E9-chord1.jpg", "E"};
        private String [] E9_sharp_11 = {"E9-sharp-11", "https://www.theguitarlesson.com/wp-content/uploads/chords/E9-sharp-11-chord1.jpg", "E"};

        private String [] E9b5 = {"E9b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/E9b5-chord1.jpg", "E"};
        private String [] Em = {"Em", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em-chord1.jpg", "E"};
        private String [] Em_maj7 = {"Em-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em-maj7-chord1.jpg", "E"};
        private String [] Em11 = {"Em11", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em11-chord1.jpg", "E"};
        private String [] Em6_9 = {"Em6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em6-9-chord1.jpg", "E"};

        private String [] Em6 = {"Em6", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em6-chord1.jpg", "E"};
        private String [] Em7 = {"Em7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em7-chord1.jpg", "E"};
        private String [] Em7b5 = {"Em7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em7b5-chord1.jpg", "E"};
        private String [] Em9 = {"Em9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em9-chord1.jpg", "E"};
        private String [] Em9_maj7 = {"Em9-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Em9-maj7-chord1.jpg", "E"};

        private String [] Emaj = {"E", "https://www.theguitarlesson.com/wp-content/uploads/chords/Emaj-chord1.jpg", "E"};
        private String [] Emaj7 = {"Emaj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Emaj7-chord1.jpg", "E"};
        private String [] Emaj7b5 = {"Emaj7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Emaj7b5-chord1.jpg", "E"};
        private String [] Emaj9 = {"Emaj9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Emaj9-chord1.jpg", "E"};




        //F
        private String [] Fadd9 = {"Fadd9", "https://www.theguitarlesson.com/wp-content/uploads/chords/F-add9-chord1.jpg", "F"};
        private String [] Faug = {"Faug", "https://www.theguitarlesson.com/wp-content/uploads/chords/F-aug-chord1.jpg", "F"};
        private String [] Fb5 = {"Fb5", "https://www.theguitarlesson.com/wp-content/uploads/chords/F-b5-chord1.jpg", "F"};
        private String [] Fdim = {"Fdim", "https://www.theguitarlesson.com/wp-content/uploads/chords/F-dim-chord1.jpg", "F"};
        private String [] Fsus4 = {"Fsus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/F-sus4-chord1.jpg", "F"};

        private String [] F11 = {"F11", "https://www.theguitarlesson.com/wp-content/uploads/chords/F11-chord1.jpg", "F"};
        private String [] F13 = {"F13", "https://www.theguitarlesson.com/wp-content/uploads/chords/F13-chord1.jpg", "F"};
        private String [] F5 = {"F5", "https://www.theguitarlesson.com/wp-content/uploads/chords/F5-chord1.jpg", "F"};
        private String [] F6_9 = {"F6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/F6-9-chord1.jpg", "F"};
        private String [] F6 = {"F6", "https://www.theguitarlesson.com/wp-content/uploads/chords/F6-chord1.jpg", "F"};

        private String [] F7_aug_b9 = {"F7-aug-b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/F7-aug-b9-chord1.jpg", "F"};
        private String [] F7_aug = {"F7-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/F7-aug-chord1.jpg", "F"};
        private String [] F7 = {"F7", "https://www.theguitarlesson.com/wp-content/uploads/chords/F7-chord1.jpg", "F"};
        private String [] F7_sharp_9 = {"F7-sharp-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/F7-sharp-9-chord1.jpg", "F"};
        private String [] F7_sus4 = {"F7-sus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/F7-sus4-chord1.jpg", "F"};

        private String [] F7b5 = {"F7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/F7b5-chord1.jpg", "F"};
        private String [] F7b9 = {"F7b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/F7b9-chord1.jpg", "F"};
        private String [] F9_aug = {"F9-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/F9-aug-chord1.jpg", "F"};
        private String [] F9 = {"F9", "https://www.theguitarlesson.com/wp-content/uploads/chords/F9-chord1.jpg", "F"};
        private String [] F9_sharp_11 = {"F9-sharp-11", "https://www.theguitarlesson.com/wp-content/uploads/chords/F9-sharp-11-chord1.jpg", "F"};

        private String [] F9b5 = {"F9b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/F9b5-chord1.jpg", "F"};
        private String [] Fm = {"Fm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm-chord1.jpg", "F"};
        private String [] Fm_maj7 = {"Fm-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm-maj7-chord1.jpg", "F"};
        private String [] Fm11 = {"Fm11", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm11-chord1.jpg", "F"};
        private String [] Fm6_9 = {"Fm6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm6-9-chord1.jpg", "F"};

        private String [] Fm6 = {"Fm6", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm6-chord1.jpg", "F"};
        private String [] Fm7 = {"Fm7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm7-chord1.jpg", "F"};
        private String [] Fm7b5 = {"Fm7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm7b5-chord1.jpg", "F"};
        private String [] Fm9 = {"Fm9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm9-chord1.jpg", "F"};
        private String [] Fm9_maj7 = {"Fm9-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fm9-maj7-chord1.jpg", "F"};

        private String [] Fmaj = {"F", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fmaj-chord1.jpg", "F"};
        private String [] Fmaj7 = {"Fmaj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fmaj7-chord1.jpg", "F"};
        private String [] Fmaj7b5 = {"Fmaj7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fmaj7b5-chord1.jpg", "F"};
        private String [] Fmaj9 = {"Fmaj9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Fmaj9-chord1.jpg", "F"};




        //G
        private String [] Gadd9 = {"Gadd9", "https://www.theguitarlesson.com/wp-content/uploads/chords/G-add9-chord1.jpg", "G"};
        private String [] Gaug = {"Gaug", "https://www.theguitarlesson.com/wp-content/uploads/chords/G-aug-chord1.jpg", "G"};
        private String [] Gb5 = {"Gb5", "https://www.theguitarlesson.com/wp-content/uploads/chords/G-b5-chord1.jpg", "G"};
        private String [] Gdim = {"Gdim", "https://www.theguitarlesson.com/wp-content/uploads/chords/G-dim-chord1.jpg", "G"};
        private String [] Gsus4 = {"Gsus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/G-sus4-chord1.jpg", "G"};

        private String [] G11 = {"G11", "https://www.theguitarlesson.com/wp-content/uploads/chords/G11-chord1.jpg", "G"};
        private String [] G13 = {"G13", "https://www.theguitarlesson.com/wp-content/uploads/chords/G13-chord1.jpg", "G"};
        private String [] G5 = {"G5", "https://www.theguitarlesson.com/wp-content/uploads/chords/G5-chord1.jpg", "G"};
        private String [] G6_9 = {"G6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/G6-9-chord1.jpg", "G"};
        private String [] G6 = {"G6", "https://www.theguitarlesson.com/wp-content/uploads/chords/G6-chord1.jpg", "G"};

        private String [] G7_aug_b9 = {"G7-aug-b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/G7-aug-b9-chord1.jpg", "G"};
        private String [] G7_aug = {"G7-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/G7-aug-chord1.jpg", "G"};
        private String [] G7 = {"G7", "https://www.theguitarlesson.com/wp-content/uploads/chords/G7-chord1.jpg", "G"};
        private String [] G7_sharp_9 = {"G7-sharp-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/G7-sharp-9-chord1.jpg", "G"};
        private String [] G7_sus4 = {"G7-sus4", "https://www.theguitarlesson.com/wp-content/uploads/chords/G7-sus4-chord1.jpg", "G"};

        private String [] G7b5 = {"G7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/G7b5-chord1.jpg", "G"};
        private String [] G7b9 = {"G7b9", "https://www.theguitarlesson.com/wp-content/uploads/chords/G7b9-chord1.jpg", "G"};
        private String [] G9_aug = {"G9-aug", "https://www.theguitarlesson.com/wp-content/uploads/chords/G9-aug-chord1.jpg", "G"};
        private String [] G9 = {"G9", "https://www.theguitarlesson.com/wp-content/uploads/chords/G9-chord1.jpg", "G"};
        private String [] G9_sharp_11 = {"G9-sharp-11", "https://www.theguitarlesson.com/wp-content/uploads/chords/G9-sharp-11-chord1.jpg", "G"};

        private String [] G9b5 = {"G9b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/G9b5-chord1.jpg", "G"};
        private String [] Gm = {"Gm", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm-chord1.jpg", "G"};
        private String [] Gm_maj7 = {"Gm-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm-maj7-chord1.jpg", "G"};
        private String [] Gm11 = {"Gm11", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm11-chord1.jpg", "G"};
        private String [] Gm6_9 = {"Gm6-9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm6-9-chord1.jpg", "G"};

        private String [] Gm6 = {"Gm6", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm6-chord1.jpg", "G"};
        private String [] Gm7 = {"Gm7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm7-chord1.jpg", "G"};
        private String [] Gm7b5 = {"Gm7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm7b5-chord1.jpg", "G"};
        private String [] Gm9 = {"Gm9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm9-chord1.jpg", "G"};
        private String [] Gm9_maj7 = {"Gm9-maj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gm9-maj7-chord1.jpg", "G"};

        private String [] Gmaj = {"G", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gmaj-chord1.jpg", "G"};
        private String [] Gmaj7 = {"Gmaj7", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gmaj7-chord1.jpg", "G"};
        private String [] Gmaj7b5 = {"Gmaj7b5", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gmaj7b5-chord1.jpg", "G"};
        private String [] Gmaj9 = {"Gmaj9", "https://www.theguitarlesson.com/wp-content/uploads/chords/Gmaj9-chord1.jpg", "G"};



        //chords by note
        private String [][] chordsA = {Aadd9, Aaug, Ab5, Adim, Asus4, A11, A13, A5, A6_9, A6,
                A7_aug_b9, A7_aug, A7, A7_sharp_9, A7_sus4, A7b5, A7b9, A9_aug, A9, A9_sharp_11,
                A9b5, Am, Am_maj7, Am11, Am6_9, Am6, Am7, Am7b5, Am9, Am9_maj7, Amaj, Amaj7, Amaj7b5,
                Amaj9};

        private String [][] chordsB = {Badd9, Baug, Bb5, Bdim, Bsus4, B11, B13, B5, B6_9, B6,
                B7_aug_b9, B7_aug, B7, B7_sharp_9, B7_sus4, B7b5, B7b9, B9_aug, B9, B9_sharp_11,
                B9b5, Bm, Bm_maj7, Bm11, Bm6_9, Bm6, Bm7, Bm7b5, Bm9, Bm9_maj7, Bmaj, Bmaj7, Bmaj7b5,
                Bmaj9};

        private String [][] chordsC = {Cadd9, Caug, Cb5, Cdim, Csus4, C11, C13, C5, C6_9, C6,
                C7_aug_b9, C7_aug, C7, C7_sharp_9, C7_sus4, C7b5, C7b9, C9_aug, C9, C9_sharp_11,
                C9b5, Cm, Cm_maj7, Cm11, Cm6_9, Cm6, Cm7, Cm7b5, Cm9, Cm9_maj7, Cmaj, Cmaj7, Cmaj7b5,
                Cmaj9};

        private String [][] chordsD = {Dadd9, Daug, Db5, Ddim, Dsus4, D11, D13, D5, D6_9, D6,
                D7_aug_b9, D7_aug, D7, D7_sharp_9, D7_sus4, D7b5, D7b9, D9_aug, D9, D9_sharp_11,
                D9b5, Dm, Dm_maj7, Dm11, Dm6_9, Dm6, Dm7, Dm7b5, Dm9, Dm9_maj7, Dmaj, Dmaj7, Dmaj7b5,
                Dmaj9};

        private String [][] chordsE = {Eadd9, Eaug, Eb5, Edim, Esus4, E11, E13, E5, E6_9, E6,
                E7_aug_b9, E7_aug, E7, E7_sharp_9, E7_sus4, E7b5, E7b9, E9_aug, E9, E9_sharp_11,
                E9b5, Em, Em_maj7, Em11, Em6_9, Em6, Em7, Em7b5, Em9, Em9_maj7, Emaj, Emaj7, Emaj7b5,
                Emaj9};

        private String [][] chordsF = {Fadd9, Faug, Fb5, Fdim, Fsus4, F11, F13, F5, F6_9, F6,
                F7_aug_b9, F7_aug, F7, F7_sharp_9, F7_sus4, F7b5, F7b9, F9_aug, F9, F9_sharp_11,
                F9b5, Fm, Fm_maj7, Fm11, Fm6_9, Fm6, Fm7, Fm7b5, Fm9, Fm9_maj7, Fmaj, Fmaj7, Fmaj7b5,
                Fmaj9};

        private String [][] chordsG = {Gadd9, Gaug, Gb5, Gdim, Gsus4, G11, G13, G5, G6_9, G6,
                G7_aug_b9, G7_aug, G7, G7_sharp_9, G7_sus4, G7b5, G7b9, G9_aug, G9, G9_sharp_11,
                G9b5, Gm, Gm_maj7, Gm11, Gm6_9, Gm6, Gm7, Gm7b5, Gm9, Gm9_maj7, Gmaj, Gmaj7, Gmaj7b5,
                Gmaj9};


        //all chords
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
