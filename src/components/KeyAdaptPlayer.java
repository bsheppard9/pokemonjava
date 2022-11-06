package components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class KeyAdaptPlayer extends KeyAdapter {

    Animator player;

    public KeyAdaptPlayer(Animator player) { this.player = player; }

    public void keyPressed(KeyEvent event) {
        player.keyPressed(event);
        //background.keyPressed(event);
        //Set<Thread> thr = Thread.getAllStackTraces().keySet();
    }

    public void keyReleased(KeyEvent event) {

        player.keyReleased(event);
    }

}
