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

    @SerializedName("land_nature")
    @Expose
    private String land_nature;

    public String getLand_nature() {
        return land_nature;
    }

    public void setLand_nature(String land_nature) {
        this.land_nature = land_nature;
    }

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

}
