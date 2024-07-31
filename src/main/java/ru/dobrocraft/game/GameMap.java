package ru.dobrocraft.game;
import ru.dobrocraft.game.GameObject;
import lombok.Getter;

@Getter
public class GameMap {

    final private int size;
    private int[][] data;


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
