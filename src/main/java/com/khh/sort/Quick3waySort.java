package com.khh.sort;

import com.khh.sort.base.AbstractSortTemplate;

/**
 * 三向切分的快速排序，用来解决含有大量重复元素的排序方法
 * Created by 951087952@qq.com on 2017/7/27.
 */
public class Quick3waySort extends AbstractSortTemplate{

    @Override
    public void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    private void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int lt = lo,i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while(i <= gt){
            int cmp  = a[i].compareTo(v);
            if(cmp < 0) exch(a,lt++,i++);
            else if(cmp > 0) exch(a,i,gt--);
            else i++;
        }
        sort(a,lo,lt - 1);
        sort(a,gt + 1,hi);
    }

    public static void main(String[] args) {
        Integer a[] = {1,15,8,12,12,12,12,12,12,12,12,5,78,95,56};

        Quick3waySort quickSort = new Quick3waySort();
        quickSort.show(a);
        quickSort.sort(a);
        quickSort.show(a);
    }
}
