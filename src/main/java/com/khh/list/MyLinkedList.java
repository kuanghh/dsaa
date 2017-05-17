package com.khh.list;



import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by 951087952@qq.com on 2017/5/16.
 * 模仿LinkedList的实现
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int theSize;
    private int modCount = 0;             //代表自构造以来对链表所做改变的次数。每次add或remove的调用都会更新modCount
    private Node<AnyType> beginMarker;   //头节点
    private Node<AnyType> endMarker;     //尾节点

    /**
     * 节点类
     * @param <AnyType>
     */
    private static class Node<AnyType>{
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;
        public Node(AnyType d,Node<AnyType> p,Node<AnyType> n){
            data = d;
            prev = p;
            next = n;
        }
    }

    public MyLinkedList(){
        clear();
    }

    public void clear(){
        beginMarker = new Node<AnyType>(null,null,null);
        endMarker = new Node<AnyType>(null,beginMarker,null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return theSize == 0;
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }

    public void add(int idx, AnyType x) {
        addBefore(getNode(idx),x);
    }

    public AnyType get(int idx){
        return getNode(idx).data;
    }

    public AnyType set(int idx,AnyType newVal){
        Node<AnyType> p = getNode(idx);
        AnyType oldVal = p.data;
        p.data = newVal;
        return oldVal;
    }

    public AnyType remove(int idx){
        return remove(getNode(idx));
    }

    /**
     * 获取某个位置上的节点
     * @param idx
     * @return
     */
    private Node<AnyType> getNode(int idx){
        Node<AnyType> p;
        if(idx < 0 || idx > size()) throw new IndexOutOfBoundsException();
        if(idx < size() / 2){
            p = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                p = p.next;
            }
        }else{
            p = endMarker.prev;
            for (int i = size(); i > idx; i--) {
                p = p.prev;
            }
        }
        return p;
    }

    /**
     * 删除指定节点
     * @param p
     * @return
     */
    private AnyType remove(Node<AnyType> p){
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount++;
        return p.data;
    }

    /**
     * 添加到某个节点的前面
     * @param p
     * @param x
     */
    private void addBefore(Node<AnyType> p,AnyType x){
        Node<AnyType> node = new Node<AnyType>(x,p.prev,p);
        node.prev.next = node;
        p.prev = node;
        theSize++;
        modCount++;
    }

    /**
     * 迭代器
     */
    private class LinkedListIterator implements Iterator<AnyType>{

        /**
         * 链表的modCount存储在数据域expectedModCount中
         *
         * 当modCount != expectedModCount时，那就代表上下操作不一致了，此时抛出异常
         *
         * 当第一次通过LinkedListIterator.remove的时候，okToRemove会变成false，
         * 此时再一次连续调用LinkedListIterator.remove，会抛出异常，但如果先调用next(),则可以继续调用remove
         */

        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarker;
        }

        @Override
        public AnyType next() {
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove(){
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
            if(!okToRemove){
                throw new NoSuchElementException();
            }
            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModCount++;
        }

    }


    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }
}
