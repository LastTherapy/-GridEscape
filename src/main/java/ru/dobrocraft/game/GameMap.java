package ru.dobrocraft.game;
import ru.dobrocraft.game.GameObject;
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
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
               data[i][j] = GameObject.EMPTY.getValue();
            }
        }
    }

    private void goalGenerate() {
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        data[x][y] = GameObject.GOAL.getValue();
        goalPosition = new Position(x, y);
    }

    private void playerGenerate() {
        int x = random.nextInt(size);
        int y = random.nextInt(size);
        data[x][y] = GameObject.PLAYER.getValue();
        playerPosition = new Position(x, y);

    }

    private void wallGenerate(int num) {

    }

    public void printMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(data[i][j]);
            }
            System.out.println();
        }
    }
}
