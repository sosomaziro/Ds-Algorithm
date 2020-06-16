package com.maziro.tree;

import com.maziro.sort.SortDemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySortTree {

    public static void main(String[] args) {
        int[] a = SortDemo.getRandomArray(10);
        BinarySortTree tree = new BinarySortTree();
        a[0] = 5;
        for (int i : a) {
            tree.add(i);
        }
        Node node = tree.find(5);
        System.out.println(node);

        tree.printTree();
        tree.delete(5);
        tree.printTree();
    }

    // 根节点
    private Node root;
    // 节点数量
    private long count;

    public BinarySortTree() {
    }

    public BinarySortTree(int root) {
        this.root = new Node(root);
        count = 1;
    }

    public BinarySortTree(int[] arr) {
        for (int value : arr) {
            add(value);
        }
    }

    public long getCount() {
        return this.count;
    }

    /**
     * 新增节点
     */
    public void add(int value) {
        if (root == null) {
            root = new Node(value, null, 0);
            count++;
        } else {
            addNode(root, value);
        }
    }

    private void addNode(Node node, int value) {
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value, node, Node.LEFT);
            } else {
                addNode(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value, node, Node.RIGHT);
            } else {
                addNode(node.right, value);
            }
        }
    }

    /**
     * 查询节点
     * 不存在返回null
     */
    public Node find(int value) {
        return findNode(root, value);
    }

    private Node findNode(Node node, int value) {
        if (node == null) {
            return null;
        } else if (node.value == value) {
            return node;
        } else if (value < node.value) {
            return findNode(node.left, value);
        } else {
            return findNode(node.right, value);
        }
    }

    /**
     * 遍历打印
     */
    public void printTree() {
        System.out.println("count = " + count);
        printNode(root);
        printAsLevel();
    }

    private void printNode(Node node) {
        if (node != null) {
            printNode(node.left);
            System.out.println("| " + node.value + " |");
            printNode(node.right);
        }
    }

    private void printAsLevel() {
        int level = 1;
        List<Node> nodes = new ArrayList<>();
        while (true) {
            if (level == 1) {
                nodes.add(root);
            } else {
                List<Node> temp = new ArrayList<>();
                nodes.forEach(n -> {
                    if (n.left != null) {
                        temp.add(n.left);
                    }
                    if (n.right != null) {
                        temp.add(n.right);
                    }
                });
                if (temp.size() < 1) {
                    break;
                }
                nodes = temp;
            }
            Integer[] ns = nodes.stream().map(n -> n.value).collect(Collectors.toList()).toArray(new Integer[nodes.size()]);
            System.out.println("第" + level + "层");
            System.out.println(Arrays.toString(ns));
            level++;
        }
    }

    /**
     * 删除节点
     */
    public void delete(int value) {
        Node node = find(value);
        delete(node);
    }


    public void delete(Node node) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            // 两边子节点都为空 直接删除节点
            if (node.parent == null) {
                root = null;
            } else if (node.parentSide == Node.LEFT) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        } else if (node.left != null && node.right != null) {
            // 交换当前节点 和他的右子节点分支的最左节点
            Node left = findLeft(node.right);
            node.swapValue(left);
            // 删除被替换节点
            delete(left);
        } else {
            if (node.left != null) {
                if (root == node) {
                    root = node.left;
                } else if (node.parentSide == Node.LEFT) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
            } else {
                if (root == node) {
                    root = node.right;
                } else if (node.parentSide == Node.LEFT) {
                    node.parent.left = node.right;
                } else {
                    node.parent.right = node.right;
                }
            }
        }
    }

    private Node findLeft(Node node) {
        if (node.left == null) {
            return node;
        }
        return findLeft(node.left);
    }
}
