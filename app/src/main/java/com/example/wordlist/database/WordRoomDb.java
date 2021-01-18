package com.example.wordlist.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wordlist.domain.Words;

@Database(entities = Words.class, version = 1)

/**
 * The class Abstract word room db extends room database
 */
public abstract class WordRoomDb extends RoomDatabase {

    private static WordRoomDb instance;

    /**
     *
     * Words dao
     *
     * @param //Singlton
        the // singlton

     * @return WordsDao
     * @throws ;

    //Singlton
    public static synchronized WordRoomDb getInstance(Context context
     */
    public abstract WordsDao wordsDao();

    // Singlton
    public static synchronized WordRoomDb getInstance(Context context) {

        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WordRoomDb.class, "word3-database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override

/**
 *
 * On create
 *
 * @param SupportSQLiteDatabase  the support SQ lite database
 */
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }

    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void,Void, Void>
    {
        private WordsDao mWordsDao;

        PopulateDataAsyncTask(WordRoomDb db)
        {
            mWordsDao = db.wordsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.insert(new Words("book", "Book", "noun"));
            mWordsDao.insert(new Words("book", "Book", "noun"));
            mWordsDao.insert(new Words("book", "Book", "noun"));
            return null;
        }
    }

}
