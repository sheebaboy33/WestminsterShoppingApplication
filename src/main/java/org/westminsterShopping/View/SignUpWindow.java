package org.westminsterShopping.View;

import org.westminsterShopping.Model.User;
import org.westminsterShopping.Model.UserDetails;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpWindow extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    JButton registerButton;
    User user;
    static Boolean newCustomer = false;

    public SignUpWindow() {

        this.setTitle("Sign Up");
        this.setSize(450, 175);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(800, 400);
        this.setBackground(new Color(255, 128, 1));
        this.setResizable(false);



        JPanel panel = new JPanel(new GridLayout(3, 2));


        JLabel  usernameLabel = new JLabel("Enter a Username: ");
        LoginWindow.designJLabel(usernameLabel, Color.WHITE);

        JLabel  passwordLabel = new JLabel("Enter a Password: ");
        LoginWindow.designJLabel(passwordLabel, Color.WHITE);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        registerButton = new JButton("Submit");
        LoginWindow.designJButton(registerButton);

        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                user = new User(username, password);
                UserDetails userDetails = new UserDetails();

                if (!(username.equals("") || password.equals(""))) {
                    if (!userDetails.isUserAvailable(username, password)) {
                        newCustomer = true;
                        userDetails.signUpNewUser(user);
                        dispose();
                        JOptionPane.showMessageDialog(SignUpWindow.this, "SignUp Successful!");
                    } else {
                        JOptionPane.showMessageDialog(SignUpWindow.this, "User already exist.");
                    }
                }
            }
        });
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Placeholder for spacing

        panel.setBackground(new Color(254, 199, 144));
        panel.setBorder(new EmptyBorder(5,10,5,5));

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(registerButton, BorderLayout.CENTER);
        buttonPanel.setBackground(new Color(254, 199, 144));
        buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));


        panel.add(buttonPanel, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.NORTH);
    }

}
