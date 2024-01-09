package org.westminsterShopping;

import java.util.ArrayList;
import java.util.List;

public class UserDetails {
    static List<User> userList = new ArrayList<>();

    public boolean isUserAvailable(String userName, String password) {

        for (User user: userList) {
            if (user.getUsername().equals(userName) && user.getPassword().equals(password)) // Check if the userName already exist
                return true;
        }
        return false;
    }

    public void displayUsers() {
        for(User user : userList) {
            System.out.println(user);
        }
    }

    public void signUpNewUser(User newUser){
        userList.add(newUser);
    }
}