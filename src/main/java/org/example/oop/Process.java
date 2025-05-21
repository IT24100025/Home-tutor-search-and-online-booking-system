package org.example.oop;

public class Process {

    LinkedList list = new LinkedList();
    public SignUp signUp = new SignUp();
    public Login login = new Login();
    public Edit edit = new Edit();
    public Delete delete = new Delete();

    // SignUp Process
    public void signUpProcess() {
        list.addLast(signUp.getFirstName(), signUp.getLastName(), signUp.getDateOfBirth(), signUp.getGender(), signUp.getNID(), signUp.getAddress(), signUp.getPhoneNo(), signUp.getStudentID(), signUp.getPassword01());
    }

    // Login Process
    public String loginProcess() {
        int index = list.index(login.getLoginStudentID());
        if (index == -99) {
            return "Login Failed! Please try again";
        }
        String storedStudentID = list.findStudentID(index);
        String storedPassword = list.findPassword(index);

        if (storedStudentID.equals(login.getLoginStudentID()) && storedPassword == null) {
            return "Your Account is Deleted!";
        } else if (storedStudentID.equals(login.getLoginStudentID()) && storedPassword.equals(login.getLoginPassword())) {
            return "Login Successful";
        } else {
            return "Login Failed! Please try again";
        }
    }

    // Edit login
    public String editLogin() {
        int index = list.index(edit.getEnterStudentID());
        if (index == -99) {
            return "Login Failed! Please try again";
        }
        String storedStudentID = list.findStudentID(index);
        String storedPassword = list.findPassword(index);

        if (storedStudentID.equals(edit.getEnterStudentID()) && storedPassword == null) {
            return "Your Account is Deleted!";
        } else if (storedStudentID.equals(edit.getEnterStudentID()) && storedPassword.equals(edit.getEnterPassword())) {
            return "Login Successful";
        } else {
            return "Login Failed! Please try again";
        }
    }

    // Edit Process
    public void editProcess(String Address, Long pNumber, String newPassword) {
        if (editLogin().equals("Login Successful")) {
            int index = list.index(edit.getEnterStudentID());
            if (index != -99) {
                list.editAddress(index, Address);
                list.editPhoneNo(index, pNumber);
                list.editPassword(index, newPassword);
            }
        }
    }

    // Delete login
    public String deleteLogin() {
        int index = list.index(delete.getEnterStudentID());
        if (index == -99) {
            return "Login Failed! Please try again";
        }
        String storedStudentID = list.findStudentID(index);
        String storedPassword = list.findPassword(index);

        if (storedStudentID.equals(delete.getEnterStudentID()) && storedPassword == null) {
            return "Your Account is Deleted!";
        } else if (storedStudentID.equals(delete.getEnterStudentID()) && storedPassword.equals(delete.getEnterPassword())) {            
            return "Login Successful";
        } else {
            return "Login Failed! Please try again";
        }
    }

    // Delete Process
    public void deleteProcess() {
        if (deleteLogin().equals("Login Successful")) {
            list.editPassword(list.index(delete.getEnterStudentID()), null);
        }
    }
}