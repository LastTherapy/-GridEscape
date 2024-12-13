package ru.dobrocraft;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;

public class Printer {
    // ANSI escape code to clear the screen
    private static final String ANSI_CLS = "\u001b[2J";
    // ANSI escape code to move the cursor to the top left corner
    private static final String ANSI_HOME = "\u001b[H";

    PrinterSettings settings = new PrinterSettings();
    FColor fColor = FColor.BLACK;
    private GameMap gameMap;

    public Printer(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public void printMap() {
        printMap(gameMap);
    }

    public void printMap(GameMap gameMap) {
        for (int i = 0; i < gameMap.getSize() + 2; i++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 0; i < gameMap.getSize(); i++) {
            System.out.print('|');
            for (int j = 0; j < gameMap.getSize(); j++) {
                int  gameObject = gameMap.getData()[j][i];
                char charObject = charObject(gameObject);
                BColor bColor = getGameObjetColor(gameObject);
                ColoredPrinter coloredPrinter = new ColoredPrinter.Builder(1, false)
                        .foreground(fColor)
                        .background(bColor)
                        .attribute(Ansi.Attribute.BOLD)
                        .build();
                coloredPrinter.print(charObject);
            }
            System.out.println('|');
        }
        for (int i = 0; i < gameMap.getSize() + 2; i++) {
            System.out.print("¯");
        }
        System.out.println();
    }


    private BColor getGameObjetColor(int gameObject) {
        switch (gameObject) {
            case 0:
                return BackGroundColor.getBColor(settings.getPlayerColor());
            case 1:
                return BackGroundColor.getBColor(settings.getEnemyColor());
            case 2:
                return BackGroundColor.getBColor(settings.getWallColor());
            case 3:
                return BackGroundColor.getBColor(settings.getGoalColor());
            default:
                return BackGroundColor.getBColor(settings.getEmptyColor());
        }
    }

    private char charObject(int gameObject) {
//                PLAYER(0),
//                ENEMY(1),
//                WALL(2),
//                GOAL(3),
//                EMPTY(4);
        switch (gameObject) {
            case 0:
                return settings.getPlayerChar();
            case 1:
                return settings.getEnemyChar();
            case 2:
                return settings.getWallChar();
            case 3:
                return settings.getGoalChar();
            default:
                return settings.getEmptyChar();
        }
    }

    public void update() {
        System.out.println(ANSI_CLS + ANSI_HOME);
        System.out.flush();
        printMap();
    }
}

class BackGroundColor {
    public static BColor getBColor(String color) {
        Ansi.BColor bColor = Ansi.BColor.valueOf(color);
        return bColor;
    }
}