package com.maziro.tree;


import com.maziro.sort.SortDemo;

import java.util.Arrays;
import java.util.HashSet;

public class AVLTreeTest {

    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = { 10, 12, 8, 9, 7, 6 };
//        int[] arr = { 10, 11, 7, 6, 8, 9 };
        long time;
        int[] ori = SortDemo.getRandomArray(30000);
        HashSet<Integer> collect = Arrays.stream(ori).collect(HashSet::new, HashSet::add, HashSet::addAll);
        Integer[] a = new Integer[collect.size()];
        collect.toArray(a);
        int[] r = SortDemo.getRandomArray(20000);

        System.out.println("================================");

        AVLTree tree = new AVLTree();
        time = System.currentTimeMillis();
        for (int i : a) {
            tree.add(i);
        }
        System.out.println("create time:" + (System.currentTimeMillis() - time));
        System.out.println("root = " + tree.root);
        System.out.println("size = " + tree.size);
//        for (int i : r) {
//            tree.remove(i);
//        }
        System.out.println("root = " + tree.root);
        System.out.println("size = " + tree.size);
        System.out.println("left = " + tree.getHeight(tree.root.left));
        System.out.println("right = " + tree.getHeight(tree.root.right));


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
        //遍历
        System.out.println("root = " + avlTree.root.value);
        System.out.println("size = " + avlTree.size);
        System.out.println("left height = " + avlTree.getHeight(avlTree.root.left));
        System.out.println("right height = " + avlTree.getHeight(avlTree.root.right));
    }

}
