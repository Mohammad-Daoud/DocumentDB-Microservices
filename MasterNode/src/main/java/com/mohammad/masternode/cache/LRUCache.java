package com.mohammad.masternode.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class LRUCache<K, V> {
    private final int MAX_CAPACITY;
    private static Node head;
    private static Node tail;
    private ConcurrentHashMap<K, Node> map;
    private ReentrantReadWriteLock readWriteLock;
    private  int  curSize;

    public static class Node <K,V>{
        public K key;
        public V val;
        Node prev;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.val = value;
        }
    }

    public LRUCache(int maxCapacity) {
        this.curSize = 0;
        this.MAX_CAPACITY = maxCapacity;
        readWriteLock = new ReentrantReadWriteLock();
        map = new ConcurrentHashMap<>(maxCapacity);
        head = new Node(0, null);
        tail = new Node(0, null);
        head.next = tail;
        tail.prev = head;
        head.prev = null;
        tail.next = null;
    }

    public V get(K key) {
        if (!map.containsKey(key))
            return null;
        Node node = map.get(key);
        moveToHead(node);
        return (V) node.val;

    }

    public void put(K key, V value) {
        Node curNode = map.get(key);
        if (curNode != null) {
            curNode.val = value;
            moveToHead(curNode);
            return;
        }
        Node newNode = new Node(key, value);
        map.put(key, newNode);
        addToHead(newNode);
        if (curSize > MAX_CAPACITY) {
            Node nodeToRemove = tail.prev;
            removeNode(nodeToRemove);
            map.remove(nodeToRemove.key);
            curSize--;
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        try {
            readWriteLock.writeLock().lock();
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    private void addToHead(Node node) {
        try {
            readWriteLock.writeLock().lock();
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    public void evict(K key) {
        Node node = map.get(key);
        if (node != null) {
            removeNode(node);
            map.remove(key);
        }
    }
}



