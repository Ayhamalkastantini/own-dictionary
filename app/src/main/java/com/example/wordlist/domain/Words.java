package com.example.wordlist.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wordTable")

/**
 * The class Words
 */
public class Words {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String wordName;
    private String wordMeaning;
    @ColumnInfo(name = "Type")
    private String wordType;



    /**
     *
     * It is a constructor.
     *
     * @param wordName  the word name
     * @param wordMeaning  the word meaning
     * @param wordType  the word type
     */
    public Words(String wordName, String wordMeaning, String wordType) {

        this.wordName = wordName;
        this.wordMeaning = wordMeaning;
        this.wordType = wordType;
    }


    /**
     *
     * Sets the identifier
     *
     * @param id  the id
     */
    public void setId(int id) {

        this.id = id;
    }


    /**
     *
     * Gets the identifier
     *
     * @return the identifier
     */
    public int getId() {

        return id;
    }


    /**
     *
     * Gets the word name
     *
     * @return the word name
     */
    public String getWordName() {

        return wordName;
    }


    /**
     *
     * Gets the word meaning
     *
     * @return the word meaning
     */
    public String getWordMeaning() {

        return wordMeaning;
    }


    /**
     *
     * Gets the word type
     *
     * @return the word type
     */
    public String getWordType() {

        return wordType;
    }
}
