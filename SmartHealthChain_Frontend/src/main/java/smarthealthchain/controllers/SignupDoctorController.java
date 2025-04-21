package medalert.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import medalert.utils.SceneNavigator;


import java.io.File;
import java.io.IOException;

public class SignupDoctorController {

    @FXML private TextField fullNameField;
    @FXML private TextField ageField;
    @FXML private TextField genderField;
    @FXML private TextField emailField;
    @FXML private TextField specializationField;
    @FXML private TextField experienceField;

    private File uploadedLicense;

    @FXML
    private void onSignupClicked(ActionEvent event) {
        String fullName = fullNameField.getText();
        String age = ageField.getText();
        String gender = genderField.getText();
        String email = emailField.getText();
        String specialization = specializationField.getText();
        String experience = experienceField.getText();

        if (fullName.isEmpty() || age.isEmpty() || gender.isEmpty() || email.isEmpty() || specialization.isEmpty() || experience.isEmpty()) {
            showAlert("Error", "All fields must be filled in.");
        } else {
            String message = "Doctor account created successfully.";
            if (uploadedLicense != null) {
                message += "\nLicense uploaded: " + uploadedLicense.getName();
            }
            showAlert("Success", message);
        }
    }

    @FXML
    private void onUploadClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload License");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        Window stage = fullNameField.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null && file.length() <= 1_000_000) {
            uploadedLicense = file;
            showAlert("File Uploaded", "Successfully uploaded: " + file.getName());
        } else if (file != null) {
            showAlert("File Too Large", "Please upload a PDF smaller than 1MB.");
        }
    }

    @FXML
    private void onLoginClicked() {
        System.out.println("Navigate to Login screen");
        SceneNavigator.switchScene("views/login.fxml");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
