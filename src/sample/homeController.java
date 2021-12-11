package sample;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class homeController {

    @FXML
    private Button startButton;


    @FXML
    private void onStartButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gamepage.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root,600,450);
        Stage stage = (Stage)startButton.getScene().getWindow();

        gameController gameController = fxmlLoader.getController();
        gameController.setStage(stage);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onExitButtonClick(){
        Platform.exit();
        System.exit(0);
    }
}
