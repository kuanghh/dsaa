package com.khh.search.symbol_table;

/**
 * Created by 951087952@qq.com on 2017/9/6.
 *
 * 基于二叉查找树的符号表
 *
 */
public class BST<Key extends Comparable<Key>,Value> extends OrderST<Key,Value> {

    //根结点
    private Node root;

    private class Node{
        private Key key;
        private Value value;
        private Node left,right;
        private int N;//以该结点为根的子树中的节点总数

        public Node(Key key,Value value,int n){
            this.key = key;
            this.value = value;
            this.N = n;

        }
    }

    @Override
    public Key min() {
        return min(root).key;
    }
    private Node min(Node x){
        if(x.left == null) return x;
        return min(x.left);
    }

    @Override
    public Key max() {
        return max(root).key;
    }

    private Node max(Node node) {
        if(node.right == null) return node;
        return max(node.right);
    }

    /**
     *  小于等于 Key 的 最大键
     */
    @Override
    public Key floor(Key key) {
        Node x = floor(root,key);
        if(x == null) return null;
        return x.key;
    }

    private Node floor(Node x,Key key){
        //查询当前节点是否存在
        if(x == null) return x;
        //比较当前节点key和指定key 的值
        int cmp = key.compareTo(x.key);
        if(cmp < 0){//指定key小于当前节点key
            return floor(x.left,key);
        }else if(cmp == 0){//指定key等于当前节点key
            return x;
        }
        Node t = floor(x.right,key);
        if(t != null) return t;
        else  return x;
    }

    /**
     * 大于等于 Key 的 最小键
     */
    @Override
    public Key ceiling(Key key) {
        Node x = ceiling(root,key);
        if(x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x,Key key){
        if(x == null) return x;
        int cmp = key.compareTo(x.key);
        if(cmp > 0){
            return ceiling(x.right,key);
        }else if(cmp == 0){
            return x;
        }
        Node t = floor(x.left,key);
        if(t != null) return t;
        else return x;
    }

    @Override
    public int rank(Key key) {
        return rank(root,key);
    }

    private int rank(Node x,Key key){
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return rank(x.left,key);
        else if(cmp > 0) return 1 + size(x.left) + rank(x.right,key);
        else return size(x.left);
    }

    @Override
    public Key select(int k) {
        return select(root,k).key;
    }

    private Node select(Node x,int k){
        if(x == null){
            return null;
        }
        int t = size(x.left);
        if(t > k) return select(x.left,k);
        else if(t < k) return select(x.right,k-t-1);
        else return x;
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }

    @Override
    public void put(Key key, Value value) {
        //查找key，找到则更新它的值，否则为它创建一个新的节点
        root = put(root,key,value);
    }

    private Node put(Node x,Key key,Value value){
        //如果key存在于x为根节点的子树中，则更新它的值
        //否则将以key和val为键值对的新结点插入到该子树中
        if(x == null) return new Node(key,value,1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = put(x.left,key,value);
        else if(cmp > 0) x.right = put(x.right,key,value);
        else x.value = value;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }


    @Override
    public Value get(Key key) {
        return get(root,key);
    }

    private Value get(Node node,Key key){
        //在以x为根结点的子树中查找并返回Key对应的值
        //如果找不到则返回null
        if(node == null) return null;
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            return get(node.left,key);
        }else if(cmp > 0){
            return get(node.right,key);
        }else{
            return node.value;
        }
    }

    @Override
    public void delete(Key key) {
        delete(root,key);
    }

    private Node delete(Node x,Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0){
            x.left = delete(x.left,key);
        }else if(cmp > 0){
            x.right = delete(x.right,key);
        }else{

            if(x.right == null) return x.left;
            if(x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax(){
        deleteMax(root);
    }
    private Node deleteMax(Node x){
        if(x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin(){
        deleteMin(root);
    }

    private Node deleteMin(Node x){
        if(x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node){
        if(node == null) return 0;
        else return node.N;
    }

    public static void main(String[] args) {

        BST<String,Integer> bst = new BST<String,Integer>();
        bst.put("S",8);
        bst.put("E",6);
        bst.put("X",1);
        bst.put("A",2);
        bst.put("R",3);
        bst.put("C",1);
        bst.put("H",2);
        bst.put("M",1);
        bst.put("Z",10);

        System.out.println("max :" + bst.max());
        bst.deleteMax();
        System.out.println("delete after max : " + bst.max());
        System.out.println("size('S') : " + bst.size());

//        System.out.println("min :" + bst.min());
//        bst.deleteMin();
//        System.out.println("delete after min :" + bst.min());
//        System.out.println("size('S') : " + bst.size());
    }
}
