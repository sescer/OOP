package ru.nsu.fit.oop.boryapatrushev.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements stack.
 *
 * @param <T> it's some data type
 */
public class Stack<T> implements Iterable {

    private final int maxSize;
    private int top = -1;
    private final T[] stackArray;

    public Iterator iterator() { return new StackIterator();}

    @SuppressWarnings("unchecked")
    public Stack(int size) {
        this.maxSize = size;
        this.stackArray = (T[]) new Object[size];
    }

    /**
     * Push element on top of stack
     * @param elem for pushing
     */
    public void push (T elem) {
        if(!isFull()) {
            stackArray[++top] = elem;
        }
        else {
            throw new StackSizeLimitException("Stack is full!");
        }
    }

    /**
     * Pop element from top of stack
     * @return if stack is not empty- returns element, otherwise StackSizeLimitException
     */
    public T pop() {
        if(!isEmpty()) {
            T result = this.stackArray[top];
            this.stackArray[top] = null;
            this.top--;
            return result;
        }
        else {
            throw new StackSizeLimitException("Stack is empty");
        }
    }

    /**
     * Check if stack is empty
     * @return returns true if stack is empty, otherwise false
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * Check if stack size equals to number of elements
     * @return returns true if stack is full, otherwise false
     */
    public boolean isFull() {
        return (top == maxSize - 1);
    }

    /**
     * Returns current number of elements in stack
     * @return integer value of number of current elements in stack
     */
    public int count() {
        return top + 1;
    }


    private class StackIterator implements Iterator {

        public boolean hasNext() { return top != -1; }

        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            return stackArray[top--];
        }
    }
}

class StackSizeLimitException
        extends RuntimeException {
    StackSizeLimitException(String errorMessage) {
        super(errorMessage);
    }
}