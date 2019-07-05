package com.example.onlinestore;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.onlinestore.Interface.UsersApi;
import com.example.onlinestore.Model.UserDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateProfile extends AppCompatActivity implements View.OnClickListener {

    EditText username,useremail,city,postal,address1,address2;
    ImageView userImage;
    String userImageName;
    String userid,tok;
    Button btn_update;
    UsersApi usersApi;
    public static final String BASE_URL = "http://10.0.2.2:3000/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);


        username = findViewById(R.id.userName);
        useremail = findViewById(R.id.userEmail);
        city = findViewById(R.id.city);
        postal = findViewById(R.id.postal);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        userImage = findViewById(R.id.updateuserprofile);

        //installizing button
        btn_update = findViewById(R.id.btn_update);
        //adding click listner in button btn_update

        btn_update.setOnClickListener(this);
        loaduserdata();
    }

    @Override
    public void onClick(View view) {

    }
    private void loaduserdata(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        usersApi = retrofit.create(UsersApi.class);
        SharedPreferences preferences=getSharedPreferences("UserData",0);
        userid = preferences.getString("uid",null);
        Toast.makeText(UpdateProfile.this, "Update user here... "+userid, Toast.LENGTH_LONG).show();
        final Call<UserDetails> userdata= usersApi.profiledata(userid);
        userdata.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                UserDetails userdetails = response.body();

                username.setText(userdetails.getUserName());
                useremail.setText(userdetails.getUserEmail());
                city.setText(userdetails.getCity());
                postal.setText(userdetails.getPostal());
                address1.setText(userdetails.getAddress1());
                address2.setText(userdetails.getAddress2());

                Toast.makeText(UpdateProfile.this, "city value +"+userdetails.getCity(), Toast.LENGTH_SHORT).show();
                StrictMode();
                try{
                    String imgurl = BASE_URL+userdetails.getUserImage();
                    URL url = new URL(imgurl);
                    userImage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Toast.makeText(UpdateProfile.this, "Failure here : "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void StrictMode() {
        android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);
    }

}
