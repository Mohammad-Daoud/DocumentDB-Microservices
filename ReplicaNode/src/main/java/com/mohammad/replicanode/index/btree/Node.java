package com.mohammad.replicanode.index.btree;

import static com.mohammad.replicanode.index.btree.BTree.getMaxChildrenNum;

class Node {
    protected int childrenNum;
    protected Entry[] children = new Entry[getMaxChildrenNum()];

    public Node(int childrenNum) {
        this.childrenNum = childrenNum;
    }
}
