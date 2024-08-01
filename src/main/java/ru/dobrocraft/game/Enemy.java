package ru.dobrocraft.game;

import lombok.Getter;

@Getter
public class Enemy implements Move {
    Position position;

    public Enemy(int x, int y) {
        position = new Position(x, y);
    }

    public Enemy(Position position) {
        this.position = position;
    }

    public Boolean move(Direction direction, GameMap gameMap) {
        int newX = position.getX();
        int newY = position.getY();
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
            return false;
        }
        if (gameMap.getData()[newX][newY] == GameObject.EMPTY.getValue()) {
            gameMap.getData()[position.getX()][position.getY()] = GameObject.EMPTY.getValue();
            gameMap.getData()[newX][newY] = GameObject.ENEMY.getValue();
            position.setPosition(newX, newY);
            return true;
        }
        return false;
    }
}