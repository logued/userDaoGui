package org.example.usergui.BusinessObjects;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.example.usergui.DTOs.User;

import java.util.Collections;
import java.util.List;

public class ShowAllUsersController {



    // dependency on the friends list Model
    private ShowAllUsersModel showAllUsersModel;

    @FXML
    public Label titleText;
    @FXML
    public TextField nameTextField;
    @FXML
    public Button showFriendsButton;
    @FXML
    public Button clearAllButton;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label messageLabel;

    /// Constructor for this Controller (required by JavaFX to create the controller.)
    ///
    public ShowAllUsersController() {
    }

    /// The Constructor is called first, then @FXML fields are populated,
    /// then initialize() is called. Constructor has no access to FXML fields,
    /// but initialize() does due to the sequence of execution.
    @FXML
    private void initialize() {
        System.out.println("Initializing FriendsListController - initialize() called.");

        // Set a listener to handle an "Enter" keypress on the name text field.
        // This can't be done in the constructor as the nameTextField will not have
        // been loaded, but initialize() is called after the UI controls are created.
        //
//        nameTextField.setOnKeyReleased(event -> {
//            if (event.getCode() == KeyCode.ENTER){
//                // find person and show their friends
//                findPersonAndShowFriends();
//            }
//        });
    }

    /// Event Listener i.e. a method that is called when some GUI event
    /// happens. This method is called when the user clicks on the ShowFriends
    /// Button control.  The method is identified in the Button definition
    /// in the  friends-list-view.fxml file.
    /// <Button fx:id="showFriendsButton"
    ///         onAction="#onShowFriendsButtonClick" ...
    @FXML
    protected void onShowAllUsersButtonClick() {
        showAllUsers();  //TODO
    }

    /// Actions to be taken when user clicks on Show Friends button, or
    /// presses ENTER when the cursor is in the TextField.
    /// Extract the name from the TextField, search for the person,
    /// if found, display the persons friends.
    ///
    private void showAllUsers() {

        messageLabel.setText(""); // Blank out previous messages

        //String name = nameTextField.getText();  // get the name to search for

//        if( name==null || name.isEmpty() ) {
//            messageLabel.setText("Please enter a name");
//            listView.getItems().clear();  // clear listview
//            return;
//        }

        /// Access the Model to retrieve the person's list of friends
        List<User> listOfUsers = showAllUsersModel.getUsers();
        if( listOfUsers==null || listOfUsers.isEmpty() ) {
            messageLabel.setText("No friends found");
            listView.getItems().clear();
            return;
        }

        /// Note that listView.getItems.clear() will clear not only the listview
        /// but also the list it is bound to.  This may not be what you intend so pay extra attention.
        /// In this example, the Model (FriendsListModel) returns a clone(copy) of the
        /// friends list, so when we clear the ListView, the *copy* of the friends list is also cleared,
        /// but the underlying source of the friends list (in the Map) is not cleared, as this
        /// controller class is dealing wit a copy of the list and has no access to the Map
        /// or to its contents.  We have not leaked any references from our Model out to the GUI.
        /// This is good practice.

        /// Convert the list into an Observable Array List (as required by ListView)
        /// and set the listView to display the list of friends.
        /// This binds the ListView to the underlying ArrayList.
        ///
        listView.setItems((FXCollections.observableList(Collections.singletonList(listOfUsers.toString()))));
    }

    /// This Controller needs to get data from the FriendsListModel
    /// (which is known as the Model in the MODEL-VIEW-CONTROLLER Architecture)
    /// The model is instantiated in the main App and is passed into this controller
    /// as a dependency using the setModel() method below.
    /// This is called "Dependency Injection" (DI), because, instead of creating
    /// the FriendsListModel here, we "inject" a reference to a Model that is
    /// created elsewhere. (This reduces the coupling between the two classes).

    ///  Setter to inject the dependency on the Model - FriendsListModel class.
    ///
    public void setModel(ShowAllUsersModel showAllUsersModel) {
        this.showAllUsersModel = showAllUsersModel;
    }

    @FXML
    protected void onClearAllButtonClick() {
        listView.getItems().clear();
        nameTextField.clear();
        messageLabel.setText("");
    }
}
