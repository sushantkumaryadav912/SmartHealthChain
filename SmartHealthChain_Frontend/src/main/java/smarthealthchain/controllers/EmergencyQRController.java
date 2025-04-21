package medalert.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import medalert.utils.SceneNavigator;

public class EmergencyQRController {

    @FXML
    private void onProfileClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/ProfilePatient.fxml");
    }

    @FXML
    private void onLoginClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/login.fxml");
    }

    @FXML
    private void onDashboardClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/dashboard.fxml");
    }

    @FXML
    private void onEmergencyClicked(ActionEvent event) {
        // No action needed - already on emergency screen
    }

    @FXML
    private void onReportClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/report-analysis.fxml");
    }
}