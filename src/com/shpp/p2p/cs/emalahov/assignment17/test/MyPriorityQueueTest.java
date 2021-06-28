package com.shpp.p2p.cs.emalahov.assignment17.test;

import com.shpp.p2p.cs.emalahov.assignment17.MyPriorityQueue;
import org.junit.Assert;
import org.junit.Test;

import java.util.PriorityQueue;

public class MyPriorityQueueTest {

    public static class Person implements Comparable<Person> {
        private final int id;

        public Person(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        @Override
        public int compareTo(Person o) {
            return (this.id - o.id);
        }

        @Override
        public String toString() {
            return "[id=" + this.id + "]";
        }
    }

    @Test
    public void testAddingToQueue() {
        System.out.println("testAddingToQueue:");
        System.out.println("MyPriorityQueue:");
        MyPriorityQueue<Person> myPriorityQueue = new MyPriorityQueue<>();
        myPriorityQueue.add(new Person(2));
        myPriorityQueue.add(new Person(5));
        myPriorityQueue.add(new Person(10));
        myPriorityQueue.add(new Person(-1));
        myPriorityQueue.add(new Person(0));
        System.out.println(myPriorityQueue);

        Assert.assertEquals(-1, myPriorityQueue.peek().getId());
        System.out.println();
    }

    @Test
    public void testRemovingFromQueue() {
        System.out.println("testRemovingFromQueue:");
        System.out.println("MyPriorityQueue:");
        MyPriorityQueue<Person> myPriorityQueue = new MyPriorityQueue<>();
        myPriorityQueue.add(new Person(2));
        myPriorityQueue.add(new Person(5));
        myPriorityQueue.add(new Person(10));
        myPriorityQueue.add(new Person(-1));
        myPriorityQueue.add(new Person(0));
        while (!myPriorityQueue.isEmpty()) {
            System.out.println(myPriorityQueue.remove());
        }
        System.out.println("size: " + myPriorityQueue.size());
        Assert.assertEquals(0, myPriorityQueue.size());
        System.out.println();
    }

    @Test
    public void testAddingOneElement() {
        System.out.println("testAddingOneElement:");
        System.out.println("MyPriorityQueue:");
        MyPriorityQueue<Person> myPriorityQueue = new MyPriorityQueue<>();
        myPriorityQueue.add(new Person(2));
        System.out.println("size: " + myPriorityQueue.size());
        System.out.println(myPriorityQueue.peek());
        Assert.assertEquals(2, myPriorityQueue.peek().getId());
        System.out.println();
    }

    @Test
    public void testAddingManyElement() {
        System.out.println("testAddingManyElement:");
        System.out.println("MyPriorityQueue:");
        MyPriorityQueue<Person> myPriorityQueue = new MyPriorityQueue<>();
        int bigSize = 100000;
        for (int i = 0; i < bigSize; i++) {
            myPriorityQueue.add(new Person(i));
        }
        System.out.println("size: " + myPriorityQueue.size());
        Assert.assertEquals(bigSize, myPriorityQueue.size());
        System.out.println();
    }

    @Test
    public void testException() {
        System.out.println("testException:");
        System.out.println("MyPriorityQueue:");
        MyPriorityQueue<Person> myPriorityQueue = new MyPriorityQueue<>();
        boolean result = false;
        try {
            myPriorityQueue.add(null);
        } catch (NullPointerException e) {
            result = true;
        }

        if (result) {
            System.out.println("Exception was thrown.");
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
        System.out.println();
    }

    @Test
    public void testPeekReturnsNull() {
        System.out.println("testPeekReturnsNull:");
        System.out.println("MyPriorityQueue:");
        MyPriorityQueue<Person> myPriorityQueue = new MyPriorityQueue<>();
        System.out.println(myPriorityQueue.peek());
        Assert.assertNull(myPriorityQueue.peek());
        System.out.println();
    }

    @Test
    public void testCorrectSort() {
        System.out.println("testCorrectSort:");
        System.out.println("PriorityQueue");
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Person(2));
        priorityQueue.add(new Person(5));
        priorityQueue.add(new Person(10));
        priorityQueue.add(new Person(-1));
        priorityQueue.add(new Person(0));
        System.out.println(priorityQueue);
        System.out.println();

        System.out.println("MyPriorityQueue:");
        MyPriorityQueue<Person> myPriorityQueue = new MyPriorityQueue<>();
        myPriorityQueue.add(new Person(2));
        myPriorityQueue.add(new Person(5));
        myPriorityQueue.add(new Person(10));
        myPriorityQueue.add(new Person(-1));
        myPriorityQueue.add(new Person(0));
        System.out.println(myPriorityQueue);

        while (!myPriorityQueue.isEmpty()) {
            if (myPriorityQueue.remove().getId() == priorityQueue.remove().getId()) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        }
        System.out.println();
    }
}
