package com.shpp.p2p.cs.emalahov.assignment17;

import java.util.Arrays;

public class MyPriorityQueue<T extends Comparable<T>> {
    private Object[] array;
    private int size = 0;

    public MyPriorityQueue() {
        int default_Size = 1;
        array = new Object[default_Size];
    }

    /**
     * This method add element to the end of array values.
     *
     * @param object added element.
     */
    public void add(T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        if (size == array.length - 1) {
            array[size++] = object;
            Arrays.sort(array);
            grow();
        }
    }

    /**
     * This method removes the head of this queue and return it.
     *
     * @return removed value.
     */
    @SuppressWarnings("unchecked")
    public T remove() {
        if (size == 0) {
            return null;
        }
        T removed = (T) array[0];
        Object[] buffer = array;
        array = new Object[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = buffer[i + 1];
        }
        size--;
        return removed;
    }

    /**
     * This method return queue size.
     *
     * @return size.
     */
    public int size() {
        return size;
    }

    /**
     * This method says if array is empty or not.
     *
     * @return boolean value.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method return the head of queue without deleting
     * and returning null when queue is empty.
     *
     * @return first element.
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) {
            return null;
        }
        return (T) array[0];
    }

    /**
     * This method grows the array for dynamic expansion during use.
     */
    private void grow() {
        Object[] buffer = array;
        array = new Object[array.length + 1];
        System.arraycopy(buffer, 0, array, 0, buffer.length);
    }

    /**
     * This method converts an array to a string in a special form.
     *
     * @return string array form.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                stringBuilder.append(array[i]);
            } else {
                stringBuilder.append(array[i]).append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder + "";
    }
}
