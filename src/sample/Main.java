package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class used to run application
 */
public class Main extends Application {
    /**
     * Starting point for application.
     * @param primaryStage Frame
     * @throws IOException Error on loading the .fxml file.
     */
    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("homepage.fxml"));
        Parent pane = fxmlLoader.load();
        Scene root = new Scene(pane, 450, 300);
        primaryStage.setTitle("Brick Destroy");
        primaryStage.setScene(root);
        primaryStage.show();
    }

    /**
     * Main Entry Point for an executable program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
