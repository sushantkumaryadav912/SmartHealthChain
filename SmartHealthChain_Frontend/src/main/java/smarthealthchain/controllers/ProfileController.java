package medalert.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    public void initialize() {
        String role = LoginController.loggedInRole;

        if (role.equals("doctor")) {
            nameLabel.setText("Dr. A. Kumar");
            roleLabel.setText("Doctor");
        } else if (role.equals("patient")) {
            nameLabel.setText("Jane Smith");
            roleLabel.setText("Patient");
        } else {
            nameLabel.setText("Unknown User");
            roleLabel.setText("Unknown Role");
        }
    }
}
