package com.studentmanagement.gui;

import javax.swing.*;
import java.awt.*;

public class MainPage extends JFrame {
    private final LoginPage loginPage;
    private final SignUpPage signUpPage;
    private final EditAccountPage editAccountPage;
    private final DeleteAccountPage deleteAccountPage;

    public MainPage() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Initialize pages
        loginPage = new LoginPage(this);
        signUpPage = new SignUpPage(this);
        editAccountPage = new EditAccountPage(this);
        deleteAccountPage = new DeleteAccountPage(this);

        // Create main panel with buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        JButton editButton = new JButton("Edit Account");
        JButton deleteButton = new JButton("Delete Account");

        // Add action listeners
        loginButton.addActionListener(e -> showLoginPage());
        signUpButton.addActionListener(e -> showSignUpPage());
        editButton.addActionListener(e -> showEditPage());
        deleteButton.addActionListener(e -> showDeletePage());

        // Add buttons to panel
        mainPanel.add(loginButton);
        mainPanel.add(signUpButton);
        mainPanel.add(editButton);
        mainPanel.add(deleteButton);

        // Add panel to frame
        add(mainPanel);
    }

    public void showMainPage() {
        setVisible(true);
        loginPage.setVisible(false);
        signUpPage.setVisible(false);
        editAccountPage.setVisible(false);
        deleteAccountPage.setVisible(false);
    }

    private void showLoginPage() {
        loginPage.setVisible(true);
        setVisible(false);
    }

    private void showSignUpPage() {
        signUpPage.setVisible(true);
        setVisible(false);
    }

    private void showEditPage() {
        editAccountPage.setVisible(true);
        setVisible(false);
    }

    private void showDeletePage() {
        deleteAccountPage.setVisible(true);
        setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainPage mainPage = new MainPage();
            mainPage.setVisible(true);
        });
    }
} 