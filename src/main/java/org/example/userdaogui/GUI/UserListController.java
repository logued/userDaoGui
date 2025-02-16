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
    /// Instantiate the Model and keep a reference to it.
    ///
    public UserListController() {
        this.userListModel = new UserListModel();
    }

    /// The Constructor is called first, then @FXML fields are populated,
    /// then initialize() is called. Constructor has no access to FXML fields,
    /// but initialize() does, due to the sequence of execution.
    /// So, this is where we initialize UI Controls (after their creation) if necessary.
    @FXML
    private void initialize() {
        System.out.println("Initializing controller - initialize() called. (not used in this sample)");
    }

    /// Event Handler (or Event Listener)
    ///  i.e. a method that is called when some GUI event happens.
    /// This method is called when the user clicks on the ShowUsers Button.
    /// The method is identified in the Button definition
    /// in the  users-listview.fxml file.
    /// <Button fx:id="showUsersButton"
    ///         onAction="#onShowAllUsersButtonClick" ...
    @FXML
    protected void onShowAllUsersButtonClick() {

        /// Actions to be taken when user clicks on Show Users button
        messageLabel.setText("onShowAllUsersButtonClick() was called."); // Blank out previous messages

        ///  Sort the user list in ascending order of first name.
        ///  Data from the Model can be manipulated in any way,
        ///  before it is displayed in a ListView UI Control

        userListModel.reloadUserListModel();
        userListModel.sortUserList( Comparator.comparing(User::getFirstName) );

        /// Bind the ListView UI Control to the ObservableList.
        /// Once bound together:
        ///  - Updates to the ObservableList will automatically be reflected in the ListView.
        ///  - Updates to the ListView will be automatically reflected in the underlying ObservableList

        listViewOfUsers.setItems(userListModel.getObservableListOfUsers());

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
        listViewOfUsers.getItems().clear();  // clears ListView
        messageLabel.setText("ListView has been cleared.");
        /// Note that listView.getItems.clear() will clear not only the ListView
        /// but also the list it is bound to. i.e. the observableListOfUsers.

    }
}
