package ru.dobrocraft.game;
import ru.dobrocraft.game.GameMap;

public class Printer {

    public static void printMap(GameMap gameMap) {

        for (int i = 0; i < gameMap.getSize(); i++) {
            for (int j = 0; j < gameMap.getSize(); j++) {
                System.out.print('t');
            }
            System.out.println();
        }
    }
}
