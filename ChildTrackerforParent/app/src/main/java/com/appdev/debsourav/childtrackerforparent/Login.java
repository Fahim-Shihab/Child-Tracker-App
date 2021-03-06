package com.appdev.debsourav.childtrackerforparent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    String Email;
    EditText emailField;
    EditText passField;
    Button btnSignIn;
    Button btnGoSignUp;
    FirebaseAuth auth;
    DatabaseReference dRef;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");



        auth= FirebaseAuth.getInstance();

        emailField= (EditText) findViewById(R.id.emailID);
        passField= (EditText) findViewById(R.id.passID);
        btnSignIn= (Button) findViewById(R.id.btnSignIn);
        btnGoSignUp= (Button) findViewById(R.id.btnGoSignUp);

        dRef= FirebaseDatabase.getInstance().getReference().child("Parents");

        //OnStart listener to check if any user is logged in
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                //Automatically go to logged in page if any
                //user has previously logged in
                if(firebaseAuth.getCurrentUser()!=null) {
                    Email= firebaseAuth.getCurrentUser().getEmail();
                    startActivity(new Intent(getApplicationContext(), ChildList.class));
                }

            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }
        });

        btnGoSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to Create Account page
                startActivity(new Intent(Login.this,CreateAccount.class));
            }
        });




    }



    @Override
    protected void onStart(){
        super.onStart();

        auth.addAuthStateListener(authStateListener);

    }

    //Sign In with existing password and user name
    void startSignIn(){
        final String myEmail= emailField.getText().toString();
        String myPass= passField.getText().toString();
        if(myEmail.equals("")||myPass.equals("")){
            Toast.makeText(getApplicationContext(),"Enter your Email ID and Password!",Toast.LENGTH_LONG).show();
        }

        else {
            auth.signInWithEmailAndPassword(myEmail, myPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "You are logged in!", Toast.LENGTH_LONG).show();

                        //If login successful, go to Home Page
                        String uid= auth.getCurrentUser().getUid();



                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else
                        Toast.makeText(getApplicationContext(), "Wrong UserID or Password", Toast.LENGTH_LONG).show();

                }
            });
        }


    }
}
