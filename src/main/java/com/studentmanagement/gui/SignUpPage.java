package com.studentmanagement.gui;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentManager;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SignUpPage extends JFrame {
    private final MainPage mainPage;
    private final StudentManager studentManager;
    
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField dobField;
    private final JComboBox<String> genderComboBox;
    private final JTextField nicField;
    private final JTextField addressField;
    private final JTextField phoneField;
    private final JPasswordField passwordField;
    private final JPasswordField confirmPasswordField;

    public SignUpPage(MainPage mainPage) {
        this.mainPage = mainPage;
        this.studentManager = new StudentManager();

        setTitle("Sign Up");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(11, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        dobField = new JTextField();
        String[] genders = {"Male", "Female", "Other"};
        genderComboBox = new JComboBox<>(genders);
        nicField = new JTextField();
        addressField = new JTextField();
        phoneField = new JTextField();
        passwordField = new JPasswordField();
        confirmPasswordField = new JPasswordField();

        // Add components to panel
        mainPanel.add(new JLabel("First Name:"));
        mainPanel.add(firstNameField);
        mainPanel.add(new JLabel("Last Name:"));
        mainPanel.add(lastNameField);
        mainPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        mainPanel.add(dobField);
        mainPanel.add(new JLabel("Gender:"));
        mainPanel.add(genderComboBox);
        mainPanel.add(new JLabel("NIC Number:"));
        mainPanel.add(nicField);
        mainPanel.add(new JLabel("Address:"));
        mainPanel.add(addressField);
        mainPanel.add(new JLabel("Phone Number:"));
        mainPanel.add(phoneField);
        mainPanel.add(new JLabel("Password:"));
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel("Confirm Password:"));
        mainPanel.add(confirmPasswordField);

        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back to Main");

        mainPanel.add(signUpButton);
        mainPanel.add(backButton);

        // Add action listeners
        signUpButton.addActionListener(e -> handleSignUp());
        backButton.addActionListener(e -> {
            clearFields();
            setVisible(false);
            mainPage.showMainPage();
        });

        // Add panel to frame
        add(mainPanel);
    }

    private void handleSignUp() {
        // Validate all fields
        if (!validateFields()) {
            return;
        }

        try {
            // Parse date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(dobField.getText(), formatter);

            // Create new student
            Student student = new Student(
                firstNameField.getText(),
                lastNameField.getText(),
                dob,
                (String) genderComboBox.getSelectedItem(),
                nicField.getText(),
                addressField.getText(),
                phoneField.getText()
            );

            // Validate passwords match
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Sign up the student
            student = studentManager.signUp(student, password);

            // Show success message with student ID
            JOptionPane.showMessageDialog(this,
                    "Sign Up Successful!\nYour Student ID is: " + student.getStudentId(),
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields and return to main page
            clearFields();
            setVisible(false);
            mainPage.showMainPage();

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this,
                    "Invalid date format. Please use YYYY-MM-DD",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateFields() {
        if (firstNameField.getText().trim().isEmpty() ||
            lastNameField.getText().trim().isEmpty() ||
            dobField.getText().trim().isEmpty() ||
            nicField.getText().trim().isEmpty() ||
            addressField.getText().trim().isEmpty() ||
            phoneField.getText().trim().isEmpty() ||
            passwordField.getPassword().length == 0 ||
            confirmPasswordField.getPassword().length == 0) {
            
            JOptionPane.showMessageDialog(this,
                    "All fields are required!",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        dobField.setText("");
        genderComboBox.setSelectedIndex(0);
        nicField.setText("");
        addressField.setText("");
        phoneField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }
} 