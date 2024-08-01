package ru.dobrocraft;

import lombok.Getter;
@Getter
public class Player implements Move {
    private Position position;

    public Player(int x, int y) {
        position = new Position(x, y);
    }

    public Player(Position position) {
        this.position = position;
    }

    @Override
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
        if (gameMap.getData()[newX][newY] != GameObject.WALL.getValue() && gameMap.getData()[newX][newY] != GameObject.ENEMY.getValue()) {
            gameMap.getData()[position.getX()][position.getY()] = GameObject.EMPTY.getValue();
            gameMap.getData()[newX][newY] = GameObject.PLAYER.getValue();
            this.position.setPosition(newX, newY);
            return true;
        }
        return false;
    }
}
