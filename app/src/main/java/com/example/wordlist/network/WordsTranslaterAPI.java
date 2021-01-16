package com.example.wordlist.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

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

        public String getTranslatedText() {
            return translatedText;
        }
    }
}
