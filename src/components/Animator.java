package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Animator implements ActionListener {

    ArrayList<BufferedImage>[] frames;
    private byte direction, prevDirection = 0, nextDirection;
    BufferedImage sprite;
    private volatile boolean running = false;
    private long prevTime, speed;
    private int currFrame, frameAtPause;
    private boolean hasTop, hasRight, hasBottom, hasLeft;
    private boolean adjust, stopAfterLastFrame, changeAfterLastFrame, halfStep,
            changeFace, hitboundary = false;
    private int pixelsL, pixelsU, pixelsR, pixelsD;
    long before1, before2, after1, after2;
    int pressedKey, releasedKey;

    public Animator(boolean hasTop, boolean hasRight, boolean hasBottom,
                        boolean hasLeft, ArrayList<BufferedImage> ... frames) {
        this.hasTop = hasTop;
        this.hasRight = hasRight;
        this.hasBottom = hasBottom;
        this.hasLeft = hasLeft;
        this.frames = frames;
        /*                       TOP (0)
        *                           ^
        *                           |
        *                           |
        *                           |
        * LEFT (3) <  - - - - - DIRECTION - - - - - > RIGHT (1)
        *                           |     X and Y values
        *                           |      Operating
        *                           |    From the Fourth
        *                           v       Quadrant
        *                        BOTTOM (2)
        * */
        if (hasBottom) {
            sprite = frames[2].get(3);
        } else if (hasRight) {
            sprite = frames[3].get(1);
        } else if (hasTop) {
            sprite = frames[0].get(3);
        } else if (hasLeft) {
            sprite = frames[1].get(1);
        }
        direction = 2; //By default, player is facing down
        prevDirection = 2;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update(System.currentTimeMillis()); //Get current time
    }

    //Sets the speed of the animation in milliseconds
    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public void update(long time) {
        if (running) {
            //Checks to see if the set time has passed before switching to the
            //next frame
            if (time - prevTime >= speed && adjust) { //Just speed
                //Here, simply move the player 4 pixels
                if (direction == 0 &&
                        (pixelsU % 16 == 0 || pixelsU % 16 == 8)) {
                    if (!changeFace) {
                        if (GameFrame.background.checkCollisions(direction,
                                GameFrame.imageBounds.getIsRect(), 0,
                                GameFrame.imageBounds.getCurrentPosition().getY())) {
                            hitboundary = false;
                            GameFrame.red.setSpeed(130);
                            GameFrame.background.setY(4);
                        } else {
                            //Slows the animation down if hitting a boundary
                            hitboundary = true;
                            SoundEffects.playCollision();
                            GameFrame.red.setSpeed(260);
                        }
                        pixelsU = (pixelsU + 4) % 16;
                    }
                    adjust = false;
                } else if (direction == 1 &&
                        (pixelsR % 16 == 0 || pixelsR % 16 == 8)) {
                    if (!changeFace) {
                        if (GameFrame.background.checkCollisions(direction,
                                GameFrame.imageBounds.getIsRect(), GameFrame.imageBounds.getWidth(),
                                GameFrame.imageBounds.getCurrentPosition().getX())) {
                            hitboundary = false;
                            GameFrame.red.setSpeed(130);
                            GameFrame.background.setX(-4);
                        } else {
                            //Slows the animation down if hitting a boundary
                            hitboundary = true;
                            GameFrame.red.setSpeed(260);
                        }
                        pixelsR = (pixelsR + 4) % 16;
                    }
                    adjust = false;
                } else if (direction == 2 &&
                        (pixelsD % 16 == 0 || pixelsD % 16 == 8)) {
                    if (!changeFace) {
                        if (GameFrame.background.checkCollisions(direction,
                                GameFrame.imageBounds.getIsRect(), GameFrame.imageBounds.getHeight(),
                                GameFrame.imageBounds.getCurrentPosition().getY())) {
                            hitboundary = false;
                            GameFrame.red.setSpeed(130);
                            GameFrame.background.setY(-4);
                        } else {
                            //Slows the animation down if hitting a boundary
                            hitboundary = true;
                            GameFrame.red.setSpeed(260);
                        }
                        pixelsD = (pixelsD + 4) % 16;
                    }
                    adjust = false;
                } else if (direction == 3 &&
                        (pixelsL % 16 == 0 || pixelsL % 16 == 8)) {
                    if (!changeFace) {
                        if (GameFrame.background.checkCollisions(direction,
                                GameFrame.imageBounds.getIsRect(), 0,
                                GameFrame.imageBounds.getCurrentPosition().getX())) {
                            hitboundary = false;
                            GameFrame.red.setSpeed(130);
                            GameFrame.background.setX(4);
                        } else {
                            //Slows the animation down if hitting a boundary
                            hitboundary = true;
                            GameFrame.red.setSpeed(260);
                        }
                        pixelsL = (pixelsL + 4) % 16;
                    }
                    adjust = false;
                }
            } else if (time - prevTime >= speed && !adjust) {// Change to (time - prevTime >= (speed * 2))
                //Update the animation frame
                if (changeFace) {
                    currFrame = frames[direction].size() - 1;
                    sprite = frames[direction].get(currFrame);
                    stop();
                } else if (halfStep) {
                    //Only for up and down
                    // If direction is either up or down AND the second frame
                    //has been reached, stop
                    currFrame = currFrame == frames[direction].size() - 1 ? 0 :
                                currFrame + 1;

                    if (direction == 0 && (pixelsU % 16 == 4 || pixelsU % 16 == 12)) {
                        //Move backround down
                        System.out.println("Current point is " + GameFrame.imageBounds
                            .getCurrentPosition().getY());
                        GameFrame.background.setY(4);
                        pixelsU = (pixelsU + 4) % 16;
                        adjust = true;
                    } else if (direction == 2 && (pixelsD % 16 == 4 ||
                            pixelsD % 16 == 12)) {
                        GameFrame.background.setY(-4);
                        pixelsD = (pixelsD + 4) % 16;
                        adjust = true;
                    } else if (direction == 1 && (pixelsR % 16 == 4 ||
                            pixelsR % 16 == 12)) {
                        GameFrame.background.setX(-4);
                        pixelsR = (pixelsR + 4) % 16;
                        adjust = true;
                    } else if (direction == 3 && (pixelsL % 16 == 4 ||
                            pixelsL % 16 == 12)) {
                        GameFrame.background.setX(4);
                        pixelsL = (pixelsL + 4) % 16;
                        adjust = true;
                    }
                    //Goes up to 2 or 4 depending on direction
                    sprite = frames[direction].get(currFrame);
                    prevTime = time;
                    if (direction == 2 && currFrame == 1 && pixelsD % 16 == 0 &&
                            stopAfterLastFrame) {
                        System.out.println("Set position one step");
                        GameFrame.imageBounds.setCurrentPosition(direction);
                        System.out.println("It's working now!");
                        stop();
                    } else if (direction == 0 && currFrame == 1 && pixelsU % 16 == 0
                            && stopAfterLastFrame) {
                        //This seems to be working
                        GameFrame.imageBounds.setCurrentPosition(direction);
                        System.out.println("The one part that's working");
                        //The difference, is that this stops (almost) appropriately
                        stop();
                    }
                } else {
                    currFrame = currFrame == frames[direction].size() - 1 ? 0 :
                            currFrame + 1;

                    //This condition ensure that the background moves as the
                    //animation continues
                    if (direction == 0 && (pixelsU % 16 == 4 || pixelsU % 16 == 12)) {
                        //Move backround down
                        if (GameFrame.background.checkCollisions(direction,true, 0,
                                GameFrame.imageBounds.getCurrentPosition().getY())) {
                            hitboundary = false;
                            GameFrame.red.setSpeed(130); //Sets normal walking speed
                            GameFrame.background.setY(4); //Stops the background from moving if the player
                            //goes beyond the bounds
                        } else {
                            //This slows the animation down, indicating a boundary
                            hitboundary = true;
                            GameFrame.red.setSpeed(260);
                        }
                        if (pixelsU + 4 == 16 && !hitboundary) { //Increments position in imagebounds
                            // at the end of animation cycle
                            GameFrame.imageBounds.setCurrentPosition(direction);
                        }
                        pixelsU = (pixelsU + 4) % 16;
                        adjust = true;
                    } else if (direction == 1 && (pixelsR % 16 == 4 ||
                            pixelsR % 16 == 12)) {
                        if (GameFrame.background.checkCollisions(direction,true,
                                GameFrame.imageBounds.getWidth(),
                                GameFrame.imageBounds.getCurrentPosition().getX())) {
                            hitboundary = false;
                            GameFrame.red.setSpeed(130); //Sets normal walking speed
                            GameFrame.background.setX(-4); //Stops the background from moving if the player
                            //goes beyond the bounds
                        } else {
                            //This slows the animation down, indicating a boundary
                            hitboundary = true;
                            GameFrame.red.setSpeed(260);
                        }
                        if (pixelsR + 4 == 16 && !hitboundary) { //Increments position in imagebounds
                            // at the end of animation cycle
                            GameFrame.imageBounds.setCurrentPosition(direction);
                        }
                        pixelsR = (pixelsR + 4) % 16;
                        adjust = true;
                    } else if (direction == 2 && (pixelsD % 16 == 4 ||
                            pixelsD % 16 == 12)) {
                        if (GameFrame.background.checkCollisions(direction,true,
                                GameFrame.imageBounds.getHeight(),
                                GameFrame.imageBounds.getCurrentPosition().getY())) {
                            hitboundary = false;
                            GameFrame.red.setSpeed(130); //Sets normal walking speed
                            GameFrame.background.setY(-4); //Stops the background from moving if the player
                            //goes beyond the bounds
                        } else {
                            //This slows the animation down, indicating a boundary
                            hitboundary = true;
                            GameFrame.red.setSpeed(260);
                        }
                        if (pixelsD + 4 == 16 && !hitboundary) { //Increments position in imagebounds
                            // at the end of animation cycle
                            GameFrame.imageBounds.setCurrentPosition(direction);
                        }
                        pixelsD = (pixelsD + 4) % 16;
                        adjust = true;
                    } else if (direction == 3 && (pixelsL % 16 == 4 ||
                            pixelsL % 16 == 12)) {
                        if (GameFrame.background.checkCollisions(direction,true, 0,
                                GameFrame.imageBounds.getCurrentPosition().getX())) {
                            hitboundary = false;
                            GameFrame.red.setSpeed(130); //Sets normal walking speed
                            GameFrame.background.setX(4); //Stops the background from moving if the player
                            //goes beyond the bounds
                        } else {
                            //This slows the animation down, indicating a boundary
                            hitboundary = true;
                            GameFrame.red.setSpeed(260);
                        }
                        if (pixelsL + 4 == 16 && !hitboundary) { //Increments position in imagebounds
                            // at the end of animation cycle
                            GameFrame.imageBounds.setCurrentPosition(direction);
                        }
                        pixelsL = (pixelsL + 4) % 16;
                        adjust = true;
                    }
                    //Goes up to 2 or 4 depending on direction
                    sprite = frames[direction].get(currFrame);
                    prevTime = time;
                    /*if (direction == 0 && (currFrame == 1 || currFrame == 3)
                            && pixelsU % 16 == 0 && changeAfterLastFrame) {
                        prevDirection = direction;
                        direction = nextDirection;
                        changeFace = false;
                        halfStep = false;
                        currFrame = 1;
                        prevTime = System.nanoTime();
                        pixelsL = 0;
                        pixelsR = 0;
                        pixelsU = 0;
                        pixelsD = 0;
                        start();
                    } else*/
                    //NOTE: The player is out of place on the map if the animation ends
                    //on an even-numbered frame
                    if (direction == 0 && (currFrame == 1 || currFrame == 3)
                                && pixelsU % 16 == 0 && stopAfterLastFrame) { //change to 3
                        System.out.println("Animation stopped properly ^.");
                        //GameFrame.imageBounds.setCurrentPosition(direction);
                            stop();

                    } else if (direction == 2 && (currFrame == 1 || currFrame == 3)
                            && pixelsD % 16 == 0 && stopAfterLastFrame) {
                        //GameFrame.imageBounds.setCurrentPosition(direction);
                        System.out.println("Animation stopped properly v.");
                        stop();
                    } else if (direction == 3 && pixelsL % 16 == 0 &&
                            currFrame == 1 && stopAfterLastFrame) { //change to 1
                        System.out.println("Animation stopped properly >.");
                        stop();
                    } else if (direction == 1 && pixelsR % 16 == 0 &&
                            currFrame == 1 && stopAfterLastFrame) { //change to 1
                        System.out.println("Animation stopped properly <.");
                        stop();
                    }

                }
            }
        }
    }




    public void start() {
        running = true;
        prevTime = System.currentTimeMillis();
        adjust = true;
        //currFrame = 0; //Comment out
        //frameAtPause = 0; //Comment out
        stopAfterLastFrame = false;
    }

    public void stop() {
        running = false;
        prevTime = 0;
        currFrame = 1; //Helps prevent arrayIndexOutOfBounds when
        //switching directions
        frameAtPause = 1;
        pixelsL = 0;
        pixelsR = 0;
        pixelsU = 0;
        pixelsD = 0;
    }

    //To be used on NPCs
    public void pause() {
        frameAtPause = currFrame;
        running = false;
    }

    //To be used on NPCs
    public void resume() {
        currFrame = frameAtPause;
        running = true;
    }

    public boolean isRunning() {
        return running;
    }

    public void keyPressed(KeyEvent event) {
        before1 = System.nanoTime();
        //before2 = System.nanoTime();
        int key = event.getKeyCode();
        pressedKey = key;
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT && hasLeft) {
            if (!isRunning()) {
                prevDirection = direction;
                direction = 3;
                prevTime = System.nanoTime();
                changeFace = false;
                halfStep = false;
                currFrame = 1;
                start();
            } else if (direction != 3) {
                //This should not work for light taps
                changeAfterLastFrame = true;
                //prevDirection = direction;
                nextDirection = 3;

                //Reminder: Call start at the end of update()
                //Assign prevDirection, changeFace, currFrame, and halfstep there
            }
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN && hasBottom) {
            if (!isRunning()) {
                prevDirection = direction;
                direction = 2;
                prevTime = System.nanoTime();
                changeFace = false;
                halfStep = false;
                currFrame = 3;
                start();
            } else if (direction != 2) {
            }
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT && hasRight) {
            if (!isRunning()) {
                prevDirection = direction;
                direction = 1;
                prevTime = System.nanoTime();
                changeFace = false;
                halfStep = false;
                currFrame = 1;
                start();
            } else if (direction != 1) {
            }
        } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP && hasTop) {
            if (!isRunning()) {
                prevDirection = direction;
                direction = 0;
                prevTime = System.nanoTime();
                changeFace = false;
                halfStep = false;
                currFrame = 3;
                start();
            } else if (direction != 0) {
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        after1 = System.nanoTime();
        long timelapse = (after1 - before1);
        //long convert = TimeUnit.SECONDS.convert(timelapse, TimeUnit.NANOSECONDS);
        int key = event.getKeyCode();
        releasedKey = key;
        System.out.println("Current frame is " + currFrame + ", keyreleased is now true.");
        if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT  || key == 37
                && hasLeft) {
            stopAfterLastFrame = true;
            if (timelapse < 130 && prevDirection != direction) {
                changeFace = true;
                halfStep = false;
            } else {
                changeFace = false;
                halfStep = false;
            }
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN
                || key == 40 && hasBottom) {
            stopAfterLastFrame = true;
            if (timelapse < 130 && prevDirection != direction) {
                changeFace = true;
                halfStep = false;
            } else if (timelapse < 130 && prevDirection == direction) {
                changeFace = false;
                halfStep = true;
            } else {
                changeFace = false;
                halfStep = false;
            }
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT
                || key == 39 && hasRight) {
            stopAfterLastFrame = true;
            if (timelapse < 130 && prevDirection != direction) {
                changeFace = true;
                halfStep = false;
            } else {
                changeFace = false;
                halfStep = false;
            }
        } else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP
                || key == 38 && hasTop) {
            stopAfterLastFrame = true;
            if (timelapse < 130 && prevDirection != direction) {
                changeFace = true;
                halfStep = false;
            } else if (timelapse < 130 && prevDirection == direction) {
                changeFace = false;
                halfStep = true;
            } else {
                changeFace = false;
                halfStep = false;
            }
        }
    }


}
