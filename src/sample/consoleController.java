package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class consoleController {

    @FXML
    private Slider levelSlider;

    @FXML
    private Slider ballCntSlider;

    public int gameLevel=0;
    public int ballCnt=0;

    public void setLevel(int level) {
        this.gameLevel = level;
        levelSlider.setValue(level);
    }

    public void setBallCount(int ballCnt) {
        this.ballCnt = ballCnt;
        ballCntSlider.setValue(ballCnt);
    }

    @FXML
    private void onSkipLevelButtonClick() {
        gameLevel+=1;
    }

    @FXML
    private void onResetBallButtonClick() {
        setBallCount(3);
    }

    @FXML
    private void onSkipSlider() {
        setLevel((int)levelSlider.getValue());
    }

    @FXML
    private void onBallSlider() {
        setBallCount((int)ballCntSlider.getValue());
    }
}
