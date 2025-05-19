package org.example.oop;

public class Link {
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String gender;
    public long NID;
    public String address;
    public long phoneNo;
    public String studentID;
    public String password;
    public Link next;

    //constructor --> for first and end
    public Link(String firstName, String lastName, String dateOfBirth, String gender, long NID, String address, long phoneNo, String studentID, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.NID = NID;
        this.address = address;
        this.phoneNo = phoneNo;
        this.studentID = studentID;
        this.password = password;
        this.next = null;
    }

    //constructor --> add element
    public Link(String firstName, String lastName, String dateOfBirth, String gender, long NID, String address, long phoneNo, String studentID, String password, Link next) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.NID = NID;
        this.address = address;
        this.phoneNo = phoneNo;
        this.studentID = studentID;
        this.password = password;
        this.next = next;
    }
}