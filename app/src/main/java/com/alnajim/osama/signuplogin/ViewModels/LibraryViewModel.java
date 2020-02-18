package com.alnajim.osama.signuplogin.ViewModels;


import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.alnajim.osama.signuplogin.Data.LibraryClient;
import com.alnajim.osama.signuplogin.Model.UserModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LibraryViewModel extends ViewModel
{

    public MutableLiveData<List<UserModel>>     LoginLiveData    = new MutableLiveData<>();
    public MutableLiveData<String>              SignUpLiveData      = new MutableLiveData<>();
    public MutableLiveData<List<UserModel>>     CheckEmailLivedata      = new MutableLiveData<>();



    public void  SignUp(String fullName ,String userName,String email,String password){
        LibraryClient.getINSTANCE().SignUp(fullName , userName,email, password).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SignUpLiveData.setValue(response.body());
                Log.i("testtest", "onResponse: "+response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("testtest", "onResponse: "+t.getMessage());

            }
        });
    }

    public void LogIn(String email,String password)
    {
        LibraryClient.getINSTANCE().Login(email,password).enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                LoginLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });
    }

    public void CheckEmail(String email)
    {
        LibraryClient.getINSTANCE().CheckEmail(email).enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                CheckEmailLivedata.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                Log.i("testagainagain", t.getMessage());

            }
        });
    }

}