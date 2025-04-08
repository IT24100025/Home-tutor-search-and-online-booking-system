package com.example.hometutorsystem;

public class Tutor {
    private String name;
    private String address;
    private String phone;
    private String grades;
    private int experience;
    private String subjects;
    private String availability;
    private String username; // associated user account

    public Tutor() {}

    public Tutor(String name, String address, String phone, String grades, int experience,
                 String subjects, String availability, String username) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.grades = grades;
        this.experience = experience;
        this.subjects = subjects;
        this.availability = availability;
        this.username = username;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getGrades() { return grades; }
    public void setGrades(String grades) { this.grades = grades; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
    public String getSubjects() { return subjects; }
    public void setSubjects(String subjects) { this.subjects = subjects; }
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public String toString() {
        return name + "," + address + "," + phone + "," + grades + "," +
                experience + "," + subjects + "," + availability + "," + username;
    }

    public static Tutor fromString(String str) {
        String[] parts = str.split(",");
        return new Tutor(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]),
                parts[5], parts[6], parts[7]);
    }
}
