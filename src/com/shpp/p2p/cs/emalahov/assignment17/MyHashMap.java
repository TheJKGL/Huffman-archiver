package com.shpp.p2p.cs.emalahov.assignment17;

import com.shpp.p2p.cs.emalahov.assignment16.MyArrayList;

import java.util.Iterator;
import java.util.Objects;

public class MyHashMap<K, V> {
    @SuppressWarnings("unchecked")
    //https://habr.com/ru/post/128017/
    //array of object MyEntry which contains pairs key - value.
    private MyEntry<K, V>[] table = new MyEntry[16];
    private final float loadFactor = 0.75f;
    private int size = 0;
    //level of capacity
    private float threshold = table.length * loadFactor;

    public static class MyEntry<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private MyEntry<K, V> next;

        private MyEntry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        private void setValue(V value) {
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        private int getHash() {
            return hash;
        }

        private MyEntry<K, V> getNext() {
            return next;
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyEntry<K, V> myEntry = (MyEntry<K, V>) o;
            return hash == myEntry.hash && Objects.equals(key, myEntry.key) && Objects.equals(value, myEntry.value);
        }

        @Override
        public final String toString() {
            return key + "=" + value;
        }
    }

    /**
     * This method returns value from our map by its key or
     * returns null if there is no such value for this key.
     *
     * @param key the key whose value is to be returned.
     * @return value which key was called.
     */
    public V get(K key) {
        int index = hash(key) % table.length;
        MyEntry<K, V> oldE = table[index];
        if (oldE != null) {
            MyEntry<K, V> e = oldE;
            if (e.getKey().equals(key)) {
                return e.getValue();
            }
            while (e.next != null) {
                e = e.next;
                if (e.getKey().equals(key)) {
                    return e.getValue();
                }
            }
            return null;
        }
        return null;
    }

    /**
     * This method adds a new key-value pair to our array of pairs.
     * Also this method check collision and doubling.
     *
     * @param key   the key whose value is to be associated.
     * @param value value which key was called.
     */
    public void put(K key, V value) {
        if (size + 1 >= threshold) {
            threshold *= 2;
            resize();
        }
        MyEntry<K, V> newE = new MyEntry<>(hash(key), key, value);
        int index = hash(key) % table.length;

        if (table[index] == null) {
            table[index] = newE;
        } else {
            MyEntry<K, V> oldE = table[index];
            MyEntry<K, V> e = table[index];
            //check if key already exists but the value is new.
            if (oldE.getKey().equals(key) && !oldE.getValue().equals(value)) {
                table[index].setValue(value);
                return;
            }
            while (e.next != null) {
                e = e.next;
                if (e.getKey().equals(key) && !e.getValue().equals(value)) {
                    e.setValue(value);
                    return;
                }
            }
            //another case: check if index is the same and check collision.
            addEntryAtLast(oldE, newE);
        }
        size++;
    }

    /**
     * This method doubles the size of our array as needed.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        MyEntry<K, V>[] buffer = table;
        table = new MyEntry[table.length * 2];
        transfer(buffer);
    }

    /**
     * This method rewrites all pairs to their new index,
     * because arrays size was changed.
     *
     * @param oldArray array before resizing.
     */
    private void transfer(MyEntry<K, V>[] oldArray) {
        MyArrayList<MyEntry<K, V>> array = hashTableToArray(oldArray);
        for (int i = 0; i < array.size(); i++) {
            array.get(i).next = null;
            K key = array.get(i).getKey();
            V value = array.get(i).getValue();

            MyEntry<K, V> newE = array.get(i);
            int index = hash(key) % table.length;

            if (table[index] == null) {
                table[index] = newE;
            } else {
                MyEntry<K, V> oldE = table[index];
                //check if key already exists but the value is new.
                if (oldE.getKey().equals(key) && !oldE.getValue().equals(value)) {
                    table[index].setValue(value);
                } else if (newE.getHash() == oldE.getHash() && //check collision.
                        !newE.getKey().equals(oldE.getKey()) &&
                        !newE.getValue().equals(oldE.getValue())) {

                    addEntryAtLast(oldE, newE);
                } else { //check if index is the same.
                    addEntryAtLast(oldE, newE);
                }
            }
        }
    }

    /**
     * This method converts an array of linked lists into an arrayList for easy iteration over it.
     *
     * @param oldArray array of linked lists.
     * @return arrayList of pairs key - value
     */
    private MyArrayList<MyEntry<K, V>> hashTableToArray(MyEntry<K, V>[] oldArray) {
        MyArrayList<MyEntry<K, V>> array = new MyArrayList<>();
        for (MyEntry<K, V> entry : oldArray) {
            if (entry != null && entry.next == null) {
                array.add(entry);
            }
            if (entry != null && entry.next != null) {
                for (MyEntry<K, V> e = entry; e != null; e = e.next) {
                    array.add(e);
                }
            }
        }
        return array;
    }

    /**
     * This method adds a pair to the end of the linked list.
     *
     * @param oldE previous pair.
     * @param newE next pair.
     */
    private void addEntryAtLast(MyEntry<K, V> oldE, MyEntry<K, V> newE) {
        while (true) {
            if (oldE.next == null) {
                oldE.next = newE;
                break;
            } else {
                oldE = oldE.next;
            }
        }
    }

    /**
     * This method return map size.
     *
     * @return size.
     */
    public int size() {
        return size;
    }

    /**
     * This method return hashCode of parameter key.
     *
     * @param key input parameter.
     * @return hashCode of key.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode());
    }

    /**
     * This method says if map contains this key.
     *
     * @param key searching key.
     * @return boolean value.
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * This method returns a list of keys.
     *
     * @return arrayList of keys.
     */
    public MyArrayList<K> keySet() {
        MyArrayList<K> keys = new MyArrayList<>();
        MyArrayList<MyEntry<K, V>> buffer = hashTableToArray(table);
        for (int i = 0; i < buffer.size(); i++) {
            keys.add(buffer.get(i).getKey());
        }
        return keys;
    }

    /**
     * This method return a list of MyEntry (pairs: key - value).
     *
     * @return arrayList of pairs.
     */
    public MyArrayList<MyEntry<K, V>> entrySet() {
        return hashTableToArray(table);
    }

    /**
     * This method converts a map to a string in a special form.
     *
     * @return string map form.
     */
    public String toString() {
        MyArrayList<MyEntry<K, V>> array = hashTableToArray(table);
        Iterator<MyEntry<K, V>> i = array.iterator();

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (; ; ) {
            MyEntry<K, V> e = i.next();
            sb.append(e.toString());
            if (!i.hasNext())
                return sb.append("}").toString();
            sb.append(",").append(" ");
        }
    }
}
