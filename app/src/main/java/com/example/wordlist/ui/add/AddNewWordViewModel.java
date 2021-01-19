package com.example.wordlist.ui.add;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.wordlist.domain.Words;
import com.example.wordlist.domain.WordsRepository;


/**
 * The class Add new word view model extends android view model
 */
public class AddNewWordViewModel extends AndroidViewModel {

    private WordsRepository mRepository;


    /**
     *
     * Add new word view model
     *
     * @param Application  the application
     * @return public
     */
    public AddNewWordViewModel(@NonNull Application application) {


        super(application);
        mRepository = new WordsRepository(application);
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
     * Update
     *
     * @param word  the word
     */
    public void update(Words word) {

        mRepository.update(word);
    }

}
