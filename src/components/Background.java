package components;

import components.Display.ImageBounds;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Background extends Entity {

    int speed = 4;
    int velX, velY = 0;
    BufferedImage image;
    //ImageBounds boundaries;
    private static int x;
    private static int y;

    public Background(int x, int y, BufferedImage image) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.image = image;
        //this.boundaries = boundaries;
        /*try {
            image = ImageIO.read(new File("/Users/brooklynsheppard/Desktop/PokemonIDE/src/"
                    + "components/Display/Background/Player'sRoom.png"));
            //img = img.getSubimage(x, y, width, height);
            //img = img.getSubimage(0, 0, 12, 14);
            //update();
        } catch (IOException ex) {
            System.out.println("Something went wrong...");
        }*/
    }

    public void update() {
        x += velX;
        y += velY;
    }

    public void draw(Graphics2D graphics2D) {
        //image = image.getSubimage(0, 0, 160, 14);
        graphics2D.drawImage(getImage(), x, y, null);
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        //Arrow keys are VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            //velY = speed; //Maybe save for GEN III
            if (checkCollisions(0, GameFrame.imageBounds.getIsRect(),
                    GameFrame.imageBounds.getHeight(),
                    GameFrame.imageBounds.getCurrentPosition().getY())) {
                update();
            }
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            //velY = -speed;
            update();
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            //velX = speed;
            update();
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            //velX = -speed;
            update();
        }
        //update();
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            velY = 0;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            velY = 0;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            velX = 0;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            velX = 0;
        }
        update();
    }

    public boolean checkCollisions(int direction, boolean isRect, int stopAt, double curr) {
        //Later, should return a boolean, and takes a tile as a parameter
        //ArrayList<Trainer> trainers = GameFrame.getTrainerList();

        //Check if bounds are rectangular
        if (isRect) {
            if (direction == 0) {
                if (curr > 0.0) return true;
            } else if (direction == 1) {
                if (curr < stopAt - 1) return true;
            } else if (direction == 2) {
                if (curr < stopAt - 1) return true;
            } else if (direction == 3) {
                if (curr > 0.0) return true;
            }
        }

        /*for (Trainer trainer : trainers) {
            //Change to for each entity from GameFrame's currentLocation
            Trainer temp = trainer;

            //Save for when tiles are set up?
            /*if (getBoundaries().x + getImage().getWidth(null) >= (trainer.x)
                    && (getBoundaries().y + getImage().getHeight(null) > trainer.getBoundaries().y
                    && getBoundaries().y <= trainer.getBoundaries().y + getImage().getHeight(null)
                    && (getBoundaries().x < trainer.getBoundaries().x))) {
                x = trainer.getBoundaries().x - getImage().getWidth(null);
            } //Left side boundary

            if ((getBoundaries().x >= trainer.getBoundaries().x
                + getImage().getWidth(null))
                && getBoundaries().y + getImage().getHeight(null) > trainer.getBoundaries().y
                && getBoundaries().y <= trainer.getBoundaries().y + getImage().getHeight(null)) {
                x = trainer.getBoundaries().x + getImage().getWidth(null);
            } //Right side boundary

            if (getBoundaries().y + getImage().getHeight(null) >= trainer.getBoundaries().y
                    && (getBoundaries().x + getImage().getWidth(null) >= trainer.getBoundaries().x
                    && getBoundaries().x < trainer.getBoundaries().x + getImage().getWidth(null)
                    && (getBoundaries().y  <= trainer.getBoundaries().y))) {
                y = trainer.getBoundaries().y - getImage().getHeight(null);
            } //Top boundary


            if (getBoundaries().y <= trainer.getBoundaries().y + getImage().getHeight(null)
                    && (getBoundaries().x + getImage().getWidth(null) >= trainer.getBoundaries().x
                    && getBoundaries().x < trainer.getBoundaries().x + getImage().getWidth(null)
                    && (getBoundaries().y > trainer.getBoundaries().y
                    && getBoundaries().y + getImage().getHeight(null) > trainer.getBoundaries().y))) {
                y = trainer.getBoundaries().y + getImage().getHeight(null);
            } //Bottom boundary - Implemented successfully

        }*/
        return false;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setX(int diff) {
        x += diff;
    }

    public void setY(int diff) {
        y += diff;
    }
}
