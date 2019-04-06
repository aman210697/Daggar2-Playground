package com.aman.dagger2playground.interfaces;

import com.aman.dagger2playground.model.RandomUsers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface RandomUsersApi {

    @GET("api")
    Call<RandomUsers> getRandomUsers(@Query("results") int size);
}