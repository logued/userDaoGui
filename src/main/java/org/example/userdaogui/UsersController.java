package org.example.userdaogui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.userdaogui.DTOs.User;

import java.util.List;

public class UsersController {

    // dependency on the users Model
    private UsersModel usersModel;

    @FXML
    public Label titleText;

    @FXML
    public Button showUsersButton;
    @FXML
    public Button clearAllButton;
    @FXML
    private ListView<User> listViewOfUsers;    // list view of user, requires
    // private ListView<String>;  //original for lists of Strings

    @FXML
    private Label messageLabel;

    /// Constructor for this Controller (required by JavaFX to create the controller.)
    ///
    public UsersController() {
    }

    /// The Constructor is called first, then @FXML fields are populated,
    /// then initialize() is called. Constructor has no access to FXML fields,
    /// but initialize() does due to the sequence of execution.
    @FXML
    private void initialize() {
        System.out.println("Initializing controller - initialize() called. (not used in this sample)");
    }

    /// Event Listener i.e. a method that is called when some GUI event
    /// happens. This method is called when the user clicks on the ShowUsers
    /// Button control.  The method is identified in the Button definition
    /// in the  friends-list-view.fxml file.
    /// <Button fx:id="showFriendsButton"
    ///         onAction="#onShowFriendsButtonClick" ...
    @FXML
    protected void onShowAllUsersButtonClick() {
        showAllUsers();
    }

    /// Actions to be taken when user clicks on Show Users button,
    /// Call the getUsers() in the Model, and display the list of
    /// users in a ListView.
    ///
    private void showAllUsers() {

        messageLabel.setText("showAllUsers() was called."); // Blank out previous messages

        /// Access the Model to retrieve the list of Users
        List<User> listOfUsers = usersModel.getUsers();

        if( listOfUsers==null || listOfUsers.isEmpty() ) {
            messageLabel.setText("No friends found");
            listViewOfUsers.getItems().clear();
            return;
        }

        ///  Sort the user list in ascending order of first name.
        ///  Data from the Model can be manipulated in any way at this point,
        ///  before it is displayed in a ListView UI Control
        listOfUsers.sort(
                (user1,user2) ->
                     user1.getFirstName().compareTo(user2.getFirstName())
        );

        /// Note that listView.getItems.clear() will clear not only the listview
        /// but also the list it is bound to.  This may not be what you intend so pay extra attention.

        /// Convert the list into an Observable Array List (as required by ListView)
        /// and set the listView to display the list of (selected) User data.
        /// This binds the ListView to the underlying ArrayList.
        /// Once bound, then any change to the ObservableArrayList
        /// will be observed by the ListView and the ListView will
        ///  be updated to reflect the change.

//        // List View deals with list of String automatically, so convert list of
//        // users into a list of String so that it can be displayed in ListView
//        //(This is not the ideal solution, but will suffice for the moment)  DL
//        ArrayList<String> userListAsStrings = new ArrayList<String>();
//
//        for( User user : listOfUsers ) {
//            userListAsStrings.add(user.toString());
//        }
//        listView.setItems((FXCollections.observableList(userListAsStrings)));

        ObservableList<User> observableUserList = FXCollections.observableArrayList();
        observableUserList.addAll(listOfUsers);

        listViewOfUsers.setItems(observableUserList);   // bind ListView to the observable list

        /// Defines a CellFactory for use by the ListViewOfUsers control.
        /// The Cell Factory defines an updateItem() method to convert and format
        /// each User element from the ObservableList<User> into type String.
        /// It is here that we can select the fields that we wish to display,
        /// and format them in whatever way we wish.
        /// Here, we select fields firstName and lastName only, and assemble them
        /// into individual Strings to be added into the ListView.
        ///
        listViewOfUsers.setCellFactory(param -> {
            return new ListCell<User>() {
                @Override
                protected void updateItem(User item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getFirstName() + " " + item.getLastName());
                    }
                }
            };
        });
    }

    /// This Controller gets data from the UsersModel
    /// (which is known as the Model in the MODEL-VIEW-CONTROLLER Architecture)
    /// The model is instantiated in the main App and is passed into this controller
    /// as a dependency using the setModel() method below.
    /// This is called "Dependency Injection" (DI), because, instead of creating
    /// the Model here, we "inject" a reference to a Model that is
    /// created elsewhere. (This reduces the coupling between the two classes).

    ///  Setter method to inject the dependency on the Model - UsersModel class.
    ///
    public void setModel(UsersModel usersModel) {
        this.usersModel = usersModel;
    }

    @FXML
    protected void onClearListButtonClick() {
        listViewOfUsers.getItems().clear();
        messageLabel.setText("Users list has been cleared.");
    }
}
