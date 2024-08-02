package ru.dobrocraft;

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
        Printer printer = new Printer(gameMap);
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        // принтер обновляет экран все время через м ногопоточность (чтобы не блокировался ввод)
        // если у  нас девелоп режим то вместо этого мы каждый раз вызываем метод  printMap() вручную
        // метод апдейт перерисовывает карту
        Thread printerThread = new Thread(printer);
        printerThread.start();

        InputController inputController = new InputController();
        while (!game.isGameOver) {
            String input = scanner.nextLine();
            if (input.equals("q")) {
                game.isGameOver = true;
            }
            Direction direction = inputController.getDirectionFromInput(input);
            if (direction != null) {
                //gameMap.smth(direction, gameMap); // Я вижу у тебя отрисовку карты с нуля, но не вижжу её перерисовку
                printer.printMap(gameMap);
            }
        }
    }
}
