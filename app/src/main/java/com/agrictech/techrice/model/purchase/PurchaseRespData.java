package com.agrictech.techrice.model.purchase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PurchaseRespData implements Serializable
{

    @SerializedName("order_id")
    @Expose
    private double orderId;
    @SerializedName("msg")
    @Expose
    private String msg;
   

    public double getOrderId() {
        return orderId;
    }

    public void setOrderId(double orderId) {
        this.orderId = orderId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
