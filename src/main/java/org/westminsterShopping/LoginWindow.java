package org.westminsterShopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener{
    private static LoginWindow loginFrame = null; // Instance of the singleton class
    private JTextField usernameField;
    private JPasswordField passwordField;
    JButton loginButton;
    JButton registerBtn;
    SignUpWindow registerWindow;
    User user;

    private LoginWindow() {
        if (loginFrame == null) {
            initializeComponents();
        }
    }

    private void initializeComponents(){
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");


        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Placeholder for spacing

        loginButton.addActionListener(this);

        panel.add(loginButton);

        // Register option
        JPanel registerPanel = new JPanel(new GridLayout(1, 2));
        JLabel newUserCheck = new JLabel("New User? ");
        registerBtn = new JButton("Sign up");
        registerBtn.addActionListener(this);

        registerPanel.add(newUserCheck);
        registerPanel.add(registerBtn);

        add(panel, BorderLayout.NORTH);
        add(registerPanel, BorderLayout.SOUTH);
    }


    public static LoginWindow getInstance() {
        if (loginFrame == null) {
            loginFrame = new LoginWindow();
        }
        return loginFrame;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            user = new User(username, password);

            UserDetails userDetails = new UserDetails();

            if (userDetails.isUserAvailable(username, password)) {
                dispose();

                // Saving the user data upon user login
                userDetails.saveUserToFile();
                JOptionPane.showMessageDialog(LoginWindow.this, "Login successful!");

                // Open the Shopping Application
                JFrame frame = new ProductDetailsWindow();
                frame.setTitle("Westminster Shopping Centre");
                frame.setSize(1000, 1200);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(LoginWindow.this, "Invalid username or password");
            }
        }
        if (e.getSource() == registerBtn) {
            //registerWindow.setVisible(true);
            registerWindow = new SignUpWindow();
        }
    }
}

