package ru.dobrocraft.game;

import ru.dobrocraft.game.GameMap;
import ru.dobrocraft.game.Movable;
import ru.dobrocraft.game.Direction;

import lombok.Getter;
@Getter
public class Player implements Movable {
    private int x;
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(Direction direction, GameMap gameMap) {
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
        if (gameMap.getData()[newX][newY] != GameObject.WALL.getValue() && gameMap.getData()[newX][newY] != GameObject.ENEMY.getValue()) {
            if (newX < 0 || newX >= gameMap.getSize() || newY < 0 || newY >= gameMap.getSize()) {
                return;
            }
            gameMap.getData()[getX()][getY()] = GameObject.EMPTY.getValue();
            gameMap.getData()[newX][newY] = GameObject.PLAYER.getValue();
            x = newX;
            y = newY;
        }
    }
}
