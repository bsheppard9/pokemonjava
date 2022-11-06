package components;

import com.sun.prism.Image;
import components.Display.ImageBounds;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Setup {

    /*Here, preparations are made in order to set up the game;
    * i.e., save file is loaded, boundaries for each town is set,
    * locations are established...*/

    private static ImageBounds currentLocation;

    public static void initialize() {



        //Create a Location class for each town/route
        //The class would contain the music, left/right entrances,
        //current point, layout and imagegrids
        /*Map<Point, Integer> boundaryU = new HashMap<>();
        //boundaryU.put(new Point(1,1), 0);
        for (byte i = 0; i < 8; i++) { //i corresponds with the y of the grid
            for (byte j = 0; j < 8; j++) { //likewise, j, with the x, because
                //the inner loop gets incremented first
                if (i != 0) { //No points for the stairs or top row
                    boundaryU.put(new Point(j, i), i - 1);
                }
            }
        }

        Map<Point, Integer> boundaryR = new HashMap<>();
        //boundaryR.put(new Point(1,1), 1);
        for (int i = 0; i < 8; i++) { //i corresponds with the x of the grid
            for (int j = 0; j < 8; j++) { //likewise, j, with the y
                if (j != 0) { //No points for the stairs or top row
                    boundaryR.put(new Point(i, j), i);
                }
            }
        }


        Map<Point, Integer> boundaryD = new HashMap<>();
        //boundaryR.put(new Point(1,1), 1);
        for (int i = 0; i < 8; i++) { //i corresponds with the y of the grid
            for (int j = 0; j < 8; j++) { //likewise, j, with the x
                if (i != 0) { //No points for the stairs or top row
                    boundaryD.put(new Point(j, i), 7 - i);
                }
            }
        }

        Map<Point, Integer> boundaryL = new HashMap<>();
        //boundaryL.put(new Point(1,1), 6);
        for (int i = 0; i < 8; i++) { //i corresponds with the x of the grid
            for (int j = 0; j < 8; j++) { //likewise, j, with the y
                if (i != 0) { //No points for the stairs or top row
                    boundaryL.put(new Point(i, j), 7 - i);
                }
            }
        }

        currentLocation = new ImageBounds("Player's Room 2F", 8, 8,
                boundaryU, boundaryR, boundaryD, boundaryL, new Point(1,1),
                new Point(0,1));*/


    }

    public static ImageBounds getCurrentLocation() {
        return currentLocation;
    }
}
