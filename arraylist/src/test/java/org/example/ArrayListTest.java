package org.example;

import org.example.ArrayList;

import java.util.Iterator;
import java.util.ListIterator;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {

    ArrayList<Integer> listOfInts = new ArrayList<Integer>();
    ArrayList<String> listOfStrings = new ArrayList<String>();

    @Test
    public void addTest() {
        listOfInts.add(1);
        listOfInts.add(2);
        listOfInts.add(3);
        listOfInts.add(4);
        listOfInts.add(5);
        listOfInts.add(6);
        listOfInts.add(7);
        listOfInts.add(8);
        listOfInts.add(9);
        listOfInts.add(10);

        assertThat(listOfInts.size()).isEqualTo(10);
        assertThat(listOfInts.isEmpty()).isFalse();
    }

    @Test
    public void addByIndexTest() {
        listOfStrings.add("1");
        listOfStrings.add("2");
        listOfStrings.add("3");

        listOfStrings.add(0, "0");

        assertThat(listOfStrings.size()).isEqualTo(4);
        assertThat(listOfStrings.get(0)).isEqualTo("0");
        assertThat(listOfStrings.get(3)).isEqualTo("3");
    }

    @Test
    public void getTest() {

        listOfInts.add(0);
        listOfInts.add(1);
        listOfInts.add(2);

        assertThat(listOfInts.get(0)).isEqualTo(0);
        assertThat(listOfInts.get(2)).isEqualTo(2);
        assertThrows(IndexOutOfBoundsException.class, () -> listOfInts.get(3));
    }

    @Test
    public void setTest() {

        listOfStrings.add("0");
        listOfStrings.add("1");
        listOfStrings.add("2");
        listOfStrings.add("3");

        listOfStrings.set(0, "777");

        assertThat(listOfStrings.get(0)).isEqualTo("777");
    }

    @Test
    public void removeTest() {
        listOfStrings.add("0");
        listOfStrings.add("1");
        listOfStrings.add("2");

        assertThat(listOfStrings.size()).isEqualTo(3);
        assertThat(listOfStrings.remove(0)).isEqualTo("0");
        assertThat(listOfStrings.size()).isEqualTo(2);
        assertThat(listOfStrings.remove(listOfStrings.size() - 1)).isEqualTo("2");
    }

    @Test
    public void cleanTest() {

        listOfInts.add(0);
        listOfInts.add(1);
        listOfInts.add(2);

        assertThat(listOfInts.size()).isEqualTo(3);

        listOfInts.clear();

        assertThat(listOfInts.size()).isEqualTo(0);
        assertThat(listOfInts.isEmpty()).isTrue();

    }

    @Test
    public void quickSortTest() {

        listOfInts.add(7);
        listOfInts.add(1);
        listOfInts.add(2);
        listOfInts.add(6);
        listOfInts.add(3);
        listOfInts.add(4);
        listOfInts.add(5);
        listOfInts.add(0);

        ArrayList.quickSort(listOfInts, 0, listOfInts.size() - 1);

        assertThat(listOfInts.get(0)).isEqualTo(0);
        assertThat(listOfInts.get(5)).isEqualTo(5);
        assertThat(listOfInts.get(listOfInts.size() - 1)).isEqualTo(7);

    }

    @Test
    public void iteratorTest() {

        listOfStrings.add("0");
        listOfStrings.add("1");
        listOfStrings.add("2");
        listOfStrings.add("3");
        listOfStrings.add("4");
        listOfStrings.add("5");

        Iterator<String> iterator = listOfStrings.iterator();

        assertThat(listOfStrings.size()).isEqualTo(6);

        assertThat(iterator.next()).isEqualTo("0");
        assertThat(iterator.next()).isEqualTo("1");
        iterator.remove();
        assertThat(listOfStrings.size()).isEqualTo(5);
        assertThat(iterator.next()).isEqualTo("2");
    }

}
