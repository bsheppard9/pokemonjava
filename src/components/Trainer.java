package components;

import components.Entity;
import components.battleClasses.pokemon.Pokemon;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Trainer extends Entity {

    int velX, velY = 0;
    int speed = 1;

    private String name;

    private String trainerClass;
    private Pokemon[] pokemonParty;
    private short prizeMoney;
    private int pokedex;
    private int trainerID;
    //private timeelapsed playTime;
    private byte badges;
    private String[][] dialogue;

    public Trainer(int x, int y, String name, String trainerClass, Pokemon[]
                   pokemonParty, short prizeMoney) {
        super(x, y);
        this.name = name;
        this.trainerClass = trainerClass;
        this.pokemonParty = pokemonParty;
        this.prizeMoney = prizeMoney;
        this.dialogue = new String[][]{{"OAK: Gary?", "Let me think..."}, {"Oh, that's right,", "I told you to", "come! Just wait!"}};
        update();
    }

    public void update() {
        x += velX;
        y += velY;

    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(getImage(), x, y, null);
        graphics2D.draw(getBoundaries());
    }

    public Image getImage() {
        //graphics2D.drawString("Hello World", 100, 100); //Draws  a string
        ImageIcon imageIcon = new ImageIcon(
                "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                        + "\\Red_Sprite_Right.png");
        return imageIcon.getImage();
    }

    public void checkCollisions() {

    }

    public Rectangle getBoundaries() {
        return new Rectangle(x, y, getImage().getWidth(null),
                getImage().getHeight(null));
    }

    public String getName() { return this.name; }

    public String getTrainerClass() { return this.trainerClass; }

    public Pokemon[] getPokemonParty() { return this.pokemonParty; }

    public short getPrizeMoney() { return this.prizeMoney; }

    public String[][] getDialogue() { return this.dialogue; }



}
