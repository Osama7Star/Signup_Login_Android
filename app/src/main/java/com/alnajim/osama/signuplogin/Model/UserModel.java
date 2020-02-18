package com.alnajim.osama.signuplogin.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Osama Alnajm on 17-Feb-20.
 */
public class UserModel
{

    @SerializedName("userId")
    private String userId ;
    @SerializedName("fullName")
    private String fullName ;
    @SerializedName("userName")
    private String userName ;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password ;

    public UserModel(String userId ,String fullName,String userName, String email, String password) {

        this.userId   = userId;
        this.fullName = fullName;
        this.userName = userName;
        this.email    = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
