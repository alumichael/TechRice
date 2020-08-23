package com.agrictech.techrice.model.schedule_period;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ScheduleResponseData implements Serializable
{

    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("period")
    @Expose
    private String period;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
