package components;

import components.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends JPanel implements ActionListener{

    //int velX, velY = 0;
    int speed = 1;
    private static int x = 66;
    private static int y = 60;
    Rectangle boundaries;
    private boolean inMotion;

    private static int idNo = 100000 + (new Random().nextInt() * 999999); //Only used for user

    public Player() {
        //boundaries = new Rectangle(x, y, getImage().getWidth(null),
                //getImage().getHeight(null));
        //addKeyListener(new KeyAdaptPlayer(this));
        update();
    }

    public void update() {
        //x += velX;
        //y += velY;
        checkCollisions();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
        repaint();
    }



    public void checkCollisions() {
        //Later, should return a boolean, and takes a tile as a parameter
        ArrayList<Trainer> trainers = GameFrame.getTrainerList();
        for (Trainer trainer : trainers) {
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
            } //Bottom boundary - Implemented successfully*/

        }
    }

    public Rectangle getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(Rectangle boundaries) {
        this.boundaries = boundaries;
    }

    public static int getIdNo() {
        return idNo;
    }

}
