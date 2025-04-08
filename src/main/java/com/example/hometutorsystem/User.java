package com.example.hometutorsystem;

public class User {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;

    public User() {}

    public User(String username, String password, String email, String phone, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return username + "," + password + "," + email + "," + phone + "," + address;
    }

    public static User fromString(String str) {
        String[] parts = str.split(",");
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}

