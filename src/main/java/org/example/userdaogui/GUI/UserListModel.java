package org.example.userdaogui.GUI;     // Feb 2025

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.userdaogui.DAOs.MySqlUserDao;
import org.example.userdaogui.DAOs.UserDaoInterface;
import org.example.userdaogui.DTOs.User;
import org.example.userdaogui.Exceptions.DaoException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The UserListModel class stores the users data in a list.
 * This is the source of user data for the Controller.
 * When this class is instantiated, it is passed into the Controller
 * so that the Controller can access the user data.
 */
public class UserListModel {

    private List<User> listOfUsers;    // model stores a list of all users (Domain Objects)

    // This model holds the ObservableList that will be bound to the LIstView UI Control in the View
    ObservableList<User> observableListOfUsers;    // observable List (a Property)

    /**
     * Constructor - populates model with data from DAO
     */
    public UserListModel() {
        /// Create an Observable Array List (as required by ListView)
        this.observableListOfUsers = FXCollections.observableArrayList();
    }

    /**
     * Get List of Users using DAO, and populate the ObservableList from the list returned.
     */
    private void populateListsUsingDao() {
        UserDaoInterface IUserDao = new MySqlUserDao();  //"IUserDao" -> "I" stands for Interface
        try {
            this.listOfUsers = IUserDao.findAllUsers();

            if(this.listOfUsers != null) {
                /// Populate the ObservableList from the List
                this.observableListOfUsers.addAll(listOfUsers);
            }
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
    }

    public void reloadUserListModel() {
        this.observableListOfUsers.clear(); // wipe out existing elements
        populateListsUsingDao();
    }

    public void sortUserList(Comparator<User> comparator) {
        this.observableListOfUsers.sort(comparator);
    }

    public ObservableList<User> getObservableListOfUsers() {

        return this.observableListOfUsers;
    }




}
