package com.example.backend;

import java.io.*;

public class LinkedList {
    int size;
    Link head;
    public String rmAdminName;
    public String rmAdminEmail;
    public String rmAddress;
    public Long rmPhoneNumber;
    public String rmAdminNIC;
    public String rmAdminPassword;
    public String rmAdminID;
    private static final String ADMIN_FILE = "D:\\manuka\\BackEnd\\src\\main\\resources\\Admin.txt";

    // Constructor
    public LinkedList() {
        this.size = 0;
        this.head = null;
        loadFromFile();
    }

    // Load admin details from Admin.txt
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    addLast(parts[1], parts[2], parts[3], Long.parseLong(parts[4]), parts[5], parts[6], parts[0]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading admin data: " + e.getMessage());
        }
    }

    // Save admin details to Admin.txt
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_FILE))) {
            Link temp = head;
            while (temp != null) {
                writer.write(String.format("%s|%s|%s|%s|%d|%s|%s\n",
                        temp.AdminID, temp.AdminName, temp.AdminEmail, temp.Address,
                        temp.PhoneNumber, temp.AdminNIC, temp.AdminPassword));
                temp = temp.next;
            }
        } catch (IOException e) {
            System.out.println("Error saving admin data: " + e.getMessage());
        }
    }

    // Check if list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get size
    public void getSize() {
        System.out.println("Size of LinkedList: " + size);
    }

    // Add elements first
    public void addFirst(String adminName, String adminEmail, String address, Long phoneNumber, String adminNIC, String adminPassword, String adminID) {
        if (isEmpty()) {
            head = new Link(adminName, adminEmail, address, phoneNumber, adminNIC, adminPassword, adminID);
        } else {
            head = new Link(adminName, adminEmail, address, phoneNumber, adminNIC, adminPassword, adminID, head);
        }
        size++;
        saveToFile();
    }

    // Add element last
    public void addLast(String adminName, String adminEmail, String address, Long phoneNumber, String adminNIC, String adminPassword, String adminID) {
        if (isEmpty()) {
            addFirst(adminName, adminEmail, address, phoneNumber, adminNIC, adminPassword, adminID);
        } else {
            Link temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Link(adminName, adminEmail, address, phoneNumber, adminNIC, adminPassword, adminID);
            size++;
        }
        saveToFile();
    }

    // Add element at any index
    public void addAny(String adminName, String adminEmail, String address, Long phoneNumber, String adminNIC, String adminPassword, String adminID, int index) {
        if (index >= 0 && index <= size) {
            if (isEmpty()) {
                addFirst(adminName, adminEmail, address, phoneNumber, adminNIC, adminPassword, adminID);
            } else if (index == 0 && index < size) {
                addFirst(adminName, adminEmail, address, phoneNumber, adminNIC, adminPassword, adminID);
            } else if (index == size) {
                addLast(adminName, adminEmail, address, phoneNumber, adminNIC, adminPassword, adminID);
            } else {
                Link temp = head;
                for (int i = 0; i < index - 1; i++) {
                    temp = temp.next;
                }
                temp.next = new Link(adminName, adminEmail, address, phoneNumber, adminNIC, adminPassword, adminID, temp.next);
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
            rmAdminName = head.AdminName;
            rmAdminEmail = head.AdminEmail;
            rmAddress = head.Address;
            rmPhoneNumber = head.PhoneNumber;
            rmAdminNIC = head.AdminNIC;
            head = head.next;
            size--;
            System.out.println("Removed Name: " + rmAdminName + " Removed Email: " + rmAdminEmail + " Removed Address: " + rmAddress);
            saveToFile();
        }
    }

    // Remove last element
    public void removeLast() {
        if (isEmpty()) {
            System.out.println("List is empty");
        } else {
            if (size == 1) {
                removeFirst();
            } else {
                Link temp = head;
                while (temp.next.next != null) {
                    temp = temp.next;
                }
                rmAdminName = temp.next.AdminName;
                rmAdminEmail = temp.next.AdminEmail;
                rmAddress = temp.next.Address;
                rmPhoneNumber = temp.next.PhoneNumber;
                rmAdminNIC = temp.next.AdminNIC;
                temp.next = null;
                size--;
                System.out.println("Removed Name: " + rmAdminName + " Removed Email: " + rmAdminEmail + " Removed Address: " + rmAddress);
                saveToFile();
            }
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
                rmAdminName = temp.next.AdminName;
                rmAdminEmail = temp.next.AdminEmail;
                rmAddress = temp.next.Address;
                rmPhoneNumber = temp.next.PhoneNumber;
                rmAdminNIC = temp.next.AdminNIC;
                temp.next = temp.next.next;
                size--;
                System.out.println("Removed Name: " + rmAdminName + " Removed Email: " + rmAdminEmail + " Removed Address: " + rmAddress);
                saveToFile();
            }
        } else {
            System.out.println("Index out of range");
        }
    }

    // Find admin ID index
    public int index(String AdminID) {
        int currentIndex = 0;
        Link temp = head;
        while (temp != null) {
            if (temp.AdminID.equals(AdminID)) {
                return currentIndex;
            }
            temp = temp.next;
            currentIndex++;
        }
        return -99;
    }

    // Find admin ID
    public String findAdminID(int index) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp.AdminID;
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
            return temp.AdminPassword;
        } else {
            return "Index outta range";
        }
    }

    // Edit Email
    public void editEmail(int index, String newEmail) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.AdminEmail = newEmail;
            saveToFile();
        } else {
            System.out.println("Index outta range");
        }
    }

    // Edit Address
    public void editAddress(int index, String newAddress) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.Address = newAddress;
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
            temp.PhoneNumber = newPhoneNo;
            saveToFile();
        } else {
            System.out.println("Index outta range");
        }
    }

    // Edit password
    public void editPassword(int index, String newPassword) {
        if (0 <= index && index < size) {
            Link temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.AdminPassword = newPassword;
            saveToFile();
        } else {
            System.out.println("Index outta range");
        }
    }
}