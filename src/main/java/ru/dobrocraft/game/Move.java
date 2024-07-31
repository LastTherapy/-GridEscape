package ru.dobrocraft.game;

public interface Move {
    public void move(Direction direction, GameMap gameMap);
    int getX();
    int getY();
}