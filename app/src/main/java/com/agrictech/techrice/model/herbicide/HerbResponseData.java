package com.agrictech.techrice.model.herbicide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HerbResponseData {

    @SerializedName("herbicide_required")
    @Expose
    private double herbicideRequired;
    @SerializedName("herbicide_type")
    @Expose
    private String herbicideType;
    @SerializedName("application_period")
    @Expose
    private String applicationPeriod;
    @SerializedName("option")
    @Expose
    private int option;
    @SerializedName("fertilizer_required")
    @Expose
    private double fertilizerRequired;


    public double getHerbicideRequired() {
        return herbicideRequired;
    }

    public void setHerbicideRequired(double herbicideRequired) {
        this.herbicideRequired = herbicideRequired;
    }

    public String getHerbicideType() {
        return herbicideType;
    }

    public void setHerbicideType(String herbicideType) {
        this.herbicideType = herbicideType;
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

    public double getFertilizerRequired() {
        return fertilizerRequired;
    }

    public void setFertilizerRequired(double fertilizerRequired) {
        this.fertilizerRequired = fertilizerRequired;
    }
}
