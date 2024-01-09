package org.westminsterShopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpWindow extends JFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    JButton registerButton;
    User user;

    public SignUpWindow() {

        this.setTitle("Sign Up");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(800, 400);


        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel  usernameLabel = new JLabel("Enter a Username: ");
        usernameField = new JTextField();
        JLabel  passwordLabel = new JLabel("Enter a Password: ");
        passwordField = new JPasswordField();
        registerButton = new JButton("Submit");

        registerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                user = new User(username, password);
                UserDetails userDetails = new UserDetails();

                if (!(username.equals("") || password.equals(""))) {
                    if (!userDetails.isUserAvailable(username, password)) {
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

        panel.add(registerButton);
        this.add(panel, BorderLayout.NORTH);
    }

}
