package com.tutorsystem.tutorbooking.model;

public class Tutor {
    private String id;
    private String email;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String subjects;
    private String grades;
    private String experience;
    private String hourlyRate;
    private String availability;
    private String bio;

    // Constructor
    public Tutor(String[] data) {
        if (data.length >= 12) {
            this.id = data[0];
            this.email = data[1];
            this.password = data[2];
            this.name = data[3];
            this.address = data[4];
            this.phone = data[5];
            this.subjects = data[6];
            this.grades = data[7];
            this.experience = data[8];
            this.hourlyRate = data[9];
            this.availability = data[10];
            this.bio = data[11];
        }
    }

    // Getters and Setters for all fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    public String toFileString() {
        return String.join("|", id, email, password, name, address, phone,
                subjects, grades, experience, hourlyRate, availability, bio);
    }
}