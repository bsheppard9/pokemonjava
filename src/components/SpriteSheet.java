package components;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    public BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage getSheet(int x, int y) {
        //The x and y indicate the coordinates where the sprite start
        BufferedImage sprite = sheet.getSubimage(x, y, 16, 16);
        return sprite;
    }
}
