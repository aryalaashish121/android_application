package com.example.onlinestore.BBL;

import com.example.onlinestore.Controller.CreateInstance;
import com.example.onlinestore.Interface.UsersApi;
import com.example.onlinestore.Model.Tokenauth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLoginBBL {
    UsersApi usersApi;

    CreateInstance createInstance;
    private String userEmail;
    private String userPassword;
    public Response<Tokenauth> tokenauthResponse;

    public UserLoginBBL(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public Response<Tokenauth> checkLogin(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usersApi = retrofit.create(UsersApi.class);

        Call<Tokenauth> tokenauthCall = usersApi.userVerification(userEmail,userPassword);
        try{
            tokenauthResponse = tokenauthCall.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tokenauthResponse;
    }


}
