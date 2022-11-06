import components.GameFrame;
import components.Setup;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Screen");
        frame.getContentPane().setPreferredSize(new Dimension(160, 144));
        //Double the dimensions of gameboy screen resolution
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Setup.initialize(); //components.Setup must be done before GameFrame is
        //initialized to ensure frame has needed variables
        frame.add(new GameFrame());
        frame.setLocationRelativeTo(null); //Centers the window in the middle of the screen
        frame.pack();
        frame.setVisible(true);
        //During moments where dialog boxes are needed:
        //frame.setEnabled(false) will (hopefully) pause the frame
        //frame.setEnabled(true) will restart

    }

}
