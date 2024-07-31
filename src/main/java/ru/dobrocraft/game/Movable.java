package ru.dobrocraft.game;

public interface Movable {
    public int move(Direction direction, GameMap gameMap);
    int getX();
    int getY();
}
