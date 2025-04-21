package medalert.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import medalert.utils.SceneNavigator;

public class SignupController {

    @FXML
    void onDoctorClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/signup-doctor.fxml");
    }

    @FXML
    void onPatientClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/signup-patient.fxml");
    }

    @FXML
    void onBackToLoginClicked(ActionEvent event) {
        SceneNavigator.switchScene("views/login.fxml");
    }
}
