package ru.dobrocraft.game;

public class InputController {
    public static Direction getDirectionFromInput(String input) {
        switch (input.toUpperCase()) {
            case "W":
                return Direction.UP;
            case "A":
                return Direction.LEFT;
            case "S":
                return Direction.DOWN;
            case "D":
                return Direction.RIGHT;
            default:
                System.out.println("Некорректное направление. Используйте WASD.");
                return null;
        }
    }
}
