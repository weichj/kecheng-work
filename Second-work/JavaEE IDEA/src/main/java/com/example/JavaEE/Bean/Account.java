package com.example.JavaEE.Bean;

public class Account {
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String UserName;
    public String Password;

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public String Identity;

//    public Account(String userName, String password) {
//        this.UserName = userName;
//        this.Password = password;
//    }
}
