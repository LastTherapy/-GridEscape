package ru.dobrocraft;

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

    public static  class Builder {
        private int size = 30;
        private int walls = 1;
        private int enemies = 1;

        public Builder setSize(int size) {
            this.size = size;
            return this;
        }
        public Builder setWalls(int walls) {
            this.walls = walls;
            return this;
        }
        public Builder setEnemies(int enemies) {
            this.enemies = enemies;
            return this;
        }

        public GameMap build() {
            GameMap gameMap = new GameMap(this);
            gameMap.clear();
            gameMap.goalGenerate();
            gameMap.playerGenerate();
            WallGenerator.getWallGenerator(gameMap).generateWall(walls);
            gameMap.enemyGenerate(enemies);
            return gameMap;
        }
    }

    private GameMap(Builder builder) {
        this.size = builder.size;
        this.data = new int[builder.size][builder.size];
        this.enemies = new Enemy[builder.enemies];
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
