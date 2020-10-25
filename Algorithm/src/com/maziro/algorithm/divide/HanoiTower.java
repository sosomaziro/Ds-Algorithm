package com.maziro.algorithm.divide;

/**
 * 分治算法
 * 汉诺塔问题
 *
 * 将第n个塔看成下层塔
 * n-1个塔看成一个上层塔
 * 移动两个塔就是固定的算法
 * 拆分n-1个上塔问题 直至细分到2塔问题
 */
public class HanoiTower {

    public static void main(String[] args) {
        hanoi("A", "B", "C", 3);
    }

    public static void hanoi(String source, String temp, String destination, int num) {
        if (num == 1) {
            System.out.println(source + " to " + destination);
        } else {
            hanoi(source, destination, temp, num - 1);
            System.out.println(source + " to " + destination);
            hanoi(temp, source, destination, num - 1);
        }
    }
}
