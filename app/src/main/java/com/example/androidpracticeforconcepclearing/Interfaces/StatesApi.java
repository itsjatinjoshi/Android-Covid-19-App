package com.example.androidpracticeforconcepclearing.Interfaces;

import com.example.androidpracticeforconcepclearing.Model.States;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatesApi {

    @GET("states")
    Call<List<States>> getStates();
}
