import javafx.application.Application;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ConnectifyGUI extends Application {

    private Directory dir = new Directory(20);

    private Label totalValue = new Label("0");
    private Label favValue = new Label("0");
    private Label emergencyValue = new Label("0");

    private Label statusLabel = new Label("Ready");

    private VBox contactsContainer = new VBox(12);

    private TextField nameField = new TextField();
    private TextField phoneField = new TextField();
    private TextField searchField = new TextField();
    private Button addBtn;
private Button deleteBtn;
private Button favoriteBtn;
private Button emergencyBtn;
private Contact selectedContact = null;
    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        root.setStyle(
                "-fx-background-color:#0F172A;"
        );

        VBox sidebar = createSidebar();

        VBox centerContent = createCenterContent();

        root.setLeft(sidebar);
        root.setCenter(centerContent);

        Scene scene = new Scene(root, 1400, 800);

        stage.setTitle("Connectify");
        stage.setScene(scene);
       
 addBtn.setOnAction(e -> {

    String phone = phoneField.getText();

    if(!phone.matches("\\d{10}")){

        updateStatus(
                "❌ Phone must contain exactly 10 digits",
                "#EF4444");

        return;
    }

    if(dir.contactExists(
            nameField.getText())){

        updateStatus(
                "⚠ Contact Already Exists",
                "#F59E0B");

        return;
    }

    dir.addContact(
            nameField.getText(),
            phoneField.getText());

            Contact contact =
        new Contact(
                nameField.getText(),
                phoneField.getText());

ContactDAO.addContact(contact);

    refreshContactList();
    updateStats();

    updateStatus(
            "✅ Contact Added",
            "#10B981");

    nameField.clear();
    phoneField.clear();
});

deleteBtn.setOnAction(e -> {

    if(selectedContact == null){

        updateStatus(
                "⚠ Select a contact first",
                "#F59E0B");

        return;
    }

    dir.deleteContact(
            selectedContact.getName());

            ContactDAO.deleteContact(
        selectedContact.getName());

    selectedContact = null;

    refreshContactList();
    updateStats();

    updateStatus(
            "🗑 Contact Deleted",
            "#EF4444");
});

favoriteBtn.setOnAction(e -> {

    if(selectedContact == null){

        updateStatus(
                "⚠ Select a contact first",
                "#F59E0B");

        return;
    }

    dir.toggleFavorite(
            selectedContact.getName());

            ContactDAO.updateContact(
        selectedContact);

    refreshContactList();
    updateStats();

    updateStatus(
            "⭐ Favorite Updated",
            "#F59E0B");
});
emergencyBtn.setOnAction(e -> {

    if(selectedContact == null){

        updateStatus(
                "⚠ Select a contact first",
                "#F59E0B");

        return;
    }

    dir.toggleEmergency(
            selectedContact.getName());

            ContactDAO.updateContact(
        selectedContact);

    refreshContactList();
    updateStats();

    updateStatus(
            "🚨 Emergency Updated",
            "#F97316");
}); 

searchField.textProperty().addListener(
    (obs, oldVal, newVal) -> {

        refreshContactList(newVal);
    }
);



stage.show();

refreshContactList();
updateStats();
    }

    private VBox createSidebar() {

        VBox sidebar = new VBox(15);

        sidebar.setPadding(new Insets(25));

        sidebar.setPrefWidth(230);

        sidebar.setStyle(
                "-fx-background-color:linear-gradient(to bottom,#020617,#111827);"
        );

        Label logo = new Label("📞 CONNECTIFY");

        logo.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:24px;" +
                "-fx-font-weight:bold;"
        );

        Button dashboardBtn =
                createSidebarButton("🏠 Dashboard");

        Button allBtn =
                createSidebarButton("📋 Display All");

        Button favBtn =
                createSidebarButton("⭐ Favorites");

        Button emergencyBtn =
                createSidebarButton("🚨 Emergency");

        dashboardBtn.setOnAction(e -> {

    refreshContactList("");

    updateStatus(
            "🏠 Dashboard",
            "#2563EB"
    );
});

allBtn.setOnAction(e -> {
    refreshContactList();
});

favBtn.setOnAction(e -> {

    contactsContainer.getChildren().clear();

    for(Contact c : dir.getContacts()) {

        if(c != null &&
           c.isFavorite()) {

            contactsContainer
                    .getChildren()
                    .add(
                            createContactCard(c)
                    );
        }
    }

    updateStatus(
            "⭐ Showing Favorite Contacts",
            "#F59E0B"
    );
});

