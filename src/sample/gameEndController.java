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

/**
 * Controller manages gameEndPage.fxml
 */
public class gameEndController implements Initializable {

    /**
     * Refers to label that display current score
     */
    @FXML
    private Label currentScoreLabel;
    /**
     * Refers to label that display high score
     */
    @FXML
    private Label highScoreLabel;

    /**
     * Read the highScore.txt file and initialize the high score label.
     * @param url The location used to resolve relative paths for the root object
     * @param resourceBundle The resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            highScoreLabel.setText(readFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Restarts the game
     * @param actionEvent detects restart button click
     * @throws IOException Error on loading the .fxml file.
     */
    @FXML
    private void onRestartButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gamepage.fxml"));
        Scene scene = new Scene(root,600,450);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Go back to home page
     * @param actionEvent detects menu button click
     * @throws IOException Error on loading the .fxml file.
     */
    @FXML
    private void onMenuButtonClick(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        Scene scene = new Scene(root,450,300);
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Set current score label to display current score
     * @param score score
     */
    @FXML
    public void setCurrentScoreLabel(int score){
        currentScoreLabel.setText(String.format("Current Score: %d",score));
    }


    /**
     * Read highscore.txt and get the text
     * @return high score
     * @throws IOException Error on loading the .fxml file.
     */
    private String readFile() throws IOException {
        File file = new File("src/sample/highscore.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();

        bufferedReader.close();
        fileReader.close();
        return line;
    }

    /**
     * Rewrites file with new high score
     * @param score score
     * @throws IOException Error on loading the .fxml file.
     */
    private void writeFile(String score) throws IOException {
        File file = new File("src/sample/highscore.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(score);
        fileWriter.close();
    }

    /**
     * Rewrites the highscore.txt to new score if current score is higher than high score
     * @param score
     * @throws IOException
     */
    public void setHighScore(int score) throws IOException {
        if (score > Integer.parseInt(highScoreLabel.getText())){
            highScoreLabel.setText(Integer.toString(score));
            writeFile(Integer.toString(score));
        }
    }
}
