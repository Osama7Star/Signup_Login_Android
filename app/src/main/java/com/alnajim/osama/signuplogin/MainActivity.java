package com.alnajim.osama.signuplogin;

/**
 * Created by Osama Alnajm on 17-Feb-20.
 * Email osama.alnagem@gmail.com
 * Twitter @osama7star
 *
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alnajim.osama.signuplogin.Authentication.Signup;
import com.alnajim.osama.signuplogin.Utilities.SessionManager;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager ;
    TextView userName ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.tvUserName);
        sessionManager = new SessionManager(this);

        /// CHECK IF THE USER IS LOGGING OR NOT
        if (!sessionManager.isLoggedIn())
        {
           startActivity(new Intent(this, Signup.class));
        }
        else
        {
            userName.setText("Welcome "+sessionManager.GetFullName());
        }

    }


    public void Logout(View view)
    {
        sessionManager.setLogin(false,"","");
        startActivity(new Intent(MainActivity.this,Signup.class));
    }
}
