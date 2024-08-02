package main.java.ru.dobrocraft;

import java.util.Random;
import lombok.Getter;

@Getter
public class GameMap {

    final private int size;
    private int[][] data;
    private Position goalPosition;
    private Position playerPosition;
    private Enemy[] enemies;
    Random random = new Random();

    public GameMap(int size) {
        this.size = size;
        this.data = new int[size][size];
        this.clear();
        this.goalGenerate();
        this.playerGenerate();
        this.wallGenerate(30);
        this.enemyGenerate(10);
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                data[i][j] = GameObject.EMPTY.getValue();
            }
        }
    }

    public void generateMap(int walls, int enemies) {
        if (walls + enemies + 2 > size * size) {
            throw new IllegalParametersException("Количество препятствий превышает размер поля");
        }
        clear();
        goalGenerate();
        playerGenerate();
        wallGenerate(walls);
        enemyGenerate(enemies);
    }

    private void goalGenerate() {
        int x = random.nextInt(size);
        int y = random.nextInt(size / 2);
        if (data[x][y] == GameObject.EMPTY.getValue()) {
            data[x][y] = GameObject.GOAL.getValue();
            goalPosition = new Position(x, y);
        } else {
            goalGenerate();
        }
    }

    private void playerGenerate() {
        int x = random.nextInt(size);
        int y = random.nextInt(size / 2) + size / 2;
        if (data[x][y] == GameObject.EMPTY.getValue()) {
            data[x][y] = GameObject.PLAYER.getValue();
            playerPosition = new Position(x, y);
        } else {
            playerGenerate();
        }
    }

    private void wallGenerate(int num) {
        final int chunkMaxSize = size / 2;
        while (num > 0) {
            int currentChunk = Math.min(num, random.nextInt(chunkMaxSize) + 1);
            num -= currentChunk;
            num += wallHorizontalGenerate(currentChunk);
            currentChunk = Math.min(num, random.nextInt(chunkMaxSize) + 1);
            num -= currentChunk;
            num += wallVerticalGenerate(random.nextInt(chunkMaxSize));
        }

    }


    private boolean addBrick(Position position) {
        if (position.getX() < size && position.getY() < size && position.getX() >= 0 && position.getY() >= 0 &&
                data[position.getX()][position.getY()] == GameObject.EMPTY.getValue()) {
            data[position.getX()][position.getY()] = GameObject.WALL.getValue();
            return true;
        }
        return false;
    }


    private int wallHorizontalGenerate(int n) {
        Position position = Position.generateRandomPosition(size);
        int direction = Math.random() > 0.5 ? 1 : -1;
        while (addBrick(position) && n > 0) {
                    n--;
                    position = new Position(position.getX() + direction, position.getY());
                }
        return n;
    }

    private int wallVerticalGenerate(int n) {
        Position position = Position.generateRandomPosition(size);
        int direction = Math.random() > 0.5 ? 1 : -1;
        while (addBrick(position) && n > 0) {
                    n--;
                    position = new Position(position.getX(), position.getY() + direction);
                }
        return n;
    }

    private void enemyGenerate(int num) {
        enemies = new Enemy[num];
        for (int i = 0; i < num; i++) {
            Position position = Position.generateRandomPosition(size);
            while (data[position.getX()][position.getY()] != GameObject.EMPTY.getValue()) {
                position = Position.generateRandomPosition(size);
            }
            data[position.getX()][position.getY()] = GameObject.ENEMY.getValue();
            enemies[i] = new Enemy(position);
        }
    }
}
