package components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyAdapt extends KeyAdapter{

    Player player; //Player info has been moved to KeyAdaptPlayer
    Textbox textbox;
    Background background;

    public KeyAdapt(Textbox textbox, Background background) {
        //this.player = player;
        this.textbox = textbox;
        this.background = background;
    }

    public void keyPressed(KeyEvent event) {
        //player.keyPressed(event);
        background.keyPressed(event);
    }

    public void keyReleased(KeyEvent event) {
        //player.keyReleased(event);
        textbox.keyReleased(event);
        background.keyReleased(event);
    }

}
