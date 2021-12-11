package sample;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;


public class ball {


    private final Circle ball;

    protected int deltaX;
    protected int deltaY;

    private AnchorPane scene;
    private brick brickClass = new brick(scene);

    public ball(Circle ball){
        this.ball = ball;
        deltaX = -1;
        deltaY = -3;
    }

    public void checkCollisionScene(Node node) {
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


}
