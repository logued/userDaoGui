package org.example.userdaogui.GUI;     // Feb 2025

import org.example.userdaogui.DAOs.MySqlUserDao;
import org.example.userdaogui.DAOs.UserDaoInterface;
import org.example.userdaogui.DTOs.User;
import org.example.userdaogui.Exceptions.DaoException;

import java.util.ArrayList;
import java.util.List;

/**
 * The UserListModel class stores the users data in a list.
 * This is the source of user data for the Controller.
 * When this class is instantiated, it is passed into the Controller
 * so that the Controller can access the user data.
 */
public class UserListModel {

    private List<User> listOfUsers;    // model stores a list of all users

    /**
     * Constructor accepts a reference to a
     */
    public UserListModel() {
    }

    /**
     *  Load Users from DAO
     */
    public void loadUsers() {

        /// Create the UserListModel (The Model), populate it with users list from DAO,
        /// and inject the Model into the Controller (i.e. pass in a reference
        /// to the Model so that the Controller can access it.) (Dependency Injection!)

        UserDaoInterface IUserDao = new MySqlUserDao();  //"IUserDao" -> "I" stands for Interface
        try {
            this.listOfUsers = IUserDao.findAllUsers();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        //return listOfUsers;
    }

    public List<User> getListOfUsers() {
        return new ArrayList<>(this.listOfUsers);   // clone/copy of the list
    }

}
