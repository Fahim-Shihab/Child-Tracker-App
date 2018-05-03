package com.appdev.debsourav.childtracker;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CreateAccount extends AppCompatActivity {

    EditText emailField;
    EditText passField;
    EditText confirmPassField, parentEmail;
    Button btnSignUp, btnGoSignIn;

    FirebaseAuth auth, auth2;
    DatabaseReference mRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        auth= FirebaseAuth.getInstance();
        auth2= FirebaseAuth.getInstance();

        mRef= FirebaseDatabase.getInstance().getReference().child("Parents");

        emailField= (EditText) findViewById(R.id.newEmail);
        passField= (EditText) findViewById(R.id.newPass);
        confirmPassField= (EditText) findViewById(R.id.confirmPass);
        parentEmail= findViewById(R.id.parentEmail);

        btnSignUp= (Button) findViewById(R.id.btnSignUp);
        btnGoSignIn= findViewById(R.id.btnGoSignIn);

        btnGoSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

    }

    String getUserID(String email){
        String str[]= email.split("@");
        return str[0];
    }

    void createAccount(){

        final String newEmail= emailField.getText().toString().trim();
        String newPass= passField.getText().toString().trim();
        String confirmPass= confirmPassField.getText().toString().trim();
        String pEmail= parentEmail.getText().toString().trim();

        String pUID= getUserID(pEmail);

        final DatabaseReference parentRef= mRef.child(pUID);

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Childs child= new Childs(newEmail);
                    parentRef.child(System.currentTimeMillis()+"").setValue(child);
                    Toast.makeText(CreateAccount.this, "Parent Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        if(newEmail.equals("")||newPass.equals("")||confirmPass.equals("")){
            Toast.makeText(getApplicationContext(),"Enter Email ID and Password!",Toast.LENGTH_LONG).show();
        }

        //Check if password and confirm password field matches
        else if(!newPass.equals(confirmPass)){
            Toast.makeText(CreateAccount.this,"Passwords didn't match!",Toast.LENGTH_LONG).show();

        }

        else{
            auth.createUserWithEmailAndPassword(newEmail,newPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(CreateAccount.this,"Account created succssfully!",Toast.LENGTH_LONG).show();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(CreateAccount.this,MainActivity.class));
                    }
                    else
                        Toast.makeText(CreateAccount.this,"Account creation unsuccssfull",Toast.LENGTH_LONG).show();

                }
            });

        }



    }


}
