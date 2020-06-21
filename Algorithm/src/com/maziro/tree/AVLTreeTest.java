package com.maziro.tree;


import com.maziro.sort.SortDemo;

import java.util.List;

public class AVLTreeTest {

    public static void main(String[] args) {
        int[] a = SortDemo.getRandomArray(30);
//        int[] a = {2,18,19,29,16,11,26,17,28,27,2,22,9,0,18,25,25,6,5,9,0,4,7,24,17,15,19,3,0,28};
//        int[] a = {1,2,3};
        int[] r = SortDemo.getRandomArray(20);
//        int[] r ={2,14,10,9,11,11,10,12,2,12,0,18,3,19,19,15,11,8,18,13};

        RBTree rbTree = new RBTree();
        for (int i : r) {
            rbTree.insert(i);
            System.out.println(i);
        }
    }

    private void avlTreeTest(int[] a, int[] r) {

        long time;
        System.out.println("================================");

        //创建一个 AVLTree对象
        AVLTreeStandard avlTree = new AVLTreeStandard();
        time = System.currentTimeMillis();
        for (int i : a) {
            avlTree.add(i);
        }
        System.out.println("create time:" + (System.currentTimeMillis() - time));
        System.out.println("root = " + avlTree.root.value);
        System.out.println("size = " + avlTree.size);

        for (int i : r) {
            avlTree.remove(i);
        }
        System.out.println("root = " + avlTree.root.value);
        System.out.println("size = " + avlTree.size);

        System.out.println("left height = " + avlTree.getHeight(avlTree.root.left));
        System.out.println("right height = " + avlTree.getHeight(avlTree.root.right));

        System.out.println("================================");

        AVLTree tree = new AVLTree();
        time = System.currentTimeMillis();
        for (int i : a) {
            tree.add(i);
        }
        System.out.println("create time:" + (System.currentTimeMillis() - time));
        System.out.println("root = " + tree.root.value);
        System.out.println("size = " + tree.size);

        for (int i : r) {
            tree.remove(i);
        }
        System.out.println("root = " + tree.root.value);
        System.out.println("size = " + tree.size);

        System.out.println("left = " + tree.getHeight(tree.root.left));
        System.out.println("right = " + tree.getHeight(tree.root.right));

        List<Integer> all = avlTree.getAll();
        System.out.println("all = " + all.size());

        List<Integer> all2 = tree.getAll();
        System.out.println("all2 = " + all2.size());

        System.out.println(all.equals(all2));
    }

}
