package com.nyakiogithendu.aarhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public EditText nameId,emailId,patientId,password,confirmPassword;
    Button btnSignup;
    TextView tvSignIn;
    FirebaseAuth mFireBaseAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFireBaseAuth=FirebaseAuth.getInstance();
        nameId=findViewById(R.id.enterFullName);

        emailId=findViewById(R.id.enteremail);
        patientId=findViewById(R.id.enterpatientId);
        password=findViewById(R.id.enterpassword);
        confirmPassword=findViewById(R.id.confirmpassword);
         progressBar=findViewById(R.id.progressBar);
        btnSignup=findViewById(R.id.signupbutton);
        tvSignIn=findViewById(R.id.textViewSignIn);
        btnSignup.setOnClickListener(new View.OnClickListener() {
                                         public void onClick(View v) {
                                            final String fname=nameId.getText().toString();
                                            final String email=emailId.getText().toString();
                                            final  String patient= patientId.getText().toString();
                                            final String  psword=password.getText().toString();
                                            final String   cpassword=confirmPassword.getText().toString();

                                             if(fname.isEmpty()){
                                                 emailId.setError("please enter your full name");
                                                 emailId.requestFocus();
                                             }
                                             else
                                             if(email.isEmpty()){
                                                 emailId.setError("please enter your email");
                                                 emailId.requestFocus();
                                             }
                                             else
                                             if(patient.isEmpty()){
                                                 emailId.setError("please enter your patientId");
                                                 emailId.requestFocus();
                                             }


                                             else
                                             if(psword.isEmpty()){
                                                 password.setError("please enter your password");
                                                 password.requestFocus();
                                             }
                                             else
                                             if(cpassword.isEmpty() && cpassword!=psword){
                                                 emailId.setError("confirm password is different from password");
                                                 emailId.requestFocus();
                                             }

                                             else
                                             if(fname.isEmpty()&&email.isEmpty()&&patient.isEmpty()&&psword.isEmpty()&&cpassword.isEmpty()){
                                                 Toast.makeText(MainActivity.this,"All fields are empty",Toast.LENGTH_SHORT).show();
                                             }

                                             else
                                                 //one error here
                                                 if(!(fname.isEmpty()&&email.isEmpty()&&patient.isEmpty()&&psword.isEmpty()&&cpassword.isEmpty())){

                                                     mFireBaseAuth.createUserWithEmailAndPassword(email,psword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                                                 @Override
                                                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                                                     if(!task.isSuccessful()){
                                                                         Toast.makeText(MainActivity.this,"Sign up unsuccessful,Please try again",Toast.LENGTH_SHORT).show();
                                                                     }
                                                                     else
                                                                     {
                                                                         User user=new User(fname,email,patient);
                                                                         FirebaseDatabase.getInstance().getReference("Users")
                                                                         .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                             @Override
                                                                             public void onComplete(@NonNull Task<Void> task) {
                                                                                 progressBar.setVisibility(View.GONE);
                                                                                 if (task.isSuccessful()) {
                                                                                     Toast.makeText(MainActivity.this,getString(R.string.registration_successfull),Toast.LENGTH_LONG).show();
                                                                                 }
                                                                             }
                                                                         }
                                                                         );


                                                                         startActivity(new Intent(MainActivity.this,AARhome.class));
                                                                     }


                                                                 }

                                                             }
                                                     );


                                                 }
                                                 else
                                                 {
                                                     Toast.makeText(MainActivity.this,"An error has occurred",Toast.LENGTH_SHORT).show();
                                                 }
                                         }
                                     }
        );
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intLogin=new Intent(MainActivity.this,Login.class);
                startActivity(intLogin);
            }
        });
    }


    }

