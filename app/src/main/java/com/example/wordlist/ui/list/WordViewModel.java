package com.example.wordlist.ui.list;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wordlist.domain.Words;
import com.example.wordlist.domain.WordsRepository;

import java.util.List;


/**
 * The class Word view model extends android view model
 */
public class WordViewModel extends AndroidViewModel {

    private WordsRepository mRepository;

    private LiveData<List<Words>> mAllWords;

    /**
     *
     * Word view model
     *
     * @param Application  the application
     * @return public
     */
    public WordViewModel(@NonNull Application application) {


        super(application);
        mRepository = new WordsRepository(application);
        mAllWords = mRepository.getAllWords();
    }



    /**
     *
     * Insert
     *
     * @param word  the word
     */
    public void insert(Words word) {

        mRepository.insert(word);
    }


    /**
     *
     * Delete
     *
     * @param word  the word
     */
    public void delete(Words word) {

        mRepository.delete(word);
    }


    /**
     *
     * Update
     *
     * @param word  the word
     */
    public void update(Words word) {

        mRepository.update(word);
    }


    /**
     *
     * Delete all words
     *
     */
    public void deleteAllWords() {

        mRepository.deleteAllWords();
    }




    /**
     *
     * Gets the all words
     *
     * @return the all words
     */
    public LiveData<List<Words>> getAllWords() {

        return mAllWords;
    }
}
