package OOP_01;

public class Edit {

    private int EnterStudentID;
    private String EnterPassword;
    private int SignUpStudentID;
    private String SignUpPassword;

    //setter
    public void setEnterStudentID( int EnterStudentID ) {
        this.EnterStudentID = EnterStudentID;
    }
    public void setEnterPassword( String EnterPassword ) {
        this.EnterPassword = EnterPassword;
    }
    public void setSignUpStudentID( int SignUpStudentID ) {
        this.SignUpStudentID = SignUpStudentID;
    }
    public void setSignUpPassword( String SignUpPassword ) {
        this.SignUpPassword = SignUpPassword;
    }

    //getter
    public int getEnterStudentID() {
        return EnterStudentID;
    }
    public String getEnterPassword() {
        return EnterPassword;
    }
    public int getSignUpStudentID() {
        return SignUpStudentID;
    }
    public String getSignUpPassword() {
        return SignUpPassword;
    }
}