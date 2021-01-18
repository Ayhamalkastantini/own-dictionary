package com.example.wordlist.domain;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wordlist.database.WordRoomDb;
import com.example.wordlist.database.WordsDao;

import java.util.List;


/**
 * The class Words repository
 */
public class WordsRepository {

    private WordsDao mWordsDao;

    private LiveData<List<Words>> getAllWords;



    /**
     *
     * It is a constructor.
     *
     * @param app  the application
     */
    public WordsRepository (Application app)
    {

        WordRoomDb db = WordRoomDb.getInstance(app);
        mWordsDao = db.wordsDao();
        getAllWords = mWordsDao.getAllWords();
    }

    //operation

    //insert


    /**
     *
     * Insert
     *
     * @param word  the word
     */
    public void insert(Words word)
    {

        new InsertAsyncTask(mWordsDao).execute(word);
    }
    //delete


    /**
     *
     * Delete
     *
     * @param word  the word
     */
    public void delete(Words word)
    {

        new DeleteAsyncTask(mWordsDao).execute(word);
    }

    //update


    /**
     *
     * Update
     *
     * @param word  the word
     */
    public void update(Words word)
    {

        new UpdateAsyncTask(mWordsDao).execute(word);
    }

    //getallwords


    /**
     *
     * Gets the all words
     *
     * @return the all words
     */
    public LiveData<List<Words>> getAllWords()
    {

        return getAllWords;
    }

    //delete all words


    /**
     *
     * Delete all words
     *
     */
    public void deleteAllWords()
    {

        new DeleteAsyncTask(mWordsDao).execute();
    }


    private static class InsertAsyncTask extends AsyncTask<Words, Void, Void>{

        private WordsDao mWordsDao;



        /**
         *
         * Insert async task
         *
         * @param wordsDao  the words dao
         * @return public
         */
        public InsertAsyncTask(WordsDao wordsDao)
        {

            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.insert(words[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Words, Void, Void>{

        private WordsDao mWordsDao;



        /**
         *
         * Delete async task
         *
         * @param wordsDao  the words dao
         * @return public
         */
        public DeleteAsyncTask(WordsDao wordsDao)
        {

            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.delete(words[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Words, Void, Void>{

        private WordsDao mWordsDao;



        /**
         *
         * Update async task
         *
         * @param wordsDao  the words dao
         * @return public
         */
        public UpdateAsyncTask(WordsDao wordsDao)
        {

            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.update(words[0]);
            return null;
        }
    }


    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void>{

        private WordsDao mWordsDao;



        /**
         *
         * Delete all words async task
         *
         * @param wordsDao  the words dao
         * @return public
         */
        public DeleteAllWordsAsyncTask(WordsDao wordsDao)
        {

            mWordsDao = wordsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllWords();
            return null;
        }
    }

}
