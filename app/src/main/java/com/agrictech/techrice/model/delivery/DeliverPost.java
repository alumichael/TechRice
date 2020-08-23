package com.agrictech.techrice.model.delivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeliverPost implements Serializable {

    @SerializedName("farmer_id")
    @Expose
    private int farmer_id;
    @SerializedName("order_id")
    @Expose
    private int order_id;

    public DeliverPost(int farmer_id, int order_id) {
        this.farmer_id = farmer_id;
        this.order_id = order_id;
    }
}
