package org.example.userdaogui;

import org.example.userdaogui.DTOs.User;

import java.util.ArrayList;
import java.util.List;

public class UsersModel {


    ArrayList<User> listOfUsers;    // model stores list of users

    public UsersModel(List<User> usersFromDaoRequest) {
        listOfUsers = new ArrayList<>(usersFromDaoRequest);  // clone the list

    }

    /**
     * @param personsName name of person whose friends we want to access
     * @return List of friends names or null (a clone copy of the list is returned)
     */
    public List<User> getUsers() {

        return new ArrayList<>(listOfUsers);  // clone(copy) the list to prevent reference leakage
    }

}
