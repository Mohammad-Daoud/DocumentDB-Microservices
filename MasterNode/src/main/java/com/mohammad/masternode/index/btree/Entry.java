package com.mohammad.masternode.index.btree;

class Entry {
    protected Comparable key;
    protected Object value;
    protected Node next;     // helper field to iterate over array entries
    public Entry(Comparable key, Object value, Node next) {
        this.key  = key;
        this.value = value;
        this.next = next;
    }
}
