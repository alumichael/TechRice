package com.agrictech.techrice.model.seed_require;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeedResponseData implements Serializable
{

    @SerializedName("seed_required")
    @Expose
    private Integer seedRequired;


    public Integer getSeedRequired() {
        return seedRequired;
    }

    public void setSeedRequired(Integer seedRequired) {
        this.seedRequired = seedRequired;
    }
}
