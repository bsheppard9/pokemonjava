package components.battleClasses;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public enum Type {
    GRASS("Grass"), FIRE("Fire"), WATER("Water"), ELECTRIC("Electric"), GROUND("Ground"),
    NORMAL("Normal"), FIGHTING("Fighting"), PSYCHIC("Psychic"), DARK("Dark"), GHOST("Ghost"),
    FLYING("Flying"), ICE("Ice"), STEEL("Steel"), DRAGON("Dragon"), FAIRY("Fairy"),
    BUG("Bug"), ROCK("Rock"), POISON("Poison");

    private String name;

    Type(String s) {
        this.name = s;
    }

    public HBox getTypeView() {
        Color color = null;
        switch (this) {
            case FIRE:
                color = Color.ORANGE;
                break;
            case WATER:
                color = Color.BLUE;
                break;
            case GRASS:
                color = Color.GREEN;
                break;
            case POISON:
                color = Color.MEDIUMPURPLE;
                break;
            case ELECTRIC:
                color = Color.YELLOW;
                break;
            case PSYCHIC:
                color = Color.MAGENTA;
                break;
            case DARK:
                color = Color.DARKGRAY;
                break;
            case GHOST:
                color = Color.PURPLE;
                break;
            case GROUND:
                color = Color.BROWN;
                break;
            case NORMAL:
                color = Color.TAN;
                break;
            case FIGHTING:
                color = Color.RED;
                break;
            case FLYING:
                color = Color.LIGHTCYAN;
                break;
            case ICE:
                color = Color.CYAN;
                break;
            case STEEL:
                color = Color.LIGHTGREY;
                break;
            case DRAGON:
                color = Color.SLATEBLUE;
                break;
            case FAIRY:
                color = Color.PINK;
                break;
            case ROCK:
                color = Color.WHEAT;
                break;
            case BUG:
                color = Color.YELLOWGREEN;
                break;
            default:
                color = Color.WHITE;
                break;
        }
        HBox view = new HBox();
        view.setAlignment(Pos.CENTER);
        view.setPadding(new Insets(5));

        Label type = new Label(this.toString());
        type.setFont(Font.font("Monospace", FontWeight.BLACK, 12));
        view.getChildren().add(type);
        view.setBackground(new Background(new BackgroundFill(color,
                new CornerRadii(0.5), null)));

        return view;
    }

    public String toString() {
        return this.name;
    }
}