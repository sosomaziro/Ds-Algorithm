package com.maziro.base;

import java.math.BigDecimal;

/**
 * 斐波那契数列
 */
public class FibonacciArray {

    public static void main(String[] args) {
        int length = 100;
        getByAdd(length);
        getByMerge(length);
    }

    /**
     * 相加法
     * O(1)
     */
    private static void getByAdd(int length) {
        BigDecimal[] array = new BigDecimal[2];
        BigDecimal result;
        for (int i = 0; i < length; i++) {
            if (i == 0 || i == 1) {
                result = BigDecimal.ONE;
            } else {
                result = array[0].add(array[1]);
            }
            array[0] = array[1];
            array[1] = result;
            System.out.println("第" + (i + 1) + " 位 ------ " + result);
        }
    }

    /**
     * 归并
     * O(n^2)
     * 所以递归算法 不是任何时候都好
     */
    private static long count;
    private static void getByMerge(int length) {
        for (int i = 1; i <= length; i++) {
            count = 0;
            System.out.println("第" + i + " 位 ------ " + merge(i) + " ------ 递归次数 " + count);
        }
    }

    private static BigDecimal merge(int length) {
        count++;
        if (length == 1 || length == 2) {
            return BigDecimal.ONE;
        } else {
            return merge(length - 1).add(merge(length - 2));
        }
    }
}
