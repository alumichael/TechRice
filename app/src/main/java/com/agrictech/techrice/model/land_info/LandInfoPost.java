package com.agrictech.techrice.model.land_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LandInfoPost implements Serializable {

    @SerializedName("land_area")
    @Expose
    private double land_area;
    @SerializedName("land_nature")
    @Expose
    private String land_nature;
    @SerializedName("farmer_id")
    @Expose
    private int farmer_id;

    public LandInfoPost(double land_area, String land_nature, int farmer_id) {
        this.land_area = land_area;
        this.land_nature = land_nature;
        this.farmer_id = farmer_id;
    }
}
