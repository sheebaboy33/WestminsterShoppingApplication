package org.westminsterShopping.Model;

import org.westminsterShopping.Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDetails {
    static List<User> userList = new ArrayList<>();

    public boolean isUserAvailable(String userName, String password) {

        for (User user : userList) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) // Check if the userName already exist
                return true;
        }
        return false;
    }


    public void signUpNewUser(User newUser) {
        userList.add(newUser);
    }

    public void saveUserToFile() {
        try {
            FileOutputStream fos = new FileOutputStream("WestminsterUserDetails.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (User user : userList) {
                oos.writeObject(user);
            }
            // System.out.println("User Details Saved to File."); (One common message would be sufficient)

            fos.close();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + ", Try again");
        }
    }


    public void retrieveDataFromFile() {
        try {
            FileInputStream fis = new FileInputStream("WestminsterUserDetails.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            for (; ; ) { // Infinite for loop
                try {
                    User user = (User) ois.readObject();
                    userList.add(user);

                } catch (EOFException e) {
                    break;
                }
            }
            fis.close();
            ois.close();

        } catch(Exception e) {
            System.out.println("File is currently Empty. Try again");
        }
    }
}
