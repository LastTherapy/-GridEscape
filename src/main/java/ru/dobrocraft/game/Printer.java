package ru.dobrocraft.game;

public class Printer {
    PrinterSettings settings = new PrinterSettings();


    public void printMap(GameMap gameMap) {
        System.out.println("_".repeat(gameMap.getSize() + 2));
        for (int i = 0; i < gameMap.getSize(); i++) {
            System.out.print('|');
            for (int j = 0; j < gameMap.getSize(); j++) {
                int  gameObject = gameMap.getData()[j][i];
                System.out.print(charObject(gameObject));
            }
            System.out.println('|');
        }
        System.out.println("_".repeat(gameMap.getSize() + 2));
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
