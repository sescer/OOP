package ru.nsu.fit.oop.boryapatrushev.stack;

import org.junit.Assert;
import org.junit.Test;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class StackTest {
    @Test
    public void test1() {
        Stack<Integer> stack = new Stack<>();

        stack.push(12335);
        stack.push(12345);
        stack.push(34);
        stack.push(2);
        stack.push(0);
        stack.push(-12);
        stack.push(1232);
        stack.push(3653);
        stack.push(155);
        stack.push(-1);
        stack.push(0);
        stack.push(-135);
        stack.push(9876);
        stack.push(15432);
        stack.push(23);
        stack.push(7);
        stack.push(-28);
        stack.push(228);

        assertThat(stack.pop(), is(228));
        assertThat(stack.pop(), is(-28));
        assertThat(stack.pop(), is(7));
        assertThat(stack.pop(), is(23));
        assertThat(stack.pop(), is(15432));
        assertThat(stack.pop(), is(9876));
        assertThat(stack.pop(), is(-135));
        assertThat(stack.pop(), is(0));
        assertThat(stack.pop(), is(-1));
        assertThat(stack.pop(), is(155));
        assertThat(stack.pop(), is(3653));
        assertThat(stack.pop(), is(1232));
        assertThat(stack.pop(), is(-12));
        assertThat(stack.pop(), is(0));
        assertThat(stack.pop(), is(2));
        assertThat(stack.pop(), is(34));
        assertThat(stack.pop(), is(12345));
        assertThat(stack.pop(), is(12335));

    }

    @Test
    public void test2() {
        Stack<String> stack = new Stack<>();

        stack.push("Hello,");
        stack.push("my");
        stack.push("name");
        stack.push("is");
        stack.push("Borya.");
        stack.push("Java");
        stack.push("is");
        stack.push("very");
        stack.push("good");
        stack.push("language");


        assertThat(stack.pop(), is("language"));
        assertThat(stack.pop(), is("good"));
        assertThat(stack.pop(), is("very"));
        assertThat(stack.pop(), is("is"));
        assertThat(stack.pop(), is("Java"));
        assertThat(stack.pop(), is("Borya."));
        assertThat(stack.pop(), is("is"));
        assertThat(stack.pop(), is("name"));
        assertThat(stack.pop(), is("my"));
        assertThat(stack.pop(), is("Hello,"));
    }

    @Test
    public void test3() {
        Stack<Boolean> stack = new Stack<>();

        stack.push(false);
        stack.push(false);
        stack.push(true);
        stack.push(false);


        assertThat(stack.pop(), is(false));
        assertThat(stack.pop(), is(true));
        assertThat(stack.pop(), is(false));
        assertThat(stack.pop(), is(false));
    }

    @Test
    public void test4() {
        Stack<String> stack = new Stack<>();

        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");
        stack.push("Despcito");
        stack.push("bruh");
        stack.push("F");

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        Assert.assertTrue(stack.size() == 30);

    }

    @Test(expected = NoSuchElementException.class)
    public void test5() {
        Stack<Integer> stack = new Stack<>();

        stack.pop();
    }
}