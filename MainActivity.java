package com.nungo.doctorappointment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText date;
    private int mYear;
    private int mMonth;
    private int mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date=findViewById(R.id.date);
        date.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if (v==date){

            final Calendar c = Calendar.getInstance();
            mYear=c.get(Calendar.YEAR);
            mMonth=c.get(Calendar.MONTH);
            mDay=c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   date.setText(dayOfMonth+"-"+(month+1)+"-"+year);
                }
            },mYear,mMonth,mDay);

            datePickerDialog.show();
        }

    }

    public void makeAppointment(View view) {
        Toast toastEnter= Toast.makeText(this,"Appointment Made Successfully",Toast.LENGTH_SHORT);
        toastEnter.show();
    }
}
