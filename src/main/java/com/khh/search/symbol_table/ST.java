package com.khh.search.symbol_table;

/**
 * Created by 951087952@qq.com on 2017/8/31.
 *
 * 这是一个符号表 ：
 *      符号表 是 一个 存储 键值对 的数据结构
 *
 *
 *      我们的所有实现遵循以下规则：
 *          1.每个健志对应一个值
 *          2.当存入新的键值对和表中已有的键冲突时，新的值会取代旧的值
 *          3.键不能为空（null）
 *          4.值不能为空（null），
 *
 */
public abstract class ST<Key,Value> {

    /**
     * 将键值对存入表中，若值为空，则将键key删除
     * @param key
     * @param value
     */
    public abstract void put(Key key, Value value);


    public abstract Value get(Key key);

    /**
     *  put(key,null) 是一种延迟型的实现，而以下方法是为了替代这种默认的方案
     * @param key
     */
    public abstract void delete(Key key);

    public boolean contains(Key key){
        return get(key) != null;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public abstract int size();

    public abstract Iterable<Key> keys();
}
