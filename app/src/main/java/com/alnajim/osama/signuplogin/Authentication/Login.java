package com.alnajim.osama.signuplogin.Authentication;
/**
 * Created by Osama Alnajm on 17-Feb-20.
 * @email osama.alnagem@gmail.com
 * Twitter @osama7star
 */
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alnajim.osama.signuplogin.MainActivity;
import com.alnajim.osama.signuplogin.Model.UserModel;
import com.alnajim.osama.signuplogin.ViewModels.LibraryViewModel;
import com.alnajim.osama.signuplogin.R;
import com.alnajim.osama.signuplogin.Utilities.SessionManager;

import java.util.List;

public class Login extends AppCompatActivity {
    LibraryViewModel libraryViewModel;
    EditText email,password;
    ProgressBar progressBar ;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        progressBar = findViewById(R.id.progressbar);

        // Session manager
        session = new SessionManager(getApplicationContext());

        try{
            Intent intent = getIntent();
            String emailStr = intent.getStringExtra("email");
            email.setText(emailStr);
        }
        catch (Exception e )
        {}
    }


    public void LogIn(View view) {
        String userNameStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        if (userNameStr.equals("") || passwordStr.equals("")) {
            Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);

            libraryViewModel.LogIn(userNameStr, passwordStr);

            libraryViewModel.LoginLiveData.observe(this, new Observer<List<UserModel>>() {
                @Override
                public void onChanged(List<UserModel> userModels) {
                    try {

                        if (!userModels.get(0).getUserId().isEmpty())
                        {
                            Toast.makeText(Login.this,
                                    "Login Successfully ", Toast.LENGTH_SHORT).show();
                            String userId    =  userModels.get(0).getUserId();
                            String userName  =  userModels.get(0).getFullName();
                            session.setLogin(true,userId,userName);
                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();
                        }


                    } catch (Exception e) {
                        Toast.makeText(Login.this,
                                "Error in the Email Or Password ", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Log.i("loginerror", "onChanged: "+e.getMessage());

                        Log.i("aaa", e.getMessage());


                    }
                }
            });
        }
    }

    public void BackToRegister(View view){startActivity(new Intent(this, Signup.class));}

}
