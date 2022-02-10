package com.mohammad.masternode.index.btree;

import static com.mohammad.masternode.index.btree.BTree.getMaxChildrenNum;

class Node {
    protected int childrenNum;
    protected Entry[] children = new Entry[getMaxChildrenNum()];

    public Node(int childrenNum) {
        this.childrenNum = childrenNum;
    }
}
