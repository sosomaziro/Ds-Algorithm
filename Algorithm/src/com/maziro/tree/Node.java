package com.maziro.tree;

public class Node {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public Node parent;
    public Node left;
    public Node right;
    public int value;
    public int parentSide; // 父节点左节点 0 有节点 1

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node parent, int parentSide) {
        this.value = value;
        this.parent = parent;
        this.parentSide = parentSide;
    }

    public void swapValue(Node node) {
        if (node != null) {
            value = node.value;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                " value=" + value +
                '}';
    }
}
