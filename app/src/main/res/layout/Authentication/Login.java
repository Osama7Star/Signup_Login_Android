package com.alnajim.osama.library.UI.Authentication;

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

import com.alnajim.osama.library.Models.UserModel;
import com.alnajim.osama.library.R;
import com.alnajim.osama.library.UI.Authentication.Signup;
import com.alnajim.osama.library.UI.MainActivity;
import com.alnajim.osama.library.Utilites.SessionManager;
import com.alnajim.osama.library.Utilites.SqliteHandler;
import com.alnajim.osama.library.ViewModels.LibraryViewModel;

import java.util.List;

public class Login extends AppCompatActivity {
    LibraryViewModel libraryViewModel;
    EditText userName,password;
    ProgressBar progressBar ;
    private SessionManager session;
    private SqliteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);

        db = new SqliteHandler(getApplicationContext());
        userName = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);
        progressBar = findViewById(R.id.progressbar);

        // Session manager
        session = new SessionManager(getApplicationContext());

        try{
            Intent intent = getIntent();
            String userNameStr = intent.getStringExtra("userName");
            userName.setText(userNameStr);
        }
        catch (Exception e )
        {}
    }


    public void LogIn(View view) {
        String userNameStr = userName.getText().toString();
        String passwordStr = password.getText().toString();

        if (userNameStr.equals("") || passwordStr.equals("")) {
            Toast.makeText(this, "Fill All Fields", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);

            libraryViewModel.LogIn(userNameStr, passwordStr);

            libraryViewModel.UserInformationLiveData.observe(this, new Observer<List<UserModel>>() {
                @Override
                public void onChanged(List<UserModel> userModels) {
                    try {
                      //  db.addUser(userModels.get(0).getUserId());
                        startActivity(new Intent(Login.this, MainActivity.class));

                        String userId    = userModels.get(0).getUserId();
                        String userName  =  userModels.get(0).getUserName();
                        session.setLogin(true,userId,userName);
                        finish();
                    } catch (Exception e) {
                        Toast.makeText(Login.this,
                                "خطأ في كلمة السر او إسم المستخدم", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        Log.i("loginerror", "onChanged: "+e.getMessage());



                    }
                }
            });
        }
    }

    public void BackToRegister(View view){startActivity(new Intent(this, Signup.class));}

}
