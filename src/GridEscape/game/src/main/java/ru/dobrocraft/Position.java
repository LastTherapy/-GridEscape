package ru.dobrocraft;

import java.util.Random;
import lombok.*;

@Getter
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position generateRandomPosition(int size) {
        Random random = new Random();
        return new Position(random.nextInt(size), random.nextInt(size));
    }

    public boolean equals(Position position) {
        return this.x == position.getX() && this.y == position.getY();
    }
}
