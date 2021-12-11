package sample;

import javafx.animation.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class gameEndController implements Initializable {


    @FXML
    private Label currentScoreLabel;

    @FXML
    private Label highScoreLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            highScoreLabel.setText(readFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onRestartButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gamepage.fxml"));
        Scene scene = new Scene(root,600,450);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void onMenuButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene scene = new Scene(root,450,300);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void setCurrentScoreLabel(int score){
        currentScoreLabel.setText(String.format("Current Score: %d",score));
    }



    private String readFile() throws IOException {
        File file = new File("src/sample/highscore.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();

        bufferedReader.close();
        fileReader.close();
        return line;
    }

    private void writeFile(String score) throws IOException {
        File file = new File("src/sample/highscore.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(score);
        fileWriter.close();
    }

    public void setHighScore(int score) throws IOException {
        if (score > Integer.parseInt(highScoreLabel.getText())){
            highScoreLabel.setText(Integer.toString(score));
            writeFile(Integer.toString(score));
        }
    }
}
