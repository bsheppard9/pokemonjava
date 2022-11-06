package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.Timer;

public class Textbox extends Entity { //Only create one instance
    private static String[][] lines;
    private static final int x = 1;
    private static final int y = 97;
    private Image next = new ImageIcon(
            "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                    + "\\src\\components\\Display\\next.png").getImage();
    int lineNo = 0; //Used to count the line number when printing
    static String line1; //Used to monitor the characters on the first line
    static String line2; //Used to monitor the characters on the second line
    int charNo = 0; //Used to count the character number when printing
    int cx = 8; //Used for the coordinates of each character printed
    int cy; //Used for the coordinates of each character printed
    boolean timerInitialized = false; //Used to indicate end of line when printing
    java.util.Timer timer = new Timer(); //Used to control text speed
    ArrayList<String> display = new ArrayList<>();
    ArrayList<Point> coordinates = new ArrayList<>();
    static boolean inTransition = false;
    static boolean waitingForInput = false;
    int inc = 0; //Used to monitor the set of lines that you are on

    private static volatile Textbox textbox = new Textbox();

    public Textbox() {
        super(x, y);
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(getImage(), x, y, null);
        //Every time draw is called, ensure that the coordinates are the same
        //each time text is redrawn
        if (!display.isEmpty()) { //This will only print when characters have
            //been added every second
            for (int i = 0; i < display.size(); i++) {
                drawText(graphics2D, display.get(i).charAt(0), coordinates.get(i));
            }
        }
        //The "next" will be shown if more text remains in the dialogue
        if ((lines[inc].length == 1 && lineNo == 0  && inc < lines.length - 1)
                || (lines[inc].length == 2 && lineNo == 1 && inc < lines.length - 1)
                || (lineNo < lines[inc].length - 1 || inc < lines.length - 1)
                && waitingForInput && !display.isEmpty()) {
            if (line2 == null && charNo == line1.length()) {
                //Accounts for cases where you only have one line
                drawNext(graphics2D);
            } else if (lines[inc][lineNo].equals(line2) && charNo == line2
                    .length()) {
                drawNext(graphics2D);
            }
        }
        if (!timerInitialized && line1 != null) { //This only allows the timer to be set once
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    addTextToDisplay(lines[inc][lineNo]);
                }
            }, 100);
            timerInitialized = true;
        }
    }

    /*public void drawText(Image image, Point coordinates) {
        this.graphics2D.drawImage(image, coordinates.x, coordinates.y, null);
    }*/

    public Image getImage() {
        //graphics2D.drawString("Hello World", 100, 100); //Draws  a string
        ImageIcon imageIcon = new ImageIcon(
                "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                        + "\\src\\components\\Display\\Textbox.png");
        return imageIcon.getImage();
    }

    public Image getCharImage(char letter) {
        ImageIcon imageIcon = new ImageIcon(
                "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                        + "\\src\\components\\Display\\Characters\\lowercase_a.png");
        switch (letter) {
            case 'a':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_a.png");
            break;
            case 'b':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_b.png");
            break;
            case 'c':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_c.png");
            break;
            case 'd':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_d.png");
            break;
            case 'e':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_e.png");
            break;
            case 'f':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_f.png");
            break;
            case 'g':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_g.png");
            break;
            case 'h':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_h.png");
            break;
            case 'i':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_i.png");
            break;
            case 'j':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_j.png");
            break;
            case 'k':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_k.png");
            break;
            case 'l':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_l.png");
            break;
            case 'm':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_m.png");
            break;
            case 'n':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_n.png");
            break;
            case 'o':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_o.png");
            break;
            case 'p':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_p.png");
            break;
            case 'q':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_q.png");
            break;
            case 'r':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_r.png");
            break;
            case 's':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_s.png");
            break;
            case 't':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_t.png");
            break;
            case 'u':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_u.png");
            break;
            case 'v':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_v.png");
            break;
            case 'w':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_w.png");
            break;
            case 'x':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_x.png");
            break;
            case 'y':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_y.png");
            break;
            case 'z':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\lowercase_z.png");
            break;
            case 'A':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\A.png");
            break;
            case 'B':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\B.png");
            break;
            case 'C':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\C.png");
            break;
            case 'D':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\D.png");
            break;
            case 'E':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\E.png");
            break;
            case 'F':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\F.png");
            break;
            case 'G':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\G.png");
            break;
            case 'H':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\H.png");
            break;
            case 'I':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\I.png");
            break;
            case 'J':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\J.png");
            break;
            case 'K':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\K.png");
            break;
            case 'L':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\L.png");
            break;
            case 'M':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\M.png");
            break;
            case 'N':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\N.png");
            break;
            case 'O':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\O.png");
            break;
            case 'P':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\P.png");
            break;
            case 'Q':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\Q.png");
            break;
            case 'R':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\R.png");
            break;
            case 'S':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\S.png");
            break;
            case 'T':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\T.png");
            break;
            case 'U':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\U.png");
            break;
            case 'V':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\V.png");
            break;
            case 'W':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\W.png");
            break;
            case 'X':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\X.png");
            break;
            case 'Y':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\Y.png");
            break;
            case 'Z':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\Z.png");
            break;
            case '.':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\period.png");
            break;
            case '?':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\question_mark.png");
            break;
            case '!':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\!.png");
            break;
            case ',':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\,.png");
            break;
            case '(':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\(.png");
            break;
            case ')':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\).png");
            break;
            case '[':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\[.png");
            break;
            case ']':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\[.png");
            break;
            case '&':
                //pk
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\pk.png");
            break;
            case '*':
                //mn
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\mn.png");
            break;
            case '-':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\-.png");
            break;
            case ':':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\colon.png");
            break;
            case ';':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\semicolon.png");
            break;
            case '/':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\slash.png");
            break;
            case '<':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\mars.png");
            break;
            case '>':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\venus.png");
            break;
            case '\'':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\apostrophe.png");
            break;
            case '0':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\0.png");
            break;
            case '1':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\1.png");
            break;
            case '2':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\2.png");
            break;
            case '3':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\3.png");
            break;
            case '4':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\4.png");
            break;
            case '5':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\5.png");
            break;
            case '6':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\6.png");
            break;
            case '7':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\7.png");
            break;
            case '8':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\8.png");
            break;
            case '9':
                imageIcon = new ImageIcon(
                        "C:\\Users\\Brooklyn\\Desktop\\gamingResearch\\PokemonIDE"
                                + "\\src\\components\\Display\\Characters\\9.png");
            break;
        }
        return imageIcon.getImage();
    }

    public void resetCursor() {
        inc++;
        charNo = 0;
        lineNo = 0;
        cx = 7; //This accounts for the space before printing a letter
        cy = 112;
    }

    public void adjustCursor() {
        charNo = 0;
        cx = 7; //This accounts for the space before printing a letter
        cy = 128;
    }

    public void clear() {
        display.clear();
        coordinates.clear();
        //timerInitialized = false;
        resetCursor();
        line1 = null;
        line2 = null;
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();

        if (key == KeyEvent.VK_ENTER) { //Later on, add rule that you are in
            //front of someone
            if (GameFrame.gamePaused == true) {
                //In the first case, translate the two lines of text to allow
                //room for another line
                if (waitingForInput && lineNo < lines[inc].length - 1) {
                    waitingForInput = false;
                    translateText();
                } else if (waitingForInput && lineNo == lines[inc].length - 1) {
                    //In the second case, completely clear the screen in order
                    //to start the next set of lines
                    clear();
                    selectNextLines();
                    waitingForInput = false;
                    timer.cancel();
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            addTextToDisplay(lines[inc][lineNo]);
                        }
                    }, 250);
                } else {
                    //In this case, the speaker is done talking, and we clear
                    //the textbox completely
                    GameFrame.inOverworldMode = true;
                    GameFrame.inInteractiveMode = false;
                    GameFrame.inBattleMode = false;
                    GameFrame.gamePaused = false;
                    clear();
                    inc = 0; //We do this here in cases where the person
                    //is finished speaking
                    timerInitialized = false;
                }
            } else {
                GameFrame.inBattleMode = false;
                GameFrame.inInteractiveMode = true;
                GameFrame.inOverworldMode = false;
                GameFrame.gamePaused = true;
            }

        }
    }

    /**
     * This method should only be called once, when talking to someone
     * @param dialogue
     */
    public void addDialogue(String[][] dialogue) {
        //Add check to see that input is not null
        inc = 0;
        this.lines = dialogue;
        selectNextLines();
    }

    public void selectNextLines() {
        //Add check to see that input is not null
        line1 = lines[inc][0];
        if (lines[inc].length > 1) {
            line2 = lines[inc][1];
        }
    }

    public void drawText(Graphics2D graphics2D, char letter, Point coor) {
        Image image = getCharImage(letter);
        graphics2D.drawImage(image, coor.x, coor.y, null);
    }

    public void drawNext(Graphics2D graphics2D) {
        graphics2D.drawImage(next, 144, 129, null);
    }

    public void addTextToDisplay(String line) {
        int width = getCharImage(line.charAt(charNo)).getWidth(null);
        cy = lineNo == 0 ? 112 : 128;
        if (line.charAt(charNo) >= 'A' && line.charAt(charNo) <= 'Z') {
            cx += line.charAt(charNo) == 'I' ? 2 : 1;
            display.add(String.valueOf(line.charAt(charNo)));
            coordinates.add(new Point(cx, cy));
            cx += line.charAt(charNo) == 'I' ? width + 1 : width;
        } else if (line.charAt(charNo) >= 'a' && line.charAt(charNo) <= 'z') {
            if (line.charAt(charNo) == 'b' || line.charAt(charNo) == 'd'
                    || line.charAt(charNo) == 'f' || line.charAt(charNo) == 'h'
                    || line.charAt(charNo) == 'k' || line.charAt(charNo) == 'l') {
                //NOTE: Using a map will not work, as it does not allow duplicates
                //if l, add 2 bytes before
                cx += 1;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += line.charAt(charNo) == 'l' ? width + 2 : width + 1;
            } else if (line.charAt(charNo) == 'i' || line.charAt(charNo) == 'j'
                    || line.charAt(charNo) == 't') {
                cx += line.charAt(charNo) == 'i' ? 4: 1;
                cy += 1;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width;
                if (line.charAt(charNo) == 'i') {
                    cx += 3;
                } else if (line.charAt(charNo) == 'j') {
                    cx += 3;
                } else if (line.charAt(charNo) == 't') {
                    cx += 2;
                } else {
                    cx += 1;
                }
            } else {
                cx += 1;
                cy += 2;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                //add 3 bytes after m, 3 bytes after u, 1 after w, 3 after y
                cx += width;
                if (line.charAt(charNo) == 'm') {
                    cx += 3;
                } else if (line.charAt(charNo) == 'u') {
                    cx += 2;
                } else if (line.charAt(charNo) == 'w') {
                    cx += 0;
                } else if (line.charAt(charNo) == 'y') {
                    cx += 2;
                } else {
                    cx += 1;
                }
            }
        } else if (line.charAt(charNo) >= 32 && line.charAt(charNo) <= 95) {
            //Check for symbols and spaces
            if (line.charAt(charNo) == '_') {
                cx += 6;
            } else if (line.charAt(charNo) == '.') {
                cx += 1;
                cy += 5;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 4;
            } else if (line.charAt(charNo) == ',') {
                cx += 1;
                cy += 4;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 5;
            } else if (line.charAt(charNo) == '/') {
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 1;
            } else if (line.charAt(charNo) == '?') {
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width;
            } else if (line.charAt(charNo) == '!') {
                cx += 2;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 2;
            } else if (line.charAt(charNo) == ']') {
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 5;
            } else if (line.charAt(charNo) == '[') {
                cx += 5;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width;
            } else if (line.charAt(charNo) == ':') {
                cx += 3;
                cy += 1;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 3;
            } else if (line.charAt(charNo) == ';') {
                cx += 3;
                cy += 1;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 3;
            } else if (line.charAt(charNo) == '-') {
                cx += 1;
                cy += 4;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 1;
            } else if (line.charAt(charNo) == '(') {
                cx += 3;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 1;
            } else if (line.charAt(charNo) == ')') {
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 3;
            } else if (line.charAt(charNo) == '\'') {
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width + 1;
            } else if (line.charAt(charNo) >= '0' && line.charAt(charNo) <= '9') {
                cx += line.charAt(charNo) == '1' ? 1 : 0;
                cy += 1;
                display.add(String.valueOf(line.charAt(charNo)));
                coordinates.add(new Point(cx, cy));
                cx += width;
            } else {
                cx += 6;
            }
        }
        //Writes on the second half of the box if there is
        //more than 1 line
        cy = lineNo == 0 ? 112 : 128;
        charNo++;
        if (charNo >= line.length() && line.equals(line1)) {
            //Automatically adjusts cursor after printing first line
            if (line2 != null) {
                adjustCursor();
                lineNo++;
            }

        }


        //Schedule the next letter here if there are more letters
        if (lineNo < lines[inc].length && line2 != null) {
            if (!line2.equals(lines[inc][lineNo]) || charNo != line2.length()) {
                //A condition for line 2
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        addTextToDisplay(lines[inc][lineNo]);
                    }
                }, 100);
            } else if (inc < lines.length - 1 || lineNo < lines[inc].length - 1) {
                waitingForInput = true;
            }
        } else if (lineNo < lines[inc].length && line2 == null) {
            //In the case where there is only one line of text
            if (charNo != line1.length()) {
                timer.cancel();
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        addTextToDisplay(lines[inc][lineNo]);
                    }
                }, 100);
            } else if (inc < lines.length - 1 || lineNo < lines[inc].length - 1) {
                waitingForInput = true;
            }
        }
    }

    /**
     * This method will be called with every call to repaint()
     * This monitors the letters that have been added to the list
     * The list should have characters added to it until all of the characters
     * in a line have been printed
     */

    public void paintComponent(final Graphics graphics) {
        paintComponent(graphics);

    }

    public void translateText() {
        //Decrease all of the letters' y values by a certain value
        //Maybe utilize removeRange() ?
        for (int i = 0; i < coordinates.size(); i++) {
            //Decrease all y values by 8
            Point point = new Point(coordinates.get(i));
            point.y -= 8;
            coordinates.remove(i);
            coordinates.add(i, point);
        }
        if (inTransition) {
            inTransition = false;

            //Move to the next line to the list
            line1 = line2;
            line2 = lines[inc][++lineNo];
            //aButtonPressed = false;
            adjustCursor();
            addTextToDisplay(line2);
        } else {
            //Schedule translateText again
            //remove line1 and line1's coordinates (to avoid printing it)
            String str = line1.replaceAll(" ", "");
            for (int j = 0; j < str.length(); j++) {
                coordinates.remove(0);
                display.remove(0);
            }
            inTransition = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    translateText();
                }
            }, 200);
        }
    }

    /*public String[] getLines() {
        return lines;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }*/

    public static Textbox getTextbox() {
        return textbox;
    }
}
