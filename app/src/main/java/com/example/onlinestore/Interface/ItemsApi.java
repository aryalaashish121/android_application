package com.example.onlinestore.Interface;

import com.example.onlinestore.Model.ItemsDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ItemsApi {

    @GET("/getItems")
    Call<List<ItemsDetail>> getItemDetail();
}
