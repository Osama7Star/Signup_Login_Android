package com.alnajim.osama.signuplogin.Data;
/**
 * Created by Osama Alnajm on 17-Feb-20.
 * @email osama.alnagem@gmail.com
 *
 */

import com.alnajim.osama.signuplogin.Model.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LibraryInterface {

    @GET("login")
    Call<List<UserModel>> LogIn(@Query("userName") String userName, @Query("password") String password);



    @GET("signup")
    Call<String> SignUp(@Query("fullName") String fullName, @Query("userName") String userName,@Query("email") String email, @Query("password") String password );

    @GET("checkemail")
    Call<List<UserModel>> CheckEmail (@Query("email")String email );


}
