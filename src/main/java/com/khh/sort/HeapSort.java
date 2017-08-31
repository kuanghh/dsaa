package com.khh.sort;

import com.khh.sort.base.AbstractSortTemplate;

/**
 * Created by 951087952@qq.com on 2017/7/28.
 * 堆排序
 * 对排序数组中，第一个元素不能作为数组元素
 *
 * 时间复杂度为  NlogN
 */
public class HeapSort extends AbstractSortTemplate{

    @Override
    public void sort(Comparable[] a) {
        int N = a.length - 1;
        for (int k = N / 2; k >= 1; k--) {
            sink(a,k,N);
        }
        while(N > 1){
            exch(a,1,N--);
            sink(a,1,N);
        }
    }

    //(下沉)
    private void sink(Comparable[] a,int k,int N){
        while(2 * k <= N){
            int j = 2 * k;
            if(j < N && less(a[j],a[j+1])) {
                j++;
            }
            if(!less(a[k],a[j])){
                break;
            }
            exch(a,k,j);
            k = j;
        }
    }

    public static void main(String[] args) {
        //第一个元素，不做排序
        Integer a[] = {-1,50,42,5,31,11,12,456,78};

        HeapSort heapSort = new HeapSort();
        heapSort.show(a);
        heapSort.sort(a);
        heapSort.show(a);
    }
}
