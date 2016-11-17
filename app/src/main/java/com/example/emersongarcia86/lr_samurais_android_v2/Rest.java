package com.example.emersongarcia86.lr_samurais_android_v2;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fabio on 08/04/2016.
 */

public class Rest {
    private static Rest ourInstance = new Rest();

    public static Rest getInstance() {
        return ourInstance;
    }

    private Rest() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("http://tsitomcat2.azurewebsites.net/")
                .build();
    }

    public Retrofit get() {
        return retrofit;
    }

    private Retrofit retrofit;
}
