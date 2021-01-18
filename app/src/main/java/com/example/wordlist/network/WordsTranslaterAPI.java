package com.example.wordlist.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * The class Words translater AP I
 */
public class WordsTranslaterAPI {

    @SerializedName("data")
    @Expose
    public Data data;

    public class Data {
        @SerializedName("translations")
        @Expose
        public List<Translation> translations;
    }

    public class Translation {
        @SerializedName("translatedText")
        @Expose
        public String translatedText;


        /**
         *
         * Gets the translated text
         *
         * @return the translated text
         */
        public String getTranslatedText() {

            return translatedText;
        }
    }
}
