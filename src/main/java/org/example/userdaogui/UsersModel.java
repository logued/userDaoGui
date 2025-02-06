package org.example.userdaogui;     // Feb 2025

import org.example.userdaogui.DTOs.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The UsersModel class stores the users data in a list.
 * This is the source of user data for the Controller.
 * When this class is instantiated, it is passed into the Controller
 * so that the Controller can access the user data.
 */
public class UsersModel {

    private final ArrayList<User> listOfUsers;    // model stores a list of all users

    /**
     * Constructor accepts a reference to a list of User objects
     * and stores that reference.
     * @param usersFromDaoRequest List of User
     */
    public UsersModel(List<User> usersFromDaoRequest) {
        listOfUsers = new ArrayList<>(usersFromDaoRequest);  // clone the list
    }

    /**
     * @return List of User or null
     */
    public List<User> getUsers() {
        return listOfUsers;
    }

}
