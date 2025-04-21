package medalert.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import medalert.utils.SceneNavigator;

public class LoginController {

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    public static String loggedInRole = ""; // "doctor" or "patient"

    @FXML
    void onCreateAccountClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/signup.fxml");
    }

    @FXML
    void onLoginButtonClicked(ActionEvent event) {
        String userId = userIdField.getText().toLowerCase();
        String password = passwordField.getText();

        if (userId.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter both User ID and Password.");
            return;
        }
        //dummy login

        if (userId.equals("doctor") && password.equals("1234")) {
            loggedInRole = "doctor";
            SceneNavigator.switchScene("views/dashboard.fxml");
        } else if (userId.equals("patient") && password.equals("1234")) {
            loggedInRole = "patient";
            SceneNavigator.switchScene("views/dashboard.fxml");
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials. Try again.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Shared Sidebar Navigation
    @FXML void onProfileClicked(ActionEvent event) {
        if (loggedInRole.equals("doctor")) {
            SceneNavigator.switchScene("views/ProfileDoctor.fxml");
        } else if (loggedInRole.equals("patient")) {
            SceneNavigator.switchScene("views/ProfilePatient.fxml");
        } else {
            showAlert(Alert.AlertType.WARNING, "Access Denied", "Please login to access your profile.");
        }
    }

    @FXML void onLoginClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/login.fxml");
    }

    @FXML void onDashboardClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/dashboard.fxml");
    }

    @FXML void onEmergencyClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/emergency.fxml");
    }

    @FXML void onReportClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/report.fxml");
    }
}
