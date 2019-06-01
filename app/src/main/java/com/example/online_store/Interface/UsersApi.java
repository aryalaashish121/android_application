package com.example.online_store.Interface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UsersApi {

    @FormUrlEncoded
    @POST("register_user")
    Call<String> registerUser(@Field("fname") String first_name, @Field("lname") String last_name, @Field("username") String username, @Field("password") String password);


    @FormUrlEncoded
    @POST("loginUser")
    Call<String> userVerification(@Field("username") String username, @Field("password") String password);


}

