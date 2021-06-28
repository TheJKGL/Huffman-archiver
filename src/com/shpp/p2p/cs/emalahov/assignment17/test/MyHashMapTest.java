package com.shpp.p2p.cs.emalahov.assignment17.test;

import com.shpp.p2p.cs.emalahov.assignment16.MyArrayList;
import com.shpp.p2p.cs.emalahov.assignment17.MyHashMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class MyHashMapTest {

    @Test
    public void testKeySet() {
        System.out.println("testKeySet:");
        System.out.println("HashMap:");
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.put("four", 4);
        hashMap.put("five", 5);
        hashMap.put("six", 6);
        hashMap.put("seven", 7);
        hashMap.put("eight", 8);
        hashMap.put("nine", 9);
        hashMap.put("ten", 10);
        hashMap.put("eleven", 11);
        hashMap.put("twelve", 12);
        hashMap.put("thirteen", 13);

        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : hashMap.keySet()) {
            arrayList.add(s);
        }
        System.out.println(arrayList);

        System.out.println("MyHashMap:");
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("one", 1);
        myHashMap.put("two", 2);
        myHashMap.put("three", 3);
        myHashMap.put("four", 4);
        myHashMap.put("five", 5);
        myHashMap.put("six", 6);
        myHashMap.put("seven", 7);
        myHashMap.put("eight", 8);
        myHashMap.put("nine", 9);
        myHashMap.put("ten", 10);
        myHashMap.put("eleven", 11);
        myHashMap.put("twelve", 12);
        myHashMap.put("thirteen", 13);

        MyArrayList<String> myArrayList = new MyArrayList<>();
        for (String s : myHashMap.keySet()) {
            myArrayList.add(s);
        }
        System.out.println(myArrayList);

        for (String s : myArrayList) {
            if (arrayList.contains(s)) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }
        System.out.println();
    }

    @Test
    public void testPuttingKeyAndValues() {
        System.out.println("testPuttingKeyAndValues:");
        System.out.println("HashMap:");
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);
        hashMap.put("four", 4);
        hashMap.put("five", 5);
        hashMap.put("six", 6);
        hashMap.put("seven", 7);
        hashMap.put("eight", 8);
        hashMap.put("nine", 9);
        hashMap.put("ten", 10);
        hashMap.put("eleven", 11);
        hashMap.put("twelve", 12);
        hashMap.put("thirteen", 13);
        System.out.println(hashMap);
        System.out.println("size: " + hashMap.size());

        System.out.println("MyHashMap:");
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("one", 1);
        myHashMap.put("two", 2);
        myHashMap.put("three", 3);
        myHashMap.put("four", 4);
        myHashMap.put("five", 5);
        myHashMap.put("six", 6);
        myHashMap.put("seven", 7);
        myHashMap.put("eight", 8);
        myHashMap.put("nine", 9);
        myHashMap.put("ten", 10);
        myHashMap.put("eleven", 11);
        myHashMap.put("twelve", 12);
        myHashMap.put("thirteen", 13);
        System.out.println(myHashMap);
        System.out.println("size: " + myHashMap.size());

        for (MyHashMap.MyEntry<String, Integer> pair : myHashMap.entrySet()) {
            if (myHashMap.containsKey(pair.getKey())) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }
        Assert.assertEquals(hashMap.size(), myHashMap.size());
        System.out.println();
    }

    @Test
    public void testGettingFromMyHashMap() {
        System.out.println("testGettingFromMyHashMap:");
        System.out.println("MyHashMap:");
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("one", 1);
        myHashMap.put("two", 2);
        myHashMap.put("three", 3);
        myHashMap.put("four", 4);
        myHashMap.put("five", 5);
        myHashMap.put("six", 6);
        myHashMap.put("seven", 7);
        myHashMap.put("eight", 8);
        myHashMap.put("nine", 9);
        myHashMap.put("ten", 10);
        myHashMap.put("eleven", 11);
        myHashMap.put("twelve", 12);
        myHashMap.put("thirteen", 13);
        System.out.println(myHashMap.size());

        for (MyHashMap.MyEntry<String, Integer> pair : myHashMap.entrySet()) {
            if (pair.getValue().equals(myHashMap.get(pair.getKey()))) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }
        System.out.println();
    }

    @Test
    public void testGetReturnNull() {
        System.out.println("testGetReturnNull:");
        System.out.println("MyHashMap:");
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        System.out.println(myHashMap.get(""));
        Assert.assertNull(myHashMap.get(""));
        System.out.println();
    }

    @Test
    public void testPuttingManyElements() {
        System.out.println("testPuttingManyElements:");
        System.out.println("MyHashMap:");
        int bigSize = 1000000;
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        for (int i = 0; i < bigSize; i++) {
            myHashMap.put(i, "Element");
        }
        System.out.println(myHashMap.size());
        Assert.assertEquals(bigSize, myHashMap.size());
        System.out.println();
    }

    @Test
    public void testPutTheSameKeyElements() {
        System.out.println("testPutTheSameKeyElements:");
        System.out.println("MyHashMap:");
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(1, "One");
        myHashMap.put(1, "Uno");
        MyArrayList<MyHashMap.MyEntry<Integer, String>> entry = myHashMap.entrySet();
        System.out.println(entry.get(0).getValue());
        Assert.assertEquals("Uno", entry.get(0).getValue());
        System.out.println();
    }
}
