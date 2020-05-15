package com.nyakiogithendu.aarhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    public EditText emailId,password;
    Button btnSigIn;
    FirebaseAuth mFireBaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mFireBaseAuth=FirebaseAuth.getInstance();
        emailId=findViewById(R.id.enteremail);
        password=findViewById(R.id.enterpassword);
        btnSigIn=findViewById(R.id.loginbutton);
        mAuthListener=new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=mFireBaseAuth.getCurrentUser();
                if( mFirebaseUser!=null){
                    Toast.makeText(Login.this,"You are logged in",Toast.LENGTH_SHORT);
                    Intent i=new Intent(Login.this,AARhome.class);
                    startActivity(i);



                }
                else{
                    Toast.makeText(Login.this,"please login in",Toast.LENGTH_SHORT);
                }
            }
        };
        btnSigIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String email=emailId.getText().toString();
                final String  psword=password.getText().toString();



                if(email.isEmpty()){
                    emailId.setError("please enter your email");
                    emailId.requestFocus();
                }

                else
                if(psword.isEmpty()){
                    password.setError("please enter your password");
                    password.requestFocus();
                }

                else
                if(email.isEmpty()&&psword.isEmpty()){
                    Toast.makeText(Login.this,"All fields are empty",Toast.LENGTH_SHORT).show();
                }

                else
                    //one error here
                    if(!(email.isEmpty()&&psword.isEmpty())) {

                        mFireBaseAuth.signInWithEmailAndPassword(email, psword).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login unsuccessful,please try again", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intToHome = new Intent(Login.this, AARhome.class);
                                    startActivity(intToHome);
                                }

                            }
                        });
                    }
                    else{
                        Toast.makeText(Login.this, "Login unsuccessful,please try again", Toast.LENGTH_SHORT).show();
                    }
                                    }

        });

    }
    protected void onStart(){
        super.onStart();
        mFireBaseAuth.addAuthStateListener(mAuthListener);
    }
}
