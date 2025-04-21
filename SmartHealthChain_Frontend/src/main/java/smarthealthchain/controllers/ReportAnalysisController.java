package medalert.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import medalert.utils.SceneNavigator;

public class ReportAnalysisController {

    @FXML
    public void initialize() {
        System.out.println("Report Analysis screen loaded");
    }

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
        SceneNavigator.switchScene("views/emergency-qr.fxml");
    }

    @FXML
    private void onReportClicked(ActionEvent event) {
        // Already on report screen
    }

    @FXML
    private void onDownloadClicked(ActionEvent event) {
        System.out.println("Download button clicked");
        // Add your report download logic here
        // Example: generateAndDownloadReport();
    }

    // Add this method for actual report generation/download
    private void generateAndDownloadReport() {
        // Implement your AI report generation and download logic
        // This would connect to your backend service
    }
}