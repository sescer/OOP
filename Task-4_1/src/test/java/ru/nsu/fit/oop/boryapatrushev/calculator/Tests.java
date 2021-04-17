package ru.nsu.fit.oop.boryapatrushev.calculator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class Tests {

    private final Parser calculator = new Parser();
    private String input = "";

    @Test
    public void test_1() throws Exception {

        input = "sin + - 1 2 1";
        double answer = calculator.calculate(input);

        Assert.assertEquals(0, answer, 0);
    }
    @Test
    public void test_2() throws Exception {

        input = "^ 2 10";
        double answer = calculator.calculate(input);
        Assert.assertEquals(1024., answer, 0);

        input = "log / 343 44";
        answer = calculator.calculate(input);
        Assert.assertEquals(Math.log(343/44.), answer, 0);

        input = "log - 151 232";
        answer = calculator.calculate(input);
        assertTrue(Double.isNaN(answer));
    }
    @Test
    public void test_3() throws Exception {

        input = "log + sqrt 1443.3434 / 343 * 44 2";
        double answer = calculator.calculate(input);
        Assert.assertEquals(Math.log(343/88.+Math.sqrt(1443.3434)), answer, 0);

        input = "cos * 2 4";
        answer = calculator.calculate(input);
        Assert.assertEquals(Math.cos(8), answer, 0);
    }
    @Test
    public void test_4() {

        input = "bruh 22 88";

        try {
            calculator.calculate(input);
        } catch (Exception e) {
            return;
        }
        Assert.fail();
    }

    @Test
    public void test_5() {

        input = "sin 2 2";

        try {
            calculator.calculate(input);
        } catch (Exception e) {
            return;
        }
        Assert.fail();
    }
}