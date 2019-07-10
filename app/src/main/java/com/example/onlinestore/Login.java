package com.example.onlinestore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinestore.BBL.UserLoginBBL;
import com.example.onlinestore.Interface.UsersApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText username, password;
    Button bt_login;
    UsersApi api;
    TextView linkRegister;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);
        linkRegister = findViewById(R.id.linkRegister);

        bt_login = findViewById(R.id.btn_login);
        bt_login.setOnClickListener(this);
        linkRegister.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_login) {
         validation_Login();

          login();

        }

        if(v.getId() == R.id.linkRegister){
            Intent register = new Intent(Login.this,Register.class);
            startActivity(register);
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

    public void validation_Login() {
        if (TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Please enter username");
            username.requestFocus();
            vibration_Mobile(500);
        } else if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Please enter password");
            password.requestFocus();
            vibration_Mobile(500);
        }
    }

    public void vibration_Mobile(int duration){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(duration);

    }

    @Override
    public void onBackPressed() {

        Toast.makeText(this, "You havenot Logged in Yet.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    }

    public void login(){
        final UserLoginBBL userLoginBBL= new UserLoginBBL(username.getText().toString(), password.getText().toString());
        StrictMode();
        if(userLoginBBL.checkLogin()!=null){
            preferences = (Login.this).getSharedPreferences("UserData",0);
               editor = preferences.edit();
            Toast.makeText(Login.this, ",Logged in", Toast.LENGTH_SHORT).show();
            vibration_Mobile(500);

                  editor.putString("token", userLoginBBL.checkLogin().body().getToken());
                  editor.putString("uid", userLoginBBL.checkLogin().body().get_id());

                  editor.commit();

                  Toast.makeText(Login.this, "Logged in" + userLoginBBL.checkLogin().body().getToken(), Toast.LENGTH_SHORT).show();

                  Intent intent = new Intent(Login.this, MainActivity.class);
                  startActivity(intent);
                  finish();
        }
    }

    public void StrictMode(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

}
