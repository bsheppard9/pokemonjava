package tests;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BackgroundTest {
    public static void main(String[] args) {
        try {
            BufferedImage img = ImageIO.read(new File("/Users/brooklynsheppard/Desktop/PokemonIDE/src/"
                    + "components/Display/Background/Player'sRoom.png"));
            //img = img.getSubimage(x, y, width, height);
            img = img.getSubimage(0, 0, 12, 14);
            //Graphics2D g = BufferedImage.getGraphics;
        } catch (IOException ex) {
            System.out.println("Something went wrong...");
        }
    }
}
