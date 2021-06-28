package com.shpp.p2p.cs.emalahov.assignment17.test;

import com.shpp.p2p.cs.emalahov.assignment17.MyStack;
import org.junit.Assert;
import org.junit.Test;

import java.util.EmptyStackException;
import java.util.Stack;

public class MyStackTest {

    /**
     * This test verifies that the stack is working correctly, pops all elements off the stack and its size.
     */
    @Test
    public void simpleTest1_check_push_pop() {
        System.out.println("simpleTest1");
        System.out.println("Stack:");
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
        System.out.println("size: " + stack.size());

        System.out.println("MyStack:");
        MyStack<Integer> myStack = new MyStack<>();
        for (int i = 0; i < 5; i++) {
            myStack.push(i);
        }
        System.out.println(myStack);
        for (int i = 0; i < 5; i++) {
            System.out.println(myStack.pop());
        }
        System.out.println("size: " + myStack.size());

        Assert.assertEquals(stack.toString(), myStack.toString());
        Assert.assertEquals(stack.size(), myStack.size());
        System.out.println();
    }

    /**
     * This test checks the work of the stack in a loop as I used it in my work.
     */
    @Test
    public void simpleTest2_check_isEmpty() {
        System.out.println("simpleTest2");
        System.out.println("Stack:");
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack);
            stack.pop();
        }

        System.out.println("MyStack:");
        MyStack<Integer> myStack = new MyStack<>();
        for (int i = 0; i < 5; i++) {
            myStack.push(i);
        }
        while (!myStack.isEmpty()) {
            System.out.println(myStack);
            myStack.pop();
        }

        Assert.assertEquals(stack.toString(), myStack.toString());
        Assert.assertEquals(stack.size(), myStack.size());
        System.out.println();
    }

    /**
     * This test checks the work of the stack with objects of type null,
     * checks the work of the peek() and pop(), push() method.
     */
    @Test
    public void simpleTest3_peek_pop() {
        System.out.println("simpleTest3");
        System.out.println("Stack:");
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(null);
        stack.push(2);
        System.out.println(stack);
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();

        System.out.println("MyStack:");
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(1);
        myStack.push(null);
        myStack.push(2);
        System.out.println(myStack);
        System.out.println(myStack.peek());
        myStack.pop();
        System.out.println(myStack.peek());
        myStack.pop();
        System.out.println(myStack.peek());
        myStack.pop();

        Assert.assertEquals(stack.toString(), myStack.toString());
        Assert.assertEquals(stack.size(), myStack.size());
        System.out.println();
    }

    /**
     * This test checks how the stack works with object deletions.
     */
    @Test
    public void simpleTest4_check_size() {
        System.out.println("simpleTest4");
        System.out.println("Stack:");
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(null);
        }
        System.out.println(stack.size());
        System.out.println(stack);
        for (int i = 0; i < 5; i++) {
            stack.pop();
        }
        System.out.println(stack.size());
        System.out.println(stack);

        System.out.println("MyStack:");
        MyStack<Integer> myStack = new MyStack<>();
        for (int i = 0; i < 5; i++) {
            myStack.push(null);
        }
        System.out.println(myStack.size());
        System.out.println(myStack);
        for (int i = 0; i < 5; i++) {
            myStack.pop();
        }
        System.out.println(myStack.size());
        System.out.println(myStack);

        Assert.assertEquals(stack.toString(), myStack.toString());
        Assert.assertEquals(stack.size(), myStack.size());
        System.out.println();
    }

    /**
     * This test will check the stack for large amounts of objects that are added to it.
     */
    @Test
    public void bigSizeTest1_check_push_pop_size() {
        System.out.println("bigSizeTest1");
        System.out.println("Stack:");
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 100000; i++) {
            stack.push(i);
        }
        System.out.println(stack.size());
        for (int i = 0; i < 20; i++) {
            stack.pop();
        }
        System.out.println(stack.size());

        System.out.println("MyStack:");
        MyStack<Integer> myStack = new MyStack<>();
        for (int i = 0; i < 100000; i++) {
            myStack.push(i);
        }
        System.out.println(myStack.size());
        for (int i = 0; i < 20; i++) {
            myStack.pop();
        }
        System.out.println(myStack.size());

        Assert.assertEquals(stack.toString(), myStack.toString());
        Assert.assertEquals(stack.size(), myStack.size());
        System.out.println();
    }

    /**
     * This test check method for throwing the exceptions.
     */
    @Test
    public void simpleTest5_check_exception() {
        System.out.println("simpleTest5_check_exception");
        MyStack<Integer> myStack = new MyStack<>();
        boolean first = false;
        boolean second = false;

        try {
            myStack.pop();
        } catch (EmptyStackException e) {
            System.out.println("Exception EmptyStackException caught in pop() method.");
            first = true;
        }
        try {
            myStack.peek();
        } catch (EmptyStackException e) {
            System.out.println("Exception EmptyStackException caught in peek() method.");
            second = true;
        }

        if (first && second) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
        System.out.println();
    }
}
