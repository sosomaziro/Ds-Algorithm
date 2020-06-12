package com.maziro.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 比较排序
 *
 * 非比较排序
 */
public class SortDemo {
    public static void main(String[] args) {
        long start;
        int[] a = getRandomArray(200);
//        printArray(a);


        start = System.currentTimeMillis();
        SortAlgorithm.shellSort(a);
        System.out.println("希尔排序---运行时间:" + (System.currentTimeMillis() - start));

//        start = System.currentTimeMillis();
//        SortAlgorithm.insertSort(a);
//        System.out.println("插入排序---运行时间:" + (System.currentTimeMillis() - start));

//        start = System.currentTimeMillis();
//        SortAlgorithm.selectSort(a);
//        System.out.println("选择排序---运行时间:" + (System.currentTimeMillis() - start));

//        start = System.currentTimeMillis();
//        SortAlgorithm.bubbleSort(a);
//        System.out.println("冒泡排序---运行时间:" + (System.currentTimeMillis() - start));
        printArray(a);
    }

    /**
     * 打印
     *
     * @param a
     */
    public static void printArray(int[] a) {
        System.out.println(Arrays.toString(a));
    }

    /**
     * 获取随机数组
     *
     * @param length
     * @return
     */
    public static int[] getRandomArray(int length) {
        int[] a = new int[length];
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(length * 10);
        }
        return a;
    }
}
