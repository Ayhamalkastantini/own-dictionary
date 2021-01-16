package com.example.wordlist.ui.api;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.wordlist.R;
import com.example.wordlist.network.WordsTranslaterAPI;
import com.example.wordlist.network.WordsTranslaterServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TranslateActivity extends AppCompatActivity{
    static final String BASE_URL = "https://google-translate1.p.rapidapi.com/";
    private EditText textToTranslate;
    private Button translateTextButton;
    private TextView translatedText;

    //view Model for translate Activity
    private TranslateViewModel translateViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

        textToTranslate = findViewById(R.id.textToTranslate);
        translatedText = findViewById(R.id.translatedText);
        translateTextButton = findViewById(R.id.translateText);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_exit);

        Intent i = getIntent();

        setTitle("Google Translate");

        translateViewModel = ViewModelProviders.of(this).get(TranslateViewModel.class);
        translateTextButton.setOnClickListener(view -> {
            translate();
        });

    }




    public void translate() {
        String translateText = textToTranslate.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WordsTranslaterServices wordsTranslaterServices = retrofit.create(WordsTranslaterServices.class);

        Call<WordsTranslaterAPI> call = wordsTranslaterServices.getTranslatedText(translateText, "nl", "en");
        call.enqueue(new Callback<WordsTranslaterAPI>() {
            @Override
            public void onResponse(Call<WordsTranslaterAPI> call, Response<WordsTranslaterAPI> response) {
                if(response.body() != null){
                    WordsTranslaterAPI wordsTranslaterAPI = response.body();
                    List translations = wordsTranslaterAPI.data.translations;
                    WordsTranslaterAPI.Translation translation = (WordsTranslaterAPI.Translation) translations.get(0);
                    translatedText.setText(translation.getTranslatedText());
                }
            }

            @Override
            public void onFailure(Call<WordsTranslaterAPI> call, Throwable t) {
                System.out.println(t.toString());
                call.cancel();
            }
        });
    }


}
