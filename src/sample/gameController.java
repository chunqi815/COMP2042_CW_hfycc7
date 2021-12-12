package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This controller controls gamepage.fxml view
 */
public class gameController implements Initializable {
    /**
     * Ball Object
     */
    @FXML
    private Circle circle;
    /**
     * Reference to Rectangle representing the bottom zone that detects when ball loses
     */
    @FXML
    private Rectangle bottom;
    /**
     * Referencing anchorpane representing gamepage scene/window of game.
     */
    @FXML
    private AnchorPane scene;
    /**
     * Referencing Rectangle that controls the paddle
     */
    @FXML
    private Rectangle paddle;
    /**
     * Referencing Label that displays the current score
     */
    @FXML
    private Label scoreLabel;

    /**
     * Stage Object
     */
    private Stage stage;
    /**
     *Player Object
     */
    private player player;
    /**
     * Ball Object
     */
    private ball ball;

    private brick brick;

    /**
     * Game Level
     */
    public int gameLevel = 1;
    /**
     * Balls Left
     */
    public int ballCnt = 3;
    /**
     * Current Score
     */
    public int score = 0;
    /**
     * How many milliseconds per frame
     */
    public int FPS = 10;
    /**
     * Bricks per lineCount
     */
    protected final int brickCnt = 10;
    /**
     * How many lines of bricks in game
     */
    protected final int lineCnt = 3;
    /**
     * Clay Brick Type
     */
    private static final int CLAY = 1;
    /**
     * Cement Brick Type
     */
    private static final int CEMENT = 2;
    /**
     * Steel Brick Type
     */
    private static final int STEEL = 3;

    /**
     * Setting Stage to this stage
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
/**
 * Array of brick object
 */
    private ArrayList<brick> bricks = new ArrayList<>();

    /**
     * Adds animation timer to the handle function
     */
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(FPS), new EventHandler<>() {
        /**
         *Moves the ball and player, check for all collisions and sets the score for every frame.
         * @param actionEvent
         */
        @Override
        public void handle(ActionEvent actionEvent) {
            player.move();
            ball.checkCollisionPaddle(paddle);
            circle.setLayoutX(circle.getLayoutX() + ball.deltaX);
            circle.setLayoutY(circle.getLayoutY() + ball.deltaY);

            if (!bricks.isEmpty()) {
                bricks.removeIf(brick -> {
                    boolean collided = ball.checkCollisionBrick(brick.brick);

                    if (collided) {
                        brick.setStrength();
                        setScore(brick);

                        if(brick.getStrength()==0) {
                            scene.getChildren().remove(brick.brick);
                            return true;
                        }

                    }
                    return false;
                });
            }
            else{
                if (gameLevel==6){
                    makeLevels(6);
                    FPS-=10;
                }
                else {
                    setReward();
                    nextLevel();
                }
            }

            ball.checkCollisionScene(scene);
            checkCollisionBottomZone();
        }
    }));

    /**
     * Sets the timeline to set cycles to indefinite and create an instance of ball and paddle.
     * @param url Location used to resolve relative paths for root objects
     * @param resourceBundle resouces used to localise the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);
        ball = new ball(circle);
        player = new player(paddle);
    }

    /**
     * Add bricks to the gameframe, sets the milisecond per frame and set the animation timeline to start.
     */
    private void startGame(){
        timeline.play();
        makeLevels(gameLevel=1);
        FPS=10;
    }

    /**
     * Paused animation timeline, clear bricks then sets the next level bricks.
     */
    private void nextLevel(){
        timeline.pause();
        bricks.clear();
        makeLevels(gameLevel+=1);
    }

    /**
     * Sets the bricks based on which level it is
     * @param gameLevel game level
     */
    public void makeLevels(int gameLevel){
        switch(gameLevel){
            case 1 -> bricks = new brick(scene).makeSingleTypeLevel(CLAY,lineCnt,brickCnt);
            case 2 -> bricks = new brick(scene).makeChessboardLevel(CLAY, CEMENT,lineCnt,brickCnt);
            case 3-> bricks = new brick(scene).makeChessboardLevel(CLAY, STEEL,lineCnt,brickCnt);
            case 4-> bricks = new brick(scene).makeChessboardLevel(STEEL, CEMENT,lineCnt,brickCnt);
            case 5-> bricks = new brick(scene).makeChessboardLevel(CEMENT, CEMENT,lineCnt,brickCnt);
            case 6-> bricks = new brick(scene).makeChessboardLevel(STEEL, STEEL,lineCnt,brickCnt);
            case 7-> gameOver();
        }
        timeline.play();
    }

    /**
     * Pauses,plays and starts game when space bar pressed. Moves Left when A is pressed. Moves Right when D is pressed. Opens console when SHIFT+ALT+F1 is pressed.
     * @param event receives the keyCode
     * @throws IOException Error on loading the .fxml file.
     */
    @FXML
    public void keyPressed(KeyEvent event) throws IOException {
//        play/pause button
        if(event.getCode() == KeyCode.SPACE) {
            if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.pause();
            } else if (timeline != null && timeline.getStatus() == Animation.Status.PAUSED) {
                timeline.play();
            } else {
                startGame();
            }
        }
