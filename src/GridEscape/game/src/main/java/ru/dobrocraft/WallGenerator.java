package ru.dobrocraft;
import java.util.Random;
import lombok.*;
// Абстрактный класс для генерации стен
public abstract class WallGenerator {
    protected GameMap gameMap;

    protected WallGenerator(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public static WallGenerator getWallGenerator(GameMap gameMap) {
        return new RandomLineWallGenerator(gameMap);
    }

    public abstract void generateWall(int wallCount);


    private static class RandomLineWallGenerator extends WallGenerator {

        private final int size;
        Random random = new Random();

        public RandomLineWallGenerator(GameMap gameMap) {
            super(gameMap);
            this.size = gameMap.getSize();
        }

        @Override
        public void generateWall(int wallCount) {
            wallGenerate(wallCount);
        }

        private void wallGenerate(int num) {
            final int chunkMaxSize = size / 2;
            while (num > 0) {
                int currentChunk = Math.min(num, random.nextInt(chunkMaxSize) + 1);
                num -= wallHorizontalGenerate(currentChunk);
                if (num <= 0) break;
                currentChunk = Math.min(num, random.nextInt(chunkMaxSize) + 1);
                num -= wallVerticalGenerate(currentChunk);
            }
        }

        private boolean addBrick(Position position) {
            if (position.getX() < size && position.getY() < size && position.getX() >= 0 && position.getY() >= 0 && gameMap.getData()[position.getX()][position.getY()] == GameObject.EMPTY.getValue()) {
                gameMap.getData()[position.getX()][position.getY()] = GameObject.WALL.getValue();
                return true;
            }
            return false;
        }

        private int wallHorizontalGenerate(int n) {
            Position position = Position.generateRandomPosition(size);
            int direction = Math.random() > 0.5 ? 1 : -1;
            int originalN = n;  // Сохраняем изначальное значение n

            while (n > 0 && addBrick(position)) {
                n--;
                position = new Position(position.getX() + direction, position.getY());

                if (position.getX() < 0 || position.getX() >= size) break;  // Проверка на выход за границы
            }

            return originalN - n;  // Возвращаем количество размещенных кирпичей
        }

        private int wallVerticalGenerate(int n) {
            Position position = Position.generateRandomPosition(size);
            int direction = Math.random() > 0.5 ? 1 : -1;
            int originalN = n;  // Сохраняем изначальное значение n

            while (n > 0 && addBrick(position)) {
                n--;
                position = new Position(position.getX(), position.getY() + direction);

                if (position.getY() < 0 || position.getY() >= size) break;  // Проверка на выход за границы
            }

            return originalN - n;  // Возвращаем количество размещенных кирпичей
        }
    }
}
