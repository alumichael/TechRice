package com.agrictech.techrice.model.schedule_period;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ScheduleResponseHead implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private ScheduleResponseData data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ScheduleResponseData getScheduleResponseData() {
        return data;
    }

    public void setScheduleResponseData(ScheduleResponseData data) {
        this.data = data;
    }

}
