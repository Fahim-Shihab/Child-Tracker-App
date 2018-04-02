package com.appdev.debsourav.childtrackerforparent;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Deb Sourav on 4/2/2018.
 */

public class Message implements Parcelable {
    String number, body, name, date, type, fullDate;

    public Message() {
    }

    public Message(String number, String body, String name, String date, String type, String fullDate) {
        this.number = number;
        this.body = body;
        this.name = name;
        this.date = date;
        this.type = type;
        this.fullDate = fullDate;
    }

    protected Message(Parcel in) {
        number = in.readString();
        body = in.readString();
        name = in.readString();
        date = in.readString();
        type = in.readString();
        fullDate = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }



    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(number);
        parcel.writeString(body);
        parcel.writeString(name);
        parcel.writeString(date);
        parcel.writeString(type);
        parcel.writeString(fullDate);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullDate() {
        return fullDate;
    }

    public void setFullDate(String fullDate) {
        this.fullDate = fullDate;
    }
}
