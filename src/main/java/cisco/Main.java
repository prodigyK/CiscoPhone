package cisco;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Cisco Update Config (Version 2.2)");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMinWidth(670);
        primaryStage.setMinHeight(570);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
