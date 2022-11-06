package components;

import java.awt.*;
import java.util.HashMap;

public class Layout extends Entity {
    private static int x = 0;
    private static int y = 0;
    private Entity[] interactables; //Must be defined in another class
    private Point[] coordinates;
    private HashMap<Trainer, Point> people; //The point is the area the person
    //can pivot around
    private Image background;

    public Layout(Entity[] interactables, Point[] coordinates,
                  HashMap<Trainer, Point> people) {
        super(x, y);
        this.interactables = interactables;
        this.coordinates = coordinates;
        //Any dropped items can be removed from list
        this.people = people;
    }



}
