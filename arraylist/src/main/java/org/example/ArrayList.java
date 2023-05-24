package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayList is a resizable array implementation in java. ArrayList grows dynamically and ensures that
 * there is always a space to add elements.  This array stores in itself elements of type T.
 * It has the following methods:
 * add element;
 * add element by index;
 * get element by index;
 * set element by index;
 * remove element by index;
 * clear the whole array;
 * and check if array is empty;
 * quick sort method that sorts in O(n*logn) time.
 * Also, this implementation of ArrayList has an elements iterator that allows to iterate through the ArrayList.
 */

public class ArrayList<T> {

    private Object[] array;

    private final int DEFAULT_CAPACITY = 10;

    private final int MULTIPLIER = 2;

    private int size = 0;

    /**
     * Setting up empty arraylist
     */
    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Setting up arraylist with defined capacity
     * @param capacity
     */
    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    public final int size() {
        return this.size;
    }

    /**
     * @param element the object to be added as a last element of array
     * if array is already full, we create a new array according to the following formula:
     * newArray.length = oldArray.length * MULTIPLIER
     * @return true after successful addition
     */
    public boolean add(T element) {

        if (array.length == size) {
            final Object[] oldArray = array;
            array = new Object[oldArray.length * MULTIPLIER];
            System.arraycopy(oldArray, 0, array, 0, oldArray.length);
        }

        array[size++] = element;
        return true;
    }

    /**
     * @param index where the new element to be added
     * @param element is an object that will be added to the index position
     * @throws IndexOutOfBoundsException if index's size more than size of arraylist
     * or index is negative
     */
    public void add(int index, T element) {

        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (size == 0 || index == size) {
            add(element);
        } else if (array.length == size) {
            final Object[] oldArray = array;
            array = new Object[oldArray.length * MULTIPLIER];

            System.arraycopy(oldArray, 0, array, 0, index);
            System.arraycopy(oldArray, index, array, index + 1, size - index);

            set(index, element);
            size++;
        } else {
            final Object[] oldArray = array;

            System.arraycopy(oldArray, 0, array, 0, index);
            System.arraycopy(oldArray, index, array, index + 1, size - index);
            set(index, element);
            size++;
        }
    }

    /**
     * @param index is a position of the element in the array to be returned
     * @return the object from array by pointed index
     * @throws IndexOutOfBoundsException if index's size more than size of arraylist
     * or index is negative
     */
    public T get(final int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return (T) array[index];
    }

    /**
     * @param index is a position in the array where will be written object
     * @param element is the object that will be written in array instead of the old one
     * @return new added object
     * @throws IndexOutOfBoundsException if index's size more than size of arraylist
     * or index is negative
     */
    public T set(int index, T element) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        array[index] = element;
        return (T) element;
    }

    /**
     * @param index is a position in the array where object will be removed from
     * @return object to be removed from the array
     * @throws IndexOutOfBoundsException if index's size more than size of arraylist
     * or index is negative
     */
    public T remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        final Object element = array[index];
        if (index != this.size() - 1) {
            System.arraycopy(array, index + 1, array, index, size - index - 1);
        }

        size--;
        return (T) element;
    }

    /**
     * method deletes all the elements of the array
     * and makes the size of arraylist equal to 0
     */
    public void clear() {
        array = new Object[1];
        size = 0;
    }

    /**
     * @return true if arraylist's size equals 0
     * or false if array is non-empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @param array is the array to be sorted by the quick sort method
     * @param first is the first index of the array to be sorted
     * @param last is the last index of the array to be sorted
     * @param <T> the object that should implement interface Comparable
     */
    public static <T extends Comparable> void quickSort(ArrayList<T> array, int first, int last) {

        if (first < last) {

            int pivot = partition(array, first, last);
            quickSort(array, first, pivot - 1);
            quickSort(array, pivot + 1, last);
        }

    }

    /**
     * @param array is the array to be separated by pivot element
     * @param first is the first index of the array to be separated
     * @param last is the last index of the array to be separated
     * @param <T> the object that should implement interface Comparable
     * variables:
     * pivot
     * wall (the least index)
     * current (the most left element after the wall).
     * If current element is smaller than the pivot, we increment the wall and do a swap
     * In the end we put the pivot element to the place of wall and
     * @return the index pivot element
     */
    private static <T extends Comparable>  int partition(ArrayList<T> array, int first, int last) {

        T pivot = array.get(last);
        int wall = (first - 1);

        for (int current = first; current < last; current++) {

            if (array.get(current).compareTo(pivot) < 0) {

                wall++;
                swap(array, wall, current);
            }
        }

        swap(array, wall + 1, last);
        return (wall + 1);
    }

    private static <T> void swap(ArrayList<T> array, int ind1, int ind2) {
        T tmp = array.get(ind1);
        array.set(ind1, array.get(ind2));
        array.set(ind2, tmp);
    }

    /**
     * @return instance of the Iterator
     */
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }


    private class ElementsIterator implements Iterator<T> {

        private static final int LAST_IS_NOT_SET = -1;
        private int index;
        private int lastIndex = -1;

        ElementsIterator() {
            this(0);
        }

        ElementsIterator(final int index) {
            this.index = index;
        }

        @Override
        public boolean hasNext() {
            return ArrayList.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastIndex = index++;
            return ArrayList.this.get(lastIndex);
        }


        @Override
        public void remove() {
            if (lastIndex == LAST_IS_NOT_SET) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(lastIndex);
            index--;
            lastIndex = LAST_IS_NOT_SET;
        }

    }
}