package com.khh.sort;

import com.khh.sort.base.AbstractSortTemplate;

/**
 * Created by 951087952@qq.com on 2017/7/27.
 *
 * 在计算机的实现中，为了给要插入的元素腾出空间，我们需要将其余所有元素在插入之前向右移动一位，这就是插入算法
 *
 * 插入排序所需要的时间取决于输入中元素的初始位置。例如，对一个很大且其中的元素已经有序（或接近有序）的数组
 * 进行排序将会比随机顺序的数组或是逆序数组进行排序要快得多
 *
 * 对于随机排列的长度为N且主键不重复的数组，平均情况下插入排序需要~N²/4次比较以及~N²/4次交换
 * 。最坏情况下要~N²/2次比较以及~N²/2次交换.最好的情况下需要N-1次比较和0次交换
 */
public class InsertSort extends AbstractSortTemplate{

    /**
     * 插入排序算法思路：
     *    假设数组的长度为k:
     *    则：
     *      第一层循环，是对数组的前i+1个元素进行插入排序,
     *      第二层循环，开始的下标是从第i个元素开始，如果存在第i个元素比第i-1个元素要小，则交换他们的位置,然后对前面的元素进行相同的操作
     *
     *    举例子：
     *          数组a = {10,16,13,12,14,15}
     *     每一次排序的情况如下：
     *          10 16 13 12 14 15  按照如下程序,此时 i = 1
     *          10 13 16 12 14 15  按照如下程序,此时 i = 2
     *          10 13 12 16 14 15  按照如下程序,此时 i = 3
     *          10 12 13 16 14 15  按照如下程序,此时 i = 3
     *          10 12 13 14 16 15  按照如下程序,此时 i = 4
     *          10 12 13 14 15 16  按照如下程序,此时 i = 5
     * @param a
     */
    @Override
    public void sort(Comparable[] a) {
        //将a[]升序排列

        for (int i = 1; i < a.length; i++) {
//            System.out.println("对多少个元素进行比较" + (i+1));
            //将a[i]插入到a[i-1]、a[i-2]、a[i-3]、a[i-4].....当中
            for(int j = i; j > 0 && less(a[j],a[j-1]); j--){
                exch(a,j,j-1);
//                System.out.println("   交换的元素是:" + a[j]+ "和" + a[j-1]);
            }
        }
    }


    public static void main(String[] args) {
        Integer a[] = {10,16,13,12,14,15};

        InsertSort sort = new InsertSort();
        sort.show(a);
        sort.sort(a);
        sort.show(a);
    }
}
