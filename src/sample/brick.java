package sample;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class brick {

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    public double probability;
    public int brickStrength;

    public AnchorPane scene ;
    public Rectangle brick;

    public brick(AnchorPane scene){
        this.scene = scene;
    }

    public ArrayList<brick> makeSingleTypeLevel(int type, int lineCnt, int brickCnt) {
        ArrayList<brick> bricks = new ArrayList<>();
        int k = 0;
        int i,j;
        for ( i = 0; i < lineCnt; i++) {
            for (j = 0; j < brickCnt; j++) {
                if (i % 2 != 0) {
                    Rectangle rectangle = new Rectangle((j * 60), k, 59, 30);
                    brick temp = makeBrick(type, rectangle);
                    scene.getChildren().add(rectangle);
                    bricks.add(temp);
                    for (j = 0; j < brickCnt; j++) {
                        Rectangle rectangle1 = new Rectangle((32+(j * 60)), k, 59, 30);
                        temp = makeBrick(type, rectangle1);
                        scene.getChildren().add(rectangle1);
                        bricks.add(temp);
                    }
                }
                else{
                    for (j = 0; j < brickCnt; j++) {
                        Rectangle rectangle = new Rectangle((j * 60), k, 59, 30);
                        brick temp = makeBrick(type,rectangle);
                        scene.getChildren().add(rectangle);
                        bricks.add(temp);
                    }
                }
            }
            k += 30;
        }
        return bricks;
    }

    public ArrayList<brick> makeChessboardLevel(int typeA, int typeB, int lineCnt, int brickCnt){
        ArrayList<brick> bricks = new ArrayList<>();
        int i, j;
        int k = 0;


        for (i = 0; i < lineCnt; i++) {
            for (j = 0; j < brickCnt; j++) {
                if (i % 2 != 0) {
                    Rectangle rectangle = new Rectangle((j * 60),  k, 59, 30);
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
                        Rectangle rectangle1 = new Rectangle((32 + (j * 60)), k, 59, 30);
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
                        Rectangle rectangle = new Rectangle((j * 60), k, 59, 30);
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
                k += 30;
            }
        }
        return bricks;
    }

    public brick makeBrick(int type, Rectangle rectangle){
        brick newBrick;
        switch(type){
            case CLAY->{
                newBrick = new ClayBrick(scene, rectangle);
            }
            case STEEL-> {
                newBrick = new SteelBrick(scene,rectangle);
            }
            case CEMENT->{
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
        System.out.println("setStrength true");
    }


}
