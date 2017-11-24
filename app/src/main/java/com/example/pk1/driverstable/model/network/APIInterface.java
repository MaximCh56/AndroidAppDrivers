package com.example.pk1.driverstable.model.network;


import com.example.pk1.driverstable.model.POJO.Category;
import com.example.pk1.driverstable.model.POJO.Driver;
import com.example.pk1.driverstable.model.POJO.ServerAnswer;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {
    @FormUrlEncoded
    @POST("search")
    Single<List<Driver>> doGetListDrivers(@Field("search") String search);

    @Headers("Content-Type: application/json")
    @POST("edit")
    Single<ServerAnswer> editDriver(@Body Driver driver);

    @GET("category")
    Single<Category> doGetListCategory();
}
