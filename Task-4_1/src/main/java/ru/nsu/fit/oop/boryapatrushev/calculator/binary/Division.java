package ru.nsu.fit.oop.boryapatrushev.calculator.binary;
import ru.nsu.fit.oop.boryapatrushev.calculator.Binary;

public class Division extends Binary {

    public static final String symbol = "/";

    @Override
    public double execute() {

        if(getArgument(1) == 0)
            throw new ArithmeticException("Division by zero!");

        return getArgument(0) / getArgument(1);
    }
}