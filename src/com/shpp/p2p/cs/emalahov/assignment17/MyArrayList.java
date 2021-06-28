package com.shpp.p2p.cs.emalahov.assignment17;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements Iterable<T> {

    private Object[] array;
    private int index;

    public MyArrayList() {
        int default_size = 8;
        this.array = new Object[default_size];
    }

    public MyArrayList(int size) {
        this.array = new Object[size];
    }

    /**
     * This method add element to the end of array values.
     *
     * @param object added element.
     */
    public void add(T object) {
        if (index == array.length - 1) {
            grow();
            array[index++] = object;
        } else if (array[index] == null) {
            array[index++] = object;
        }
    }

    /**
     * This method add element to index position in the array values.
     *
     * @param index  of value.
     * @param object added element.
     */
    public void add(int index, T object) {
        if (index < 0 || index > this.index) {
            throw new IndexOutOfBoundsException("index: " + index + " size: " + this.index);
        }
        if (this.index == array.length) {
            array[this.index++] = object;
        } else {
            Object[] buffer = array;
            array = new Object[array.length];
            for (int i = 0; i < index; i++) {
                array[i] = buffer[i];
            }
            array[index] = object;
            this.index++;
            for (int i = index; i < buffer.length - 1; i++) {
                array[i + 1] = buffer[i];
            }
            if (this.index == array.length - 1) {
                grow();
            }
        }
    }

    /**
     * This method return value by his index in the array.
     *
     * @param index position in the array starts at 0.
     * @return value.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index > this.index - 1) {
            throw new IndexOutOfBoundsException("index: " + index + " size: " + this.index);
        }
        return (T) array[index];
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
        for (int i = 0; i < this.index - 1; i++) {
            if (object.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method removes element by index from an array.
     *
     * @param index position in the array starts at 0.
     * @return removed value.
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index > this.index - 1) {
            throw new IndexOutOfBoundsException("index: " + index + " size: " + this.index);
        }
        T removed = (T) array[index];

        Object[] buffer = array;
        array = new Object[array.length - 1];
        for (int i = 0; i < index; i++) {
            array[i] = buffer[i];
        }
        for (int i = index; i < buffer.length - 1; i++) {
            array[i] = buffer[i + 1];
        }
        this.index--;
        return removed;
    }

    /**
     * This method clears the entire array, it becomes empty.
     */
    public void clear() {
        array = new Object[0];
        index = 0;
    }

    /**
     * This method returns the index of a specific element.
     *
     * @param object accepted element.
     * @return index of this element.
     */
    public int indexOf(T object) {
        if (object == null) {
            for (int i = 0; i < this.index - 1; i++) {
                if (null == array[i]) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.index - 1; i++) {
                if (object.equals(array[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * This method replace old value at index position to new value element.
     *
     * @param index   of value which should be replaced.
     * @param element new value.
     * @return old value.
     */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        if (index < 0 || index > this.index - 1) {
            throw new IndexOutOfBoundsException("index: " + index + " size: " + this.index);
        }
        T oldValue = (T) array[index];
        array[index] = element;
        return oldValue;
    }

    /**
     * This method converts an array to a string in a special form.
     *
     * @return string array form.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < index; i++) {
            if (i == index - 1) {
                stringBuilder.append(array[i]);
            } else {
                stringBuilder.append(array[i]).append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder + "";
    }

    /**
     * This method doubles the array for dynamic expansion during use.
     */
    private void grow() {
        Object[] buffer = array;
        array = new Object[array.length * 2];
        System.arraycopy(buffer, 0, array, 0, buffer.length);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    private class MyIterator<E> implements Iterator<E> {
        int nextElement;

        public boolean hasNext() {
            return nextElement != size();
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (nextElement > array.length - 1) {
                throw new NoSuchElementException();
            }

            return (E) array[nextElement++];
        }

    }
}



