package com.example.backend;

public class Process {

    AdminLogin login = new AdminLogin();
    AdminSignUp signUp = new AdminSignUp();
    AdminEdit edit = new AdminEdit();
    AdminDelete delete = new AdminDelete();
    LinkedList list = new LinkedList();

        //signUp
    public void SingUpDetail() {
        list.addLast(signUp.getAdminName(), signUp.getAdminEmail(), signUp.getAddress(), signUp.getPhoneNumber(), signUp.getAdminNIC(), signUp.getAdminPassword(), signUp.getAdminID());
    }

        //Login process
    public String LoginProcess() {
        int index = list.index(login.getEnterAdminID());
        if(index == -99) {
            return "Login Failed! please try again";
        }
        String storedStudentID = list.findAdminID(index);
        String storedPassword = list.findPassword(index);

        if(storedStudentID.equals(login.getEnterAdminID()) && storedPassword == null) {
            return "Login Failed! please try again";
        }
        else if (storedStudentID.equals(login.getEnterAdminID()) && storedPassword.equals(login.getEnterAdminPassword())) {
            return "Login Successful";
        }
        else {
            return "Login Failed! please try again";
        }
    }

        //Edit Login
    public String EditLogin() {
        int index = list.index(edit.getEditAdminID());
        if(index == -99) {
            return "Login Failed! please try again";
        }
        String storedStudentID = list.findAdminID(index);
        String storedPassword = list.findPassword(index);

        if(storedStudentID.equals(edit.getEditAdminID()) && storedPassword == null) {
            return "Login Failed! please try again";
        }
        else if (storedStudentID.equals(edit.getEditAdminID()) && storedPassword.equals(edit.getEditAdminPassword())) {
            return "Login Successful";
        }
        else {
            return "Login Failed! please try again";
        }
    }

        //Edit Process
    public void EditProcess(String Email, String Address, long PhoneNumber, String Password) {
        if(EditLogin().equals("Login Successful")) {
            int index = list.index(edit.getEditAdminID());
            if(index == -99) {
                list.editEmail(index, Email);
                list.editAddress(index, Address);
                list.editPhoneNo(index, PhoneNumber);
                list.editPassword(index, Password);
            }
        }
    }

        //Delete Login
    public String DeleteLogin() {
        int index = list.index(delete.getDeleteAdminID());
        if(index == -99) {
            return "Login Failed! please try again";
        }
        String storedStudentID = list.findAdminID(index);
        String storedPassword = list.findPassword(index);

        if(storedStudentID.equals(delete.getDeleteAdminID()) && storedPassword == null) {
            return "Login Failed! please try again";
        }
        else if (storedStudentID.equals(delete.getDeleteAdminID()) && storedPassword.equals(delete.getDeleteAdminPassword())) {
            return "Login Successful";
        }
        else {
            return "Login Failed! please try again";
        }
    }

        //Delete Process
    public void DeleteProcess() {
        if (DeleteLogin().equals("Login Successful")) {
            list.editPassword(list.index(delete.getDeleteAdminID()), null );
        }
    }


    public void StudentDetails() {
        //chat
    }

    public void TutorDetails() {
        //chat
    }
}
