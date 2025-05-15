package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static final String FILE_PATH = "D:\\java Ultimate\\Test Project\\OOP_Test_01- done\\OOP\\src\\main\\resources\\students.txt";
    private List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
        loadStudentsFromFile();
    }

    public boolean login(String studentId, String password) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId) && student.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public Student signUp(Student student, String password) {
        student.setPassword(password);
        students.add(student);
        saveStudentsToFile();
        return student;
    }

    public boolean editAccount(String studentId, String password, String newAddress, 
                             String newPhone, String newPassword) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId) && student.getPassword().equals(password)) {
                if (newAddress != null) student.setAddress(newAddress);
                if (newPhone != null) student.setPhoneNumber(newPhone);
                if (newPassword != null) student.setPassword(newPassword);
                saveStudentsToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteAccount(String studentId, String password) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId) && student.getPassword().equals(password)) {
                students.remove(student);
                saveStudentsToFile();
                return true;
            }
        }
        return false;
    }

    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                // Write each student's data in a CSV-like format
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                    student.getStudentId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getDateOfBirth(),
                    student.getGender(),
                    student.getNicNumber(),
                    student.getAddress(),
                    student.getPhoneNumber(),
                    student.getPassword()
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentsFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 9) {
                        Student student = new Student(
                            data[1], // firstName
                            data[2], // lastName
                            LocalDate.parse(data[3]), // dateOfBirth
                            data[4], // gender
                            data[5], // nicNumber
                            data[6], // address
                            data[7]  // phoneNumber
                        );
                        student.setStudentId(data[0]);
                        student.setPassword(data[8]);
                        students.add(student);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
} 