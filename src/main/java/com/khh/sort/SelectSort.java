package com.khh.sort;

import com.khh.base.AbstractSortTemplate;

/**
 * Created by 951087952@qq.com on 2017/7/27.
 * 选择排序:
 *      首先，找到元素最小的一个元素，然后把它与第一个元素交换位置，然后对剩下的元素重复以上操作
 *
 *     对于长度为N的数组，选择排序需要N²/2的比较和N次交换，所以时间复杂度应该为 N²
 */
public class SelectSort extends AbstractSortTemplate{

    @Override
    public void sort(Comparable[] a) {
        //将a[]按升序排序
        for (int i = 0; i < a.length; i++) {
            int min = i;//用来记录元素最小的位置
            for (int j = i+1; j < a.length; j++) { //将a[i]与a[i+1...N]中最小的元素找出来
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);//交换两个元素
        }
    }

    public static void main(String[] args) {
        Integer a[] = {85,45,78,62,95,12,45,87};

        SelectSort sort = new SelectSort();
        sort.show(a);
        sort.sort(a);
        sort.show(a);
    }
}
