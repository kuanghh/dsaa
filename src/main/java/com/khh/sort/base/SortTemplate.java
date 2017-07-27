package com.khh.sort.base;

/**
 * Created by 951087952@qq.com on 2017/7/27.
 * 排序算法模板
 */
public interface SortTemplate {

    /**
     * 排序算法
     * @param a
     */
    void sort(Comparable[] a);

    /**
     * 对元素进行比较
     * @param v
     * @param w
     * @return
     */
    boolean less(Comparable v,Comparable w);

    /**
     * 将元素交换位置
     * @param a
     * @param i
     * @param j
     */
    void exch(Comparable[] a,int i , int j);

    /**
     * 打印数组
     * @param a
     */
    void show(Comparable[] a);

    /**
     * 测试数据元素是否有序
     * @param a
     * @return
     */
    boolean isSorted(Comparable[] a);
}
