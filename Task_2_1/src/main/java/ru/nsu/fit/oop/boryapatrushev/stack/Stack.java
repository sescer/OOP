package ru.nsu.fit.oop.boryapatrushev.stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class implements stack.
 *Also it includes push,pop & size functions.
 *
 * @param <T> it's some data type
 */

public class Stack<T> implements Iterable<T> {
    private final List<T> elements;

    public Stack() {
        elements = new ArrayList<>();
    }


    public void push(T item) {
        elements.add(item);
    }


    public T pop() {
        if (elements.isEmpty()) { 
            throw new NoSuchElementException("Stack is Empty"); 
        }

        return elements.remove(elements.size() - 1);
    }


    public int size() {
        return elements.size();
    }

    /**
     *
     * @return an iterator over the stack.
     *
     *Iterator iterates through the objects on the stack in LIFO order.
     */
    @Override
    public Iterator<T> iterator() {
        return new StackIterator<>(size() - 1);
    }

    private class StackIterator<T> implements Iterator<T> {
        int currentIndex;

        public StackIterator(int index) {
            currentIndex = index;
        }
        @Override
        public boolean hasNext() {
            return currentIndex >= 0;
        }
        @Override
        public T next() {
            if (!hasNext()) { 
                throw new NoSuchElementException("Stack underflow"); 
            }


            return (T) elements.get(currentIndex--);
        }
    }
}
