package com.studentmanagement.gui;

import com.studentmanagement.service.StudentManager;
import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private final MainPage mainPage;
    private final StudentManager studentManager;
    private final JTextField studentIdField;
    private final JPasswordField passwordField;

    public LoginPage(MainPage mainPage) {
        this.mainPage = mainPage;
        this.studentManager = new StudentManager();
        
        setTitle("Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components
        JLabel studentIdLabel = new JLabel("Student ID:");
        studentIdField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back to Main");

        // Add components to panel
        mainPanel.add(studentIdLabel);
        mainPanel.add(studentIdField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(new JLabel()); // Empty space
        mainPanel.add(loginButton);
        mainPanel.add(new JLabel()); // Empty space
        mainPanel.add(backButton);

        // Add action listeners
        loginButton.addActionListener(e -> handleLogin());
        backButton.addActionListener(e -> {
            clearFields();
            setVisible(false);
            mainPage.showMainPage();
        });

        // Add panel to frame
        add(mainPanel);
    }

    private void handleLogin() {
        String studentId = studentIdField.getText();
        String password = new String(passwordField.getPassword());

        if (studentManager.login(studentId, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            clearFields();
            setVisible(false);
            mainPage.showMainPage();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Student ID or Password",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        studentIdField.setText("");
        passwordField.setText("");
    }
} 