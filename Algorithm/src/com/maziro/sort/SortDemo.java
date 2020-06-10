package com.maziro.sort;

import java.util.Arrays;
import java.util.Random;

public class SortDemo {
    public static void main(String[] args) {
        long start;
        int[] a = getRandomArray(50000);
        printArray(a);

        start = System.currentTimeMillis();
        SortAlgorithm.selectSort(a);
        System.out.println("选择排序---运行时间:" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        SortAlgorithm.bubbleSort(a);
        System.out.println("冒泡排序---运行时间:" + (System.currentTimeMillis() - start));
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