//      paddle move left
        if (event.getCode() == KeyCode.A) {
            player.setMoveLeft(true);
            player.setMoveRight(false);
        }
//      paddle move right
        if (event.getCode() == KeyCode.D) {
            player.setMoveRight(true);
            player.setMoveLeft(false);
        }

        if (event.isAltDown() && event.isShiftDown() && event.getCode() == KeyCode.F1){
            if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING){
                timeline.pause();
            }
            openConsole();
        }
        setScoreLabel();
    }

    /**
     * Stops paddle from continuing moving left or right after key is released.
     * @param e receives keyReleased.
     */
    public void paddleKeyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.A) {
            player.setMoveLeft(false);
        }
        if (e.getCode() == KeyCode.D) {
            player.setMoveRight(false);
        }
    }

    /**
     * Sets the score label to display the current score
     */
    @FXML
    private void setScoreLabel(){
        if(timeline.getStatus() == Animation.Status.RUNNING){
            scoreLabel.setText(String.format("Current Score: %d || Balls: %d",getScore(),(ballCnt)));
        }
        else if (timeline.getStatus() == Animation.Status.PAUSED){
            scoreLabel.setText("Press Spacebar to Play/Pause");
        }
    }

    /**
     * Checks if ball collides with the bottom of the game window.
     */
    private void checkCollisionBottomZone(){

        if(circle.getBoundsInParent().intersects(bottom.getBoundsInParent())){
            timeline.pause();
            setPenalty();
            circle.setLayoutX(300);
            circle.setLayoutY(386);
            paddle.setLayoutX(225);
            paddle.setLayoutY(403);
            ballCnt -=1;
        }
        if(ballCnt==0){
            gameOver();
        }
    }

    /**
     * Adds the score to total current score if brick breaks.
     * @param brick brick object
     */
    private void setScore(brick brick){
        if (brick instanceof ClayBrick){
            score+= 1;
        }
        else if (brick instanceof CementBrick){
            score+= 2;
        }
        else if (brick instanceof SteelBrick){
            score+= 3;
        }
    }

    /**
     * Getter to return score
     * @return
     */
    public int getScore(){
        return score;
    }

    /**
     * Minus the current score as a penalty
     */
    public void setPenalty(){
        score-=10;
    }

    /**
     * Adds to the current score as a reward.
     */
    public void setReward(){
        score+=10;
    }

    /**
     * Opens up game over page once player loses
     */
    public void gameOver() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameEndPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) this.scene.getScene().getWindow();
            gameEndController gameEndController = fxmlLoader.getController();
            gameEndController.setCurrentScoreLabel(score);
            gameEndController.setHighScore(score);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens up console once player keys SHIFT+ALT+F1
     * @throws IOException Error on loading the .fxml file.
     */
    private void openConsole() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource( "consoleView.fxml"));
        Parent pane = fxmlLoader.load();
        Scene consoleScene = new Scene(pane, 350, 80);

        consoleController consoleController = fxmlLoader.getController();
        consoleController.setLevel(gameLevel);
        consoleController.setBallCount(ballCnt);
        Stage console = new Stage();

        console.initModality(Modality.APPLICATION_MODAL);
        console.initOwner(stage);
        console.setScene(consoleScene);
        console.centerOnScreen();
        console.show();
        console.setOnCloseRequest(event -> {
            ballCnt = consoleController.ballCnt;
            if (gameLevel != consoleController.gameLevel) {
                gameLevel = consoleController.gameLevel - 1;
                nextLevel();
            }
        });
    }
    }

