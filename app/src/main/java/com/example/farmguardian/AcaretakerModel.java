package com.example.farmguardian;

public class AcaretakerModel {

    private String fullNames;
    private String location;
    private String contact;
    private String experience;
    private int available;

    public AcaretakerModel(String fullNames, String location, String contact, String experience, int available) {
        this.fullNames = fullNames;
        this.location = location;
        this.contact = contact;
        this.experience = experience;
        this.available = available;
    }




    public String getFullNames() {
        return fullNames;
    }

    public String getLocation() {
        return location;
    }

    public String getContact() {
        return contact;
    }

    public String getExperience( ) {
        return experience;
    }

    public int isAvailable() {
        return available;
    }
}

