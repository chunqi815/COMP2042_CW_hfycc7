package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homepage.fxml"));
        Parent pane = fxmlLoader.load();
        Scene root = new Scene(pane, 450, 300);
        primaryStage.setTitle("Brick Destroy");
        primaryStage.setScene(root);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
