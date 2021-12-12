package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Brick Class
 */
public class brick {
    /**
     * Assign each brick type to a value
     */
    private static final int CLAY = 1;
    private static final int CEMENT = 2;
    private static final int STEEL = 3;
    /**
     * Probability of bricks breaking
     */
    public double probability;
    /**
     * Strength of bricks
     */
    public int brickStrength;
    /**
     * Reference to AnchorPane represent game scene
     */
    public AnchorPane scene ;
    /**
     * Reference to Rectangle representing brick
     */
    public Rectangle brick;

    /**
     *
     * @param scene The circle referring to ball
     */
    public brick(AnchorPane scene){
        this.scene = scene;
    }

    /**
     * Creates an array of bricks and returns it
     * @param type brick type
     * @param lineCnt array line count
     * @param brickCnt Number of bricks per line
     * @return array
     */
    public ArrayList<brick> makeSingleTypeLevel(int type, int lineCnt, int brickCnt) {
        ArrayList<brick> bricks = new ArrayList<>();
        int k = 0;
        int i,j;
        for ( i = 0; i < lineCnt; i++) {
            for (j = 0; j < brickCnt; j++) {
                if (i % 2 != 0) {
                    Rectangle rectangle = new Rectangle((j * 60), k, 60, 20);
                    brick temp = makeBrick(type, rectangle);
                    scene.getChildren().add(rectangle);
                    bricks.add(temp);
                    for (j = 0; j < brickCnt; j++) {
                        Rectangle rectangle1 = new Rectangle((32+(j * 60)), k, 60, 20);
                        temp = makeBrick(type, rectangle1);
                        scene.getChildren().add(rectangle1);
                        bricks.add(temp);
                    }
                }
                else{
                    for (j = 0; j < brickCnt; j++) {
                        Rectangle rectangle = new Rectangle((j * 60), k, 60, 20);
                        brick temp = makeBrick(type,rectangle);
                        scene.getChildren().add(rectangle);
                        bricks.add(temp);
                    }
                }
            }
            k += 20;
        }
        return bricks;
    }

    /**
     * Creates an array of two types of bricks and returns it
     * @param typeA first type of brick
     * @param typeB second type of brick
     * @param lineCnt array line count
     * @param brickCnt Number of bricks per line
     * @return array
     */
    public ArrayList<brick> makeChessboardLevel(int typeA, int typeB, int lineCnt, int brickCnt){
        ArrayList<brick> bricks = new ArrayList<>();
        int i, j;
        int k = 0;


        for (i = 0; i < lineCnt; i++) {
            for (j = 0; j < brickCnt; j++) {
                if (i % 2 != 0) {
                    Rectangle rectangle = new Rectangle((j * 60),  k, 60, 20);
                    sample.brick temp;
                    if ((i + j) % 2 == 0){
                        temp = makeBrick(typeA, rectangle);
                    }
                    else {
                        temp = makeBrick(typeB, rectangle);
                    }
                    scene.getChildren().add(rectangle);
                    bricks.add(temp);

                    for (j = 0; j < brickCnt; j++) {
                        Rectangle rectangle1 = new Rectangle((32 + (j * 60)), k, 60, 20);
                        if ((i + j) % 5 == 0) {
                            temp = makeBrick(typeA,rectangle1);
                        } else {
                            temp = makeBrick(typeB,rectangle1);
                        }
                        scene.getChildren().add(rectangle1);
                        bricks.add(temp);
                    }
                } else {
                    for (j = 0; j < brickCnt; j++) {
                        Rectangle rectangle = new Rectangle((j * 60), k, 60, 20);
                        sample.brick temp;
                        if ((i + j) % 2 == 0){
                            temp = makeBrick(typeA, rectangle);
                        }
                        else {
                            temp = makeBrick(typeB, rectangle);
                        }
                        scene.getChildren().add(rectangle);
                        bricks.add(temp);
                    }
                }
                k += 20;
            }
        }
        return bricks;
    }

    /**
     * Sets the brick according to the brick style
     * @param type brick type
     * @param rectangle brick
     * @return brick style
     */
    public brick makeBrick(int type, Rectangle rectangle){
        brick newBrick;
        switch(type){
            case CLAY->{
                newBrick = new ClayBrick(scene, rectangle);
            }
            case STEEL -> {
                newBrick = new SteelBrick(scene,rectangle);
            }
            case CEMENT ->{
                newBrick = new CementBrick(scene,rectangle);
            }
            default->throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return newBrick;
    }
    public int getStrength() {
        return brickStrength;
    }

    public void setStrength() {
        Random rnd = new Random();
        if (rnd.nextDouble() <= probability) {
            brickStrength--;
        }
    }



}
