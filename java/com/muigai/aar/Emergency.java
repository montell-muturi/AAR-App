package com.muigai.aar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Emergency extends AppCompatActivity {
    public static final String LOG_TAG = Login.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
    }

    public void Emergency(View view) {
        Intent myIntent = new Intent(this, Emergency.class);
        //start a second activity using the method start activity
        Log.d(LOG_TAG, "Button Emergency  Clicked");

    }
}
