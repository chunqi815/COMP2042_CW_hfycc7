package sample;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

public class gameController {

    @FXML
    private Rectangle paddle;

    @FXML
    private void move (KeyEvent event){
        if(event.getCode()==KeyCode.A){
            if(paddle.getLayoutX()>0){
                paddle.setLayoutX(paddle.getLayoutX()-5);
            }
        }
        if(event.getCode()==KeyCode.D){
            if(paddle.getLayoutX()<540){
                paddle.setLayoutX(paddle.getLayoutX() + 5);
            }
        }
    }
        }
