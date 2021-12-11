package sample;

import com.sun.javafx.font.directwrite.RECT;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class player {

    private static final int DEF_MOVE_AMOUNT = 5;
    private final Rectangle paddle;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    public player(Rectangle paddle) {
        this.paddle = paddle;
    }

    public void setMoveLeft(boolean moveLeft){
        this.moveLeft=moveLeft;
    }

    public void setMoveRight(boolean moveRight){
        this.moveRight=moveRight;
    }

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
