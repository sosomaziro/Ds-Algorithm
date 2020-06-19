package com.maziro.tree;

public class Node {

    public Node left;
    public Node right;
    public int value;
    public int height;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 0;
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
