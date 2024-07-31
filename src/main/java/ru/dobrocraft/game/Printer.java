package ru.dobrocraft.game;
import ru.dobrocraft.game.GameMap;

public class Printer {
    GameSettings settings = new GameSettings();


    public void printMap(GameMap gameMap) {

        for (int i = 0; i < gameMap.getSize(); i++) {
            for (int j = 0; j < gameMap.getSize(); j++) {
                int  gameObject = gameMap.getData()[i][j];
                System.out.print(charObject(gameObject));
            }
            System.out.println();
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
}
