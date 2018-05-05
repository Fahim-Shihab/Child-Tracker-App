package com.appdev.debsourav.childtrackerforparent;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChildList extends AppCompatActivity {

    FirebaseAuth auth;
    DatabaseReference childRef;
    RecyclerView childRView;
    public static String childID="";

    String getUserID(String email){
        String str[]= email.split("@");
        return str[0];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_list);

        setTitle("Children");

        auth= FirebaseAuth.getInstance();
        childRView = findViewById(R.id.childRView);
        childRView.setLayoutManager(new LinearLayoutManager(this));

        String userID= getUserID(auth.getCurrentUser().getEmail());
        //Toast.makeText(this, userID, Toast.LENGTH_SHORT).show();
        childRef= FirebaseDatabase.getInstance().getReference().child("Parents").child(userID);

        FirebaseRecyclerAdapter<Childs, ChildViewHolder> firebaseRecyclerAdapter= new FirebaseRecyclerAdapter <Childs, ChildViewHolder>
                (Childs.class, R.layout.child_card, ChildViewHolder.class, childRef) {
            @Override
            protected void populateViewHolder(final ChildViewHolder viewHolder, final Childs model, int position) {

                viewHolder.setEmail(model.getEmail());

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View view) {
                        childID=getUserID(model.getEmail());
                        Intent intent= new Intent(ChildList.this,MainActivity.class);
                        startActivity(intent);

                    }
                });
            }
        };

        childRView.setAdapter(firebaseRecyclerAdapter);
    }
}