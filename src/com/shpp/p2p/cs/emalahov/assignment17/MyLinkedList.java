package com.shpp.p2p.cs.emalahov.assignment17;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {

    private int index = 0;
    private Node<T> first;
    private Node<T> last;

    private static class Node<T> {
        private final T element;
        private Node<T> next;
        private Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public MyLinkedList() {
        last = null;
        first = null;
    }

    /**
     * This method add element to index position in the array values.
     *
     * @param index  of value.
     * @param object added element.
     */
    public void add(int index, T object) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("index: " + index + " size: " + this.index);
        }
        if (index == size()) {
            addLast(object);
        } else if (index == 0) {
            Node<T> oldNode = first;
            Node<T> newNode = new Node<>(null, object, oldNode);
            first = newNode;
            oldNode.prev = newNode;
            this.index++;
        } else {
            Node<T> oldNode = first;
            for (int i = 0; i < index; i++) {
                oldNode = oldNode.next;
            }
            Node<T> newNode = new Node<>(oldNode.prev, object, oldNode);
            oldNode.prev.next = newNode;
            oldNode.prev = newNode;
            this.index++;
        }
    }

    /**
     * This method return value by his index in the array.
     *
     * @param index position in the array starts at 0.
     * @return value.
     */
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index: " + index + " size: " + this.index);
        }
        Node<T> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.element;
    }

    /**
     * This method return array size.
     *
     * @return size.
     */
    public int size() {
        return index;
    }

    /**
     * This method says if array contains this element.
     *
     * @param object searching element
     * @return boolean value.
     */
    public boolean contains(T object) {
        MyLinkedList.Node<T> node = first;
        for (int i = 0; i < index; i++) {
            if (object == null) {
                if (node.element == null) {
                    return true;
                }
                node = node.next;
            }
            if (node.element.equals(object)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * This method removes element by index from an array.
     *
     * @param index position in the array starts at 0.
     * @return removed value.
     */
    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index: " + index + " size: " + this.index);
        }
        if (index == 0) {
            removeFirst();
        } else if (index == size() - 1) {
            removeLast();
        } else {
            Node<T> oldNode = first;
            for (int i = 0; i < index; i++) {
                oldNode = oldNode.next;
            }
            oldNode.prev.next = oldNode.next;
            oldNode.next.prev = oldNode.prev;
            this.index--;
        }
    }

    /**
     * This method clears the entire array, it becomes empty.
     */
    public void clear() {
        first = new Node<>(null, null, null);
        last = new Node<>(null, null, null);
        index = 0;
    }

    /**
     * This method returns the index of a specific element.
     *
     * @param object accepted element.
     * @return index of this element.
     */
    public int indexOf(Object object) {
        int index = 0;
        Node<T> oldNode = first;
        if (object == null) {
            for (int i = 0; i < this.index; i++) {
                if (null == oldNode.element) {
                    return index;
                }
                oldNode = oldNode.next;
                index++;
            }
        } else {
            for (int i = 0; i < this.index; i++) {
                if (object.equals(oldNode.element)) {
                    return index;
                }
                oldNode = oldNode.next;
                index++;
            }
        }
        return -1;
    }

    /**
     * This method add object to the end position of the array.
     *
     * @param object added element.
     */
    public void addLast(T object) {
        if (isEmpty()) {
            Node<T> newNode = new Node<>(null, object, null);
            index++;
            first = newNode;
            last = newNode;
        } else {
            Node<T> bufLast = last;
            last = new Node<>(bufLast, object, null);
            bufLast.next = last;
            index++;
        }
    }

    /**
     * This method add object to the start position of the array.
     *
     * @param object added element.
     */
    public void addFirst(T object) {
        if (isEmpty()) {
            Node<T> newNode = new Node<>(null, object, null);
            index++;
            first = newNode;
            last = newNode;
        } else {
            Node<T> bufLast = first;
            first = new Node<>(null, object, bufLast);
            bufLast.prev = first;
            index++;
        }
    }

    /**
     * This method removes the last element from the array.
     *
     * @return removed element.
     */
    public T removeLast() {
        Node<T> node = last;
        if (size() == 0) {
            throw new NoSuchElementException();
        } else if (size() == 1) {
            first = null;
            last = null;
            index--;
            return node.element;
        }
        last = last.prev;
        last.next = null;
        index--;
        return node.element;
    }

    /**
     * This method removes the first element from the array.
     *
     * @return removed element.
     */
    public T removeFirst() {
        Node<T> node = first;
        if (size() == 0) {
            throw new NoSuchElementException();
        } else if (size() == 1) {
            first = null;
            last = null;
            index--;
            return node.element;
        }
        first = first.next;
        first.prev = null;
        index--;
        return node.element;
    }

    /**
     * This method return the first element from the array,
     * without deleting.
     *
     * @return first element.
     */
    public T getFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        return first.element;
    }

    /**
     * This method return the last element from the array,
     * without deleting.
     *
     * @return last element.
     */
    public T getLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        return last.element;
    }

    /**
     * This method converts an array to a string in a special form.
     *
     * @return string array form.
     */
    public String toString() {
        if (first == null) {
            return "[]";
        }
        StringBuilder result = new StringBuilder();
        result.append("[");
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            if (i == index - 1) {
                result.append(node.element);
            } else {
                result.append(node.element).append(", ");
            }
            node = node.next;
        }
        result.append("]");
        return result + "";
    }

    /**
     * This method says if array is empty or not.
     *
     * @return boolean value.
     */
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        Node<T> head = first;

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> next = head;
            head = head.next;
            return next.element;
        }
    }
}
