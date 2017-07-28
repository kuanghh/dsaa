package com.khh.queue;

import com.khh.base.AbstractSortTemplate;

/**
 * Created by 951087952@qq.com on 2017/7/28.
 * 基于堆的优先队列
 *
 */
public class MaxPQ <Key extends Comparable<Key>> {

    private Key[] pq; //基于堆的完全按二叉树
    private int N = 0; //基于pq[1..N]中，p[0]不使用

    public MaxPQ(int maxN){
        pq = (Key[]) new Comparable[maxN +1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];    //从根节点得到最大元素
        exch(1,N--);  //将其和最后一个节点交换
        pq[N+1] = null;     //防止对象游离
        sink(1);            //恢复堆的有序性
        return max;
    }

    //恢复堆的有序性(下沉)
    private void sink(int k){
        while(2 * k <= N){
            int j = 2 * k;
            if(j < N && less(j,j+1)) {
                j++;
            }
            if(!less(k,j)){
                break;
            }
            exch(k,j);
            k = j;
        }
    }

    //由下至上的堆有序化（上浮）
    private void swim(int k){
        while(k > 1 && less(k / 2,k)){
            exch(k/2,k);
            k = k / 2;
        }
    }


    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i,int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
