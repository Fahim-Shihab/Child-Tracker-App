package com.appdev.shan.childtracker;

public class Message {
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
