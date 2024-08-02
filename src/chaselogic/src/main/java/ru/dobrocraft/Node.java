package main.java.ru.dobrocraft;

import lombok.Getter;
@Getter
public class Node {
    private Position position;
    private int g; // стоимость пути от старта
    private int h; // стоимость пути до цели
    private int f; // g + h
    private Node parent;

    public Node(Position position, Node parent, int g, int h) {
        this.position = position;
        this.g = g;
        this.h = h;
        this.f = g + h;
        this.parent = parent;
    }

    class NodeComparator implements java.util.Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return Integer.compare(n1.getF(), n2.getF());
        }
    }
}
