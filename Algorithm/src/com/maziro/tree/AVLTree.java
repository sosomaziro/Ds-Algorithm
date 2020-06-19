package com.maziro.tree;

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
        } else {
            remove(node.right, node, value);
        }
        balanceCheck(node);
    }

    private void remove(Node node, Node parent) {
        if (root.left == null && root.right == null) {
            size--;
            root = null;
        } else if (node.left != null && node.right != null) {
            Node min = getMin(node.right);
            int value = min.value;
            remove(min, getParent(node, min));
            node.value = value;
        } else {
            size--;
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
