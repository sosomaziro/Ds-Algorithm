package com.maziro.tree;

import com.maziro.sort.SortDemo;

/**
 * inventors
 * Adelson-Velsky and Landis
 * AVL算法实现
 */
public class AVLTree {
    public static void main(String[] args) {
//        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[] a = SortDemo.getRandomArray(20000);
        AVLTree tree = new AVLTree();
        for (int i : a) {
            tree.add(i);
        }
        System.out.println("root = " + tree.root);
        tree.remove(13);
        tree.remove(12);
        tree.remove(14);
        tree.remove(10);
        tree.remove(11);
        tree.remove(16);
        tree.remove(15);
        tree.remove(9);
        System.out.println("root = " + tree.root);
        System.out.println("height = " + tree.getHeight(tree.root));
        System.out.println("left = " + tree.getHeight(tree.root.left));
        System.out.println("right = " + tree.getHeight(tree.root.right));
    }

    Node root;
    long size;

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
    public void add(int value) {
        size++;
        add(root, value);
    }

    private void add(Node node, int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        if (node.value == value) {
            size--;
            return;
        } else if (node.value > value) {
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                add(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                add(node.right, value);
            }
        }
        balanceCheck(node);
    }

    private void balanceCheck(Node node) {
        if (node == null) {
            return;
        }
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (node.left != null && getHeight(node.left.right) > getHeight(node.left.left)) {
                // 左旋 node.left
                leftRotate(node.left);
            }
            // 右旋 node
            rightRotate(node);
        } else if (balanceFactor < -1) {
            if (node.right != null && getHeight(node.right.left) > getHeight(node.right.right)) {
                // 右旋 node.right
                rightRotate(node.right);
            }
            // 左旋 node
            leftRotate(node);
        }
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
        Node toLeft = new Node(node.value);
        toLeft.left = node.left;
        node.left = toLeft;
        if (node.right != null) {
            toLeft.right = node.right.left;
            node.value = node.right.value;
            node.right = node.right.right;
        }
    }

    /**
     * 右旋
     *
     * @param node
     */
    public void rightRotate(Node node) {
        Node toRight = new Node(node.value);
        toRight.right = node.right;
        node.right = toRight;
        if (node.left != null) {
            toRight.left = node.left.right;
            node.value = node.left.value;
            node.left = node.left.left;
        }
    }

    /**
     * 删除
     */
    public void remove(int value) {
        remove(root, null, value);
    }

    private void remove(Node node, Node parent, int value) {
        if (node == null) {
            return;
        }
        if (node.value > value) {
            remove(node.left, node, value);
        } else if (node.value == value) {
            remove(node, parent);
            size--;
        } else {
            remove(node.right, node, value);
        }
        balanceCheck(node);
    }

    private void remove(Node node, Node parent) {
        if (root.left == null && root.right == null) {
            root = null;
        } else if (node.left != null && node.right != null) {
            Node min = getMin(node.right);
            int value = min.value;
            remove(min, getParent(node, min));
            node.value = value;
        } else {
            if (node.left != null) {
                if (node == root) {
                    root = root.left;
                } else {
                    if (parent.left == node) {
                        parent.left = node.left;
                    } else {
                        parent.right = node.left;
                    }
                }
            } else {
                if (node == root) {
                    root = root.right;
                } else {
                    if (parent.left == node) {
                        parent.left = node.right;
                    } else {
                        parent.right = node.right;
                    }
                }
            }
        }
    }

    /**
     * 获取父节点
     */
    private Node getParent(Node node, Node target) {
        if (node.left == target || node.right == target) {
            return node;
        } else if (node.value > target.value) {
            return getParent(node.left, target);
        } else {
            return getParent(node.right, target);
        }
    }

    private Node getMin(Node node) {
        return node.left == null ? node : getMin(node.left);
    }

}
