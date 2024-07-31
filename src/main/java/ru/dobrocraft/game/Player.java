package ru.dobrocraft.game;

import ru.dobrocraft.game.GameMap;
import ru.dobrocraft.game.Movable;
import ru.dobrocraft.game.Direction;

import lombok.Getter;

public class Player implements Movable {

    @lombok.Getter
    private int x;

    @lombok.Getter
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int move(Direction direction, GameMap gameMap) {
        int newX = getX();
        int newY = getY();
        switch (direction) {
            case LEFT:
                newX--;
                break;
            case UP:
                newY--;
                break;
            case RIGHT:
                newX++;
                break;
            case DOWN:
                newY++;
                break;
        }
        if (newX < 0 || newX >= gameMap.getSize() || newY < 0 || newY >= gameMap.getSize()) {
            return getX();
        }
        if (gameMap.data[newX][newY] == GameObject.WALL.getValue() || gameMap.data[newX][newY] == GameObject.ENEMY.getValue()) {
            return getX();
        }
        return newX;
    }
}