emergencyBtn.setOnAction(e -> {

    contactsContainer.getChildren().clear();

    for(Contact c : dir.getContacts()) {

        if(c != null &&
           c.isEmergency()) {

            contactsContainer
                    .getChildren()
                    .add(
                            createContactCard(c)
                    );
        }
    }

    updateStatus(
            "🚨 Showing Emergency Contacts",
            "#F97316"
    );
});



        sidebar.getChildren().addAll(
                logo,
                dashboardBtn,
                allBtn,
                favBtn,
                emergencyBtn
        );

        return sidebar;
    }

    private VBox createCenterContent() {

        VBox center = new VBox(20);

        center.setPadding(new Insets(25));

        Label title =
                new Label("⚡ Connectify Dashboard");

        title.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:32px;" +
                "-fx-font-weight:bold;"
        );

        searchField.setPromptText(
        "🔍 Search by name, phone, nickname or email");

        searchField.setPrefWidth(350);

        searchField.setStyle(
                "-fx-background-radius:20;" +
                "-fx-padding:10;"
        );

        HBox header =
                new HBox(20, title, searchField);

        header.setAlignment(Pos.CENTER_LEFT);

        HBox stats = new HBox(
                20,
                createCard("👥 Contacts", totalValue),
                createCard("⭐ Favorites", favValue),
                createCard("🚨 Emergency", emergencyValue)
        );

        nameField.setPromptText("Enter Name");

        phoneField.setPromptText("Enter Phone");

        addBtn =
        createActionButton("➕ Add");

deleteBtn =
        createActionButton("🗑 Delete");

favoriteBtn =
        createActionButton("⭐ Favorite");

emergencyBtn =
        createActionButton("🚨 Emergency");

        HBox actions = new HBox(
                15,
                addBtn,
                deleteBtn,
                favoriteBtn,
                emergencyBtn
        );

        VBox form = new VBox(
                10,
                nameField,
                phoneField,
                actions
        );

        form.setPadding(new Insets(20));

        form.setStyle(
                "-fx-background-color:#111827;" +
                "-fx-background-radius:20;"
        );

        contactsContainer.setPadding(new Insets(5));

ScrollPane scrollPane =
        new ScrollPane(contactsContainer);

scrollPane.setFitToWidth(true);

scrollPane.setPrefHeight(400);

scrollPane.setStyle(
        "-fx-background:#0F172A;" +
        "-fx-background-color:transparent;"
);

        

        statusLabel.setStyle(
                "-fx-background-color:#1E293B;" +
                "-fx-text-fill:white;" +
                "-fx-padding:10;" +
                "-fx-background-radius:10;"
        );

        Label footer = new Label(
    "© 2026 Connectify | Developed by Nitika Dewangan"
);

footer.setStyle(
    "-fx-text-fill:#94A3B8;" +
    "-fx-font-size:12px;"
);

footer.setMaxWidth(Double.MAX_VALUE);
footer.setAlignment(Pos.CENTER);

        center.getChildren().addAll(
        header,
        stats,
        form,
        scrollPane,
        statusLabel,
        footer
);
        refreshContactList();
