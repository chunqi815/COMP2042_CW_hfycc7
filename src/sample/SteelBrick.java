package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class SteelBrick extends brick {

    public SteelBrick(AnchorPane scene,Rectangle rectangle) {
        super(scene);
        super.brickStrength=2;
        super.probability = 0.4;
        super.brick = rectangle;
        super.brick.setFill(Color.rgb(203,203, 201));
        super.brick.setStroke(Color.GRAY);
        System.out.format("%d",brickStrength);
    }

}
