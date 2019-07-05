package com.example.onlinestore.Interface;

import com.example.onlinestore.Model.ItemsDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersApi {

    @FormUrlEncoded
    @POST("user/userRegistration")
    Call<String> registerUser(@Field("userName") String name, @Field("userEmail") String email, @Field("userPassword") String password,@Field("userImage") String userImage, @Field("city") String city, @Field("postal") String postal, @Field("userAddress1") String address1, @Field("userAddress2") String address2);


    @FormUrlEncoded
    @POST("user/userLogin")
    Call<String> userVerification(@Field("userEmail") String username, @Field("userPassword") String password);

    @GET("product/displayAllProduct")
    Call<List<ItemsDetail>> getItemDetail();

}

