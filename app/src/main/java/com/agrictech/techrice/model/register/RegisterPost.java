package com.agrictech.techrice.model.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RegisterPost implements Serializable {

    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("lga")
    @Expose
    private String lga;

    public RegisterPost(String firstname,String lastname,
                        String email,String phone, String password,String state,String lga) {
        super();
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.state = state;
        this.lga = lga;
    }
}
