package com.example.onlinestore;

import com.example.onlinestore.BBL.UserLoginBBL;
import com.example.onlinestore.BBL.UserRegistrationBBL;

import org.junit.Assert;
import org.junit.Test;

public abstract class Logintest {

    @Test
    public void testLogin(){
        UserLoginBBL loginBBL = new UserLoginBBL();
        Boolean result = loginBBL.checkUser("aryalaashish121@gmail.com","changedpass");
        Assert.assertEquals(true,result);
    }

@Test
    public void RegisterUserTest(){
    UserRegistrationBBL registrationBBL = new UserRegistrationBBL("Zonal","zo@gmail.com","passs","image.jpg","Zora","99220","address1","address2");


    }
    }
