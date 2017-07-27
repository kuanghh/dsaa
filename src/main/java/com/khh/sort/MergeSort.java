package com.khh.sort;

import com.khh.sort.base.AbstractSortTemplate;

/**
 * Created by 951087952@qq.com on 2017/7/27.
 * 归并排序,其时间复杂度为 nlogn
 *
 *
 */
public class MergeSort extends AbstractSortTemplate{

    private Comparable[] aux;//辅助数组

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private void sort(Comparable[] a,int lo,int hi){
        if(hi <= lo) return;
        int mid = lo + (hi - lo)/2;//(lo + hi)/2

        //...   ------->这里可以做一些文章，比如当前分出来的数组长度小于12，那么可以在这里选择使用希尔排序，而不再继续分下去

        sort(a,lo,mid);//将左半边排序
        sort(a,mid+1,hi);//将右半边排序
        merge(a,lo,mid,hi);//归并
    }

    /**
     * 合并
     * @param a
     * @param lo
     * @param mid
     * @param hi
     */
    private void merge(Comparable[] a,int lo,int mid,int hi){
        //将a[lo..mid]和a[mid+1..hi]归并
        int i = lo,j = mid +1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if(i > mid){ //因为i是从左半部分的0位置开始，当i大于mid的时候，证明左边数组的元素已经归并完成，所以直接归并右边数组剩下的元素
                a[k] = aux[j++];
            }else if(j > hi){//因为j是从右边部分半部分的mid+1位置开始，当j大于hi的时候，证明右边数组的元素已经归并完成，所以直接归并左边数组剩下的元素
                a[k] = aux[i++];
            }else if(less(aux[j],aux[i])){//比较右边数组和左边数组当前位置下的元素大小
                a[k] = aux[j++];
            }else{        //比较右边数组和左边数组当前位置下的元素大小
                a[k] = aux[i++];
            }
        }
    }


    public static void main(String[] args) {
        Integer a[] = {10,15,8,12,5,78,95,56};

        MergeSort mergeSort = new MergeSort();
        mergeSort.show(a);
        mergeSort.sort(a);
        mergeSort.show(a);
    }
}
