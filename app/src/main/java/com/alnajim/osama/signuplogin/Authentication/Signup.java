package com.alnajim.osama.signuplogin.Authentication;
/**
 * Created by Osama Alnajm on 17-Feb-20.
 * @email osama.alnagem@gmail.com
 * Twitter @osama7star
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.alnajim.osama.signuplogin.Model.UserModel;
import com.alnajim.osama.signuplogin.R;
import com.alnajim.osama.signuplogin.ViewModels.LibraryViewModel;

import java.util.List;

public class Signup extends AppCompatActivity {

    LibraryViewModel libraryViewModel;
    ProgressBar progressBar ;
    Context context ;
    Button register ;

    EditText fullName,userName,email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        progressBar = findViewById(R.id.progressbar);
        register = findViewById(R.id.btnRegister);
        context = this;
        libraryViewModel = ViewModelProviders.of(this).get(LibraryViewModel.class);
        InitView();
    }



        public void SignUp(View view)
        {

            final String fullNameStr = fullName.getText().toString().toLowerCase();
            String userNameStr1 = userName.getText().toString();
            final String userNameStr = userNameStr1.replace(" ", "");
            final String passwordStr = password.getText().toString();
            final String emailStr = email.getText().toString().replace(" ", "");


            if (fullNameStr.equals("") || userNameStr.equals("") || passwordStr.equals("") || emailStr.equals("") )
            {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();

            }
            else {
                register.setEnabled(true);

                if (userNameStr.length()<5)
                {
                    Toast.makeText(context, "User Name Must Be More thant 5 Letters", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    progressBar.setVisibility(View.VISIBLE);
                    libraryViewModel.CheckEmail(emailStr);
                    libraryViewModel.CheckEmailLivedata.observe(this, new Observer<List<UserModel>>() {
                        @Override
                        public void onChanged(List<UserModel> userModels)
                        {
                            try {
                                if (userModels.size()>0)
                                {
                                    Toast.makeText(Signup.this, "Email Already Exist , Please Try Another Email  ", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);

                                }
                                else
                                {
                                    progressBar.setVisibility(View.VISIBLE);

                                    libraryViewModel.SignUp(fullNameStr,userNameStr,emailStr,passwordStr);
                                    libraryViewModel.SignUpLiveData.observe((LifecycleOwner) context, new Observer<String>() {
                                        @Override
                                        public void onChanged(String s)
                                        {
                                            try
                                            {
                                                //session.setLogin(true);
                                                Toast.makeText(Signup.this, "Succesfully Registed", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Signup.this, Login.class);
                                                intent.putExtra("email",emailStr);
                                                startActivity(intent);

                                                finish();

                                            }
                                            catch (Exception e )
                                            {
                                                progressBar.setVisibility(View.GONE);

                                                Toast.makeText(Signup.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });
                                }
                            }
                            catch (Exception e )
                            {

                            }

                        }
                    });
                }

            }
        }


    public void goToLogin(View view ){startActivity(new Intent(this, Login.class));}





    public void InitView()
    {
        fullName = findViewById(R.id.etFullName);
        userName = findViewById(R.id.etUserName);
        email    = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);

    }
}
