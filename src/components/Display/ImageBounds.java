package components.Display;

import components.Background;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageBounds { ;


    private String name;
    private int width;
    private int height;
    private boolean isRect;
    private Map<Point, Integer> distanceU;
    private Map<Point, Integer> distanceR;
    private Map<Point, Integer> distanceB;
    private Map<Point, Integer> distanceL;
    private Point currentPosition;
    private Point exitPoint;


    public ImageBounds(String name, int width, int height,
                     boolean isRect,
                     Point currentPosition, Point exitPoint) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.isRect = isRect;
        //this.distanceU = topBoundary;
        //this.distanceR = rightBoundary;
        //this.distanceB = bottomBoundary;
        //this.distanceL = leftBoundary;
        this.currentPosition = currentPosition;
        this.exitPoint = exitPoint;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean getIsRect() {
        return this.isRect;
    }

    public Point getCurrentPosition() {
        return this.currentPosition;
    }

    public void setCurrentPosition(int direction) {
        if (direction == 0) {
            //Vertical direction
            this.currentPosition.y -= 1;
            //System.out.println("Y value is " + this.currentPosition.y);
        } else if (direction == 2) {
            this.currentPosition.y += 1;
            //System.out.println("Y value is " + this.currentPosition.y);
        } else if (direction == 1) {
            //Horizontal direction
            this.currentPosition.x += 1;
            //System.out.println("X value is " + this.currentPosition.x);
        } else if (direction == 3){
            this.currentPosition.x -= 1;
            //System.out.println("X value is " + this.currentPosition.x);
        }
    }

    public void getExitPoint() {

    }


}
