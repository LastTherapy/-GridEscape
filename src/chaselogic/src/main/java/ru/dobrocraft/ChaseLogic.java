package main.java.ru.dobrocraft;

import java.util.*;
public class ChaseLogic {

    private static final int[] directionRows = {-1, 1, 0, 0};
    private static final int[] directionColumns = {0, 0, -1, 1};

    public List<Node> findPath(Node chaser, Node goal, char[][] gameMap) {
        PriorityQueue<Node> openList = new PriorityQueue<>(new NodeComparator());
        Set<Node> closedList = new HashSet<>();
        openList.add(chaser);

        Map<String, Integer> gScores = new HashMap<>();
        gScores.put(getXYKey(chaser), 0);

        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();

            if (currentNode.getX() == goal.getX() && currentNode.getY() == goal.getY()) {
                return constructPath(currentNode);
            }
            closedList.add(currentNode);

            for (int i = 0; i < 4; i++) {
                int newRow = currentNode.getX() + directionRows[i];
                int newCol = currentNode.getY() + directionColumns[i];
                String neighbourKey = newRow + "," + newCol;

                if (isValidPosition(newRow, newCol, gameMap) && !closedList.contains(neighbourNode)) {
                    int tentativeGScore = gScores.get(neighbourKey) + 1;
                    if (tentativeGScore < gScores.getOrDefault(neighbourKey, Integer.MAX_VALUE)) {
                        int h = formula(newRow, goal.getX(), newCol, goal.getY());
                        Node neighbourNode = new Node(newRow, newCol, currentNode, tentativeGScore, h);
                        gScores.put(neighbourKey, tentativeGScore);
                        if (!openList.contains(neighbourNode)) {
                            openList.add(neighbourNode);
                        }
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    public int formula (int x1, int x2, int y1, int y2) {
        // 1 - chaser, 2 - goal
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    // TO DO: поправить валидные объекты для врага и игрока
    private boolean isValidPosition(int row, int col, char[][] gameMap) {
        return row >= 0 && row < gameMap.length && col >= 0 && col < gameMap.length &&
                gameMap[row][col] != GameObject.WALL.getValue() &&
                gameMap[row][col] != GameObject.ENEMY.getValue();
    }

    private List<Node> constructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    } // возможно надо возвращать шаг, а не путь

    private String getXYKey(Node node) {
        return node.getX() + "," + node.getY();
    }
}