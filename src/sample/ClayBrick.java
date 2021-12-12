package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Clay Brick child class of brick
 */
public class ClayBrick extends brick{
    /**
     * Sets brick strength, probability of breaking, brick color and brick border color
     * @param scene
     * @param rectangle brick object
     */
    public ClayBrick(AnchorPane scene, Rectangle rectangle) {
        super(scene);
        super.brickStrength=1;
        super.probability = 1;
        super.brick = rectangle;
        super.brick.setFill(Color.rgb(178, 34, 34));
        super.brick.setStroke(Color.GRAY);
    }
}
