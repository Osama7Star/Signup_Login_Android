package com.alnajim.osama.signuplogin.Data;
/**
 * Created by Osama Alnajm on 17-Feb-20.
 * @email osama.alnagem@gmail.com
 *
 */

import com.alnajim.osama.signuplogin.Model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LibraryClient {
    private static final String BASE_URL = "http://osama.maanorg.org/api/web/v1/recipes/";

    private LibraryInterface libraryInterface;
    private static LibraryClient INSTANCE;

    public LibraryClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        libraryInterface = retrofit.create(LibraryInterface.class);
    }

    public static LibraryClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new LibraryClient();
        }
        return INSTANCE;
    }



    public Call<String> SignUp(String fullName, String userName,String email, String password) {
        return libraryInterface.SignUp(fullName, userName, email,password);
    }

    public Call<List<UserModel>> Login(String email, String password) {
        return libraryInterface.LogIn( email,password);
    }

    public Call<List<UserModel>> CheckEmail(String email){
        return libraryInterface.CheckEmail(email);
    }


}
