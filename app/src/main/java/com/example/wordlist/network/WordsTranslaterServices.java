package com.example.wordlist.network;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface WordsTranslaterServices {

    @Headers({
            "x-rapidapi-key: 2d00fa31f4mshd55c377efd56320p1a7914jsn2b316e458a8b",
            "accept-encoding: application/gzip",
            "x-rapidapi-host: google-translate1.p.rapidapi.com",
            "useQueryString: true",
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("language/translate/v2")
    Call<WordsTranslaterAPI> getTranslatedText(@Field("q") String textToTranslate, @Field("source") String source, @Field("target") String target );
}
