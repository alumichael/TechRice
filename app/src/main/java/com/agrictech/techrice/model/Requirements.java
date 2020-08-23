package com.agrictech.techrice.model;

public class Requirements {
    String title;
    String icon;
    String detail;

    public Requirements(String title, String icon, String detail) {
        this.title = title;
        this.icon = icon;
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public String getIcon() {
        return icon;
    }

    public String getDetail() {
        return detail;
    }
}
