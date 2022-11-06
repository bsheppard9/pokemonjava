package components;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SpriteLoader {
    public BufferedImage loadImage(String path) throws IOException {
        //URL url = this.getClass().getResource(path);
        return ImageIO.read(new File(path));
    }
}
