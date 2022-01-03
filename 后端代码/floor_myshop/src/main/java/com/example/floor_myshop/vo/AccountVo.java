package com.example.floor_myshop.vo;

import java.io.Serializable;

public class AccountVo implements Serializable {

    private String userName;
    private String password;
    private String cd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public AccountVo() {
    }
}
