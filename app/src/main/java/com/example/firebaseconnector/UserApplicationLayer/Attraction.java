package com.example.firebaseconnector.UserApplicationLayer;

public class Attraction {
    private String id;
    private String name;

    private String address;
    private String longitude;
    private String latitude;
    private String openTime;
    private String closeTime;
    private String description;

    public Attraction() {
    }

//    public Attraction(int id, String name, String address, Double longitude, Double latitude, String openTime, String closeTime, String description) {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.longitude = longitude;
//        this.latitude = latitude;
//        this.openTime = openTime;
//        this.closeTime = closeTime;
//        this.description = description;
//    }
//
//    public Attraction(String name, String address, String openTime, String closeTime) {
//        this.name = name;
//        this.address = address;
//        this.openTime = openTime;
//        this.closeTime = closeTime;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
