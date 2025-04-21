package medalert.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import medalert.utils.SceneNavigator;

public class LandingPageController {

    @FXML
    void onLoginClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/login.fxml");
    }

    @FXML
    void onDashboardClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/dashboard.fxml");
    }

    @FXML
    void onEmergencyClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/emergency-qr.fxml");
    }

    @FXML
    void onReportClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/report-analysis.fxml");
    }

    @FXML
    void onProfileClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/profile.fxml"); // Placeholder, you can implement profile later
    }
}
