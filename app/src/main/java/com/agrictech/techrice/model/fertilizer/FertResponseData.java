package com.agrictech.techrice.model.fertilizer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FertResponseData implements Serializable
{

    @SerializedName("fertilizer_required")
    @Expose
    private Double fertilizerRequired;
    @SerializedName("fertilizer_type")
    @Expose
    private String fertilizerType;
    @SerializedName("application_period")
    @Expose
    private String applicationPeriod;
    @SerializedName("option")
    @Expose
    private int option;


    public Double getFertilizerRequired() {
        return fertilizerRequired;
    }

    public void setFertilizerRequired(Double fertilizerRequired) {
        this.fertilizerRequired = fertilizerRequired;
    }

    public String getFertilizerType() {
        return fertilizerType;
    }

    public void setFertilizerType(String fertilizerType) {
        this.fertilizerType = fertilizerType;
    }

    public String getApplicationPeriod() {
        return applicationPeriod;
    }

    public void setApplicationPeriod(String applicationPeriod) {
        this.applicationPeriod = applicationPeriod;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }


}
