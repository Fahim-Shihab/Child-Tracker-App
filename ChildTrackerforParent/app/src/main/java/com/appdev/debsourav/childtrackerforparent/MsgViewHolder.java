package com.appdev.debsourav.childtrackerforparent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Deb Sourav on 4/2/2018.
 */

public class MsgViewHolder extends RecyclerView.ViewHolder{
    View view;

    public MsgViewHolder(View itemView) {
        super(itemView);
        view= itemView;
    }

    public void setNumber(String Number){
        TextView txtNumber= view.findViewById(R.id.Number);

        txtNumber.setText("Phone number: "+Number);
    }

    public void setType(String Type){
        TextView txtType= view.findViewById(R.id.Type);
        txtType.setText("Call type: "+Type);
    }

    public void setBody(String Body){
        TextView txtBody= view.findViewById(R.id.Body);
        txtBody.setText("Call type: "+Body);

    }

}


