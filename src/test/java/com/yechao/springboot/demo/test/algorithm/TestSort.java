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

    public int[] quickSort(int[] arr, int left, int right) {
        int length = arr.length;
        int partitionIndex;
        if (left < right) {
            partitionIndex = partition(arr, left, right);
            System.out.println("pIndex:" + partitionIndex);
            quickSort(arr, 0, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        int p = left;
        int index = p + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[p]) {
                int temp = arr[i];
                arr[i] = arr[p];
                arr[p] = temp;
                index++;
            }
        }
        int t = arr[p];
        arr[p] = arr[index - 1];
        arr[index - 1] = t;
        return index - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] quickSort2(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int key = arr[i];
            while (i < j) {
                //4.1 ，从右往左找到第一个小于key的数
                while (i < j && arr[j] > key) {
                    j--;
                }
                // 4.2 从左往右找到第一个大于key的数
                while (i < j && arr[i] <= key) {
                    i++;
                }
                //4.3 交换
                if (i < j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            int temp = arr[i];
            arr[i] = arr[left];
            arr[left] = temp;
            quickSort2(arr, left, i - 1);
            quickSort2(arr, i + 1, right);
        }
        return arr;

    }

    @Test
    public void testBubbleSort() {
        int[] ints = {1, 9, 5, 3, 7, 8, 10};
//        int[] result = BubbleSort(ints);
//        int[] result = selectionSort(ints);
        int[] result = insertionSort(ints);
//        int[] result = quickSort(ints, 0, ints.length - 1);
//        int[] result = quickSort2(ints, 0, ints.length - 1);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i] + " ");
        }
    }


}
