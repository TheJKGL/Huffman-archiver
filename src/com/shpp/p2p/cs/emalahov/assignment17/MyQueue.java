package com.shpp.p2p.cs.emalahov.assignment17;

import java.util.NoSuchElementException;

public class MyQueue<T> {

    private final MyLinkedList<T> list = new MyLinkedList<>();

    /**
     * This method add element to the end of queue values.
     *
     * @param object added element.
     */
    public void add(T object) {
        list.addLast(object);
    }

    /**
     * This method return the first object without deleting
     * and throws the exception when queue is empty.
     *
     * @return first element.
     */
    public T element() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return list.getFirst();
    }

    /**
     * This method return the first object without deleting
     * and returning null when queue is empty.
     *
     * @return first element.
     */
    public T peek() {
        if (size() == 0) {
            return null;
        }
        return list.getFirst();
    }

    /**
     * This method returns the first element and delete it
     * and returning null when queue is empty.
     *
     * @return first element.
     */
    public T poll() {
        if (size() == 0) {
            return null;
        }
        return list.removeFirst();
    }

    /**
     * This method returns the first element and delete it
     * and returning null when queue is empty.
     *
     * @return first element.
     */
    public T remove() {
        return list.removeFirst();
    }

    /**
     * This method return queue size.
     *
     * @return size.
     */
    public int size() {
        return list.size();
    }

    /**
     * This method says if array is empty or not.
     *
     * @return boolean value.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * This method says if array contains this element.
     *
     * @param object searching element
     * @return boolean value.
     */
    public boolean contains(T object) {
        return list.contains(object);
    }

    /**
     * This method converts an queue to a string in a special form.
     *
     * @return string queue form.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size(); i++) {
            if (i == size() - 1) {
                stringBuilder.append(list.get(i));
            } else {
                stringBuilder.append(list.get(i)).append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder + "";
    }
}
