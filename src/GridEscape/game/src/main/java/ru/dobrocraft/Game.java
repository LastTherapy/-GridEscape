package ru.dobrocraft;

import com.beust.jcommander.JCommander;
import java.util.Scanner;

public class Game {
    private Boolean isGameOver = false;
    private Boolean isDevelop = false;

    public static void main(String[] args) {
        // сделать экземпляр игры и старт
        GameConfig gameConfig = new GameConfig();
        JCommander.newBuilder().addObject(gameConfig).build().parse(args);
        int size = gameConfig.getFieldSize();
        int enemiesCount = gameConfig.getEnemiesCount();
        int wallsCount = gameConfig.getWallsCount();
//        GameMap gameMap = new GameMap(size);
//        gameMap.generateMap(wallsCount, enemiesCount);
        GameMap gameMap = new GameMap.Builder()
                .setEnemies(enemiesCount)
                .setWalls(wallsCount)
                .setSize(size).build();

        Printer printer = new Printer(gameMap);
        printer.printMap(gameMap);
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        while (!game.isGameOver) {
            String command = scanner.nextLine().toUpperCase();
            if (command.equals("Q")) {
                break;
            }
            Direction direction = InputController.getDirectionFromInput(command);
            if (direction == null) {
                continue;
            }
            gameMap.getPlayer().move(direction, gameMap);
            printer.update();
        }
        }
    }
