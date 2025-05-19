package org.example.oop;

import java.io.*;

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
    public String rmStudentID;
    public String Sid;
    private static final String FILE_PATH = "D:\\java Ultimate\\Test Project\\OOP_Final_push\\OOP_01\\src\\main\\resources\\data\\students.txt";

    // Constructor
    public LinkedList() {
        this.size = 0;
        this.head = null;
        loadFromFile();
    }

    // Check if empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check size
    public void getSize() {
        System.out.println("Size of LinkedList: " + size);
    }

    // Add elements first
    public void addFirst(String firstName, String lastName, String dateOfBirth, String gender, long NID, String address, long phoneNo, String studentID, String password) {
        if (isEmpty()) {
            head = new Link(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
        } else {
            head = new Link(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password, head);
        }
        size++;
        saveToFile();
    }

    // Add element last
    public void addLast(String firstName, String lastName, String dateOfBirth, String gender, long NID, String address, long phoneNo, String studentID, String password) {
        if (isEmpty()) {
            addFirst(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
        } else {
            Link temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Link(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
            size++;
        }
        saveToFile();
    }

    // Add element at any index
    public void addAny(String firstName, String lastName, String dateOfBirth, String gender, long NID, String address, long phoneNo, String studentID, String password, int index) {
        if (index >= 0 && index <= size) {
            if (isEmpty()) {
                addFirst(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
            } else if (index == 0 && index < size) {
                addFirst(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
            } else if (index == size) {
                addLast(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password);
            } else {
                Link temp = head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                temp.next = new Link(firstName, lastName, dateOfBirth, gender, NID, address, phoneNo, studentID, password, temp.next);
                size++;
            }
            saveToFile();
        } else {
            System.out.println("Index out of range");
        }
    }

    // Remove first element
    public void removeFirst() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            rmFirstName = head.firstName;
            rmLastName = head.lastName;
            rmDateOfBirth = head.dateOfBirth;
            rmGender = head.gender;
            rmNID = head.NID;
            rmAddress = head.address;
            rmPhoneNo = head.phoneNo;
            rmStudentID = head.studentID;
            head.password = null;
            head = head.next;
            size--;
            System.out.println("Removed name: " + rmFirstName + " " + rmLastName + "\nID: " + rmStudentID);
            saveToFile();
        }
    }

    // Remove last element
    public void removeLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else if (size == 1) {
            removeFirst();
        } else {
            Link temp = head;
            while (temp.next.next != null) {
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
            temp.next = null;
            size--;
            System.out.println("Removed name: " + rmFirstName + " " + rmLastName + "\nID: " + rmStudentID);
            saveToFile();
        }
    }

    // Remove element at any index
    public void removeAny(int index) {
        if (index >= 0 && index < size) {
            if (index == 0) {
                removeFirst();
            } else if (index == size - 1) {
                removeLast();
            } else {
                Link temp = head;
                for (int i = 0; i < index - 1; i++) {
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
                temp.next = temp.next.next;
                size--;
                System.out.println("Removed name: " + rmFirstName + " " + rmLastName + "\nID: " + rmStudentID);
                saveToFile();
            }
        } else {
            System.out.println("Index out of range");
        }
    }

    // Find student ID index
    public int index(String studentID) {
        int currentIndex = 0;
        Link temp = head;
        while (temp != null) {
            if (temp.studentID.equals(studentID)) {
                return currentIndex;
            }
            temp = temp.next;
            currentIndex++;
        }
        return -99;
    }

    // Find student ID
    public String findStudentID(int index) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.studentID;
        } else {
            return "not found";
        }
    }

    // Find password
    public String findPassword(int index) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.password;
        } else {
            return "Index outta range";
        }
    }

    // Edit Address
    public void editAddress(int index, String newAddress) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.address = newAddress;
            saveToFile();
        } else {
            System.out.println("Index outta range");
        }
    }

    // Edit Phone Number
    public void editPhoneNo(int index, long newPhoneNo) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.phoneNo = newPhoneNo;
            saveToFile();
        } else {
            System.out.println("Index outta range");
        }
    }

    // Edit Password
    public void editPassword(int index, String newPassword) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.password = newPassword;
            saveToFile();
        } else {
            System.out.println("Index outta range");
        }
    }

    // Find student
    public void findStudent(int index) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            System.out.println("Name is " + temp.firstName + " " + temp.lastName + "\nNID is " + temp.NID + "\nAddress is " + temp.address + "\nPhone number is " + temp.phoneNo + "\nDate of born is " + temp.dateOfBirth + "\nGender is " + temp.gender + "\nStudentID is " + temp.studentID);
        } else {
            System.out.println("Index outta range");
        }
    }

    // Display details
    public void display() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            Link temp = head;
            while (temp != null) {
                System.out.println("Name is " + temp.firstName + " " + temp.lastName + "\nNID is " + temp.NID + "\nAddress is " + temp.address + "\nPhone number is " + temp.phoneNo + "\nDate of born is " + temp.dateOfBirth + "\nGender is " + temp.gender + "\nStudentID is " + temp.studentID);
                System.out.println();
                temp = temp.next;
            }
        }
    }

    // Save to file
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Link temp = head;
            while (temp != null) {
                writer.write(String.format("%s|%s|%s|%s|%d|%s|%d|%s|%s%n",
                        temp.firstName, temp.lastName, temp.dateOfBirth, temp.gender, temp.NID,
                        temp.address, temp.phoneNo, temp.studentID, temp.password == null ? "" : temp.password));
                temp = temp.next;
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Load from file
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 9) {
                    String password = parts[8].isEmpty() ? null : parts[8];
                    addLast(parts[0], parts[1], parts[2], parts[3], Long.parseLong(parts[4]),
                            parts[5], Long.parseLong(parts[6]), parts[7], password);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}