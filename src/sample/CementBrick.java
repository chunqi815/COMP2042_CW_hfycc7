package sample;

import java.awt.*;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class CementBrick extends brick {

    public CementBrick(AnchorPane scene, Rectangle rectangle) {
        super(scene);
        super.brick = rectangle;
        super.brickStrength = 2;
        super.probability = 1;
        super.brick.setFill(Color.rgb(147,147, 147));
        super.brick.setStroke(Color.GRAY);

    }
}
