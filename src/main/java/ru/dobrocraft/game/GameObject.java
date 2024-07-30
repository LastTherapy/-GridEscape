package ru.dobrocraft.game;

public enum GameObject {
    PLAYER(0),
    ENEMY(1),
    WALL(2),
    GOAL(3),
    EMPTY(4);

    final int value;


    private GameObject(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
