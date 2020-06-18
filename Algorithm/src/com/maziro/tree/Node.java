package com.maziro.tree;

public class Node {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public Node parent;
    public Node left;
    public Node right;
    public int value;
    public int height;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
    }

    public void swapValue(Node node) {
        if (node != null) {
            value = node.value;
        }
    }

    public int leftOrRight() {
        return this.parent.left == this ? LEFT : RIGHT;
    }

    public void setChild(int side, Node child) {
        if (side == LEFT) {
            this.left = child;
        } else {
            this.right = child;
        }
        if (child != null) {
            child.parent = this;
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                " value=" + value +
                '}';
    }
}
