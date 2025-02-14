package org.example.userdaogui.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.userdaogui.DTOs.User;

import java.util.Comparator;
import java.util.List;

public class UserListController {

    // dependency on the user list Model
    private UserListModel userListModel;

    @FXML
    public Label titleText;
    @FXML
    public Button showUsersButton;
    @FXML
    public Button clearAllButton;
    @FXML
    private ListView<User> listViewOfUsers;    // list view of users
    // private ListView<String>;  //original for lists of Strings
    @FXML
    private Label messageLabel;

    /// Constructor for this Controller (required by JavaFX to create the controller.)
    /// Instantiate ethe Model and keep a reference to it.
    /// ///
    public UserListController() {
        this.userListModel = new UserListModel();
    }

    /// The Constructor is called first, then @FXML fields are populated,
    /// then initialize() is called. Constructor has no access to FXML fields,
    /// but initialize() does, due to the sequence of execution.
    /// So, this is where we initialize UI Controls if necessary.
    @FXML
    private void initialize() {
        System.out.println("Initializing controller - initialize() called. (not used in this sample)");
    }

    /// Event Listener i.e. a method that is called when some GUI event
    /// happens. This method is called when the user clicks on the ShowUsers
    /// Button control.  The method is identified in the Button definition
    /// in the  users-listview.fxml file.
    /// <Button fx:id="showUsersButton"
    ///         onAction="#onShowAllUsersButtonClick" ...
    @FXML
    protected void onShowAllUsersButtonClick() {

        /// Actions to be taken when user clicks on Show Users button
        messageLabel.setText("onShowAllUsersButtonClick() was called."); // Blank out previous messages

        /// request the Model to load and store the list of Users from DAO
        userListModel.loadUsers();

        ///  get a copy of the User list
        List<User> listOfUsers = userListModel.getListOfUsers();

        if (listOfUsers == null || listOfUsers.isEmpty()) {
            messageLabel.setText("No Users found.");
            listViewOfUsers.getItems().clear();
            return;
        }

        ///  Sort the user list in ascending order of first name.
        ///  Data from the Model can be manipulated in any way at this point,
        ///  before it is displayed in a ListView UI Control
        listOfUsers.sort( Comparator.comparing(User::getFirstName) );

        /// Convert the list into an Observable Array List (as required by ListView)
        /// and set the ListView to display the list of (selected) User data.
        /// This code binds the ListView to the underlying ArrayList.
        /// Once bound, then any changes to the ObservableArrayList
        /// will be observed by the ListView, and the ListView will
        /// be updated to reflect those changes.
        ///
        ObservableList<User> observableUserList = FXCollections.observableArrayList();
        observableUserList.addAll(listOfUsers);

        // Bind the ListView UI Control to the ObservableList.
        // Once bound together:
        // Updates to the ObservableList will automatically be reflected in the ListView.
        // Updates to the ListView will be automatically reflected in the underlying ObservableList
        listViewOfUsers.setItems(observableUserList);   // bind ListView to the observable list

        /// Defines a CellFactory for use by the ListViewOfUsers control.
        /// The Cell Factory defines an updateItem() method to convert and format
        /// each User element from the ObservableList<User> into a String.
        /// It is here that we can select the fields that we wish to display,
        /// and format them in whatever way we wish (as a String)
        /// Here, we select fields firstName and lastName only, and concatenate
        /// them into one String to be added into the ListView as a row.
        ///    https://openjfx.io/javadoc/21/javafx.controls/javafx/scene/control/Cell.html
        ///
        listViewOfUsers.setCellFactory(param -> {
            return new ListCell<User>() {
                @Override
                protected void updateItem(User user, boolean empty) {
                    super.updateItem(user, empty);

                    if (empty || user == null) {
                        setText(null);
                    } else {
                        setText(user.getFirstName() + " " + user.getLastName());
                    }
                }
            };
        });
    }

    @FXML
    protected void onClearListButtonClick() {
        listViewOfUsers.getItems().clear();  // clears ListView and the underlying list
        messageLabel.setText("Users list has been cleared.");
        /// Note that listView.getItems.clear() will clear not only the ListView
        /// but also the list it is bound to. i.e. the observableUserList.

    }
}
