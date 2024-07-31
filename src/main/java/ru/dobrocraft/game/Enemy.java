package ru.dobrocraft.game;

import ru.dobrocraft.game.GameMap;
import ru.dobrocraft.game.Move;
import ru.dobrocraft.game.Direction;
import ru.dobrocraft.game.Position;

import lombok.Getter;

@Getter
public class Enemy implements Move {
    Position position;
    public Enemy(int x, int y) {
        position = new Position(x, y);
    }

    public void move(Direction direction, GameMap gameMap) {
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
        if (gameMap.getData()[newX][newY] == GameObject.EMPTY.getValue()) {
            if (newX < 0 || newX >= gameMap.getSize() || newY < 0 || newY >= gameMap.getSize()) {
                return;
            }
            if (newX == gameMap.getGoalX() && newY == gameMap.getGoalY()) {

                return;
            }
            gameMap.getData()[position.getX()][position.getY()] = GameObject.EMPTY.getValue();
            gameMap.getData()[newX][newY] = GameObject.ENEMY.getValue();
             position.setPosition(newX, newY);
        }
    }
}