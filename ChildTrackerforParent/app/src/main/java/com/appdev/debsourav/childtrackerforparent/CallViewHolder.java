package com.appdev.debsourav.childtrackerforparent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Deb Sourav on 3/30/2018.
 */

public class CallViewHolder extends RecyclerView.ViewHolder {
    View callView;
    public CallViewHolder(View itemView) {
        super(itemView);
        callView= itemView;
    }

    public void setCaller(String phNumber){
        TextView txtCaller= callView.findViewById(R.id.phNo);

        txtCaller.setText("Phone number: "+phNumber);
    }

    public void setCallerName(String Name){
        TextView txtCaller= callView.findViewById(R.id.caller);

        txtCaller.setText("Call from/to: "+Name);
    }

    public void setCallType(String callType){
        TextView txtCallType= callView.findViewById(R.id.callType);
        txtCallType.setText("Call type: "+callType);
    }

    public void setCallDuration(String callDuration){
        TextView txtCallDate= callView.findViewById(R.id.callDuration);
        txtCallDate.setText("Call duration: "+callDuration);

    }

    public void setCallDate(String callDate){
        TextView txtCallDate= callView.findViewById(R.id.callDate);
        txtCallDate.setText("Call date: "+callDate);

    }
}
