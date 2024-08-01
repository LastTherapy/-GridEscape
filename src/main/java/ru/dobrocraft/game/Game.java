package ru.dobrocraft.game;

import com.beust.jcommander.JCommander;
import java.util.Scanner;

public class Game {
    private Boolean isGameOver = false;
    public static void main(String[] args) {
        // сделать экземпляр игры и старт
        GameConfig gameConfig = new GameConfig();
        JCommander.newBuilder().addObject(gameConfig).build().parse(args);
        int size = gameConfig.getFieldSize();
        int enemiesCount = gameConfig.getEnemiesCount();
        int wallsCount = gameConfig.getWallsCount();
        GameMap gameMap = new GameMap(size);
        gameMap.generateMap(wallsCount, enemiesCount);
        Printer printer = new Printer();
        printer.printMap(gameMap);
        Scanner scanner = new Scanner(System.in);

        InputController inputController = new InputController();
        while (!isGameOver) {
            String input = scanner.nextLine();
            if (input.equals("q")) {
                isGameOver = true;
            }
            Direction direction = inputController.getDirectionFromInput(input);
            if (direction != null) {
                //gameMap.smth(direction, gameMap); // Я вижу у тебя отрисовку карты с нуля, но не вижжу её перерисовку
                printer.printMap(gameMap);
            }


        }
    }
}
