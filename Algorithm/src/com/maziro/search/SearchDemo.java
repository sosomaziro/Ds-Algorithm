package com.maziro.search;

import com.maziro.sort.SortAlgorithm;
import com.maziro.sort.SortDemo;

import java.util.Arrays;

public class SearchDemo {
    public static void main(String[] args) {
        int[] a = SortDemo.getRandomArray(10000000);
        SortAlgorithm.mergeSort(a);
//        int[] a = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,100,100,100,17,18};

        int searchElement = 100;

        System.out.println("顺序查找：" + SearchAlgorithm.seqSearch(a, searchElement));

        System.out.println("二分查找：" + SearchAlgorithm.binarySearchLoop(a, searchElement));

        System.out.println("二分查找归并：" + SearchAlgorithm.binarySearchMerge(a, searchElement));

        System.out.println("二分查找归并 返回所有：" + Arrays.toString(SearchAlgorithm.binarySearchAll(a, searchElement)));

        System.out.println("斐波那契查找：" + SearchAlgorithm.fibonacciSearch(a, searchElement));
    }
}
