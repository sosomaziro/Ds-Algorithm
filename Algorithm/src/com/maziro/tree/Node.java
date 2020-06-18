package com.maziro.tree;

public class Node {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public Node left;
    public Node right;
    public int value;
    public int height;

    public Node(int value) {
        this.value = value;
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
