package com.appdev.debsourav.childtrackerforparent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class AppViewHolder extends RecyclerView.ViewHolder{
    View appView;
    public AppViewHolder(View itemView) {

        super(itemView);
        appView= itemView;
    }
    public void setAppName(String AppName){
        TextView txtCaller= appView.findViewById(R.id.AppName);

        txtCaller.setText("App Name: "+AppName);
    }

    public void setDuration(String Duration){
        TextView txtCalle= appView.findViewById(R.id.AppDuration);

        txtCalle.setText("Total Use: "+Duration);
    }

    public void setLastAccessed(String LastAccessed){
        TextView txtCallType= appView.findViewById(R.id.AppAccessed);
        txtCallType.setText("Last Accessed: "+LastAccessed);
    }
}
