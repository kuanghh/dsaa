package com.khh.sort.base;

/**
 * Created by 951087952@qq.com on 2017/7/27.
 */
public class AbstractSortTemplate implements SortTemplate {
    @Override
    public void sort(Comparable[] a) {

    }

    @Override
    public boolean less(Comparable v, Comparable w) {

        return v.compareTo(w) < 0;
    }

    @Override
    public void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    @Override
    public void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    @Override
    public boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }
}
