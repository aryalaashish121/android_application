package com.example.onlinestore.Model;

public class UserDetails {
    private String _id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userImage;
    private String city;
    private String postal;
    private String address1;
    private String address2;

    public UserDetails( String userName, String userEmail, String userPassword, String userImage, String city, String postal, String address1, String address2) {

        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userImage = userImage;
        this.city = city;
        this.postal = postal;
        this.address1 = address1;
        this.address2 = address2;
    }

    public String getUserid() {
        return _id;
    }

    public void setUserid(String _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
