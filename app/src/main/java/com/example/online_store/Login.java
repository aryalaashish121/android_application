package com.example.online_store;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.online_store.Interface.UsersApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button bt_login;
    UsersApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        bt_login = findViewById(R.id.btn_login);
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
            validation_Login();
            if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                Intent adminlogin = new Intent(Login.this, Cart.class);
                startActivity(adminlogin);
                Toast.makeText(Login.this, "Welcome to Admin Dashboard!", Toast.LENGTH_SHORT).show();
            }
            else{
                login();
            }

        }

    }
    private void createInstance(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(UsersApi.class);
    }
    public void login() {
        createInstance();
        Call<String> checkUSer = api.userVerification(username.getText().toString(), password.getText().toString());
        checkUSer.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().equals("login")) {
                    Intent userdash = new Intent(Login.this, MainActivity.class);
                    startActivity(userdash);
                    Toast.makeText(Login.this, "Login Sucessful", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(Login.this, "Login Denied!", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void validation_Login() {
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Please enter username");
            username.requestFocus();
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Please enter password");
            password.requestFocus();
        }
    }


}
