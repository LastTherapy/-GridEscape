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
        // принтер обновляет экран все время через м ногопоточность (чтобы не блокировался ввод)
        // если у  нас девелоп режим то вместо этого мы каждый раз вызываем метод  printMap() вручную
        // метод апдейт перерисовывает карту
//        Thread printerThread = new Thread(printer);
//        printerThread.start();

        }
    }
