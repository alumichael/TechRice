package com.agrictech.techrice.model.purchase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PurchasePost implements Serializable {

    @SerializedName("farmer_id")
    @Expose
    private int farmer_id;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("products")
    @Expose
    private String products;
    @SerializedName("amount")
    @Expose
    private int amount;

    public PurchasePost(int farmer_id, String reference, String products, int amount) {
        this.farmer_id = farmer_id;
        this.reference = reference;
        this.products = products;
        this.amount = amount;
    }
}
