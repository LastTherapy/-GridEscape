package ru.dobrocraft.game;

public interface Movable {
    public void move(Direction direction, GameMap gameMap);
    int getX();
    int getY();
}
