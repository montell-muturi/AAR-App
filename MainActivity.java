package com.wagura.doctorsprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button Appointments;
    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Appointments = findViewById(R.id.view_appointments);
        Logout= findViewById(R.id.btnLogout);

        Appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointments();
            }

        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogout();


            }
        });
    }

    private void openLogout() {
        Intent intent = new Intent(this, Logout.class);
        startActivity(intent);

    }


    public void openAppointments() {
        Intent intent2 = new Intent(this, Appointments.class);
        startActivity(intent2);
    }


}
