package com.khh.sort;

import com.khh.sort.base.AbstractSortTemplate;

/**
 * 快速排序
 * Created by 951087952@qq.com on 2017/7/27.
 */
public class QuickSort extends AbstractSortTemplate{

    @Override
    public void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    private void sort(Comparable[] a,int lo,int hi){
        if(hi <= lo) return;        //这里可以替换成if(hi <= lo+M){Insertion.sort(a,lo,hi);return;},来提升速度,M是随着系统的变化而变化,一般5-15是个不错的结果
        int j = partition(a,lo,hi); //切分
        sort(a,lo,j-1);         //将左半部分a[lo...j-1]排序
        sort(a,j+1,hi);               //将右半部分a[j+1...hi]排序
    }

    private int partition(Comparable[] a, int lo, int hi) {

        int i = lo, j = hi+1;       //左右扫描的指针,i为左，j为右
        Comparable v = a[lo];       //默认获取第一个元素为切分元素
        while(true){
            //扫描左右，检查扫描是否结束并交换元素
            while(less(a[++i],v)){   //从右边开始找，找到比切分元素大的元素
                if(i == hi) break;
            }
            while(less(v,a[--j])){   //从左边开始找，找到比切分元素小的元素
                if(j == lo) break;
            }
            if(i >= j) break;
            exch(a,i,j);            //交换
        }
        exch(a,lo,j);
        return j;
    }

    public static void main(String[] args) {
        Integer a[] = {1,15,8,12,5,78,95,56};

        QuickSort quickSort = new QuickSort();
        quickSort.show(a);
        quickSort.sort(a);
        quickSort.show(a);
    }
}
