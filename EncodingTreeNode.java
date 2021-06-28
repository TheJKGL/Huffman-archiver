package com.shpp.p2p.cs.emalahov.assignment15;

public class EncodingTreeNode implements Comparable<EncodingTreeNode>{
    protected Byte letter;
    protected int frequency;
    protected EncodingTreeNode leftChild = null;
    protected EncodingTreeNode rightChild = null;
    protected boolean isVisited = false;

    protected EncodingTreeNode() {
    }

    protected EncodingTreeNode(Byte letter) {
        this.letter = letter;
    }

    protected EncodingTreeNode(Byte letter, int frequency) {
        this.letter = letter;
        this.frequency = frequency;
    }

    protected EncodingTreeNode(Byte letter, int frequency, EncodingTreeNode leftChild, EncodingTreeNode rightChild) {
        this.letter = letter;
        this.frequency = frequency;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(EncodingTreeNode node) {
        return this.frequency - node.frequency;
    }
}
