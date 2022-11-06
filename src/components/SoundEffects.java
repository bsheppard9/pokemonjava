package components;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundEffects {
    static File collision;

    public SoundEffects() {
        collision = new File("/Users/brooklynsheppard/Desktop/" +
                "PokemonIDE/src/components/SFX/SFX_COLLISION.wav");
    }

    public static void playCollision() {
        try {
            AudioInputStream aiStream = AudioSystem.getAudioInputStream(collision.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(aiStream);
            clip.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