updateStats();
        return center;
    }
        private Button createSidebarButton(String text) {

        Button btn = new Button(text);

        btn.setPrefWidth(180);
        btn.setPrefHeight(45);

        btn.setStyle(
                "-fx-background-color:#1E293B;" +
                "-fx-text-fill:white;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:12;"
        );

        return btn;
    }

    private Button createActionButton(String text) {

        Button btn = new Button(text);

        btn.setPrefWidth(140);
        btn.setPrefHeight(45);

        btn.setStyle(
                "-fx-background-color:linear-gradient(to right,#2563EB,#06B6D4);" +
                "-fx-text-fill:white;" +
                "-fx-font-size:14px;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:15;"
        );

        return btn;
    }

    private VBox createCard(
            String title,
            Label valueLabel) {

        Label titleLabel = new Label(title);

        titleLabel.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:16px;"
        );

        valueLabel.setStyle(
                "-fx-text-fill:white;" +
                "-fx-font-size:30px;" +
                "-fx-font-weight:bold;"
        );

        VBox card = new VBox(
                15,
                titleLabel,
                valueLabel
        );

        card.setPadding(new Insets(20));

        card.setPrefSize(220,130);

        card.setStyle(
                "-fx-background-color:linear-gradient(to right,#2563EB,#7C3AED);" +
                "-fx-background-radius:20;"
        );

        return card;
    }
    
    private HBox createContactCard(Contact c) {

    Label nameLabel =
            new Label("👤 " + c.getName());

    nameLabel.setStyle(
            "-fx-text-fill:white;" +
            "-fx-font-size:18px;" +
            "-fx-font-weight:bold;"
    );

    Label phoneLabel =
            new Label("📞 " + c.getPhone());

    phoneLabel.setStyle(
            "-fx-text-fill:#CBD5E1;" +
            "-fx-font-size:14px;"
    );

    VBox leftContent =
            new VBox(
                    8,
                    nameLabel,
                    phoneLabel
            );

    String badges = "";

    if(c.isFavorite())
        badges += "⭐ ";

    if(c.isEmergency())
        badges += "🚨";

    Label rightIcons =
            new Label(badges);

    rightIcons.setStyle(
            "-fx-font-size:22px;"
    );

    Region spacer = new Region();

    HBox.setHgrow(
            spacer,
            Priority.ALWAYS
    );

    HBox card = new HBox(
            20,
            leftContent,
            spacer,
            rightIcons
    );

    card.setPadding(
            new Insets(18)
    );

    card.setStyle(
            "-fx-background-color:rgba(255,255,255,0.08);" +
            "-fx-background-radius:18;" +
            "-fx-border-color:rgba(255,255,255,0.12);" +
            "-fx-border-radius:18;"
    );

    card.setOnMouseClicked(e -> {

        selectedContact = c;

        if(e.getClickCount() == 2){

            openContactDetails(c);

        } else {

            refreshContactList(
                    searchField.getText()
            );
        }
    });

    if(selectedContact == c) {

        card.setStyle(
                "-fx-background-color:rgba(37,99,235,0.35);" +
                "-fx-background-radius:18;" +
                "-fx-border-color:#3B82F6;" +
                "-fx-border-width:2;" +
                "-fx-border-radius:18;"
        );
    }

    return card;
}
    private void refreshContactList() {


    contactsContainer.getChildren().clear();

    Contact[] contacts = dir.getContacts();

    for(Contact c : contacts) {

        if(c == null) continue;

        Label nameLabel =
        new Label("👤 " + c.getName());

nameLabel.setStyle(
        "-fx-text-fill:white;" +
        "-fx-font-size:18px;" +
        "-fx-font-weight:bold;"
);

Label phoneLabel =
        new Label("📞 " + c.getPhone());

phoneLabel.setStyle(
        "-fx-text-fill:#CBD5E1;" +
        "-fx-font-size:14px;"
);

        VBox leftContent =
                new VBox(8,
                        nameLabel,
                        phoneLabel);

        String badges = "";

        if(c.isFavorite())
            badges += "⭐ ";

        if(c.isEmergency())
            badges += "🚨";

        Label rightIcons =
                new Label(badges);

        rightIcons.setStyle(
                "-fx-font-size:22px;"
        );

        Region spacer = new Region();

        HBox.setHgrow(
                spacer,
                Priority.ALWAYS
        );

        HBox card = new HBox(
                20,
                leftContent,
                spacer,
                rightIcons
        );

        card.setPadding(
                new Insets(18)
        );

        card.setStyle(
                "-fx-background-color:rgba(255,255,255,0.08);" +
                "-fx-background-radius:18;" +
                "-fx-border-color:rgba(255,255,255,0.12);" +
                "-fx-border-radius:18;"
        );

        card.setOnMouseClicked(e -> {

    selectedContact = c;

    if(e.getClickCount() == 2){
        openContactDetails(c);
    } else {
        refreshContactList(searchField.getText());
    }
});
       if(selectedContact == c) {

    card.setStyle(
            "-fx-background-color:rgba(37,99,235,0.35);" +
            "-fx-background-radius:18;" +
            "-fx-border-color:#3B82F6;" +
            "-fx-border-width:2;" +
            "-fx-border-radius:18;"
    );
}

        contactsContainer
                .getChildren()
                .add(card);
    }
}

    private void updateStats() {

    totalValue.setText(
            String.valueOf(
                    dir.getTotalContacts()));

    favValue.setText(
            String.valueOf(
                    dir.getFavoriteCount()));

    emergencyValue.setText(
            String.valueOf(
                    dir.getEmergencyCount()));
}

private void refreshContactList(String searchText) {

    contactsContainer.getChildren().clear();

    Contact[] contacts = dir.getContacts();

    for(Contact c : contacts) {

        if(c == null)
            continue;

        boolean match =
                c.getName()
                 .toLowerCase()
                 .contains(searchText.toLowerCase())

                ||

                c.getPhone()
                 .contains(searchText)

                ||

                c.getNickname()
                 .toLowerCase()
                 .contains(searchText.toLowerCase())

                ||

                c.getEmail()
                 .toLowerCase()
                 .contains(searchText.toLowerCase());

        if(!match)
            continue;

        contactsContainer.getChildren().add(
        createContactCard(c)
);
    }
}

