package com.yechao.springboot.demo.test.algorithm;

import org.junit.Test;

/**
 * @Author yechao111987@126.com
 * @date 2019/1/2 19:15
 */
public class TestSort {

    /**
     * 冒泡排序（Bubble Sort）
     *
     * @param ints
     * @return
     */

    public int[] BubbleSort(int[] ints) {
        int length = ints.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (ints[j] > ints[j + 1]) {
                    int temp = ints[j + 1];
                    ints[j + 1] = ints[j];
                    ints[j] = temp;
                }
            }
        }
        return ints;
    }

    /**
     * 选择排序（Selection Sort）
     * 找出最小数的index，如果比较数赋给a[i]
     *
     * @param arrs
     */
    public int[] selectionSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (arr[j] > arr[minIndex]) {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }

    public int[] insertionSort(int[] arr) {
        int length = arr.length;
        int preIndex, current;
        for (int i = 1; i < length; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 & arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
        return arr;
    }

    @Test
    public void testBubbleSort() {
        int[] ints = {1, 5, 3, 7, 8, 10};
//        int[] result = BubbleSort(ints);
//        int[] result = selectionSort(ints);
        int[] result = insertionSort(ints);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i] + " ");
        }
    }


}
