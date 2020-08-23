package com.agrictech.techrice.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginPost implements Serializable {

    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;

    public LoginPost(String phone, String password) {
        super();
        this.phone = phone;
        this.password = password;
    }

}
