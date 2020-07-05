package com.muigai.aar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void emergency(View view) {
        Intent myIntent = new Intent(this, Emergency.class);
        //start a second activity using the method start activity
        Log.d(LOG_TAG, "Button Emergency  Clicked");
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

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.imageView:

                Uri myUri = Uri.parse("tel:0751100055");
                Intent my_intent = new Intent(Intent.ACTION_DIAL, myUri);
                startActivity(my_intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Emergency(View view) {


               ImageView imageview=findViewById(R.id.imageView);

                    Uri myUri = Uri.parse("tel:0725225225");
                    Intent my_intent = new Intent(Intent.ACTION_DIAL, myUri);
                    startActivity(my_intent);



            }

        }



