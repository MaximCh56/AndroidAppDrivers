package com.example.pk1.driverstable.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class APIClient {
    public static APIInterface getApi(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+url+"/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(APIInterface.class);

    }
}
