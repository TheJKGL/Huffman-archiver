package com.shpp.p2p.cs.emalahov.assignment17.test;

import com.shpp.p2p.cs.emalahov.assignment17.MyLinkedList;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyLinkedTest {

    /**
     * This test checks the addition of a large number of elements to the list,
     * how the getFirst(), getLast(), size() methods work.
     */
    @Test
    public void bigSizeTest1_check_getFirst_getLast_size() {
        System.out.println("bigSizeTest1:");
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 100000; i++) {
            list.addLast(i);
        }

        for (int i = 0; i < 100000; i++) {
            myLinkedList.addLast(i);
        }

        System.out.println("first: " + myLinkedList.getFirst());
        System.out.println("last: " + myLinkedList.getLast());
        System.out.println("size: " + myLinkedList.size());

        Assert.assertEquals(list.getFirst(), myLinkedList.getFirst());
        Assert.assertEquals(list.getLast(), myLinkedList.getLast());
        Assert.assertEquals(list.size(), myLinkedList.size());

        Assert.assertEquals(list.toString(), myLinkedList.toString());
        System.out.println();
    }

    /**
     * This test checks the addition of a large number of elements to the end of the list,
     * how the indexOf(), getLast() methods work.
     */
    @Test
    public void bigSizeTest2_check_indexOf_getLast() {
        System.out.println("bigSizeTest2:");
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.addLast(i);
        }
        list.add(100000, 312312312);
        System.out.println("LinkedList:");
        System.out.println(list.getLast());
        System.out.println("index of 312312312: " + list.indexOf(312312312));

        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 100000; i++) {
            myLinkedList.addLast(i);
        }
        myLinkedList.add(100000, 312312312);
        System.out.println("MyLinkedList:");
        System.out.println(myLinkedList.getLast());
        System.out.println("index of 312312312: " + myLinkedList.indexOf(312312312));

        Assert.assertEquals(list.indexOf(312312312), myLinkedList.indexOf(312312312));
        Assert.assertEquals(list.getLast(), myLinkedList.getLast());
        System.out.println();
    }

    /**
     * This test checks the addition of the element to the end of the list, to the middle,
     * to the start positions, removing from start, end of the list and getting.
     */
    @Test
    public void simpleTest1_check_removeFirst_removeLast_clear() {
        System.out.println("simpleTest1:");
        System.out.println("LinkedList:");
        MyLinkedList<String> array = new MyLinkedList<>();
        LinkedList<String> list = new LinkedList<>();
        list.addLast("1");
        list.addLast("3");
        list.add(2, "2");
        list.addFirst("0");
        list.addLast("4");
        list.add(3, "7");
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        list.addFirst("f");
        //list.clear();
        list.removeFirst();
        list.removeLast();
        System.out.println(list.getFirst());
        System.out.println(list);

        System.out.println("MyLinkedList:");
        array.addLast("1");
        array.addLast("3");
        array.add(2, "2");
        array.addFirst("0");
        array.addLast("4");
        array.add(3, "7");
        System.out.println(array);
        System.out.println(array.getFirst());
        System.out.println(array.getLast());
        array.addFirst("f");
        //array.clear();
        array.removeFirst();
        array.removeLast();
        System.out.println(array.getFirst());
        System.out.println(array);

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks of a null object is added to the beginning and end of the list.
     */
    @Test
    public void simpleTest2_check_addFirst_addLast_indexOf_contains() {
        System.out.println("simpleTest2:");
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(null);
        list.addLast(1);
        list.addLast(null);

        System.out.println("LinkedList:");
        System.out.println(list);
        System.out.println(list.indexOf(null));
        System.out.println(list.contains(null));

        MyLinkedList<Integer> array = new MyLinkedList<>();
        array.addFirst(null);
        array.addLast(1);
        array.addLast(null);

        System.out.println("MyLinkedList:");
        System.out.println(array);
        System.out.println(array.indexOf(null));
        System.out.println(array.contains(null));

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks for deletion from the beginning and end of a list of elements.
     */
    @Test
    public void simpleTest3_check_addLast_removeByIndex() {
        System.out.println("simpleTest3:");
        LinkedList<Double> list = new LinkedList<>();
        list.addLast(1.0);
        list.addLast(2.0);
        list.addLast(3.0);
        list.addLast(4.0);
        list.remove(0);
        list.remove(2);

        System.out.println("LinkedList:");
        System.out.println(list);

        MyLinkedList<Double> array = new MyLinkedList<>();
        array.addLast(1.0);
        array.addLast(2.0);
        array.addLast(3.0);
        array.addLast(4.0);
        array.remove(0);
        array.remove(2);

        System.out.println("MyLinkedList:");
        System.out.println(array);

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks if an item is removed from the middle of the list.
     */
    @Test
    public void simpleTest4_check_addLast_remove() {
        System.out.println("simpleTest4:");
        LinkedList<Double> list = new LinkedList<>();
        list.addLast(1.0);
        list.addLast(2.0);
        list.addLast(3.0);
        list.addLast(4.0);
        list.remove(2);

        System.out.println("LinkedList:");
        System.out.println(list);

        MyLinkedList<Double> array = new MyLinkedList<>();
        array.addLast(1.0);
        array.addLast(2.0);
        array.addLast(3.0);
        array.addLast(4.0);
        array.remove(2);

        System.out.println("MyLinkedList:");
        System.out.println(array);

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This method checks for adding to the middle of the list, methods indexOf(),
     * and removing from the middle.
     */
    @Test
    public void simpleTest5_check_addLast_indexOf_addByIndex() {
        System.out.println("simpleTest5:");
        LinkedList<String> list = new LinkedList<>();
        list.addLast("H");
        list.addLast("e");
        list.addLast("l");
        list.addLast("l");
        list.addLast("o");
        list.addLast(" ");
        list.addLast("w");
        list.addLast("o");
        list.addLast("r");
        list.addLast("l");
        list.addLast("d");

        System.out.println("LinkedList:");
        System.out.println(list);
        System.out.println(list.indexOf(" "));
        list.remove(5);
        System.out.println(list.indexOf("w"));
        list.add(5, "space");
        System.out.println(list);
        System.out.println(list.indexOf("H"));

        MyLinkedList<String> array = new MyLinkedList<>();
        array.addLast("H");
        array.addLast("e");
        array.addLast("l");
        array.addLast("l");
        array.addLast("o");
        array.addLast(" ");
        array.addLast("w");
        array.addLast("o");
        array.addLast("r");
        array.addLast("l");
        array.addLast("d");

        System.out.println("MyLinkedList:");
        System.out.println(array);
        System.out.println(array.indexOf(" "));
        array.remove(5);
        System.out.println(array.indexOf("w"));
        array.add(5, "space");
        System.out.println(array);
        System.out.println(array.indexOf("H"));

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks how element replacement works,
     * how methods work removeFirst(),removeLast().
     */
    @Test
    public void simpleTest6_check_addByIndex_removeByIndex() {
        System.out.println("simpleTest6:");
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.addFirst(5);

        System.out.println("LinkedList:");
        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        list.remove(2);
        list.add(2, 6);
        list.remove(4);
        list.add(4, 10);
        System.out.println(list);
        System.out.println("removeFirst:" + list.removeFirst());
        System.out.println("removeLast:" + list.removeLast());
        System.out.println(list);

        MyLinkedList<Integer> array = new MyLinkedList<>();
        array.addFirst(1);
        array.addFirst(2);
        array.addFirst(3);
        array.addFirst(4);
        array.addFirst(5);

        System.out.println("MyLinkedList:");
        System.out.println(array);
        System.out.println(array.getFirst());
        System.out.println(array.getLast());
        array.remove(2);
        array.add(2, 6);
        array.remove(4);
        array.add(4, 10);
        System.out.println(array);
        System.out.println("removeFirst:" + array.removeFirst());
        System.out.println("removeLast:" + array.removeLast());
        System.out.println(list);

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test check method for throwing the exceptions.
     */
    @Test
    public void simpleTest7_check_exception() {
        System.out.println("simpleTest7_check_exception");
        MyLinkedList<Integer> array = new MyLinkedList<>();
        boolean first = false;
        boolean second = false;
        boolean third = false;
        boolean fourth = false;
        boolean fifth = false;

        try {
            array.add(1, 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception IndexOutOfBound caught in add() method.");
            first = true;
        }
        try {
            array.get(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception IndexOutOfBound caught in get() method.");
            second = true;
        }
        try {
            array.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception IndexOutOfBound caught in remove() method.");
            third = true;
        }
        try {
            array.getFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Exception NoSuchElementException caught in getFirst() method.");
            fourth = true;
        }
        try {
            array.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Exception NoSuchElementException caught in removeFirst() method.");
            fifth = true;
        }

        if (first && second && third && fourth && fifth) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
        System.out.println();
    }
}
