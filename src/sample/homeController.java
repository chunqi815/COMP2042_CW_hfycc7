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
        Parent root = FXMLLoader.load(getClass().getResource("gamepage.fxml"));
        Scene scene = new Scene(root,600,450);
        Stage stage = (Stage)startButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onExitButtonClick(){
        Platform.exit();
        System.exit(0);
    }
}
