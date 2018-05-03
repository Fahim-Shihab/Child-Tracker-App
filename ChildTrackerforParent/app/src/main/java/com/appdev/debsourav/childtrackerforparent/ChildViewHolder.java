package com.appdev.debsourav.childtrackerforparent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Deb Sourav on 5/2/2018.
 */

public class ChildViewHolder extends RecyclerView.ViewHolder{
    View view;
    TextView txtEmail;

    public ChildViewHolder(View itemView) {
        super(itemView);
        view= itemView;
        txtEmail= view.findViewById(R.id.txtEmail);
    }

    public void setEmail(String email){
        txtEmail.setText(email);

    }
}
