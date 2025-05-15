package OOP_01;

public class LinkedList {
    public int size;
    public Link head;
    public String rmFirstName;
    public String rmLastName;
    public long rmNID;
    public String rmAddress;
    public long rmPhoneNo;
    public String rmDateOfBirth;
    public String rmGender;
    public int rmStudentID;

    //constructor
    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    //check id empty
    public boolean isEmpty() {
        return size == 0;
    }

    //check size
    public void getSize() {
        System.out.println("Size of LinkedList: " + size);
    }

    //add elements first
    public void addFirst(String firstName, String lastName, String dateOfBirth, String gender, long NID, String address, long phoneNo, int studentID, String password) {
        if(isEmpty()) {
            head = new Link(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
        }
        else {
            head = new Link(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password, head);
        }
        size++;
    }

    //add element last
    public void addLast(String firstName, String lastName, String dateOfBirth, String gender, long NID, String address, long phoneNo, int studentID, String password) {
        if(isEmpty()) {
            addFirst(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
        }
        else {
            Link temp = head;
            while(temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Link(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
            size++;
        }
    }

    //add element any
    public void addAny(String firstName, String lastName, String dateOfBirth, String gender, long NID, String address, long phoneNo, int studentID, String password, int index) {
        if(index >= 0 && index <= size) {
            if(isEmpty()) {
                addFirst(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
            }
            else if(index == 0 && index < size) {
                addFirst(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
            }
            else if (index == size) {
                addLast(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
            }
            else {
                Link temp = head;
                for(int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                temp.next = new Link(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password, temp.next);
                size++;
            }
        }
        else {
            System.out.println("Index out of range");
        }
    }

    //remove fist element
    public void removeFirst() {
        if(isEmpty()) {
            System.out.println("List is empty");
        }
        else {
            rmFirstName = head.firstName;
            rmLastName = head.lastName;
            rmDateOfBirth = head.dateOfBirth;
            rmGender = head.gender;
            rmNID = head.NID;
            rmAddress = head.address;
            rmPhoneNo = head.phoneNo;
            rmStudentID = head.studentID;
            head.password = null;
            System.out.println("Removed name : " + rmFirstName + " " + rmLastName + "\nID : " + rmStudentID);
        }
    }

    //remove last element
    public void removeLast() {
        if(isEmpty()) {
            System.out.println("List is empty");
        }
        else {
            if(size == 1) {
                removeFirst();
            }
            else {
                Link temp = head;
                while(temp.next.next != null) {
                    temp = temp.next;
                }
                rmFirstName = temp.next.firstName;
                rmLastName = temp.next.lastName;
                rmDateOfBirth = temp.next.dateOfBirth;
                rmGender = temp.next.gender;
                rmNID = temp.next.NID;
                rmAddress = temp.next.address;
                rmPhoneNo = temp.next.phoneNo;
                rmStudentID = temp.next.studentID;
                temp.next.password = null;
                System.out.println("Removed name : " + rmFirstName + " " + rmLastName + "\nID : " + rmStudentID);
            }
        }
    }

    //remove element any place
    public void removeAny(int index) {
        if(index >= 0 && index < size) {
            if(index == 0) {
                removeFirst();
            }
            else if(index == size - 1) {
                removeLast();
            }
            else {
                Link temp = head;
                for(int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                rmFirstName = temp.next.firstName;
                rmLastName = temp.next.lastName;
                rmDateOfBirth = temp.next.dateOfBirth;
                rmGender = temp.next.gender;
                rmNID = temp.next.NID;
                rmAddress = temp.next.address;
                rmPhoneNo = temp.next.phoneNo;
                rmStudentID = temp.next.studentID;
                temp.next.password = null;
                System.out.println("Removed name : " + rmFirstName + " " + rmLastName + "\nID : " + rmStudentID);
            }
        }
        else {
            System.out.println("Index out of range");
        }
    }

    //find student ID
    public int findStudentID(int index) {
        if(0 <= index && index < size) {
            Link temp = head;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.studentID;
        }
        else {
            return -99;
        }
    }

    //find password
    public String findPassword(int index) {
        if(0 <= index && index < size) {
            Link temp = head;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.password;
        }
        else {
            return "Index out of range";
        }
    }

    //Edit Address
    public void editAddress(int index, String newAddress) {
        if(0 <= index && index < size) {
            Link temp = head;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.address = newAddress;
        }
        else {
            System.out.println("Index out of range");
        }
    }

    //Edit Phone Number
    public void editPhoneNo(int index, long newPhoneNo) {
        if(0 <= index && index < size) {
            Link temp = head;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.phoneNo = newPhoneNo;
        }
        else {
            System.out.println("Index out of range");
        }
    }

    //Edit Password
    public void editPassword(int index, String newPassword) {
        if(0 <= index && index < size) {
            Link temp = head;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.password = newPassword;
        }
        else {
            System.out.println("Index out of range");
        }
    }

    //find element
    public void findStudent(int index) {
        if(0 <= index && index < size) {
            Link temp = head;
            for(int i = 0; i < index; i++) {
                temp = temp.next;
            }
            System.out.println("Name is " + temp.firstName + " " + temp.lastName + "\nNID is " + temp.NID + "\nAddress is " + temp.address + "\nPhone number is " + temp.phoneNo + "\nDate of born is " + temp.dateOfBirth + " \nGender is " + temp.gender + "\nStudentID is " + temp.studentID);
        }
        else {
            System.out.println("Index out of range");
        }
    }

    //display details
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            Link temp = head;
            while (temp != null) {
                System.out.println("Name is " + temp.firstName + " " + temp.lastName + "\nNID is " + temp.NID + "\nAddress is " + temp.address + "\nPhone number is " + temp.phoneNo + "\nDate of born is " + temp.dateOfBirth + " \nGender is " + temp.gender + "\nStudentID is " + temp.studentID);
                System.out.println();
                temp = temp.next;
            }
        }
    }
}