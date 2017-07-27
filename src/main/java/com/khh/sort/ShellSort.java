package com.khh.sort;

import com.khh.sort.base.AbstractSortTemplate;

/**
 * 希尔排序
 * Created by 951087952@qq.com on 2017/7/27.
 *
 *
 *希尔排序的思想是使数组中任意间隔为h的元素都是有序的
 * 实现希尔排序的一种方法是对于每个h，用插入排序将h个子数组独立的排序
 *
 *
 * 希尔排序比插入排序和选择排序要快得多，并且数组越大，优势就越大
 *
 */
public class ShellSort extends AbstractSortTemplate {

    @Override
    public void sort(Comparable[] a) {
        //将a[]按升序排列
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            //将数组变为h有序
            for (int i = h; i < N; i++) {
                //将a[i]插入到a[i-h],a[i-2*h],a[i-3*h]..之中
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
        Integer a[] = {10,15,8,12,5,78,95,56};

        ShellSort shellSort = new ShellSort();
        shellSort.show(a);
        shellSort.sort(a);
        shellSort.show(a);
    }
}