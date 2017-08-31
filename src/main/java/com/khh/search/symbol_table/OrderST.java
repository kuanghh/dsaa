package com.khh.search.symbol_table;

/**
 * Created by 951087952@qq.com on 2017/8/31.
 *
 * 这是一个有序符号表，因为很多键都是可以用来比较的
 *
 */
public abstract class OrderST<Key extends Comparable<Key>,Value> extends ST<Key,Value>{

    /**
     * 最小的键
     * @return
     */
    abstract Key min();

    /**
     * 最大的键
     */
    abstract Key max();


    /**
     *  小于等于 Key 的 最大键
     */
    abstract Key floor(Key key);

    /**
     * 大于等于 Key 的 最大键
     */
    abstract Key ceiling();

    /**
     *  小于Key的键的数量
     * @param key
     * @return
     */
    abstract int rank(Key key);

    /**
     * 排名为k的键
     * @param k
     * @return
     */
    abstract Key select(int k);

    /**
     * 删除最小的键
     */
     public void deleteMin(){
        delete(min());
     }

    /**
     * 删除最大的键
     */
    public void deleteMax(){
        delete(max());
    }


    /**
     * [lo...hi]之间键的数量
     * @param lo
     * @param hi
     * @return
     */
    int size(Key lo, Key hi){

        if(lo.compareTo(hi) < 0){
            return 0;
        }else if(contains(hi)){
            return rank(hi) - rank(lo) + 1;
        }else{
            return rank(hi) - rank(lo);
        }

    }

    /**
     * [lo...hi]之间所有键,已排序
     * @param lo
     * @param hi
     * @return
     */
    abstract Iterable<Key> keys(Key lo,Key hi);

    @Override
    public Iterable<Key> keys() {
        return keys(min(),max());
    }
}
