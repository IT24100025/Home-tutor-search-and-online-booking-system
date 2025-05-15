package OOP_01;
import java.util.Scanner;

public class Process {
    Scanner input = new Scanner(System.in);

    LinkedList list = new LinkedList();
    SignUp signUp = new SignUp();
    Login login = new Login();
    Edit edit = new Edit();
    Delete delete = new Delete();

        //SignUp

    int year = 2025;
    int id01 = (year - 2000) * 100000;
    int again01;
    int x = -1;

    //signup process
    public void signUpProcess(String FName, String LName, int date, int month, int year, String gender, Long NIC, String Address, Long pNumber) {
        //Enter details
        signUp.setFirstName(input.next(FName));

        signUp.setLastName(input.next(LName));

        int DD = input.nextInt(date);

        int MM = input.nextInt(month);

        int YYYY = input.nextInt(year);

        String DOB = DD + "-" + MM + "-" + YYYY;

        signUp.setDateOfBirth(DOB);

        signUp.setGender(gender);

        signUp.setNID(NIC);

        signUp.setAddress(Address);

        signUp.setPhoneNo(pNumber);
    }

    //Creat Student ID
    public void createStudentID() {
        while (true){
            id01 = id01 + 1;

            if(id01 != list.findStudentID(++x)) {
                signUp.setSid(id01);
                break;
            }
        }
    }

    //Create Password
    public void createPassword(String password01, String password02) {
        do {
            signUp.setPassword01(password01);

            signUp.setPassword02(password02);;

            if(signUp.getPassword01().equals(signUp.getPassword02())) {
                System.out.println("sign up finished");
                again01 = 1;
            }
            else {
                System.out.println("password is not matching, try again");
                again01 = -99;
            }
        }while (again01 == -99);

        list.addLast(signUp.getFirstName(), signUp.getLastName(), signUp.getDateOfBirth(), signUp.getGender(), signUp.getNID(), signUp.getAddress(), signUp.getPhoneNo(), signUp.getSid(), signUp.getPassword02());
    }

        //Login

    //logging process
    public void loginProcess(int studentID, String password) {

        login.setLoginStudentID(studentID);

        login.setLoginPassword(password);

        int index = login.getLoginStudentID() - 2500001;

        login.setSignUpStudentID(list.findStudentID(index));
        login.setSignUpPassword(list.findPassword(index));
    }

    //Check Login Process
    public void checkLogin() {
        if (login.getLoginStudentID() == login.getSignUpStudentID() && login.getSignUpPassword() == null) {
            System.out.println("Your account is deleted");
        }
        else if (login.getLoginStudentID() == login.getSignUpStudentID() && login.getLoginPassword().equals(login.getSignUpPassword())) {
            System.out.println("Login Successful");
        }
        else {
            System.out.println("Login Failed! Please try again");
        }
    }

        //Edit

    int again02;
    String newPassword01;
    String newPassword02;

    //Edit Login Process
    public void editLoginProcess(int studentID, String password) {

        edit.setEnterStudentID(studentID);

        edit.setEnterPassword(password);

        int index = edit.getEnterStudentID() - 2500001;

        edit.setSignUpStudentID(list.findStudentID(index));

        edit.setSignUpPassword(list.findPassword(index));
    }

    //Check Edit Login Process
    public String checkEdit() {
        if(edit.getEnterStudentID() == edit.getSignUpStudentID() && edit.getEnterPassword().equals(edit.getSignUpPassword())) {
            return "Successful";
        }
        else {
            return "Failed";
        }
    }

    //Edit Process
    public void editProcess(String Address, Long pNumber, String newPassword_01, String newPassword_02) {

        int index = edit.getEnterStudentID() - 2500001;

        if(checkEdit().equals("Successful")) {

            list.editAddress(index, Address);

            list.editPhoneNo(index, pNumber);

            //creat new password
            do {
                newPassword01 = newPassword_01;

                newPassword02 = newPassword_02;

                if(newPassword01.equals(newPassword02)) {
                    list.editPassword(index, newPassword02);
                    again02= 1;
                }
                else {
                    System.out.println("password is not matching, try again");
                    again02 = -99;
                }
            }while (again02 == -99);

            System.out.println("Edit Successful");
        }
        else {
            System.out.println("Student ID or password is not matching, try again");
        }

    }

        //Delete

    String del;

    //Delete Login Process
    public void deleteLoginProcess(int studentID, String password) {
        delete.setEnterStudentID(studentID);

        delete.setEnterPassword(password);

        int index = delete.getEnterStudentID() - 2500001;

        delete.setSignUpStudentID(list.findStudentID(index));

        delete.setSignUpPassword(list.findPassword(index));
    }

    //Check Delete login process
    public String checkDelete() {
        if(delete.getEnterStudentID() == delete.getSignUpStudentID() && delete.getEnterPassword().equals(delete.getSignUpPassword())) {
            return "Successful";
        }
        else {
            return "Failed";
        }
    }

    //Delete Process
    public void deleteProcess(String confirm) {

        int index = delete.getEnterStudentID() - 2500001;

        if(checkDelete().equals("Successful")) {

            del = confirm;

            if(del.equals("Yes") || del.equals("yes")) {
                list.removeAny(index);
                System.out.println("Your account is deleted");
            }
            else if(del.equals("No") || del.equals("no")) {
                System.out.println("This account is not deleted");
            }
        }
        else{
            System.out.println("Student ID or password is not matching, try again");
        }
    }
}
