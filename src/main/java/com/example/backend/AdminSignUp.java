package com.example.backend;

public class AdminSignUp {
    private String AdminName;
    private String AdminEmail;
    private String Address;
    private Long PhoneNumber;
    private String AdminNIC;
    private String AdminPassword;
    private String AdminID;

        //setters

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }
    public void setAdminEmail(String adminEmail) {
        AdminEmail = adminEmail;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public void setPhoneNumber(Long phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    public void setAdminNIC(String adminNIC) {
        AdminNIC = adminNIC;
    }
    public void setAdminPassword(String adminPassword) {
        AdminPassword = adminPassword;
    }
    public void setAdminID(String adminID) {
        AdminID = adminID;
    }


    //Getters

    public String getAdminName() {
        return AdminName;
    }
    public String getAdminEmail() {
        return AdminEmail;
    }
    public String getAddress() {
        return Address;
    }
    public Long getPhoneNumber() {
        return PhoneNumber;
    }
    public String getAdminNIC() {
        return AdminNIC;
    }
    public String getAdminPassword() {
        return AdminPassword;
    }
    public String getAdminID() {
        return AdminID;
    }
}
