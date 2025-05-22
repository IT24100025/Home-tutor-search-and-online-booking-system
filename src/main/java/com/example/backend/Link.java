package com.example.backend;

public class Link {
    public String AdminName;
    public String AdminEmail;
    public String Address;
    public Long PhoneNumber;
    public String AdminNIC;
    public String AdminPassword;
    public String AdminID;
    public Link next;

    //constructor --> for first and end
    public Link(String adminName, String adminEmail, String address, Long phoneNumber, String adminNIC, String adminPassword, String adminID) {
        this.AdminName = adminName;
        this.AdminEmail = adminEmail;
        this.Address = address;
        this.PhoneNumber = phoneNumber;
        this.AdminNIC = adminNIC;
        this.AdminPassword = adminPassword;
        this.AdminID = adminID;
        this.next = null;
    }

    //constructor --. add element
    public Link(String adminName, String adminEmail, String address, Long phoneNumber, String adminNIC, String adminPassword, String adminID, Link next) {
        AdminName = adminName;
        AdminEmail = adminEmail;
        Address = address;
        PhoneNumber = phoneNumber;
        AdminNIC = adminNIC;
        AdminPassword = adminPassword;
        AdminID = adminID;
        this.next = next;
    }
}
