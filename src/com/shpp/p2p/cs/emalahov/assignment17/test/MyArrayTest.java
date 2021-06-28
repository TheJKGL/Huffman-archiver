package com.shpp.p2p.cs.emalahov.assignment17.test;

import com.shpp.p2p.cs.emalahov.assignment17.MyArrayList;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MyArrayTest {

    /**
     * This test checks how a list works with a large number of elements,
     * adding them to the middle of the list using a loop.
     */
    @Test
    public void bigSizeTest1_check_add_get() {
        System.out.println("bigSizeTest1");
        System.out.println("ArrayList:");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println("Element [99]: " + list.get(99));
        for (int i = 0; i < 30; i++) {
            list.add(30, 30);
        }

        System.out.println("MyArrayList:");
        MyArrayList<Integer> array = new MyArrayList<>();
        for (int i = 0; i < 100; i++) {
            array.add(i);
        }
        System.out.println("Element [99]: " + array.get(99));
        for (int i = 0; i < 30; i++) {
            array.add(30, 30);
        }
        Assert.assertEquals(list.toString(), array.toString());
        Assert.assertEquals(list.size(), array.size());
        System.out.println();
    }

    /**
     * This test checks how, with a large number of elements,
     * an element is inserted at the end, the correctness of the indexOf() method.
     */
    @Test
    public void bigSizeTest2_check_add_indexOf() {
        System.out.println("bigSizeTest2");
        System.out.println("ArrayList:");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        list.add(100000, 312312312);
        System.out.println("312312312: " + list.indexOf(312312312));
        list.add(0, 212212212);

        System.out.println("MyLinkedList:");
        MyArrayList<Integer> array = new MyArrayList<>();
        for (int i = 0; i < 100000; i++) {
            array.add(i);
        }
        array.add(100000, 312312312);
        System.out.println("312312312: " + array.indexOf(312312312));
        array.add(0, 212212212);
        Assert.assertEquals(list.indexOf(212212212), array.indexOf(212212212));
        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks the removal of elements from
     * the beginning, end of the middle, second-to-last(предпоследний) element.
     * Also checks adding to the beginning, middle and end of the list.
     */
    @Test
    public void simpleTest1_check_removeByIndex() {
        System.out.println("simpleTest1");
        System.out.println("ArrayList:");
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(5, 6);
        list.remove(0);
        list.remove(1);
        list.add(0, 7);
        list.add(2, 8);

        list.remove(4);
        System.out.println(list);
        list.add(4, 5);
        System.out.println(list);

        System.out.println("MyArrayList:");
        MyArrayList<Integer> array = new MyArrayList<>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(5, 6);
        array.remove(0);
        array.remove(1);
        array.add(0, 7);
        array.add(2, 8);

        array.remove(4);
        System.out.println(array);
        array.add(4, 5);
        System.out.println(array);
        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks additions and deletions through a loop.
     */
    @Test
    public void simpleTest2_check_removeInTheLoop_size() {
        System.out.println("simpleTest2");
        System.out.println("ArrayList:");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        System.out.println(list.size());
        for (int i = 0; i < 25; i++) {
            list.remove(i);
        }

        System.out.println(list);

        System.out.println("MyArrayList:");
        MyArrayList<Integer> array = new MyArrayList<>();
        for (int i = 0; i < 50; i++) {
            array.add(i);
        }
        System.out.println(array.size());
        for (int i = 0; i < 25; i++) {
            array.remove(i);
        }

        System.out.println(array);
        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks for adding through a loop to the middle of the list,
     * and methods such as size(),clear()
     */
    @Test
    public void simpleTest3_check_addInTheLoop_size_clear() {
        System.out.println("simpleTest3");
        System.out.println("ArrayList:");
        ArrayList<String> list = new ArrayList<>();
        list.add("start");
        list.add("end");
        for (int i = 0; i < 10; i++) {
            list.add(1, "" + i);
        }
        System.out.println(list.size());
        System.out.println(list);
        list.clear();
        System.out.println(list);

        System.out.println("MyArrayList:");
        MyArrayList<String> array = new MyArrayList<>();
        array.add("start");
        array.add("end");
        for (int i = 0; i < 10; i++) {
            array.add(1, "" + i);
        }
        System.out.println(array.size());
        System.out.println(array);
        array.clear();
        System.out.println(array);

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test also checks the addition to the middle
     * and the correctness of the indexOf() method.
     */
    @Test
    public void simpleTest4_check_addByIndex_indexOf() {
        System.out.println("simpleTest4");
        System.out.println("ArrayList:");
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("f");

        list.add(2, "2");
        System.out.println("index [c]: " + list.indexOf("c"));
        System.out.println(list);

        System.out.println("MyArrayList:");
        MyArrayList<String> array = new MyArrayList<>();
        array.add("a");
        array.add("b");
        array.add("c");
        array.add("d");
        array.add("f");

        array.add(2, "2");
        System.out.println("index [c]: " + array.indexOf("c"));
        System.out.println(array);

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks how the list works with null objects,
     * adding them to the beginning at the end, indexOf();
     */
    @Test
    public void simpleTest5_check_addTheNull_removeByIndex() {
        System.out.println("simpleTest5");
        System.out.println("ArrayList:");
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("f");
        list.add(0, null);
        list.add(null);

        System.out.println("index of null: " + list.indexOf(null));
        list.remove(0);
        System.out.println("index of null: " + list.indexOf(null));
        System.out.println(list);

        System.out.println("MyArrayList:");
        MyArrayList<String> array = new MyArrayList<>();
        array.add("a");
        array.add("b");
        array.add("c");
        array.add("d");
        array.add("f");
        array.add(0, null);
        array.add(null);

        System.out.println("index of null: " + array.indexOf(null));
        array.remove(0);
        System.out.println("index of null: " + array.indexOf(null));
        System.out.println(array);

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test checks the work of the contains() method and,
     * in the same way as the sheet works with the get() method in a loop.
     */
    @Test
    public void simpleTest6_check_indexing_contains() {
        System.out.println("simpleTest6");
        System.out.println("ArrayList:");
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("f");

        System.out.println("contains d?: " + list.contains("d"));
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            System.out.println(" " + list.indexOf(list.get(i)));
        }

        System.out.println("MyArrayList:");
        MyArrayList<String> array = new MyArrayList<>();
        array.add("a");
        array.add("b");
        array.add("c");
        array.add("d");
        array.add("f");

        System.out.println("contains d?: " + array.contains("d"));
        for (int i = 0; i < array.size(); i++) {
            System.out.print(array.get(i));
            System.out.println(" " + array.indexOf(array.get(i)));
        }

        Assert.assertEquals(list.toString(), array.toString());
        System.out.println();
    }

    /**
     * This test check method for throwing the exceptions.
     */
    @Test
    public void simpleTest7_check_exception() {
        System.out.println("simpleTest7_check_exception");
        MyArrayList<Integer> array = new MyArrayList<>();
        boolean first = false;
        boolean second = false;
        boolean third = false;
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
        if (first && second && third) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
        System.out.println();
    }
}
