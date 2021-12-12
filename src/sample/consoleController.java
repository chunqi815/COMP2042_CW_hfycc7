package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
/**
 * This controller will be used to control consoleView.fxml.
 */
public class consoleController {
    /**
     * Reference to JavaFX slider node representing level
     */
    @FXML
    private Slider levelSlider;
    /**
     * Reference to JavaFX Slider node representing ball count.
     */
    @FXML
    private Slider ballCntSlider;
    /**
     * Game Level
     */
    public int gameLevel=0;
    /**
     * Ball Count
     */
    public int ballCnt=0;

    /**
     * Set the slider to current level
     * @param level current game level
     */
    public void setLevel(int level) {
        this.gameLevel = level;
        levelSlider.setValue(level);
    }

    /**
     * Set slider to current ball count
     * @param ballCnt current ball count
     */
    public void setBallCount(int ballCnt) {
        this.ballCnt = ballCnt;
        ballCntSlider.setValue(ballCnt);
    }

    /**
     * Skips current level on clicked
     */
    @FXML
    private void onSkipLevelButtonClick() {
        gameLevel+=1;
    }

    /**
     * Reset ball count once clicked
     */
    @FXML
    private void onResetBallButtonClick() {
        setBallCount(3);
    }

    /**
     * Set current level to slider value
     */
    @FXML
    private void onSkipSlider() {
        setLevel((int)levelSlider.getValue());
    }

    /**
     * Set ball count to slider value
     */
    @FXML
    private void onBallSlider() {
        setBallCount((int)ballCntSlider.getValue());
    }
}
