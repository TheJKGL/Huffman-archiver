package com.shpp.p2p.cs.emalahov.assignment17.test;

import com.shpp.p2p.cs.emalahov.assignment17.MyQueue;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyQueueTest {

    /**
     * This method tests the correct operation of the remove() method
     * and how the queue size changes.
     */
    @Test
    public void simpleTest1_check_add_remove_size() {
        System.out.println("simpleTest1");
        System.out.println("Queue:");
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        System.out.println(queue.size());
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.remove());
        }
        System.out.println("size:" + queue.size());

        System.out.println("MyQueue:");
        MyQueue<Integer> myQueue = new MyQueue<>();
        for (int i = 0; i < 5; i++) {
            myQueue.add(i);
        }
        System.out.println(myQueue.size());
        System.out.println(myQueue);
        for (int i = 0; i < 5; i++) {
            System.out.println(myQueue.remove());
        }
        System.out.println("size:" + myQueue.size());

        Assert.assertEquals(queue.toString(), myQueue.toString());
        Assert.assertEquals(queue.size(), myQueue.size());
        System.out.println();
    }

    /**
     * This method tests the queue for the work in a loop
     * and the correctness of the output of the elements
     */
    @Test
    public void simpleTest2_check_poll_add_size() {
        System.out.println("simpleTest2");
        System.out.println("Queue:");
        Queue<String> queue = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            queue.add(i + "");
        }
        System.out.println(queue);
        while (queue.size() != 0) {
            System.out.println(queue.poll());
        }

        System.out.println("MyQueue:");
        MyQueue<String> myQueue = new MyQueue<>();
        for (int i = 0; i < 10; i++) {
            myQueue.add(i + "");
        }
        System.out.println(myQueue);
        while (myQueue.size() != 0) {
            System.out.println(myQueue.poll());
        }

        Assert.assertEquals(queue.toString(), myQueue.toString());
        System.out.println();
    }

    /**
     * This method tests the queue for the work in a loop.
     */
    @Test
    public void simpleTest3_check_peek_poll() {
        System.out.println("simpleTest3");
        System.out.println("Queue:");
        Queue<String> queue = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            queue.add(i + "");
        }
        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }

        System.out.println("MyQueue:");
        MyQueue<String> myQueue = new MyQueue<>();
        for (int i = 0; i < 5; i++) {
            myQueue.add(i + "");
        }
        while (myQueue.peek() != null) {
            System.out.println(myQueue.poll());
        }

        Assert.assertEquals(queue.toString(), myQueue.toString());
        System.out.println();
    }

    /**
     * This test checks if the queue outputs the first item correctly.
     */
    @Test
    public void simpleTest4_check_remove() {
        System.out.println("simpleTest4");
        System.out.println("Queue:");
        Queue<Double> queue = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            queue.add(1.0);
        }
        System.out.println(queue.remove());

        System.out.println("MyQueue:");
        MyQueue<Double> myQueue = new MyQueue<>();
        for (int i = 0; i < 5; i++) {
            myQueue.add(1.0);
        }
        System.out.println(myQueue.remove());

        Assert.assertEquals(queue.toString(), myQueue.toString());
        System.out.println();
    }

    /**
     * This method tests the queue on a large number of elements.
     */
    @Test
    public void bigSizeTest1_check_element_size() {
        System.out.println("bigSizeTest1");
        System.out.println("Queue:");
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < 100000; i++) {
            queue.add(i);
        }
        System.out.println(queue.size());
        System.out.println(queue.element());

        System.out.println("MyQueue:");
        MyQueue<Integer> myQueue = new MyQueue<>();
        for (int i = 0; i < 100000; i++) {
            myQueue.add(i);
        }
        System.out.println(myQueue.size());
        System.out.println(myQueue.element());

        Assert.assertEquals(queue.toString(), myQueue.toString());
        System.out.println();
    }

    /**
     * This test check method for throwing the exceptions.
     */
    @Test
    public void simpleTest5_check_exception() {
        System.out.println("simpleTest5_check_exception");
        MyQueue<Integer> myQueue = new MyQueue<>();
        boolean first = false;
        boolean second = false;
        boolean third = false;

        try {
            myQueue.element();
        } catch (NoSuchElementException e) {
            System.out.println("Exception NoSuchElementException caught in element() method.");
            first = true;
        }

        if (myQueue.peek() == null) {
            System.out.println("Method peek returned null.");
            second = true;
        }
        if (myQueue.poll() == null) {
            System.out.println("Method poll returned null.");
            third = true;
        }

        if (first && second && third) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
        System.out.println();
    }
}
