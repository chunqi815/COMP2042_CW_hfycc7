package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class gameController implements Initializable {

    @FXML
    private Rectangle paddle;

    @FXML
    private Circle ball;

    @FXML
    private AnchorPane scene;

    @FXML
    private Rectangle bottom;

    double deltaX = -1;
    double deltaY = -3;

    private static final int DEF_MOVE_AMOUNT = 5;

    public int gameStrike = 0;
    public int gameLevel = 1;

    private ArrayList<Rectangle> bricks = new ArrayList<>();

    //1 Frame every 10 millis, which means 100 FPS
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent actionEvent) {
            checkCollisionPaddle(paddle);
            ball.setLayoutX(ball.getLayoutX() + deltaX);
            ball.setLayoutY(ball.getLayoutY() + deltaY);

            if(!bricks.isEmpty()){
                bricks.removeIf(brick -> checkCollisionBrick(brick));
            } else {
                gameLevel++;
                timeline.stop();
                nextLevel();
            }

            checkCollisionScene(scene);
            checkCollisionBottomZone();
        }
    }));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    @FXML
    public void playPauseKey(KeyEvent event){

        if(event.getCode() == KeyCode.SPACE) {
            if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.pause();
            } else if (timeline != null && timeline.getStatus() == Animation.Status.PAUSED) {
                timeline.play();
            } else {
                startGame();
            }
        }
    }

    private void startGame(){
        timeline.play();
        if(gameStrike == 0){
        createBricks();}
    }

    private void nextLevel(){
        createBricks();
        timeline.play();
    }

    public void createBricks() {
        if(gameLevel==1) {
            makeSingleTypeLevel();
        }
        else{
            makeChessboardLevel();
        }
    }

    private void makeSingleTypeLevel() {
        int k = 0;
        int i,j;
        for ( i = 0; i < 3; i++) {
            for (j = 0; j < 10; j++) {
                if (i % 2 != 0) {
                    Rectangle rectangle = new Rectangle((j * 60), k, 59, 30);
                    clayBrick(rectangle);
                    rectangle.setStroke(Color.GRAY);
                    scene.getChildren().add(rectangle);
                    bricks.add(rectangle);
                    for (j = 0; j < 10; j++) {
                        Rectangle rectangle1 = new Rectangle((32+(j * 60)), k, 59, 30);
                        clayBrick1(rectangle1);
                        rectangle1.setStroke(Color.GRAY);
                        scene.getChildren().add(rectangle1);
                        bricks.add(rectangle1);
                    }
                }
                else{
                    for (j = 0; j < 10; j++) {
                        Rectangle rectangle = new Rectangle((j * 60), k, 59, 30);
                        clayBrick(rectangle);
                        rectangle.setStroke(Color.GRAY);
                        scene.getChildren().add(rectangle);
                        bricks.add(rectangle);
                    }
                }
            }
            k += 30;
        }
    }

    private void makeChessboardLevel(){
        int i, j;
        int k = 0;
        int size = 3;

        for (i = 0; i < size; i++) {
            for (j = 0; j < 10; j++) {
                if (i % 2 != 0) {
                    Rectangle rectangle = new Rectangle((j * 60),  k, 59, 30);
                    if ((i + j) % 2 == 0){
                        checkGameLevelOdd(rectangle);
                    }
                    else {
                        checkGameLevelEven(rectangle);
                    }
                    rectangle.setStroke(Color.GRAY);
                    scene.getChildren().add(rectangle);
                    bricks.add(rectangle);

                    for (j = 0; j < 10; j++) {
                        Rectangle rectangle1 = new Rectangle((32 + (j * 60)), k, 59, 30);
                        if ((i + j) % 5 == 0) {
                            checkGameLevelOdd1(rectangle1);
                        } else {
                            checkGameLevelEven1(rectangle1);
                        }
                        rectangle1.setStroke(Color.GRAY);
                        scene.getChildren().add(rectangle1);
                        bricks.add(rectangle1);
                    }
                } else {
                    for (j = 0; j < 10; j++) {
                        Rectangle rectangle = new Rectangle((j * 60), k, 59, 30);
                        if ((i + j) % 2 == 0){
                            checkGameLevelOdd(rectangle);
                        }
                        else {
                            checkGameLevelEven(rectangle);
                        }
                        rectangle.setStroke(Color.GRAY);
                        scene.getChildren().add(rectangle);
                        bricks.add(rectangle);
                    }
                }
                k += 30;
            }
        }
    }

    private void checkGameLevelOdd(Rectangle rectangle) {
        if(gameLevel==2){clayBrick(rectangle);}
        else if(gameLevel==3){clayBrick(rectangle);}
        else if(gameLevel==4){steelBrick(rectangle);}
    }

    private void checkGameLevelOdd1(Rectangle rectangle1) {
        if(gameLevel==2){clayBrick1(rectangle1);}
        else if(gameLevel==3){clayBrick1(rectangle1);}
        else if(gameLevel==4){steelBrick1(rectangle1);}
    }

    private void checkGameLevelEven(Rectangle rectangle) {
        if(gameLevel==2){cementBrick(rectangle);}
        else if(gameLevel==3){steelBrick(rectangle);}
        else if(gameLevel==4){cementBrick(rectangle);}
    }

    private void checkGameLevelEven1(Rectangle rectangle1) {
        if(gameLevel==2){cementBrick1(rectangle1);}
        else if(gameLevel==3){steelBrick1(rectangle1);}
        else if(gameLevel==4){cementBrick1(rectangle1);}
    }

    private void clayBrick(Rectangle rectangle) {
        rectangle.setFill(Color.rgb(178, 34, 34));
    }

    private void clayBrick1(Rectangle rectangle1) {
        rectangle1.setFill(Color.rgb(178, 34, 34));
    }

    private void cementBrick(Rectangle rectangle) {
        rectangle.setFill(Color.rgb(147,147, 147));
    }

    private void cementBrick1(Rectangle rectangle1) {
        rectangle1.setFill(Color.rgb(147,147, 147));
    }

    private void steelBrick(Rectangle rectangle) {
        rectangle.setFill(Color.rgb(203,203, 201));
    }

    private void steelBrick1(Rectangle rectangle1) {
        rectangle1.setFill(Color.rgb(203,203, 201));
    }

    @FXML
    public void paddleMovement(KeyEvent event) {
//      paddle move left
        if (event.getCode() == KeyCode.A) {
            if (paddle.getLayoutX() > 0) {
                paddle.setLayoutX(paddle.getLayoutX() - DEF_MOVE_AMOUNT);
            }
        }
//      paddle move right
        if (event.getCode() == KeyCode.D) {
            if (paddle.getLayoutX() < 450) {
                paddle.setLayoutX(paddle.getLayoutX() + DEF_MOVE_AMOUNT);
            }
        }
    }

    private void checkCollisionScene(Node node) {
        Bounds bounds = node.getBoundsInLocal();
        boolean rightBorder = ball.getLayoutX() >= (600 - ball.getRadius());
        boolean leftBorder = ball.getLayoutX() <= (0 + ball.getRadius());
        boolean bottomBorder = ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius());
        boolean topBorder = ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius());

        if (rightBorder || leftBorder) {
            deltaX *= -1;
        }
        if (bottomBorder || topBorder) {
            deltaY *= -1;
        }

    }


    public boolean checkCollisionBrick(Rectangle brick) {

        if (ball.getBoundsInParent().intersects(brick.getBoundsInParent())) {
            boolean rightBorder = ball.getLayoutX() >= ((brick.getX() + brick.getWidth()) - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= (brick.getX() + ball.getRadius());
            boolean bottomBorder = ball.getLayoutY() >= ((brick.getY() + brick.getHeight()) - ball.getRadius());
            boolean topBorder = ball.getLayoutY() <= (brick.getY() + ball.getRadius());

            if (rightBorder || leftBorder) {
                deltaX *= -1;
            }
            if (bottomBorder || topBorder) {
                deltaY *= -1;
            }
            scene.getChildren().remove(brick);

            return true;
        }
        return false;
    }
    public void checkCollisionPaddle(Rectangle paddle){

        if(ball.getBoundsInParent().intersects(paddle.getBoundsInParent())){

            boolean rightBorder = ball.getLayoutX() >= ((paddle.getLayoutX() + paddle.getWidth()) - ball.getRadius());
            boolean leftBorder = ball.getLayoutX() <= (paddle.getLayoutX() + ball.getRadius());
            boolean bottomBorder = ball.getLayoutY() >= ((paddle.getLayoutY() + paddle.getHeight()) - ball.getRadius());
            boolean topBorder = ball.getLayoutY() <= (paddle.getLayoutY() + ball.getRadius());

            if (rightBorder || leftBorder) {
                deltaX *= -1;
            }
            if (bottomBorder || topBorder) {
                deltaY *= -1;
            }
        }
    }

    private void checkCollisionBottomZone(){

        if(ball.getBoundsInParent().intersects(bottom.getBoundsInParent())){
            timeline.pause();

            deltaX = -1;
            deltaY = -3;

            ball.setLayoutX(300);
            ball.setLayoutY(386);
            paddle.setLayoutX(225);
            paddle.setLayoutY(403);
            gameStrike +=1;
        }
        if(gameStrike ==3){
             gameOver();  ;
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

