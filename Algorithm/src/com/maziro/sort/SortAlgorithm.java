package com.maziro.sort;

/**
 * 2020-06
 */
public class SortAlgorithm {

    /**
     * 冒泡
     * O(n^2)
     */
    public static void bubbleSort(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        boolean swap; // 是否进行了交换标识
        for (int i = 0; i < array.length - 1; i++) {
            swap = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap = true;
                    swapArray(array, j, j + 1);
                }
            }
            if (!swap) {
                break;
            }
        }
    }

    /**
     * 选择
     * O(n^2)
     */
    public static void selectSort(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        for (int i = 0; i < array.length - 1; i ++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swapArray(array, i, j);
                }
            }
        }
    }

    /**
     * 插入
     * O(n^(1~2))
     */
    public static void insertSort(int[] array) {
        if (array == null || array.length < 1) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
//                if (array[i])
            }
        }
    }

    private static void swapArray(int[] a, int i1, int i2) {
        int temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }
}
