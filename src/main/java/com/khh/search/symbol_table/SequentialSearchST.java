package com.khh.search.symbol_table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 951087952@qq.com on 2017/8/31.
 *
 *   顺序查找，基于无序链表
 *
 *
 *     以下实现方法：
 *          一开始是没有节点的：null
 *          put ("s","ss")     :  ("s","ss")->null
 *          put ("c","cc")     :  ("c","cc")->("s","ss")->null
 *
 *          不断的往链表头上面插入新的数据
 *
 *
 *     分析：
 *          由于每次get，最大比较的次数是1,2,3,4,5.....N
 *          平均比较次数是(1+2+3+4+5+...+N)/N = (N+1)/2 ~ N/2 ，证明了基于链表的实现以及顺序查找是非常低效的
 */
public class SequentialSearchST<Key,Value> extends ST<Key,Value>{

    private Node first;  // 链表首节点

    @Override
    void put(Key key, Value value) {
        if(isEmpty()){
            first = new Node(key,value,null);
            return ;
        }

        for(Node node = first; node != null ; node = node.next){
            if(node.key.equals(key)){
                node.value = value;
                return;
            }
        }
        first = new Node(key,value,first);
    }

    @Override
    Value get(Key key) {

        if(isEmpty()){
            return null;
        }
        for(Node node = first; node != null ; node = node.next){
            if(node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    @Override
    void delete(Key key) {

        //删除数据，分以下三种情况

        //1.符号表没有数据 也就是first == null
        if(isEmpty()){
            return ;
        }
        //2.符号表只有一个数据,也就是 first.next = null
        if(size() == 1){
            if(first.key.equals(key)){
                first = null;
                return ;
            }
        }

        //3.符号表大于一个数据，也就是 first.next != null
        if(key.equals(first.key)){
            first = first.next;
            return ;
        }

        Node preNode = first;
        for(Node x = first.next; x != null ; x = x.next){
            if(key.equals(x.key)){
                preNode.next = x.next;
                return;
            }
            preNode = x;
        }
    }

    @Override
    int size() {

        if(first == null){
            return 0;
        }

        int count = 1;
        for(Node node = first; node.next != null; node = node.next){
            count ++;
        }
        return count;
    }

    @Override
    Iterable<Key> keys() {
        List<Key> list = new ArrayList<>();

        if(first != null){
            for(Node node = first; node != null; node = node.next){
                list.add(node.key);
            }
        }

        return list;
    }

    private class Node{

        Key key;
        Value value;
        Node next;

        public Node(Key key,Value value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public static void main(String[] args) {
        SequentialSearchST<String,String> st1 = new SequentialSearchST<>();
        st1.put("1","111");
        st1.put("2","222");
        st1.put("3","333");
        st1.put("4","444");
        st1.put("4","555");

//        st1.delete("4");
//        st1.delete("1");
//        st1.delete("3");
//        st1.delete("2");

        Iterable<String> keys = st1.keys();
        Iterator<String> iterator = keys.iterator();

        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println("key: " + key +"   ,value :" + st1.get(key));
        }

    }
}
