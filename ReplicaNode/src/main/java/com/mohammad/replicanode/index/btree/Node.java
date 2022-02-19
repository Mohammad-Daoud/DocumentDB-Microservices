package com.mohammad.replicanode.index.btree;

import static com.mohammad.replicanode.index.btree.BTree.getMaxNodeChildrenNum;

class Node {
    protected int childrenNum;
    protected Entry[] children = new Entry[getMaxNodeChildrenNum()];

    public Node(int childrenNum) {
        this.childrenNum = childrenNum;
    }
}
