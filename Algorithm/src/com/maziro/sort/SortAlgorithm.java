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
        for (int i = 0; i < array.length - 1; i++) {
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
            int index = i - 1;
            int value = array[i];
            while (index >= 0 && array[index] > value) {
                array[index + 1] = array[index];
                index--;
            }
            if (index + 1 != i) {
                array[index + 1] = value;
            }
        }
    }

    /**
     * shellSort
     */
    public static void shellSort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        int gap;
        for (gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 执行插入排序 步长为gap
                int index = i - gap; // 第一个开始比较的值
                int value = arr[i]; // 要执行插入的值
                while (index >= 0 && arr[index] > value) {
                    arr[index + gap] = arr[index];
                    index -= gap;
                }
                if (index + gap != i) {
                    // 如果已经执行过交换 要执行插入的值位置已经变化
                    arr[index + gap] = value;
                }
            }
        }
    }

    /**
     * merge
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        int[] temp = new int[arr.length];
        divide(arr, 0, arr.length - 1, temp);
    }

    private static void divide(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            // 分
            divide(arr, left, mid, temp);
            divide(arr, mid + 1, right, temp);
            // 合
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 零食数组开始游标
        int tempIndex = left;
        // 左数组开始游标
        int leftIndex = left;
        // 右数组开始游标
        int rightIndex = mid + 1;

        while (leftIndex <= mid && rightIndex <= right) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                temp[tempIndex] = arr[leftIndex];
                leftIndex++;
            } else {
                temp[tempIndex] = arr[rightIndex];
                rightIndex++;
            }
            tempIndex++;
        }
        while (leftIndex <= mid) {
            temp[tempIndex] = arr[leftIndex];
            leftIndex++;
            tempIndex++;
        }
        while (rightIndex <= right) {
            temp[tempIndex] = arr[rightIndex];
            rightIndex++;
            tempIndex++;
        }
        if (right + 1 - left >= 0) System.arraycopy(temp, left, arr, left, right + 1 - left);
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }
        quickSortMerge(arr, 0, arr.length - 1);
    }

    private static void quickSortMerge(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];

        while (l < r) {
            // 以中点为基准 小的放左边 大的放右边
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }

            swapArray(arr, l, r);

            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }

        if (l == r) {
            l++;
            r--;
        }

        if (left < r) {
            quickSortMerge(arr, left, r);
        }
        if (right > l) {
            quickSortMerge(arr, l, right);
        }
    }

    /**
     * 基数排序
     * 桶排序
     */
    public static void radixSort(int[] arr, int maxLength) {

        int[][] bucket = new int[10][arr.length];
        int[] bucketCount = new int[10];

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 装入桶
            for (int value : arr) {
                int bucketNum = value / n % 10;
                bucket[bucketNum][bucketCount[bucketNum]] = value;
                bucketCount[bucketNum]++;
            }
            // 倒出桶
            int index = 0;
            for (int j = 0; j < bucketCount.length; j++) {
                int[] ints = bucket[j];
                if (bucketCount[j] != 0) {
                    System.arraycopy(ints, 0, arr, index, bucketCount[j]);
                    index += bucketCount[j];
                }
                bucketCount[j] = 0;
            }
        }
    }

    /**
     * 数组元素交换位置
     */
    private static void swapArray(int[] a, int i1, int i2) {
        int temp = a[i1];
        a[i1] = a[i2];
        a[i2] = temp;
    }
}
