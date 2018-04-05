package com.appdev.debsourav.childtrackerforparent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Deb Sourav on 4/2/2018.
 */

public class MsgViewHolder extends RecyclerView.ViewHolder{
    View view;
    TextView txtNumber, txtDate, txtBody;
    ImageView msgIcon;

    public MsgViewHolder(View itemView) {
        super(itemView);
        view= itemView;
        txtNumber= view.findViewById(R.id.Number);
        txtDate = view.findViewById(R.id.Date);
        txtBody= view.findViewById(R.id.Body);
        msgIcon= view.findViewById(R.id.msgIcon);
    }

    public void setNumber(String Number){
        txtNumber.setText(Number);
    }

    public void setDate(String Date){
        txtDate.setText(Date);
    }

    public void setBody(String Body){

        txtBody.setText(Body);

    }

}


