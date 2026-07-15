package com.careconnect.backend.dto;

public class UserProfileUpdateDTO {
    private String name;
    private String phone;
    private String location;
    private String preferredDonationType;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getPreferredDonationType() { return preferredDonationType; }
    public void setPreferredDonationType(String preferredDonationType) { this.preferredDonationType = preferredDonationType; }
}
