package com.app.firebase.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.json.JSONObject;

/**
 * Created by Aakash Singh on 18-12-2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Contacts{
    private String name;
    private String email;
    private int phone;
    private int officePhone;
    private double latitude;
    private double longitude;

    public Contacts() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(int officePhone) {
        this.officePhone = officePhone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
