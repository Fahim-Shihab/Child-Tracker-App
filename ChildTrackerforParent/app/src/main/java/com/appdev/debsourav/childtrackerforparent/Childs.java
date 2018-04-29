package com.appdev.debsourav.childtrackerforparent;

/**
 * Created by Deb Sourav on 4/29/2018.
 */

public class Childs {
    String email, name;

    public Childs() {
    }

    public Childs(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
