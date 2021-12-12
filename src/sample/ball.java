package sample;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Ball Object Class
 */
public class ball {

    /**
     * Refers to Circle node
     */
    private final Circle ball;
    /**
     * Speed in X direction
     */
    protected int deltaX;
    /**
     * Speed in Y direction
     */
    protected int deltaY;
    /**
     * Refers to AnchorPane node
     */
    private AnchorPane scene;

    /**
     * Sets Speed x to -1
     * Sets Speed y to -3
     * @param ball The Circle refers to the ball
     */
    public ball(Circle ball){
        this.ball = ball;
        deltaX = -1;
        deltaY = -3;
    }

    /**
     * Checks if ball collides with the game scene
     * @param node
     */
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

    /**
     * Checks if ball collidesm with brick
     * @param brick brick object
     * @return true if collide, false if did not collide
     */
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

    /**
     * Checks if ball collides with paddle
     * @param paddle paddle object
     */
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
