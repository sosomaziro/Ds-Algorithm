package com.maziro.tree;


import com.maziro.sort.SortDemo;

import java.util.Arrays;
import java.util.HashSet;

@SuppressWarnings("ALL")
public class AVLTreeDemo {

    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = { 10, 12, 8, 9, 7, 6 };
//        int[] arr = { 10, 11, 7, 6, 8, 9 };
        int[] ori = SortDemo.getRandomArray(20000);
        HashSet<Integer> collect = Arrays.stream(ori).collect(HashSet::new, HashSet::add, HashSet::addAll);
        Integer[] a = new Integer[collect.size()];
        collect.toArray(a);

        int[] r = SortDemo.getRandomArray(10000);
        AVLTree tree = new AVLTree();
        for (int i : a) {
            tree.add(i);
        }
        System.out.println("root = " + tree.root);
        System.out.println("size = " + tree.size);
        for (int i : r) {
            tree.remove(i);
        }
        System.out.println("root = " + tree.root);
        System.out.println("size = " + tree.size);
        System.out.println("height = " + tree.getHeight(tree.root));
        System.out.println("left = " + tree.getHeight(tree.root.left));
        System.out.println("right = " + tree.getHeight(tree.root.right));


        System.out.println("================================");

        //创建一个 AVLTree对象
        AVLTreeDemo avlTree = new AVLTreeDemo();
        for (int i : a) {
            avlTree.add(i);
        }
        System.out.println("root = " + avlTree.root.e);
        System.out.println("size = " + avlTree.size);
        for (int i : r) {
            avlTree.remove(i);
        }
        //遍历
        System.out.println("root = " + avlTree.root.e);
        System.out.println("size = " + avlTree.size);
        System.out.println("height = " + avlTree.getHeight(avlTree.root));
        System.out.println("left height = " + avlTree.getHeight(avlTree.root.left));
        System.out.println("right height = " + avlTree.getHeight(avlTree.root.right));
    }

    /**
     * AVLTree是BST，所以节点值必须是可比较的
     */
    private class Node {
        public int e;
        public Node left;
        public Node right;
        public int height;

        public Node(int e) {
            this.e = e;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTreeDemo() {
        root = null;
        size = 0;
    }

    //获取某一结点的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取节点的平衡因子
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断树是否为平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactory = Math.abs(getBalanceFactor(node));
        if (balanceFactory > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }


    /**
     * 右旋转
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node t3 = x.right;
        x.right = y;
        y.left = t3;
        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 左旋转
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node t2 = x.left;
        x.left = y;
        y.right = t2;
        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(int e) {
        root = add(root, e);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, int e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e < node.e)
            node.left = add(node.left, e);
        else if (e > node.e)
            node.right = add(node.right, e);
        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1 && getBalanceFactor(node.left) > 0) {
            //右旋LL
            return rightRotate(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) < 0) {
            //左旋RR
            return leftRotate(node);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }


    public int remove(int e) {
        Node node = getNode(root, e);
        if (node != null) {
            root = remove(root, e);
            return node.e;
        }
        return 0;
    }

    public Node getNode(Node node, int e) {
        if (node == null) {
            return null;
        } else if (node.e == e) {
            return node;
        } else if (node.e > e) {
            return getNode(node.left, e);
        } else {
            return getNode(node.right, e);
        }
    }

    private Node minimum(Node node) {
        return node.left == null ? node : minimum(node.left);
    }

    private Node remove(Node node, int e) {
        if (node == null)
            return null;
        Node retNode;
        if (e < node.e) {
            node.left = remove(node.left, e);
            retNode = node;
        } else if (e > node.e) {
            node.right = remove(node.right, e);
            retNode = node;
        } else {   // e.compareTo(node.e) == 0
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            // 待删除节点右子树为空的情况
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 待删除节点左右子树均不为空的情况
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = remove(node.right, successor.e);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }
        if (retNode == null)
            return null;
        //维护平衡
        //更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        //计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            //右旋LL
            return rightRotate(retNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            //左旋RR
            return leftRotate(retNode);
        }
        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            node.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

}
