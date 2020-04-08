package com.antailbaxt3r.restapidemo.retrofit;

import com.antailbaxt3r.restapidemo.models.RandomUser;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitAPIInterface {

    @GET("api")
    Call<RandomUser> getUser(@QueryMap Map<String, Integer> map);

}