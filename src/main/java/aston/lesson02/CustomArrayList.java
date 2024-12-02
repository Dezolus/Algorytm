package aston.lesson02;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomArrayList<T extends Comparable<T>> {

    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public CustomArrayList(int capacity) {
        elements = new Object[capacity];
    }

    public CustomArrayList(T[] elements) {
        this.elements = new Object[elements.length];
    }


    public void add(T t) {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements, size * 2);
        }
        elements[size++] = t;
    }

    public void addAll(ArrayList<T> list) {
        for (T e : list) {
            add(e);
        }
    }

    public void clear() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public T get(int index) {
        return (T) elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void remove(int index) {
        elements[index] = null;
    }

    public void remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(t)) {
                elements[i] = null;
            }
        }
    }

    public void sort() {
        quickSort(0, size-1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }

    private int partition(int low, int high) {
        T pivot = (T) elements[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (((T) elements[j]).compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }
}
