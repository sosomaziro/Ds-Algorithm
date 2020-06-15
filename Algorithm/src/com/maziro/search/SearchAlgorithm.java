package com.maziro.search;

import java.util.Arrays;

public class SearchAlgorithm {

    /**
     * 顺序查找
     */
    public static int seqSearch(int[] arr, int element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找
     */
    public static int binarySearchLoop(int[] arr, int element) {
        if (checkArray(arr))
            return -1;

        int left = 0;
        int right = arr.length - 1;
        int mid = (right + left) / 2;
        int last;
        while (true) {
            if (arr[mid] == element) {
                return mid;
            } else if (arr[mid] > element) {
                right = mid - 1;
            } else if (arr[mid] < element) {
                left = mid + 1;
            }
            last = mid;
            mid = (right + left) / 2;
            if (mid == last) {
                break;
            }
        }
        return -1;
    }

    /**
     * 二分归并查找
     */
    public static int binarySearchMerge(int[] arr, int element) {
        if (checkArray(arr))
            return -1;
        return binarySearchMerge(arr, 0, arr.length - 1, element);
    }

    private static int binarySearchMerge(int[] arr, int left, int right, int element) {
        int mid = (left + right) / 2;
        if (left > right) {
            return -1;
        } else if (arr[mid] == element) {
            return mid;
        } else if (arr[mid] > element) {
            return binarySearchMerge(arr, left, mid - 1, element);
        } else if (arr[mid] < element) {
            return binarySearchMerge(arr, mid + 1, right, element);
        }
        return -1;
    }

    /**
     * 二分查找返回所有
     */
    public static int[] binarySearchAll(int[] arr, int element) {
        if (checkArray(arr))
            return null;
        return binarySearchAll(arr, 0, arr.length - 1, element);
    }

    private static int[] binarySearchAll(int[] arr, int left, int right, int element) {
        /*
         使用插值查找 优化二分查找
         当数组分布越平均 插值查找提升越明显
         */
//        int mid = (left + right) / 2; // 二分查找
        int mid = left + (element - arr[left]) / (arr[right] - arr[left]) * (right - left) / 2; // 插值查找

        if (left > right) {
            return null;
        } else if (arr[mid] == element) {
            // 向左 右扫描
            int scanLeft = mid;
            int scanRight = mid;

            do {
                scanLeft--;
            } while (arr[scanLeft] == element);
            do {
                scanRight++;
            } while (arr[scanRight] == element);

            int[] ints = new int[scanRight - scanLeft - 2 + 1];
            for (int i = scanLeft + 1, j = 0; i < scanRight; i++, j++) {
                ints[j] = i;
            }
            return ints;

        } else if (arr[mid] > element) {
            return binarySearchAll(arr, left, mid - 1, element);
        } else if (arr[mid] < element) {
            return binarySearchAll(arr, mid + 1, right, element);
        }
        return null;
    }


    /**
     * fibonacci查找算法
     */
    public static int fibonacciSearch(int[] arr, int element) {
        if (checkArray(arr))
            return -1;
        // 构建数列
        int[] fib = getFibonacci();

        // 创建临时数列，长度为斐波那契数列的一个数，使用最后一位补齐空白位
        int k = 0;
        while (arr.length > fib[k]) {
            k++;
        }
        int[] temp =  Arrays.copyOf(arr, fib[k]);
        int i = arr.length;
        while (i < fib[k] - 1) {
            temp[i] = temp[arr.length - 1];
            i++;
        }

        // 迭代查找
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + fib[k - 1] - 1;
            if (temp[mid] > element) {
                // 下一个 mid = l + fib[k - 2] - 1
                k--;
                r = mid - 1;
            } else if (temp[mid] < element) {
                // 下一个 mid = (mid + 1) + fib[k - 3] - 1
                k -= 2;
                l = mid + 1;
            } else {
                if (mid <= r) {
                    return mid;
                } else {
                    // 匹配值为最后一位，在数组之外的补齐部分搜到匹配，返回最后一位
                    return r;
                }
            }
        }

        return -1;
    }

    private static int[] getFibonacci() {
        int fibSize = 45; // int 极限到第45个
        int[] fib = new int[fibSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fibSize; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    public static boolean checkArray(int[] arr) {
        return arr == null || arr.length < 1;
    }
}








