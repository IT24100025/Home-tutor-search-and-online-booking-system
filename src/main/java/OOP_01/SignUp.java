package OOP_01;

public class SignUp {

    private String firstName;
    private String lastName;
    private long NID;
    private String address;
    private long phoneNo;
    private String dateOfBirth;
    private String gender;
    private int Sid;
    private String password01;
    private String password02;

    //default constructor
    public SignUp() {
        this.firstName = "First name";
        this.lastName = "Last name";
        this.NID = 0;
        this.address = "address";
        this.phoneNo = 0;
        this.dateOfBirth = "dateOfBirth";
        this.gender = "gender";
    }

    //setter
    public void setFirstName( String name ) {
        this.firstName = name;
    }
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    public void setNID( long NID ) {
        this.NID = NID;
    }
    public void setAddress( String address ) {
        this.address = address;
    }
    public void setPhoneNo( long phoneNo ) {
        this.phoneNo = phoneNo;
    }
    public void setDateOfBirth( String dateOfBirth ) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setGender( String gender ) {
        this.gender = gender;
    }
    public void setSid(int sid) {
        Sid = sid;
    }
    public void setPassword01(String password01) {
        this.password01 = password01;
    }
    public void setPassword02(String password02) {
        this.password02 = password02;
    }

    //getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public long getNID() {
        return NID;
    }
    public String getAddress() {
        return address;
    }
    public long getPhoneNo() {
        return phoneNo;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public int getSid() {
        return Sid;
    }
    public String getPassword01() {
        return password01;
    }
    public String getPassword02() {
        return password02;
    }


}