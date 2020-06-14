package com.maziro.sort;

import java.util.Arrays;
import java.util.Random;

/**
 复杂度
            最坏      最好       稳定
 冒泡        n^2       n           Y
 选择        n^2       n^2         N
 插入        n^2       n           Y
 快速        n^2       nlogn       N
 希尔        nlogn     nlogn       N
 归并        nlogn     nlogn       Y
 计数        n k       n k         N
 桶          n k       n k         N
 基数        n k       n k         N
 堆          nlogn     nlogn       N
 */

public class SortDemo {
    public static void main(String[] args) {
        long start;
        int[] a = getRandomArray(200000);
//        printArray(a);
        start = System.currentTimeMillis();

//        SortAlgorithm.radixSort(a, 9);
//        System.out.println("基数排序---运行时间:" + (System.currentTimeMillis() - start));

//        SortAlgorithm.quickSort(a);
//        System.out.println("快速排序---运行时间:" + (System.currentTimeMillis() - start));

//        SortAlgorithm.mergeSort(a);
//        System.out.println("归并排序---运行时间:" + (System.currentTimeMillis() - start));

//        SortAlgorithm.shellSort(a);
//        System.out.println("希尔排序---运行时间:" + (System.currentTimeMillis() - start));

//        SortAlgorithm.insertSort(a);
//        System.out.println("插入排序---运行时间:" + (System.currentTimeMillis() - start));

        SortAlgorithm.selectSort(a);
        System.out.println("选择排序---运行时间:" + (System.currentTimeMillis() - start));

//        SortAlgorithm.bubbleSort(a);
//        System.out.println("冒泡排序---运行时间:" + (System.currentTimeMillis() - start));

//        printArray(a);
        check(a);
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
            a[i] = random.nextInt(length);
        }
        return a;
    }

    public static void check(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                throw new RuntimeException("error:" + i);
            }
        }
    }
}
