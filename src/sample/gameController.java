package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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


    private ArrayList<Rectangle> bricks = new ArrayList<>();

    //1 Frame evey 10 millis, which means 100 FPS
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {

            checkCollisionPaddle(paddle);
            ball.setLayoutX(ball.getLayoutX() + deltaX);
            ball.setLayoutY(ball.getLayoutY() + deltaY);

            if(!bricks.isEmpty()){
                bricks.removeIf(brick -> checkCollisionBrick(brick));
            } else {
                timeline.stop();
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
    public void startKey(KeyEvent event){
        if (event.getCode() == KeyCode.SPACE){
            startGame();
        }
    }

    private void startGame(){
        createBricks();
        timeline.play();
    }

    @FXML
    public void paddleMovement(KeyEvent event) {
//      paddle move left
        if (event.getCode() == KeyCode.A) {
            if (paddle.getLayoutX() > 0) {
                paddle.setLayoutX(paddle.getLayoutX() - 5);
            }
        }
//      paddle move right
        if (event.getCode() == KeyCode.D) {
            if (paddle.getLayoutX() < 450) {
                paddle.setLayoutX(paddle.getLayoutX() + 5);
            }
        }
    }

    private void checkCollisionScene(Node node) {
        Bounds bounds = node.getBoundsInLocal();
        boolean rightBorder = ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius());
        boolean leftBorder = ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius());
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

    public void checkCollisionBottomZone(){
        if(ball.getBoundsInParent().intersects(bottom.getBoundsInParent())){
            timeline.stop();
            bricks.forEach(brick -> scene.getChildren().remove(brick));
            bricks.clear();

            deltaX = -1;
            deltaY = -3;

            ball.setLayoutX(300);
            ball.setLayoutY(300);

        }
    }
    public void createBricks(){
        double width = 560;
        double height = 200;

        int spaceCheck = 1;

        for (double i = height; i > 0 ; i = i - 50) {
            for (double j = width; j > 0 ; j = j - 25) {
                if(spaceCheck % 2 == 0){
                    Rectangle rectangle = new Rectangle(j,i,30,30);
                    rectangle.setFill(Color.FIREBRICK);
                    scene.getChildren().add(rectangle);
                    bricks.add(rectangle);
                }
                spaceCheck++;
            }
        }
    }
}
