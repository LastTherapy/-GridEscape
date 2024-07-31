package ru.dobrocraft.game;

import com.beust.jcommander.JCommander;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        GameConfig gameConfig = new GameConfig();
        JCommander.newBuilder().addObject(gameConfig).build().parse(args);
        int size = gameConfig.getFieldSize();
        int enemiesCount = gameConfig.getEnemiesCount();
        int wallsCount = gameConfig.getWallsCount();
        GameMap gameMap = new GameMap(size);
        Printer printer = new Printer();
        printer.printMap(gameMap);
    }
}
