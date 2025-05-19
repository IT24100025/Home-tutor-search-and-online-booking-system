package org.example.oop;

public class Edit {

    private String EnterStudentID;
    private String EnterPassword;

    //setter
    public void setEnterStudentID( String EnterStudentID ) {
        this.EnterStudentID = EnterStudentID;
    }
    public void setEnterPassword( String EnterPassword ) {
        this.EnterPassword = EnterPassword;
    }

    //getter
    public String getEnterStudentID() {
        return EnterStudentID;
    }
    public String getEnterPassword() {
        return EnterPassword;
    }
}