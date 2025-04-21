package medalert.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneNavigator {
    private static Stage stage;

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void switchScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getClassLoader().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Apply dashboard.css only if we're switching to dashboard.fxml
            if (fxmlPath.contains("dashboard")) {
                scene.getStylesheets().add(SceneNavigator.class.getClassLoader()
                        .getResource("styles/dashboard.css").toExternalForm());
            }
            // Apply signup.css if we're switching to signup.fxml
            else if (fxmlPath.contains("signup")) {
                scene.getStylesheets().add(SceneNavigator.class.getClassLoader()
                        .getResource("styles/signup.css").toExternalForm());
            }

            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
