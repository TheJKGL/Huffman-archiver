package com.shpp.p2p.cs.emalahov.assignment17;

import java.util.EmptyStackException;

public class MyStack<T> {

    private final MyArrayList<T> array = new MyArrayList<>();

    /**
     * This method add element to the end of array values.
     *
     * @param object added element.
     */
    public T push(T object) {
        array.add(object);
        return object;
    }

    /**
     * This method return the top element from the stack and delete it.
     *
     * @return top element.
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array.remove(size() - 1);
    }

    /**
     * This method says if stack is empty or not.
     *
     * @return boolean value.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * This method return the top element from the stack, without deleting.
     *
     * @return top element.
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array.get(size() - 1);
    }

    /**
     * This method return stack size.
     *
     * @return size.
     */
    public int size() {
        return array.size();
    }

    /**
     * This method says if array is empty or not.
     *
     * @return boolean value.
     */
    public boolean empty() {
        return size() == 0;
    }

    /**
     * This method converts an stack to a string in a special form.
     *
     * @return string stack form.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size(); i++) {
            if (i == size() - 1) {
                stringBuilder.append(array.get(i));
            } else {
                stringBuilder.append(array.get(i)).append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder + "";
    }
}
