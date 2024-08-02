package main.java.ru.dobrocraft;

import java.util.*;
public class ChaseLogic {

    private static final int[] directionRows = {-1, 0, 1, 0};
    private static final int[] directionColumns = {0, 1, 0, -1};

    public List<Position> findPath(Position cheser, Position goal, GameMap gameMap) {
        //аз есмь умный код
    }

    public int formula (Position cheser, Position goal) {
        return Math.abs(cheser.getX() - goal.getX()) + Math.abs(cheser.getY() - goal.getY());
    }

    // TO DO: поправить валидные объекты для врага и игрока
    private boolean isValidPosition(Position position, GameMap gameMap) {
        int row = position.getX();
        int col = position.getY();
        return row >= 0 && row < gameMap.getSize() && col >= 0 && col < gameMap.getSize() &&
                gameMap.getData()[row][col] != GameObject.WALL.getValue() &&
                gameMap.getData()[row][col] != GameObject.ENEMY.getValue();
    }

    private List<Position> constructPath(Node node) {
        List<Position> path = new ArrayList<>();
        while (node != null) {
            path.add(node.getPosition());
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    } // возможно надо возвращать шаг, а не путь

}