private void updateStatus(
        String message,
        String color) {

    statusLabel.setText(message);

    statusLabel.setStyle(
            "-fx-background-color:" + color + ";" +
            "-fx-text-fill:white;" +
            "-fx-padding:10;" +
            "-fx-font-weight:bold;" +
            "-fx-background-radius:10;"
    );

    PauseTransition pause =
            new PauseTransition(Duration.seconds(3));

    pause.setOnFinished(e -> {

        statusLabel.setText("Ready");

        statusLabel.setStyle(
                "-fx-background-color:#1E293B;" +
                "-fx-text-fill:white;" +
                "-fx-padding:10;" +
                "-fx-background-radius:10;"
        );
    });

    pause.play();
}

private void openContactDetails(Contact contact) {

    Stage popup = new Stage();

    VBox root = new VBox(15);
    root.setPadding(new Insets(20));

    root.setStyle(
            "-fx-background-color:#111827;"
    );

    Label title =
            new Label("📇 Contact Details");

    title.setStyle(
            "-fx-text-fill:white;" +
            "-fx-font-size:22px;" +
            "-fx-font-weight:bold;"
    );

    Label nicknameLabel =
        new Label("Nickname :");

nicknameLabel.setStyle(
        "-fx-text-fill:white;"
);

TextField nicknameField =
        new TextField(contact.getNickname());

    Label alternateLabel =
        new Label("Alternate Number :");

alternateLabel.setStyle(
        "-fx-text-fill:white;"
);

TextField alternateField =
        new TextField(contact.getAlternateNumber());

    Label emailLabel =
        new Label("Email :");

emailLabel.setStyle(
        "-fx-text-fill:white;"
);

TextField emailField =
        new TextField(contact.getEmail());

    Label nameLabel =
        new Label(contact.getName());

nameLabel.setStyle(
        "-fx-text-fill:#60A5FA;" +
        "-fx-font-size:40px;" +
        "-fx-font-weight:bold;"
);


        Label phoneLabel =
                new Label("📞 " + contact.getPhone());

        phoneLabel.setStyle(
                "-fx-text-fill:#CBD5E1;" +
                "-fx-font-size:14px;"
        );

    Label favoriteLabel =
            new Label(
                    contact.isFavorite()
                            ? "⭐ Favorite"
                            : "☆ Not Favorite"
            );

    Label emergencyLabel =
            new Label(
                    contact.isEmergency()
                            ? "🚨 Emergency"
                            : "○ Normal Contact"
            );

    nameLabel.setStyle("-fx-text-fill:white;");
    phoneLabel.setStyle("-fx-text-fill:white;");
    favoriteLabel.setStyle("-fx-text-fill:white;");
    emergencyLabel.setStyle("-fx-text-fill:white;");

    Button saveBtn =
            new Button("💾 Save");

    saveBtn.setStyle(
            "-fx-background-color:#2563EB;" +
            "-fx-text-fill:white;"
    );

    saveBtn.setOnAction(e -> {

        String email =
        emailField.getText();

if(!email.isEmpty() &&
   !email.matches(
           "^[A-Za-z0-9+_.-]+@(.+)$")) {

    Alert alert =
            new Alert(
                    Alert.AlertType.ERROR);

    alert.setTitle(
            "Invalid Email");

    alert.setHeaderText(null);

    alert.setContentText(
            "Please enter a valid email address.");

    alert.showAndWait();

    return;
}

String alternate =
        alternateField.getText();

if(!alternate.isEmpty() &&
   !alternate.matches("\\d{10}")) {

    Alert alert =
            new Alert(
                    Alert.AlertType.ERROR);

    alert.setTitle(
            "Invalid Alternate Number");

    alert.setHeaderText(null);

    alert.setContentText(
            "Alternate number must contain exactly 10 digits.");

    alert.showAndWait();

    return;
}

        contact.setNickname(
                nicknameField.getText());

       contact.setAlternateNumber(
        alternate);

        contact.setEmail(
        email.trim());

        ContactDAO.updateContact(
        contact);

        popup.close();

        updateStatus(
                "✅ Contact Updated",
                "#10B981"
        );
    });

    root.getChildren().addAll(
        title,
        nameLabel,
        phoneLabel,

        nicknameLabel,
        nicknameField,

        alternateLabel,
        alternateField,

        emailLabel,
        emailField,

        favoriteLabel,
        emergencyLabel,

        saveBtn
);

    Scene scene =
            new Scene(root, 400, 450);

    popup.setTitle(
            contact.getName()
    );

    popup.setScene(scene);

    popup.show();
}
}