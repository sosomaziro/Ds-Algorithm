package com.maziro.tree;

public class Node {

    public Node left;
    public Node right;
    public int value;
    // 插入时计算高度最花时间 所以直接记录
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
