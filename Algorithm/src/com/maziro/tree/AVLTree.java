package com.maziro.tree;

/**
 * inventors
 * Adelson-Velsky and Landis
 * AVL算法实现
 */
public class AVLTree {
    public static void main(String[] args) {

        AVLTree tree = new AVLTree();
    }

    private Node root;

    public AVLTree() {
    }

    /**
     * 获取节点高度
     *
     * @param node
     * @return
     */
    public int getHeight(Node node) {
        return node == null ? 0 : Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    /**
     * 搜索节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        return search(root, value);
    }

    private Node search(Node node, int value) {
        if (node == null) {
            return null;
        } else if (node.value > value) {
            return search(node.left, value);
        } else if (node.value == value) {
            return node;
        } else {
            return search(node.right, value);
        }
    }

    /**
     * 新增节点
     *
     * @param value
     */
    public Node add(int value) {
        return add(root, value);
    }

    private Node add(Node node, int value) {
        Node result;
        if (node == null) {
            result = null;
        } else if (node.value > value) {
            if (node.left == null) {
                node.left = new Node(value, node);
                result = node.left;
            } else {
                result = add(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value, node);
                result = node.right;
            } else {
                result = add(node.right, value);
            }
        }

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getHeight(node.right.left) > getHeight(node.right.right)) {
                // 右旋 node.right

            }
            // 左旋 node

        } else if (balanceFactor < -1) {
            if (getHeight(node.left.right) > getHeight(node.left.left)) {
                // 左旋 node.left

            }
            // 右旋 node

        }
        return result;
    }

    private int getBalanceFactor(Node node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 左旋
     *
     * @param node
     */
    public void leftRotate(Node node) {
        Node toLeft = new Node(node.value, node);
        node.left.parent = toLeft;
        toLeft.left = node.left;

        if (node.right.left != null) {
            node.right.left.parent = toLeft;
            toLeft.left = node.right.left;
        }
        if (node.right.right != null) {
            node.right = node.right.right;
            node.right.right.parent = node;
        }
    }

    /**
     * 右旋
     *
     * @param node
     */
    public void rightRotate(Node node) {

    }

    /**
     * 删除
     */
    public void remove(int value) {

    }

}
