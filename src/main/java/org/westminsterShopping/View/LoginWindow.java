package org.westminsterShopping.View;

import org.westminsterShopping.Model.User;
import org.westminsterShopping.Model.UserDetails;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame implements ActionListener{
    private static LoginWindow loginFrame = null; // Instance of the singleton login class
    private JTextField usernameField;
    private JPasswordField passwordField;
    JButton loginButton;
    JButton registerBtn;
    SignUpWindow registerWindow;
    User user;

    private LoginWindow() {

       initializeComponents();

       this.setBackground(new Color(255, 128, 1));

    }

    private void initializeComponents(){

        add(addLogo(), BorderLayout.NORTH);
        this.setResizable(false); //  For design purpose

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.setBorder(new EmptyBorder(new Insets(20, 20, 30, 20)));

        JLabel usernameLabel = new JLabel("Username:");
        designJLabel(usernameLabel, Color.BLACK);
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        designJLabel(passwordLabel, Color.BLACK);


        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        designJButton(loginButton);

        //inputPanel.add(logoIcon);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(new JLabel()); // Placeholder for spacing

        loginButton.addActionListener(this);

        inputPanel.add(loginButton);

        // Register option
        JPanel registerPanel = new JPanel(new GridLayout(1, 2));
        registerPanel.setSize(100, 200);


        JLabel newUserCheck = new JLabel("New User? ");
        designJLabel(newUserCheck, Color.BLACK);


        registerBtn = new JButton("Sign up");
        designJButton(registerBtn);

        registerBtn.addActionListener(this);

        // registerPanel.setBorder(new EmptyBorder(new Insets(20, 20, 30, 20)));
        designJPanel(registerPanel);

        registerPanel.add(newUserCheck);
        registerPanel.add(registerBtn);

        inputPanel.setBackground(new Color(254, 235, 216));
        registerPanel.setBackground(new Color(254, 199, 144));


        add(inputPanel, BorderLayout.CENTER);
        add(registerPanel, BorderLayout.SOUTH);

    }

    public JLabel addLogo() {
        ImageIcon logo = new ImageIcon("src/main/java/org/westminsterShopping/Controller/online-shopping.jpg");
        JLabel logoIcon = new JLabel(logo);
        logoIcon.setSize(50, 50);
        logoIcon.setLayout(new FlowLayout());
        logoIcon.setHorizontalAlignment(JLabel.CENTER);
        logoIcon.setVerticalAlignment(JLabel.CENTER);

        logoIcon.setOpaque(true);
        logoIcon.setBackground(new Color(254, 199, 144));

        return logoIcon;
    }

    public static void designJLabel(JLabel label, Color color) {
        label.setForeground(color);
        label.setFont(new Font("Monospaced", Font.ITALIC, 15));


    }

    public static void designJButton(JButton button) {

        button.setBackground(new Color(255, 128, 1));
        button.setOpaque(true);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Monospaced", Font.BOLD, 13));
        button.setBorderPainted(false);
        button.setFocusPainted(false);

    }

    public static void designJPanel(JPanel panel) {

        panel.setBackground(new Color(254, 217, 181));
        panel.setOpaque(true);
        panel.setForeground(Color.BLACK);
        panel.setFont(new Font("Monospaced", Font.BOLD, 13));
        panel.setBorder(new EmptyBorder(new Insets(20, 20, 30, 20)));


    }

    public static LoginWindow getInstance() {
        if (loginFrame == null ) {
            loginFrame = new LoginWindow();
        }
        return loginFrame;
    }

    public static LoginWindow getLoginInstanceState() {
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
                usernameField.setText("");
                passwordField.setText("");

                // Saving the user data upon user login
                userDetails.saveUserToFile();
                JOptionPane.showMessageDialog(LoginWindow.this, "Login successful!");

                // Open the Shopping Application
                //if (ProductDetailsWindow.getProductsWindowInstance() == null) {
                    JFrame frame = ProductDetailsWindow.getProductsWindowInstance();
                    frame.setTitle("Westminster Shopping Centre");
                    frame.setSize(1000, 1200);
                    frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                    frame.setVisible(true);
                //}

            } else {
                JOptionPane.showMessageDialog(LoginWindow.this, "Invalid username or password");
            }
        }
        if (e.getSource() == registerBtn) {
            registerWindow = new SignUpWindow();
        }
    }
}

