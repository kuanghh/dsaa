package com.khh.search.symbol_table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * Created by 951087952@qq.com on 2017/8/31.
 *
 *  二分查找(基于有序数组)
 *
 *      二分查找的时间复杂度是: lgN + 1
 *
 *   但二分查找的方法，在put进数据或者delete数据的时候，时间复杂度依然为N，对于处理大数据来说，依然是不够的
 *
 */
public class BinarySearchST<Key extends Comparable<Key>,Value> extends  OrderST<Key,Value> {

    private Key[] keys;
    private Value[] values;
    private int N;//长度

    public BinarySearchST(int capacity){

        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    //重新分配空间
    private void resize(int max){
        Key[] keysTemp = (Key[]) new Comparable[max];
        Value[] valuesTemp = (Value[]) new Object[max];

        for (int i = 0; i < N; i++) {
            keysTemp[i] = keys[i];
            valuesTemp[i] = values[i];
        }
        keys = keysTemp;
        values = valuesTemp;
    }

    //检查是否还有空间
    private void checkSize(){
        if(size() == keys.length){//如果位置已经满了，则扩展
            resize(keys.length * 2 + 1);
        }
    }

    //小于Key的键的数量 ,相当于找出 大于等于key的最小值的位置，,这里使用基于有序数组的二分查找
    @Override
    public int rank(Key key) {

        int lo = 0, hi = N - 1;
        while(lo <= hi){
            int mid = (lo + hi) / 2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0) {
                hi = mid - 1;
            }else if(cmp > 0){
                lo = mid + 1;
            }else{
                return mid;
            }
        }

        return lo;
    }

    @Override
    public Key select(int k) {
        return keys[k];
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {

        List<Key> list = new ArrayList<>();

        for(int i = rank(lo) ; i < rank(hi);i++ ){
            list.add(keys[i]);
        }
        if(contains(hi)){
//            list.add(keys[rank(hi)]);
            list.add(hi);
        }

        return list;
    }

    @Override
    public void put(Key key, Value value) {

        //1、找出比key小的数量
        int i = rank(key);
        //2、若等于，则替换旧的值
        if(i < N && key.equals(keys[i])){
            values[i] = value;
            return;
        }
        //3、若小于，则把找到键以及后面的键往后移一位，值也按照相同操作，然后再把值插入进去

        checkSize();
        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        N++;
    }

    @Override
    public Value get(Key key) {

        if(isEmpty()){
            return null;
        }
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0) return values[i];

        return null;
    }

    @Override
    public void delete(Key key) {
        int i = rank(key);
        if(i < N && keys[i].compareTo(key) == 0){
            if(i == N - 1){
                keys[i] = null;
                values[i] = null;
            }
            for (int j = i; j < N - 1; j++) {
                keys[j] = keys[j + 1];
                values[j] = values[j + 1];
            }
            N--;
        }
    }

    @Override
    public int size() {
        return N;
    }


    @Override
    public  Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[N-1];
    }

    //小于等于key的最大值
    @Override
    public Key floor(Key key) {

        int i = rank(key);
        if(contains(key)){
            return key;
        }
        if(i == 0) return null;
        return keys[i - 1];
    }

    //大于等于key的最小值
    @Override
    public Key ceiling(Key key) {
        int i = rank(key);

        return keys[i];
    }


    public static void main(String[] args) {
        BinarySearchST<Integer,String> st1 = new BinarySearchST<>(1);
        st1.put(1,"111");
        st1.put(3,"333");
        st1.put(5,"555");
        st1.put(7,"777");
        st1.put(9,"999");

        st1.delete(4);

        Iterable<Integer> keys = st1.keys();
        Iterator<Integer> iterator = keys.iterator();

        while(iterator.hasNext()){
            Integer key = iterator.next();
            System.out.println("key : " + key + "  ,value :" + st1.get(key));
        }

        System.out.println(st1.ceiling(1));
        System.out.println(st1.ceiling(2));
        System.out.println(st1.ceiling(9));
        System.out.println(st1.ceiling(10));

        System.out.println(st1.floor(1));
        System.out.println(st1.floor(2));
        System.out.println(st1.floor(9));
        System.out.println(st1.floor(10));
    }
}
