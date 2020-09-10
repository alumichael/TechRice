package com.agrictech.techrice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class UserPreferences {
    private SharedPreferences sharedPreferences;
    private Editor editor;
    private Context _context;

    @SuppressLint({"CommitPrefEdits"})
    public UserPreferences(Context _context) {
        this._context = _context;
        sharedPreferences = _context.getSharedPreferences(Constant.USER_PREF, Constant.PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

 
    public int getSeedling() {
        return sharedPreferences.getInt(Constant.SEEDLING, 0);
    }

    public void setSeedling(int totalAmount) {
        editor.putInt(Constant.SEEDLING, totalAmount);
        editor.apply();
    }


    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(Constant.IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.apply();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(Constant.IS_FIRST_TIME_LAUNCH, true);
    }


    public void setNotification(boolean notification) {
        editor.putBoolean(Constant.NOTIFICATION, notification);
        editor.apply();
    }

    public boolean getNotification() {
        return sharedPreferences.getBoolean(Constant.NOTIFICATION, true);
    }


    public void setFarmerId(int customer_id) {
        editor.putInt(Constant.FARMER_ID, customer_id);
        editor.apply();
    }

    public int getFarmerId() {
        return sharedPreferences.getInt(Constant.FARMER_ID, 0);
    }

    public void setFullName(String name) {
        editor.putString(Constant.FULL_NAME, name);
        editor.apply();
    }

    public String getFullName() {
        return sharedPreferences.getString(Constant.FULL_NAME, "");
    }

    public void setEmail(String email) {
        editor.putString(Constant.EMAIL, email);
        editor.apply();
    }

    public String getEmail() {
        return sharedPreferences.getString(Constant.EMAIL, "");
    }

    public void setPassword(String password) {
        editor.putString(Constant.PASSWORD, password);
        editor.apply();
    }

    public String getPassword() {
        return sharedPreferences.getString(Constant.PASSWORD, "");
    }

    public void setUserFirstname(String firstname) {
        editor.putString(Constant.FIRST_NAME, firstname);
        editor.apply();
    }

    public String getUserFirstname() {
        return sharedPreferences.getString(Constant.FIRST_NAME, "");
    }

    public void setUserLastname(String lastname) {
        editor.putString(Constant.LAST_NAME, lastname);
        editor.apply();
    }

    public String getUserLastname() {
        return sharedPreferences.getString(Constant.LAST_NAME, "");
    }

    public void setState(String userState) {
        editor.putString(Constant.STATE, userState);
        editor.apply();
    }

    public String getState() {
        return sharedPreferences.getString(Constant.STATE, "");
    }


    public void setRequestState(String userState) {
        editor.putString(Constant.REQUEST_STATE, userState);
        editor.apply();
    }

    public String getRequestState() {
        return sharedPreferences.getString(Constant.REQUEST_STATE, "");
    }

    public void setLGA(String region) {
        editor.putString(Constant.LGA, region);
        editor.apply();
    }

    public String getLGA() {
        return sharedPreferences.getString(Constant.LGA, "");
    }

    public void setLandArea(String landArea) {
        editor.putString(Constant.LAND_AREA, landArea);
        editor.apply();
    }


    public String getLandArea() {
        return sharedPreferences.getString(Constant.LAND_AREA, "");
    }

    public void setLandNature(String landNature) {
        editor.putString(Constant.LAND_NATURE, landNature);
        editor.apply();
    }


    public String getLandNature() {
        return sharedPreferences.getString(Constant.LAND_NATURE, "");
    }


    public void setPlantDate(String plantDate) {
        editor.putString(Constant.PLANT_DATE, plantDate);
        editor.apply();
    }


    public String getPlantDate() {
        return sharedPreferences.getString(Constant.PLANT_DATE, "");
    }



    public void setPhone(String phone) {
        editor.putString(Constant.PHONE, phone);
        editor.apply();
    }

    public String getPhone() {
        return sharedPreferences.getString(Constant.PHONE, "");
    }


    public void setOrderId(String orderId) {
        editor.putString(Constant.ORDER_ID, orderId);
        editor.apply();
    }

    public String getOrderId() {
        return sharedPreferences.getString(Constant.ORDER_ID, "");
    }





}
