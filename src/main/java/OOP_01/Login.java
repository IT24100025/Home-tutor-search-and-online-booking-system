package OOP_01;

public class Login {

    private int loginStudentID;
    private String loginPassword;
    private int signUpStudentID;
    private String signUpPassword;


    //setters
    public void setLoginStudentID(int loginStudentID) {
        this.loginStudentID = loginStudentID;
    }
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    public void setSignUpStudentID(int signUpStudentID) {
        this.signUpStudentID = signUpStudentID;
    }
    public void setSignUpPassword(String signUpPassword) {
        this.signUpPassword = signUpPassword;
    }

    //getters
    public int getLoginStudentID() {
        return loginStudentID;
    }
    public String getLoginPassword() {
        return loginPassword;
    }
    public int getSignUpStudentID() {
        return signUpStudentID;
    }
    public String getSignUpPassword() {
        return signUpPassword;
    }
}