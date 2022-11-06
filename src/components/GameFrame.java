package components;

import components.Display.ImageBounds;
import components.battleClasses.pokemon.Pokemon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameFrame extends JPanel implements ActionListener{

    javax.swing.Timer mainTimer;
    static Player player;
    Textbox textbox;
    Trainer trainer;
    static Animator red;
    static ImageBounds imageBounds;
    static Background background;
    //static boolean showDialogueBox = true;
    static boolean  playerInMotion;
    static boolean gamePaused = false;
    static boolean inBattleMode = false;
    static boolean inInteractiveMode = false;
    static boolean inOverworldMode = true;
    static Layout currentLocation;
    static boolean changeSteps;
    static SoundEffects sfx;

    static ArrayList<Trainer> trainerList = new ArrayList<>();

    //Each setting has its own trainers

    public GameFrame() {
        setFocusable(true);

        //player = new Player();
        //player = new Player(138, 98); The bottom right corner
        //trainer = new Trainer(48, 48, "", "", new Pokemon[] {}, (short) 0);
        //trainerList.add(trainer);

        sfx = new SoundEffects();
        textbox = new Textbox();
        imageBounds = new ImageBounds("Player's Room", 8, 8, true,
                new Point(3, 4), new Point(7, 1));
        //Setup.initialize();
        SpriteLoader loader = new SpriteLoader();
        BufferedImage spriteSheet1 = null;
        BufferedImage spriteSheet2 = null;
        BufferedImage spriteSheet3 = null;
        BufferedImage spriteSheet4 = null;
        try {
            BufferedImage image = ImageIO.read(new File("/Users/brooklynsheppard/Desktop/PokemonIDE/src/"
                    + "components/Display/Background/Player'sRoom.png"));
            //Polygon boundaries = new Polygon(new int[] {}, new int[] {}, 4);
            //Boundaries should be done in relation to the player's location
            background = new Background(0, 0, image);
            spriteSheet1 = loader.loadImage("/Users/brooklynsheppard/Desktop/" +
                    "PokemonIDE/src/components/Display/Sprites/Red_Spritesheet_Up.png");
            spriteSheet2 = loader.loadImage("/Users/brooklynsheppard/Desktop/" +
                    "PokemonIDE/src/components/Display/Sprites/Red_Spritesheet_Left.png");
            spriteSheet3 = loader.loadImage("/Users/brooklynsheppard/Desktop/" +
                    "PokemonIDE/src/components/Display/Sprites/Red_Spritesheet_Down.png");
            spriteSheet4 = loader.loadImage("/Users/brooklynsheppard/Desktop/" +
                    "PokemonIDE/src/components/Display/Sprites/Red_Spritesheet_Right.png");
        } catch (IOException ex) {
            System.out.println("Something went wrong loading the background/avatar");
        }
        SpriteSheet ss1 = new SpriteSheet(spriteSheet1);
        SpriteSheet ss2 = new SpriteSheet(spriteSheet2);
        SpriteSheet ss3 = new SpriteSheet(spriteSheet3);
        SpriteSheet ss4 = new SpriteSheet(spriteSheet4);
        ArrayList<BufferedImage> tsprites = new ArrayList<>();
        ArrayList<BufferedImage> lsprites = new ArrayList<BufferedImage>();
        ArrayList<BufferedImage> bsprites = new ArrayList<>();
        ArrayList<BufferedImage> rsprites = new ArrayList<>();

        tsprites.add(ss1.getSheet(0, 0));
        tsprites.add(ss1.getSheet(16, 0));
        tsprites.add(ss1.getSheet(32, 0));
        tsprites.add(ss1.getSheet(48, 0));

        lsprites.add(ss2.getSheet(0, 0));
        lsprites.add(ss2.getSheet(16, 0));

        bsprites.add(ss3.getSheet(0, 0));
        bsprites.add(ss3.getSheet(16, 0));
        bsprites.add(ss3.getSheet(32, 0));
        bsprites.add(ss3.getSheet(48, 0));

        rsprites.add(ss4.getSheet(0, 0));
        rsprites.add(ss4.getSheet(16, 0));

        red = new Animator(true, true, true, true, tsprites, rsprites,
                bsprites, lsprites);
        red.setSpeed(130); //Set to 65? Originally 125
        //red.start();

        addKeyListener(new KeyAdapt(textbox, background));
        addKeyListener(new KeyAdaptPlayer(red));
        mainTimer = new Timer(10, this); //Resets the screen every 10 millisecs
        mainTimer.start();
        //textbox.addDialogue(trainer.getDialogue()); //This must be altered to
        //take in input right before the interaction
        setBackground(Color.BLACK);
        //imageBounds = Setup.getImageBounds();
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        if (!inBattleMode) {
            background.draw(graphics2D);
            //NOTE: These must be done in a particular order
            //Draw layout
            //Draw any items lying around
            //player.draw(graphics2D);
            //Draw trainers in the area
            //trainer.draw(graphics2D);
            red.update(System.currentTimeMillis()); //DO NOT CONVERT TO NANO
            //LEAVE THIS ALONE; PLAYER'S POSITION ON SCREEN SHOULD BE CONSTANT
            ((Graphics2D) graphics).drawImage(red.sprite, 48, 64, null);

            repaint();
            if (changeSteps) { actionPerformed(new ActionEvent(player, ActionEvent.ACTION_FIRST, null));}
        }


        if (inBattleMode) {

        } else if (inInteractiveMode) {
            //The lines need to be added before the textbox is drawn
            textbox.draw(graphics2D);
        } else if (inOverworldMode) {

        }

    }


    @Override
    public void actionPerformed(ActionEvent event) {
        //player.update();
        //red.update(System.currentTimeMillis());
        repaint();
    }

    public static ArrayList<Trainer> getTrainerList() {
        return trainerList;
    }
}
