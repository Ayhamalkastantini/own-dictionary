package com.example.wordlist.network;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface WordsTranslaterServices {

    @Headers({
            "x-rapidapi-key: ba88cac567msh9e877ec44635ea0p1bb4d1jsnb329d93fb2de",
            "accept-encoding: application/gzip",
            "x-rapidapi-host: google-translate1.p.rapidapi.com",
            "useQueryString: true",
            "content-type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("language/translate/v2")
    Call<WordsTranslaterAPI> getTranslatedText(@Field("q") String textToTranslate, @Field("source") String source, @Field("target") String target );
}
