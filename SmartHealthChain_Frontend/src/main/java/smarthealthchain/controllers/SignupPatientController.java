package medalert.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import medalert.utils.SceneNavigator;
import java.io.File;

public class SignupPatientController {

    @FXML private TextField fullNameField;
    @FXML private TextField ageField;
    @FXML private TextField genderField;
    @FXML private TextField emailField;
    @FXML private TextField medicalHistoryField;

    private File uploadedPDF;

    @FXML
    private void onSignupClicked(ActionEvent event) {
        String fullName = fullNameField.getText();
        String age = ageField.getText();
        String gender = genderField.getText();
        String email = emailField.getText();
        String medicalHistory = medicalHistoryField.getText();

        if (fullName.isEmpty() || age.isEmpty() || gender.isEmpty() || email.isEmpty()) {
            showAlert("Error", "All fields must be filled in.");
        } else {
            String message = "Account created successfully.";
            if (uploadedPDF != null) {
                message += "\nPDF uploaded: " + uploadedPDF.getName();
            }
            showAlert("Success", message);
        }
    }

    @FXML
    private void onUploadClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF Report");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        Window stage = fullNameField.getScene().getWindow(); // Any node reference will work
        File file = fileChooser.showOpenDialog(stage);
        if (file != null && file.length() <= 1_000_000) {
            uploadedPDF = file;
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
