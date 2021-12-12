package sample;

import com.sun.javafx.font.directwrite.RECT;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

/**
 * This class controls the paddle movements and position.
 */
public class player {
    /**
     * set move amount
     */
    private static final int DEF_MOVE_AMOUNT = 5;
    /**
     * Reference to Rectangle that represents paddle
     */
    private final Rectangle paddle;
    /**
     * Permission to move left
     */
    private boolean moveLeft = false;
    /**
     * Permission to move right
     */
    private boolean moveRight = false;

    /**
     *
     * @param paddle The Rectangle refering to paddle
     */
    public player(Rectangle paddle) {
        this.paddle = paddle;
    }

    /**
     * Set the moveLeft state
     * @param moveLeft Permission to move left
     */
    public void setMoveLeft(boolean moveLeft){
        this.moveLeft=moveLeft;
    }

    /**
     * Set the moveRight state
     * @param moveRight Permission to move right
     */
    public void setMoveRight(boolean moveRight){
        this.moveRight=moveRight;
    }

    /**
     * Movement of paddle
     */
    public void move() {
        if(moveLeft) {
            if (paddle.getLayoutX() > 0) {
                paddle.setLayoutX(paddle.getLayoutX() - DEF_MOVE_AMOUNT);
            }
        }
        else if(moveRight) {
            if (paddle.getLayoutX() < 450) {
                paddle.setLayoutX(paddle.getLayoutX() + DEF_MOVE_AMOUNT);
            }
        }
    }

}
