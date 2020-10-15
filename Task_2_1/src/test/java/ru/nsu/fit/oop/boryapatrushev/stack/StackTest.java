package ru.nsu.fit.oop.boryapatrushev.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StackTest {

    @Test
    public void Test1() {
        Stack<Integer> Stack = new Stack<>(18);

        Stack.push(12335);
        Stack.push(12345);
        Stack.push(34);
        Stack.push(2);
        Stack.push(0);
        Stack.push(-12);
        Stack.push(1232);
        Stack.push(3653);
        Stack.push(155);
        Stack.push(-1);
        Stack.push(0);
        Stack.push(-135);
        Stack.push(9876);
        Stack.push(15432);
        Stack.push(23);
        Stack.push(7);
        Stack.push(-28);
        Stack.push(228);

        assertThat(Stack.pop(), is(228));
        assertThat(Stack.pop(), is(-28));
        assertThat(Stack.pop(), is(7));
        assertThat(Stack.pop(), is(23));
        assertThat(Stack.pop(), is(15432));
        assertThat(Stack.pop(), is(9876));
        assertThat(Stack.pop(), is(-135));
        assertThat(Stack.pop(), is(0));
        assertThat(Stack.pop(), is(-1));
        assertThat(Stack.pop(), is(155));
        assertThat(Stack.pop(), is(3653));
        assertThat(Stack.pop(), is(1232));
        assertThat(Stack.pop(), is(-12));
        assertThat(Stack.pop(), is(0));
        assertThat(Stack.pop(), is(2));
        assertThat(Stack.pop(), is(34));
        assertThat(Stack.pop(), is(12345));
        assertThat(Stack.pop(), is(12335));

    }

    @Test
    public void Test2() {
        Stack<Object> Stack = new Stack<>(36);

        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");
        Stack.push("Despcito");
        Stack.push("bruh");
        Stack.push("F");

        Stack.pop();
        Stack.pop();
        Stack.pop();
        Stack.pop();
        Stack.pop();
        Stack.pop();

        Assert.assertTrue(Stack.count() == 30);
    }

    @Test
    public void Test3() {
        Stack<String> Stack = new Stack<>(10);

        Stack.push("Hello,");
        Stack.push("my");
        Stack.push("name");
        Stack.push("is");
        Stack.push("Borya.");
        Stack.push("Java");
        Stack.push("is");
        Stack.push("very");
        Stack.push("good");
        Stack.push("language");


        assertThat(Stack.pop(), is("language"));
        assertThat(Stack.pop(), is("good"));
        assertThat(Stack.pop(), is("very"));
        assertThat(Stack.pop(), is("is"));
        assertThat(Stack.pop(), is("Java"));
        assertThat(Stack.pop(), is("Borya."));
        assertThat(Stack.pop(), is("is"));
        assertThat(Stack.pop(), is("name"));
        assertThat(Stack.pop(), is("my"));
        assertThat(Stack.pop(), is("Hello,"));
    }

    @Test
    public void Test4() {
        Stack<Boolean> stack = new Stack<>(4);

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
    public void Test5() {
        Stack<Object> Stack = new Stack<>(1);

        try {
            Stack.pop();
        }
        catch (StackSizeLimitException e) {
            return;
        }

        Assert.fail("\nExpected: stack is empty. In Fact: stack isn't empty");
    }

    @Test
    public void Test6() {
        Stack<Object> Stack = new Stack<>(2);
        Stack.push(1234);
        Stack.push(1234);

        try {
            Stack.push(1234);
        }
        catch (StackSizeLimitException e) {
            return;
        }

        Assert.fail("\nExpected: stack is full. In Fact: stack isn't full.");
    }

    @Test
    public void Test7() {
        Stack<Object> Stack = new Stack<>(2);
        Stack.push(1234);
        Stack.push(4321);

        Iterator StackIt = Stack.iterator();
        boolean check = StackIt.hasNext();

        Assert.assertEquals(true, check);
    }

    @Test
    public void Test8() {
        Stack<Object> Stack = new Stack<>(5);
        Stack.push("Java");
        Stack.push(1234);
        Stack.push(12.0f);
        Stack.push('Z');

        Object elem = null;

        for (Object o: Stack) {
            elem = o;
        }

        Assert.assertEquals("Java", elem);
    }

    @Test
    public void Test9() {
        Stack<Object> Stack = new Stack<>(5);
        Stack.push("Hello");
        Stack.push(123);
        Stack.push(18.0f);

        boolean full = Stack.isFull();
        boolean empty = Stack.isEmpty();
        int cnt = Stack.count();

        Assert.assertEquals(false, full);
        Assert.assertEquals(false, empty);
        Assert.assertEquals(3, cnt);
    }
}