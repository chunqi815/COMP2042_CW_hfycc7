package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class gameController implements Initializable {

    @FXML
    private Circle circle;
    @FXML
    private Rectangle bottom;
    @FXML
    private AnchorPane scene;
    @FXML
    private Rectangle paddle;

    private player player;
    private ball ball;
    private brick brick;

    public int gameStrike = 0;
    public int gameLevel = 2;
    protected final int brickCnt = 10;
    protected final int lineCnt = 3;
    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;



    private ArrayList<brick> bricks = new ArrayList<>();

    //1 Frame every 10 millis, which means 100 FPS
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), new EventHandler<>() {

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

                        if(brick.getStrength()==0) {
                            scene.getChildren().remove(brick.brick);
                            System.out.println("test");
                            return true;
                        }

                    }
                    return false;
                });
            }
            else {
                nextLevel();
            }

            ball.checkCollisionScene(scene);
            checkCollisionBottomZone();
        }
    }));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);
        ball = new ball(circle);
        player = new player(paddle);
    }

    private void startGame(){
        timeline.play();
        makeLevels(gameLevel);
    }

    private void nextLevel(){
        timeline.pause();
        bricks.clear();
        makeLevels(gameLevel++);
    }


    public void makeLevels(int gameLevel){
        switch(gameLevel){
            case 1 -> bricks = new brick(scene).makeSingleTypeLevel(CLAY,lineCnt,brickCnt);
            case 2 -> bricks = new brick(scene).makeChessboardLevel(CLAY, CEMENT,lineCnt,brickCnt);
            case 3-> bricks = new brick(scene).makeChessboardLevel(CLAY, STEEL,lineCnt,brickCnt);
            case 4-> bricks = new brick(scene).makeChessboardLevel(STEEL, CEMENT,lineCnt,brickCnt);
        }

    }

    @FXML
    public void keyPressed(KeyEvent event) {
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
    }

    public void paddleKeyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.A) {
            player.setMoveLeft(false);
        }
        if (e.getCode() == KeyCode.D) {
            player.setMoveRight(false);
        }
    }

    private void checkCollisionBottomZone(){

        if(circle.getBoundsInParent().intersects(bottom.getBoundsInParent())){
            timeline.pause();

            circle.setLayoutX(300);
            circle.setLayoutY(386);
            paddle.setLayoutX(225);
            paddle.setLayoutY(403);
            gameStrike +=1;
        }
        if(gameStrike ==3){
            gameOver();
        }
    }

    public void gameOver() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("gameEndPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) this.scene.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

