package com.agrictech.techrice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OnlyFarmerIDPost implements Serializable {

    @SerializedName("farmer_id")
    @Expose
    private int farmer_id;

    public OnlyFarmerIDPost(int farmer_id) {
        this.farmer_id = farmer_id;
    }
}
