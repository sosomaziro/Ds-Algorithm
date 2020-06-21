package com.maziro.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * inventors
 * Adelson-Velsky and Landis
 * AVL算法实现
 */
public class AVLTree {

    Node root;
    int size;

    public AVLTree() {
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
    public void add(int value) {
        root = add(root, value);
    }

    private Node add(Node node, int value) {
        if (node == null) {
            size++;
            return new Node(value);
        } else if (node.value > value) {
            node.left = add(node.left, value);
        } else if (node.value < value) {
            node.right = add(node.right, value);
        }
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        return balanceCheck(node);
    }

    //获取某一结点的高度
    int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private Node balanceCheck(Node node) {
        if (node == null) {
            return node;
        }
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                // 左旋 node.left
                node.left = leftRotate(node.left);
            }
            // 右旋 node
            return rightRotate(node);
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                // 右旋 node.right
                node.right = rightRotate(node.right);
            }
            // 左旋 node
            return leftRotate(node);
        }
        return node;
    }

    private int getBalanceFactor(Node node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 左旋
     *
     * @param node
     */
    public Node leftRotate(Node t) {
        Node r = t.right;
        Node rl = r.left;
        r.left = t;
        t.right = rl;
        updateHeight(t);
        updateHeight(r);
        return r;
    }

    private void updateHeight(Node node) {
        if (node != null)
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    /**
     * 右旋
     *
     * @param node
     */
    public Node rightRotate(Node t) {
        Node l = t.left;
        Node lr = l.right;
        l.right = t;
        t.left = lr;
        updateHeight(t);
        updateHeight(l);
        return l;
    }

    /**
     * 删除
     */
    public void remove(int value) {
        Node target = search(value);
        if (target != null) {
            size--;
            root = remove(root, target);
        }
    }

    private Node remove(Node node, Node target) {
        if (node == null) {
            return null;
        }
        if (node == target) {
            node = doRemove(node);
//            updateHeight(node);
        } else if (node.value > target.value) {
            node.left = remove(node.left, target);
        } else {
            node.right = remove(node.right, target);
        }
        updateHeight(node);
        return balanceCheck(node);
    }

    private Node doRemove(Node node) {
        if(node.left == null && node.right == null) {
            return null;
        } else if (node.left != null && node.right != null) {
            Node min = getMin(node.right);
            int value = min.value;
            node.right = remove(node.right, min);
            node.value = value;
            return node;
        } else {
            if (node.left != null) {
                return node.left;
            } else {
                return node.right;
            }
        }
    }

    private Node getMin(Node node) {
        return node.left == null ? node : getMin(node.left);
    }

    /**
     * 遍历
     */
    public List<Integer> getAll() {
        List<Integer> all = new ArrayList<>(size);
        getAll(root, all);
        return all;
    }

    public void getAll(Node node, List<Integer> all) {
        if (node != null) {
            getAll(node.left, all);
            all.add(node.value);
            getAll(node.right, all);
        }
    }

}
