package medalert;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import medalert.utils.SceneNavigator;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneNavigator.setStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/landing.fxml"));
        primaryStage.setTitle("MedAlert");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
