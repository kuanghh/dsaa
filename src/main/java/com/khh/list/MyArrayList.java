package com.khh.list;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 951087952@qq.com on 2017/5/16.
 * 模仿ArrayList的实现
 */
public class MyArrayList<AnyType> implements Iterable<AnyType>{

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private AnyType[] theItems;

    public MyArrayList(){
        clear();
    }

    public void clear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }
    public int size(){
        return this.theSize;
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public void trimToSize(){
        ensureCapacity(theSize);
    }
    public void ensureCapacity(int newCapacity){
        if(newCapacity < theSize){
            return;
        }
        AnyType[] old = theItems;
        theItems = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public AnyType get(int index){
        if(index < 0 || index >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
       return theItems[index];
    }

    public AnyType set(int index , AnyType newVal){
        if(index < 0 || index >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }
        AnyType oldVal = theItems[index];
        theItems[index] = newVal;
        return oldVal;
    }

    public boolean add(AnyType x){
        add(size(),x);
        return true;
    }
    public boolean add(int idx ,AnyType x){
        if(theItems.length == size()){
            ensureCapacity(theSize * 2 + 1);
        }
        if(idx > size()){
            idx = size();
        }
        for (int i = size(); i > idx; i--) {
            theItems[i] = theItems[i-1];
        }
        theItems[idx] = x;
        theSize++;
        return true;
    }

    public AnyType remove(int idx){
        if(idx < 0 || idx >= size()){
            throw new ArrayIndexOutOfBoundsException();
        }

        AnyType x = theItems[idx];
        for(int i = idx; i < size(); i++){
            theItems[i] = theItems[i-1];
        }
        theSize--;
        return x;
    }
    @Override
    public Iterator<AnyType> iterator() {


        return null;
    }

    private class ArrayListIterator implements Iterator<AnyType>{

        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public AnyType next() {
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
