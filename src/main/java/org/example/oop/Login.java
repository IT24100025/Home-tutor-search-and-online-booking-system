package org.example.oop;

public class Login {

    private String loginStudentID;
    private String loginPassword;

    //setters
    public void setLoginStudentID(String loginStudentID) {
        this.loginStudentID = loginStudentID;
    }
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    //getters
    public String getLoginStudentID() {
        return loginStudentID;
    }
    public String getLoginPassword() {
        return loginPassword;
    }
}