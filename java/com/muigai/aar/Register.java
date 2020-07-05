package com.muigai.aar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    public static final String LOG_TAG = Register.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void launchRegister(View view) {
        Intent myIntent = new Intent(this, Register.class);
        //start a second activity using the method start activity
        Log.d(LOG_TAG, "Button Register Clicked");


        startActivity(myIntent);
    }


    public void launchLogin(View view) {
        Intent myIntent = new Intent(this, Login.class);
        //start a second activity using the method start activity
        Log.d(LOG_TAG, "Button Login Clicked");


        startActivity(myIntent);

    }

    public void CreateAccount(View view) {
        Toast toastSubmit = Toast.makeText(this,"Account Creation Successful",Toast.LENGTH_SHORT);
        toastSubmit.show();


    }
}