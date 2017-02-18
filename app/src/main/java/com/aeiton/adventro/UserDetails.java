package com.aeiton.adventro;

/**
 * Created by Rumaan on 18-Feb-17.
 */

public class UserDetails {
    private static UserDetails instance;

    private String name, email, phone, gender, latitude, lat_t, longitude, long_t;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        instance.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        instance.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        instance.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        instance.gender = gender;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        instance.latitude = latitude;
    }

    public String getLat_t() {
        return lat_t;
    }

    public void setLat_t(String lat_t) {
        instance.lat_t = lat_t;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        instance.longitude = longitude;
    }

    public String getLong_t() {
        return long_t;
    }

    public void setLong_t(String long_t) {
        instance.long_t = long_t;
    }

    // Return only one instance of class
    public static UserDetails getInstance() {
        if (instance == null) {
            instance = new UserDetails();
        }
        return instance;
    }
}
